(defproject store "0.1.0-SNAPSHOT"
  :description "ClojureStore, a colege project"
  :url "http://example.com/non-existent"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [clj-http "2.0.0"]]
  :main ^:skip-aot store.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
