(ns metabug.named-test
  (:require-macros [metabug.macro :refer [assume-named]]))

(assume-named test-numbers-equal (= 3 (+ 1 2)))

(assume-named test-predicate (string? "foo"))

(assume-named not-nil (not nil))
