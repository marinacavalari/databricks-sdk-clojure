(ns databricks-sdk.core
  (:require
   [clojure.data.json :as json]
   [databricks-sdk.impl.databricks :as impl]))


(defn create-cluster!
  "Create a new cluster within a Databricks Account
   **Input**
   Must have at least [token timeout host context]
   - `:token` your databricks token to use the API
   - `:timeout` the time limit in milliseconds to wait for the response.
   - `:host` your account host, e.g. `http://abc.cloud.databricks.com`
   - `:context` is your data to the request, in this case use a cluster information, as below:
  ```clojure
     {:cluster_name 'my-cluster'
      :spark_version '7.3.x-scala2.12'
      :node_type_id 'i3.xlarge'
      :spark_conf {:spark.speculation true}
      :aws_attributes {:availability 'SPOT'
                       :zone_id  'us-west-2a'}
      :num_workers 25}
  ```
  **Output**
   Returns the new created `cluster-id`."
  [options]
  (-> options
      (assoc :endpoint :clusters/list)
      impl/request!
      :body)

(defn list-clusters
  "List all clusters from a databrick account.
   **Input**
    Must have at least [token timeout host context]
    - `:token` your databricks token to use the API
    - `:timeout` the time limit in milliseconds to wait for the response.
    - `:host` your account host, e.g. `http://abc.cloud.databricks.com`
    - `:context` is your data to the request, in this case use empty {}
   
   **Output**
   And its returns is the `clusters` list itself"
  [options]
  (-> options
      (assoc :endpoint :clusters/list)
      impl/request!
      :body))

(defn edit-cluster!
  "Edit a specific cluster within a Databricks Account
   **Input**
   Must have at least [token timeout host context]
   - `:token` your databricks token to use the API
   - `:timeout` the time limit in milliseconds to wait for the response.
   - `:host` your account host, e.g. `http://abc.cloud.databricks.com`
   - `:context` is your data to the request, in this case use a cluster information, as below:
  ```clojure
     {:cluster_id '1x2e34r5t'
      :spark_version '7.3.x-scala2.12'
      :node_type_id 'i3.xlarge'
      :num_workers 10}
 ```
  **Output**
   {:status 200}"
  [options]
  {:status (-> options
               (assoc :endpoint :clusters/edit)
               impl/request!
               :status)})

(defn start-cluster!
  "Start a specific cluster within a Databricks Account
   **Input**
   Must have at least [token timeout host context]
   - `:token` your databricks token to use the API
   - `:timeout` the time limit in milliseconds to wait for the response.
   - `:host` your account host, e.g. `http://abc.cloud.databricks.com`
   - `:context` is your data to the request, in this case use a cluster id information, as below:
  ```clojure
     {:cluster_id '1x2e34r5t'}
 ```
  **Output**
   {:status 200}"
  [options]
  {:status (-> options
               (assoc :endpoint :clusters/start)
               impl/request!
               :status)})
      



