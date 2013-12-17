(ns clj-mandrill.interop
  (:require [clj-http.client :as client]
            [clj-mandrill.core :refer :all])
  (:use [cheshire.core])
  (:gen-class
   :methods [#^{:static true} [callMandrill [java.lang.String
                                             java.lang.String
                                             java.lang.String
                                             java.lang.String] java.lang.String]
             #^{:static true} [sendTemplate [java.lang.String
                                             java.lang.String
                                             java.lang.String
                                             java.lang.String] java.lang.String]]))

(defn -callMandrill
  [mandrill-api-key method params & [opts]]
  (generate-string (binding [*mandrill-api-key* mandrill-api-key]
                     (call-mandrill method (parse-string params true) (parse-string opts true)))))

(defn -sendTemplate
  [mandrill-api-key template message template-content]
  (generate-string (binding [*mandrill-api-key* mandrill-api-key]
                     (send-template template (parse-string message true) (parse-string template-content true)))))