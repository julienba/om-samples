(ns om-samples.core
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]))

(defroutes app-routes
  ; to serve document root address
  (GET "/" [] "<p>Hello from compojure</p>")
  ; to serve static pages saved in resources/public directory
  (route/resources "/")
  (route/files "/main" {:root "main"})
  (route/files "/js" {:root "js"})
  ; if page is not found
  (route/not-found "Page not found"))

(def app
  (-> (handler/site app-routes)))

(do
  (use 'ring.adapter.jetty)
  (defonce server (run-jetty #'app {:port 8080 :join? false})))
