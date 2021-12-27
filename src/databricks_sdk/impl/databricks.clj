(ns databricks-sdk.impl.databricks
  (:require [databricks-sdk.impl.endpoints :as endpoints]
            [org.httpkit.client :as http]
            [clojure.data.json :as json]))

(defn request [token url]
  (let [token  "bla"
        url "http://bla.databrics.cloud/api/2.0/clusters/list"
        context {}]))


(defn request2! [token timeout host endpoint context]
  (-> {:url (str host endpoint)
       :method :get
       :timeout timeout
       :accept :json
       :headers {"Authorization" (str "Bearer " token)}}
      http/request
      deref
      :body
      (json/read-str :key-fn keyword)))

(comment
  (request2! "ad" 10000 "http://dummy.restapiexample.com" "/api/v1/employees" {}))

;; (defn with-full-url [{:keys [uri host] :as request-map} context]
;;   (let [resolved-uri (apply format uri (:path-params context))]
;;     (assoc request-map :url (str host resolved-uri))))
;; (defn request!
;;   [host api-token timeout endpoint context]
;;   (let [base-request-raw (merge (base-request host api-token timeout))
;;         endpoint-request (get endpoints/request endpoint {})
;;         request-map-raw  (merge base-request-raw endpoint-request context)
;;         request-map      (with-full-url request-map-raw context)
;;         response         (or (:response-fn request-map) identity)]
;;     (-> request-map
;;         sni-client/req!
;;         :body
;;         response)))