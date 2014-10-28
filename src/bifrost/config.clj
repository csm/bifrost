(ns
  ^:cfj-config
  bifrost.config
  (:require [confijulate.core :as cfj]))

(def ^:cfj-base base
  {
    :asgard-baseurl "localhost"
    :regions ["us-east-1"]
  })

(def config cfj/get-cfg)
(def baseurl (config :asgard-baseurl))
(def regions (config :regions))