(ns main.youmag.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [clojure.string :as string]))

(enable-console-print!)

;; ~ State --------------------------------------------------------------------
(def app-state (atom {:title "Youmag"
                      :t1s [{:name "Actualités" :path "FR/actualites"}
                            {:name "Sport" :path "FR/sport"}
                            {:name "Tech-Médias" :path "FR/tech.medias"}
                            {:name "Culture" :path "FR/culture"}
                            {:name "Lifrestyle" :path "FR/lifestyle"}]
                      :block {:image "http://localhost:8080/img/bc.png"
                              :theme {:name "Bitcoin" :path "/FR/tech-medias/web/bitcoin"}
                              :title "Neque porro quisquamn"
                              :text "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus eleifend cursus dolor eget sagittis. Vivamus volutpat, sapien vel accumsan semper, libero libero luctus dolor, sagittis tempus urna neque ut nisi. Sed fringilla arcu ut rutrum laoreet. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Pellentesque odio enim, fringilla quis mi ac, laoreet imperdiet dolor. "}
                      :theme {:path "#"
                              :name "theme"}}))

;; ~ Header -------------------------------------------------------------------


(defn nav-element [cursor owner]
  (om/component
   (dom/li nil
           (dom/a #js{:href (:path cursor)} (:name cursor)))))


(defn header [cursor owner]
  (om/component
   (dom/header nil
   (dom/nav #js{:className "navbar navbar-default navbar-fixed-top" :role"navigation"}
            (dom/div #js{:className "container"}
                     (apply dom/ul #js{:className "nav navbar-nav"}
                            (om/build-all nav-element (:t1s cursor))))))))


;; ~ Blocks -------------------------------------------------------------------

(defn block [elem owner]
  (om/component
   (dom/div #js{:className "block"}
            (dom/div #js{:className "blockContainer"}
                     (dom/div #js{:className "images"}
                              (dom/img #js{:src (:image elem) }))
                     (dom/div #js{:className "theme"}
                              (dom/a #js{:href (get-in elem [:theme :path])}
                                     (get-in elem [:theme :name])))
                     (dom/h3 #js{:className "title"}
                             (:title elem))
                     (dom/div #js{:className "text"}
                             (:text elem))
                     ))))


(defn blocks [elems owner]
  (om/component
   (apply dom/div #js{:className "leftSide"}
            (om/build-all block elems))))


(defn right-block[item owner]
  (om/component
   (dom/li nil
           (dom/a #js{:href (:path item)}
                  (:name item)))))

(defn right-blocks [items owner]
  (om/component
   (dom/div #js{:className "block"}
            (dom/h3 #js{:className "block"} "Highlight")
            (apply dom/ul nil
                   (om/build-all right-block items)))))

;; ~ Main entry point --------------------------------------------------------

(defn app [app owner opts]
  (om/component
   (dom/div nil
            (om/build header app)
            (dom/div #js{:className "container"}
                     (dom/div #js{:className "row"}
                              (dom/div #js{:className "col-md-8"}
                                       (om/build blocks (repeat 3 (:block app))))
                              (dom/div #js{:className "col-md-4"}
                                       (om/build right-blocks (repeat 12 (:theme app)))))))))


(om/root
 app
 app-state
 {:target (. js/document (getElementById "app"))})
