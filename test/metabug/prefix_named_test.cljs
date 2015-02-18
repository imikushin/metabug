(ns metabug.prefix-named-test
  (:require-macros [metabug.macro :refer [assume-prefix-named]]))

(assume-prefix-named test-numbers-equal (= 3 (+ 1 2)))

(assume-prefix-named test-predicate (string? "foo"))

(assume-prefix-named not-nil (not nil))
