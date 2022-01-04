(ns databricks-sdk.impl.endpoints)

(def request
  {:clusters/create            {:method      :post
                                :uri         "/api/2.0/clusters/create"
                                :response-fn :cluster_id}
   :clusters/list              {:method      :get
                                :uri         "/api/2.0/clusters/list"
                                :response-fn :clusters}
   :clusters/edit              {:method :post
                                :uri    "/api/2.0/clusters/edit"}
   :clusters/start             {:method :post
                                :uri    "/api/2.0/clusters/start"}
   :clusters/terminate         {:method :post
                                :uri    "/api/2.0/clusters/delete"}})



