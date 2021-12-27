(ns databricks-sdk.impl.databricks
  (:require [databricks-sdk.impl.endpoints :as endpoints]
            [org.httpkit.client :as http]
            [clojure.data.json :as json]))
(defn request2! [token timeout host endpoint context]
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

(comment
  (request2! "ad" 10000 "http://dummy.restapiexample.com" "/api/v1/employees" {}))