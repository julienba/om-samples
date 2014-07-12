(defproject om-samples "0.1.0-SNAPSHOT"
  :description "Reuse om component"
  :url ""
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :source-paths  ["src" "src-cljs"]

  :ring {:handler om-samples.core/app}


  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2227" :scope "provided"]

                 ;; Om components
                 [org.clojure/core.async "0.1.267.0-0d7780-alpha" :scope "provided"]
                 [om "0.5.1"]
                 [net.drib/mrhyde "0.5.3"]
                 [cljs-ajax "0.2.6"]

                 ;; Routes and resources
                 [compojure "1.1.6"]
                 [ring/ring-jetty-adapter "1.1.7"]
                 [org.clojure/data.json "0.2.5"]

                 [org.clojure/tools.reader "0.8.3"]
                 ]

  :plugins [[lein-cljsbuild "1.0.4-SNAPSHOT"]
            [lein-ring "0.8.10"]]

  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                  [ring-mock "0.1.5"]]}}

  :cljsbuild {
    :builds [;; main
             {:id "youmag"
              :source-paths ["src-cljs" "main/youmag/src"]
              :compiler {
                :output-to "main/youmag/youmag.js"
                :output-dir "main/youmag/out"
                :source-map true
                :optimizations :none}}]})
