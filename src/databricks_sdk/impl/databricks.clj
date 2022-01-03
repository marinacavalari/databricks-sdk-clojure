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
  (let [request (-> options
                    request-raw
                    (select-keys [:body :status :error]))]
    (cond
      (:error request)
      {:error {:code 1
               :message (-> request :error Throwable->map :cause)}}
      
      (string/starts-with? (str (:status request)) "2")
      {:result [(json/read-str request :key-fn keyword)]}

      :else
      {:error {:code (:status request)
               :message (:body request)}})))
