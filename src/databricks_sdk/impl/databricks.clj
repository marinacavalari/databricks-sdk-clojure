(ns databricks-sdk.impl.databricks
  (:require [databricks-sdk.impl.endpoints :as endpoints]
            [selmer.parser :as parser]
            [org.httpkit.sni-client :as sni-client]))

(defn base-request [host api-token]
  {:host host
   :timeout (* 30 1000)
   :headers {"Authorization" (str "Bearer " api-token)}})

(defn with-full-url [{:keys [uri host] :as request-map} context]
  (let [resolved-uri (parser/render uri context)]
    (assoc request-map :url (str host resolved-uri))))

(defn connect-api!
  [host api-token endpoint context]
  (let [base-request-raw    (merge (base-request host api-token))
        endpoint-request (get endpoints/request endpoint {})
        request-map-raw  (merge base-request-raw endpoint-request context)
        request-map      (with-full-url request-map-raw context)
        response         (or (:response-fn request-map) identity)]
    (-> request-map
        sni-client/req!
        :body
        response)))

(defn create-cluster! [token host payload]
  (connect-api! host token :clusters/create {:payload payload}))

(defn list-clusters [token host]
  (connect-api! host token :clusters/list {}))