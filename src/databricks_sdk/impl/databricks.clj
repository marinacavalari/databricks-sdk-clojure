(ns databricks-sdk.impl.databricks
  (:require
   [clojure.data.json :as json]
   [clojure.string :as string]
   [databricks-sdk.impl.endpoints :as endpoints]
   [org.httpkit.client :as http]))

(defn uri-parser [endpoint options]
  (or (some->> endpoint
               :query-params
               (map options)
               (apply format (:uri endpoint)))
      (:uri endpoint)))

(defn request-raw [{:keys [token timeout host endpoint body] :as options}]
  (let [endpoint  (get endpoints/request endpoint)
        uri (uri-parser endpoint options)]
    (-> {:url (str host uri)
         :method (:method endpoint)
         :timeout timeout
         :accept :json
         :content-type :json
         :headers {"Authorization" (str "Bearer " token)}
         :body (json/write-str body)}
        http/request
        deref)))

(defn request! [options]
  (let [{:keys [error status body]} (request-raw options)]
    (cond
      error
      {:error {:code 1
               :message (-> error Throwable->map :cause)}}

      (string/starts-with? (str status) "2")
      {:result (json/read-str body :key-fn keyword)}

      :else
      {:error {:code status
               :message body}})))