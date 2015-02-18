(ns metabug.test
  (:require-macros [metabug.macro :as mb])
  (:require [metabug.named-test]
            [metabug.prefix-named-test]
            [metabug.gen-test]))

(enable-console-print!)

(defn main []
  (mb/check-assumptions metabug.named-test)
  (mb/check-assumptions metabug.prefix-named-test)
  (mb/check-assumptions metabug.gen-test))

(set! *main-cli-fn* main)
