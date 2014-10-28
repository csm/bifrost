(defproject bifrost "0.1.0-SNAPSHOT"
  :description "Rainbow bridge to Asgard (EC2 management tool from Netflix)"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  ; Set asgard-url for an Asgard instance to run against.
  :profiles {:dev {:env {:asgard-url "http://localhost"}}}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [clj-http "1.0.0"]
                 [slingshot "0.12.1"]
                 [confijulate "0.4.1"]])
