(ns metabug.test
  (:require-macros [metabug.macro :as mb]
                   [cljs.test :refer [deftest is run-tests]])
  (:require [metabug.named-test]
            [metabug.prefix-named-test]
            [metabug.gen-test]
            [cljs.test :as t]))

(deftest generated-vars-meta
  (let [vars (mb/get-vars metabug.gen-test)]
    (is (= 3 (count vars)))
    (is (every? (fn [v] (some-> v meta :test meta :assumption)) vars)) ;; FAILS because (:test (meta v)) is nil
    (is (every? (fn [v] (fn? @v)) vars))                    ;; FAILS because (deref v) is nil
    ))

(deftest named-vars-meta
  (let [vars (mb/get-vars metabug.named-test)]
    (is (= 3 (count vars)))
    (is (every? (fn [v] (some-> v meta :test meta :assumption)) vars))
    (is (every? (fn [v] (fn? @v)) vars))))

(deftest prefix-named-vars-meta
  (let [vars (mb/get-vars metabug.prefix-named-test)]
    (is (= 3 (count vars)))
    (is (every? (fn [v] (some-> v meta :test meta :assumption)) vars))
    (is (every? (fn [v] (fn? @v)) vars))))

(defn main []
  ;(mb/print-vars metabug.named-test)
  ;(mb/print-vars metabug.prefix-named-test)
  ;(mb/print-vars metabug.gen-test)

  (run-tests))

(enable-console-print!)
(set! *main-cli-fn* main)
