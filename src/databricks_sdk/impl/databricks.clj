(ns databricks-sdk.impl.databricks
  (:require
   [databricks-sdk.impl.endpoints :as endpoints]
   [org.httpkit.client :as http]
   [clojure.data.json :as json]))

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
  (let [request (-> options
                    request-raw
                    (select-keys [:body :status :error]))
        error   (:error request)]
    (if error
      {:body (str error)}
      request)))

(comment
(json/read-str "java.net.UnknownHostException: dbc-82a233e4-f494.cloud.databricks.co: nodename nor servname provided, or not known" :key-fn keyword)

(:body {:body "java.net.UnknownHostException: dbc-82a233e4-f494.cloud.databricks.co: nodename nor servname provided, or not known"}))