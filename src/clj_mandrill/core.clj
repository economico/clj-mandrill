(ns clj-mandrill.core
  (:require [clj-http.client :as client])
  (:use [cheshire.core]))

(def ^:dynamic *mandrill-api-key*
  (let [env (System/getenv)]
    (get env "MANDRILL_API_KEY" (System/getProperty "MANDRILL_API_KEY"))))

(def mandrill-url "https://mandrillapp.com/api/1.0/")


(defn method-url
  "URL for calling a method"
  [method]
  (str mandrill-url method ".json"))

(defn call-mandrill
  "Call an API method on Mandrill

  The available options are:

  :clj-http-options - extra options you would like passed through to clj-http

  Example calls:

  (call-mandrill \"users/ping\" {:id \"a1a1a1a1a1\"})

  (call-mandrill \"users/ping\" {:id \"a1a1a1a1a1\"} {:clj-http-options {:socket-timeout 1000}})"
  [method params & [opts]]
  (let [base-params  {:as :json
                      :content-type :json
                      :body (generate-string (assoc params :key *mandrill-api-key*))}
        final-params (merge base-params (:clj-http-options opts))]
    (:body (client/post (method-url method) final-params))))

(defn send-message
  "Send a message. Pass in a message map.

  See:

  https://mandrillapp.com/api/docs/messages.html#method=send"
  [message]
  (call-mandrill "messages/send" {:message message}))

(defn send-template
  "Send a message based on a template. Pass in a template name, message map and an optional template-content array.

  See:

  https://mandrillapp.com/api/docs/messages.html#method=send-template"
  ([template message]
    (send-template template message []))
  ([template message template-content]
    (call-mandrill "messages/send-template" {:template_name template :message message :template_content template-content})))

(defn user-info
  "Get information about your Mandrill account

  See:

  https://mandrillapp.com/api/docs/users.html#method=info"
  []
  (call-mandrill "users/info" {}))

(defn ping
  "Validate an API key and respond to a ping

  See:

  https://mandrillapp.com/api/docs/users.html#method=ping"
  []
  (call-mandrill "users/ping" {}))

(defn senders
  "Return the senders that have tried to use this account, both verified and unverified.

  See:

  https://mandrillapp.com/api/docs/users.html#method=senders"
  []
  (call-mandrill "users/senders" {}))

