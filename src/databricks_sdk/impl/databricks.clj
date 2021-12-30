(ns databricks-sdk.impl.databricks
  (:require
   [databricks-sdk.impl.endpoints :as endpoints]
   [org.httpkit.client :as http]))

(def response-status
  {:400 "Unable to resolve, reason: Bad request"
   :200 "Success"
   :500 "Unable to resolve, reason: Internal Error"
   :201 "Created"
   :401 "Unable to resolve, reason: Unauthorized"
   :406 "Unable to resolve, reason: Method not allowed"
   :429 "Unable to resolve, reason: Too many requests"})

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
                    (select-keys [:body :status]))
        status (get response-status (keyword (str (:status request))))]
    (if (= (:status request) (or 200 201))
      request
      {:status status})))


