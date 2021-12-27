(ns databricks-sdk.impl.endpoints)

(def request
  {:clusters/create            {:method      :post
                                :uri         "/api/2.0/clusters/create"}
   :clusters/list              {:method      :get
                                :uri         "/api/2.0/clusters/list"}})


