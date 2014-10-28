(ns bifrost.core-test
  (:import (clojure.lang ExceptionInfo))
  (:require [clojure.test :refer :all]
            [bifrost.core :refer :all]
            [slingshot.slingshot :refer [try+]]))

(deftest get-applications
  (testing "Get application list"
    (try
      (applications)
      (assert true)
      (catch ExceptionInfo e
        (prn e)
        (assert false)))))

(deftest get-applications-info
  (testing "Get all application info"
    (try
      (into {} (map (fn [appname] [appname (application appname)])
                    (map #(% :name)
                         [(first (applications))])))
      (catch ExceptionInfo e
        (prn e)
        (assert false)))))

(deftest get-instance
  (testing "Get an instance info"
    (try
      (let [app (application ((first (applications)) :name))]
        (instances ((first
                      ((first (app :groups)) :instances)) :instanceId)))
      (catch ExceptionInfo e
        (prn e)
        (assert false)))))

(deftest get-instances-by-app
  (testing "Get instances by app dict"
    (try
      (let [app (application ((first (applications)) :name))]
        (instances-in-app app))
      (catch ExceptionInfo e
        (prn e)
        (assert false)))))