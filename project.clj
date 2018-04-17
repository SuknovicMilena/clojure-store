(defproject store "0.1.0-SNAPSHOT"
  :description "ClojureStore, a college project"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [clj-http "2.0.0"]
                 [ring/ring-core "1.6.3"]
                 [ring/ring-jetty-adapter "1.6.3"],
                 [ring/ring-json "0.4.0"]
                 [ring/ring-defaults "0.3.1"]
                 [korma "0.4.3"]
                 [mysql/mysql-connector-java "5.1.6"]
                 [metosin/compojure-api "2.0.0-alpha19"]]
  :ring {:handler store.api/app}
  :plugins [[lein-ring "0.12.4"]
            [compojure "1.6.1"]]
  :target-path "target/%s"
  :profiles {:dev {:dependencies [[javax.servlet/javax.servlet-api "3.1.0"]]
                   :plugins [[lein-ring "0.12.0"]]}}
)
  ; root - h!49S^YlAawP1nnuRR2N
  ; store - 2#$o6PZGAbUvjm!T$ztP