(ns bifrost.core
  (:require [clj-http.client :as client]
            [slingshot.slingshot :refer [throw+]]
            [clojure.string :refer [join]]))

(defn applications
  "Fetch all applications."
  [baseurl]
  ((client/get (str baseurl "/application/list.json") {:as :json}) :body))

(defn application
  "Fetch details for the named application."
  [baseurl name]
  ((client/get (str baseurl "/application/show/" name ".json") {:as :json}) :body))

(defn instances
  [baseurl region id & ids]
  ((client/get (str baseurl "/" region "/instance/find.json?by=instanceId&value="
                    (join "," (cons id ids))) {:as :json}) :body))

(defn instances-in-app
  [baseurl region app]
  (apply instances
         (cons baseurl
               (cons region
                     (map #(% :instanceId) (flatten (map #(% :instances) (app :groups))))))))