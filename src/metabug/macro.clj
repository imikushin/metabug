(ns metabug.macro
  (:require [cljs.analyzer.api :as aapi]))

;; DEFINE

(defmacro assume-named [name a]
  (assert (symbol? name) (str name))
  `(def ~(vary-meta name assoc :test `(with-meta (fn []) {:assumption true}))
     (fn [] (assert ~a (str '~a " => " ~a)))))

(defmacro assume-prefix-named [name a]
  (assert (symbol? name) (str name))
  `(assume-named ~(symbol (str "prefix-" name)) ~a))

(defmacro assume [a]
  `(assume-named ~(gensym "assume") ~a))

;; RUN

(defn ->cljs-vars [namespace]
  (assert (symbol? namespace) (str namespace))
  (->> (aapi/ns-interns namespace)
    (map (fn [[k _]] `(var ~(symbol (name namespace) (name k)))))
    (into [])))

(defmacro check-assumptions [ns]
  (assert (symbol? ns) (str ns))
  `(let [all-vars# ~(->cljs-vars ns)
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
     (println)
     ))
