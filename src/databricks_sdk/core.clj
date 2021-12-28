(ns databricks-sdk.core
  (:require [databricks-sdk.impl.databricks :as impl]))

(defn create-cluster! 
  "Create a new cluster in a determinated Databricks account.
   Options must have at least [token timeout host context]
     :token your databricks personal token to use the API
     :timeout to connect and request
     :host your account host, it should be something like: http://abc.cloud.databricks.com
     :context is your data to the request, in this case use a cluster information, as below:
   {:cluster_name 'my-cluster'
    :spark_version '7.3.x-scala2.12' 
    :node_type_id  'i3.xlarge'                               
    :spark_conf {:spark.speculation true}
    :aws_attributes {:availability 'SPOT'
                     :zone_id  'us-west-2a'} 
    :num_workers 25}
   And returns a new cluster-id "
  [options]
  (-> options
      (assoc :endpoint :clusters/list)
      impl/request!))

(defn list-clusters 
  "List all clusters from a databrick account.
   Options must have at least [token timeout host context]
     :token your databricks personal token to use the API
     :timeout to connect and request
     :host your account host, it should be something like: http://abc.cloud.databricks.com
   And its returns is the clusters list itself"
  [options]
  (-> options
      (assoc :endpoint :clusters/list)
      impl/request!))

