(ns clonathan.views
  (:require [clj-jtwig.core :refer [render-resource]]
            [ring.util.response :refer [content-type response]]
            [compojure.response :refer [Renderable]]))

(deftype JtwigRenderable [template-filename params]
  Renderable
  (render [this request]
    (-> (render-resource template-filename params)
        (response)
        (content-type "text/html; charset=utf-8"))))

; params is an optional map that will get passed to clj-jtwig.core/render-resource. this is will
; need to contain any variables you want to use in 'template-filename'
(defn render [template-filename & [params]]
  (JtwigRenderable. template-filename params))
