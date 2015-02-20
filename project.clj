(defproject metabug "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2894"]]

  :node-dependencies [[source-map-support "0.2.8"]]

  :plugins [[lein-cljsbuild "1.0.5"]
            [lein-npm "0.5.0"]]

  :cljsbuild {
              :builds [{:source-paths   ["src" "test"]
                        :notify-command ["node" "./target/out/test.js"]
                        :compiler       {:target         :nodejs
                                         :main           metabug.test
                                         :output-to      "target/out/test.js"
                                         :output-dir     "target/out"
                                         :optimizations  :none
                                         :pretty-print   true
                                         :cache-analysis true
                                         :source-map     true}}]})
