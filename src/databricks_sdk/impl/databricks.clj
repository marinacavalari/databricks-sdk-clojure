(ns databricks-sdk.impl.databricks
  (:require
   [databricks-sdk.impl.endpoints :as endpoints]
   [org.httpkit.client :as http]
   [clojure.data.json :as json]
   [clojure.string :as string]))

(defn request-raw [{:keys [token timeout host endpoint context]}]
  (-> {:url (str host (-> endpoints/request endpoint :uri))
       :method (-> endpoints/request endpoint :method)
       :timeout timeout
       :accept :json
       :headers {"Authorization" (str "Bearer " token)}
       :payload context}
      http/request
      deref))

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
