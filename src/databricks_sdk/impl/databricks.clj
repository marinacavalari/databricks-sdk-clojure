(ns databricks-sdk.impl.databricks
  (:require
   [clojure.data.json :as json]
   [databricks-sdk.impl.endpoints :as endpoints]
   [org.httpkit.client :as http]))

(defn request! [{:keys [token timeout host endpoint context]}]
  (-> {:url (str host (-> endpoints/request endpoint :uri))
       :method (-> endpoints/request endpoint :method)
       :timeout timeout
       :accept :json
       :headers {"Authorization" (str "Bearer " token)}
       :payload context}
      http/request
      deref
      :body
      (json/read-str :key-fn keyword)))