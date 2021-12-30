(ns databricks-sdk.impl.databricks
  (:require
   [databricks-sdk.impl.endpoints :as endpoints]
   [org.httpkit.client :as http]))

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
  (-> options
      request-raw
      (select-keys [:body :status])))

