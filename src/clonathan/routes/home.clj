(ns clonathan.routes.home
  (:require [compojure.core :refer :all]
            [clonathan.views :refer [render]]
            [clojure.java.io :refer [file]]))

(defn isDir [path]
  (.isDirectory (file path)))

(defn isFile [path]
  (.isFile (file path)))

(defn listFiles [path]
  (filter #(.isFile %1) (.listFiles (file path))))

(defn listDirs [path]
  (filter #(.isDirectory %1) (.listFiles (file path))))

(defn home [path]
  (cond
    (isDir path) (render "views/listdir.html" {:files (listFiles path) :dirs (listDirs path) :current (file path)})
    (isFile path) (render "views/listdir.html")
    :else "404"
  ))

(defroutes home-routes
  (GET ["/p/:path" :path #".*"] [path] (home path))
  (GET "/" [] (home "/media")))
