(ns databricks-sdk.impl.databricks
  (:require [databricks-sdk.impl.endpoints :as endpoints]
            [selmer.parser :as parser]
            [org.httpkit.sni-client :as sni-client]))

(defn base-request [host api-token timeout]
  {:host host
   :timeout timeout
   :headers {"Authorization" (str "Bearer " api-token)}})

(defn with-full-url [{:keys [uri host] :as request-map} context]
  (let [resolved-uri (apply format uri (:path-params context))]
    (assoc request-map :url (str host resolved-uri))))
(defn request!
  [host api-token timeout endpoint context]
  (let [base-request-raw (merge (base-request host api-token timeout))
        endpoint-request (get endpoints/request endpoint {})
        request-map-raw  (merge base-request-raw endpoint-request context)
        request-map      (with-full-url request-map-raw context)
        response         (or (:response-fn request-map) identity)]
    (-> request-map
        sni-client/req!
        :body
        response)))