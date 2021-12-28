(ns databricks-sdk.core
  (:require [databricks-sdk.impl.databricks :as impl]))

(defn create-cluster! 
  "bla"
  
  [options]
  (-> options
      (assoc :endpoint :clusters/list))


  (impl/request! token timeout host :clusters/create {:payload payload}))

(defn list-clusters 
  "List all clusters from a databrick account.
   
   
   
   *Options*
   - :token blabla"
  [options]
  (-> options
      (assoc :endpoint :clusters/list 
             :context {})
      impl/request!))

;; old_token = 'dapid7b3065f214e904d53361a1944ec48cf'
;; new_token = 'dapie65924c185b1ce96948f244abac5b74c'

;; old_host = 'https://dbc-82a233e4-f494.cloud.databricks.com'
;; new_host = 'https://nubank-e2-staging.cloud.databricks.com'

(def api-token "dapie65924c185b1ce96948f244abac5b74c")