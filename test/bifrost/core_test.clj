(ns bifrost.core-test
  (:import (clojure.lang ExceptionInfo))
  (:require [clojure.test :refer :all]
            [bifrost.core :refer :all]
            [slingshot.slingshot :refer [try+]]
            [environ.core :refer [env]]))

(def baseurl (env :asgard-url))

(deftest get-applications
  (testing "Get application list"
    (try
      (applications baseurl)
      (assert true)
      (catch ExceptionInfo e
        (prn e)
        (assert false)))))

(deftest get-applications-info
  (testing "Get all application info"
    (try
      (into {} (map (fn [appname] [appname (application baseurl appname)])
                    (map #(% :name)
                         [(first (applications baseurl))])))
      (catch ExceptionInfo e
        (prn e)
        (assert false)))))

(deftest get-instance
  (testing "Get an instance info"
    (try
      (let [app (application baseurl ((first (applications baseurl)) :name))]
        (instances baseurl "us-west-2"
                   ((first
                      ((first (app :groups)) :instances)) :instanceId)))
      (catch ExceptionInfo e
        (prn e)
        (assert false)))))

(deftest get-instances-by-app
  (testing "Get instances by app dict"
    (try
      (let [app (application baseurl ((first (applications baseurl)) :name))]
        (instances-in-app baseurl "us-west-2" app))
      (catch ExceptionInfo e
        (prn e)
        (assert false)))))