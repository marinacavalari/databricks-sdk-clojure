(ns databricks-sdk.core
  (:require [databricks-sdk.impl.databricks :as impl]))

(defn create-cluster! [{:keys [token host payload timeout]}]
  (impl/request! host token timeout :clusters/create {:payload payload}))

(defn list-clusters [token host timeout]
  (impl/request! host token timeout :clusters/list {}))
