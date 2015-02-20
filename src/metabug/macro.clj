(ns metabug.macro
  (:require [cljs.analyzer.api :as aapi]))

;; DEFINE

(defmacro assume-named [name a]
  (assert (symbol? name) (str name))
  `(def ~(vary-meta name assoc :assumption true)
     (fn [] (assert ~a (str '~a " => " ~a)))))

(defmacro assume-prefix-named [name a]
  (assert (symbol? name) (str name))
  `(assume-named ~(symbol (str "prefix-" name)) ~a))

(defn- hashname [[s & _ :as form]]
  (symbol (str (name s) (hash (str form)))))

(defmacro assume [a]
  `(assume-named ~(hashname &form) ~a))

(defmacro show-form [& _]
  `'~&form)

;; RUN

(defn- vars [namespace]
  (assert (symbol? namespace) (str namespace))
  (->> (aapi/ns-interns namespace)
    (map (fn [[k _]] `(var ~(symbol (name namespace) (name k)))))
    (into [])))

(defmacro get-vars [ns]
  (assert (symbol? ns) (str ns))
  `(let [all-vars# ~(vars ns)
         all-vars# (sort-by (comp :line meta) all-vars#)]
     all-vars#))

(defmacro print-vars [ns]
  (assert (symbol? ns) (str ns))
  `(let [all-vars# ~(vars ns)
         all-vars# (sort-by (comp :line meta) all-vars#)
         assumptions# (filter #(some-> % meta :test meta :assumption) all-vars#)]
     (println)
     (println "ns: " '~ns)
     (println)
     (println "all-vars: " all-vars#)
     (println "assumptions: " assumptions#)
     (println "1st var: " (first all-vars#))
     (println "1st var value nil?: " (nil? @(first all-vars#)))
     (println "1st var meta: " (-> (first all-vars#) meta))
     (println "1st var :test meta (if any): " (some-> (first all-vars#) meta :test meta))
     (println)))
