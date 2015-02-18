(ns metabug.gen-test
  (:require-macros [metabug.macro :refer [assume]]))

(assume (= 3 (+ 1 2)))

(assume (string? "foo"))

(assume (not nil))
