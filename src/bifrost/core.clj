(ns bifrost.core
  (:require [clj-http.client :as client]
            [slingshot.slingshot :refer [throw+]]
            [clojure.string :refer [join]]
            [bifrost.config :as config]))

(defn applications
  "Fetch all applications."
  []
  ((client/get (str config/baseurl "/application/list.json") {:as :json}) :body))

(defn application
  "Fetch details for the named application."
  [name]
  ((client/get (str config/baseurl "/application/show/" name ".json") {:as :json}) :body))

(defn instances
  "Fetch all instances from one or more instance IDs."
  [id & ids]
  (concat
    (map #((client/get (str config/baseurl "/" % "/instance/find.json?by=instanceId&value="
                            (join "," (cons id ids))) {:as :json}) :body)
         config/regions)))

(defn instances-in-app
  "Given an application object (as returned by application) return all
  instances for that application, in the given region."
  [app]
  (apply instances
         (map #(% :instanceId) (flatten (map #(% :instances) (app :groups))))))

(defn cluster
  "Return info about a given cluster"
  [cluster]
  (concat
    (map #((client/get (str config/baseurl "/" % "/cluster/show/" cluster ".json") {:as :json}) :body)
         config/regions)))

(defn auto-scaling-groups
  [id & ids]
  (concat
    (map #((client/get (str config/baseurl "/" (first %) "/autoScaling/show/" (second %)) {:as :json}) :body)
         (for [x config/regions y (cons id ids)] [x y]))))