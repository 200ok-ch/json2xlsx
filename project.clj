(defproject json2xlsx "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/tools.cli "0.3.7"]
                 [dk.ative/docjure "1.12.0"]
                 [cheshire "5.8.0"]]
  :main ^:skip-aot json2xlsx.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :dev {:plugins [[lein-binplus "0.6.6"]]}}

  :bin {:name "json2xlsx"
        :bin-path "~/bin"
        :bootclasspath false
        :jvm-opts ["-server" "-Dfile.encoding=utf-8" "$JVM_OPTS" ]})
