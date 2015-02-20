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
    (is (every? (fn [v] (some-> v meta :assumption)) vars))
    (is (every? (fn [v] (fn? @v)) vars))))

(deftest named-vars-meta
  (let [vars (mb/get-vars metabug.named-test)]
    (is (= 3 (count vars)))
    (is (every? (fn [v] (some-> v meta :assumption)) vars))
    (is (every? (fn [v] (fn? @v)) vars))))

(deftest prefix-named-vars-meta
  (let [vars (mb/get-vars metabug.prefix-named-test)]
    (is (= 3 (count vars)))
    (is (every? (fn [v] (some-> v meta :assumption)) vars))
    (is (every? (fn [v] (fn? @v)) vars))))

(deftest show-form
  (is (= '(mb/show-form 1 2 3)
         (mb/show-form 1 2 3))))

(defn some-fun
  {:foo :bar :baz true}
  [x]
  (str x))

(deftest fn-meta
  (is (= '([x]) (-> #'some-fun meta :arglists)))
  (is (= :bar (-> #'some-fun meta :foo)))
  (is (-> #'some-fun meta :baz)))

(defn main []
  ;(mb/print-vars metabug.named-test)
  ;(mb/print-vars metabug.prefix-named-test)
  ;(mb/print-vars metabug.gen-test)

  (run-tests))

(enable-console-print!)
(set! *main-cli-fn* main)
