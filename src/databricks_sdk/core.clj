(ns databricks-sdk.core
  (:require
   [databricks-sdk.impl.databricks :as impl]))

(defn create-cluster!
  "Create a new cluster within a Databricks Account
   **Input**
   Must have at least [token timeout host body]
   - `:token` your databricks token to use the API
   - `:timeout` the time limit in milliseconds to wait for the response.
   - `:host` your account host, e.g. `http://abc.cloud.databricks.com`
   - `:body` is your data to the request, in this case use a cluster information, as below:
  ```clojure
     {:cluster_name \"my-cluster\"
      :spark_version \"7.3.x-scala2.12\"
      :node_type_id \"i3.xlarge\"
      :spark_conf {:spark.speculation true}
      :aws_attributes {:availability \"SPOT\"
                       :zone_id  \"us-west-2a\"}
      :num_workers 25}
  ```
  **Output**
   
   ```clojure 
      {:result {:cluster_id \"123\"}}
   ``` "
  [options]
  (-> options
      (assoc :endpoint :clusters/list)
      impl/request!))

(defn list-clusters
  "List all clusters from a databrick account.
   **Input**
    Must have at least [token timeout host body]
    - `:token` your databricks token to use the API
    - `:timeout` the time limit in milliseconds to wait for the response.
    - `:host` your account host, e.g. `http://abc.cloud.databricks.com`
    - `:body` is your data to the request, in this case use empty {}
   
   **Output**
   
   ```clojure 
      {:result {:clusters [{:cluster_id \"123\"}]}}
   ``` "
  [options]
  (-> options
      (assoc :endpoint :clusters/list)
      impl/request!))

(defn edit-cluster!
  "Edit a specific cluster within a Databricks Account
   **Input**
   Must have at least [token timeout host body]
   - `:token` your databricks token to use the API
   - `:timeout` the time limit in milliseconds to wait for the response.
   - `:host` your account host, e.g. `http://abc.cloud.databricks.com`
   - `:body` is your data to the request, in this case use a cluster information, as below:
  ```clojure
     {:cluster_id \"1x2e34r5t\"
      :spark_version \"7.3.x-scala2.12\"
      :node_type_id \"i3.xlarge\"
      :num_workers 10}
 ```
  **Output**
   
  ```clojure 
    {:result {:cluster_id \"123\"}}
  ``` "
  [options]
  (-> options
      (assoc :endpoint :clusters/edit)
      impl/request!))

(defn start-cluster!
  "Start a specific cluster within a Databricks Account
   **Input**
   Must have at least [token timeout host body]
   - `:token` your databricks token to use the API
   - `:timeout` the time limit in milliseconds to wait for the response.
   - `:host` your account host, e.g. `http://abc.cloud.databricks.com`
   - `:body` is your data to the request, in this case use a cluster id information, as below:
  ```clojure
     {:cluster_id \"1x2e34r5t\"}
 ```
   **Output**
   
  ```clojure 
     {:result {:cluster_id \"123\"}}
  ``` "
  [options]
  (-> options
      (assoc :endpoint :clusters/start)
      impl/request!))

(defn terminate-cluster!
  "Terminate a specific cluster within a Databricks Account
   **Input**
   Must have at least [token timeout host body]
   - `:token` your databricks token to use the API
   - `:timeout` the time limit in milliseconds to wait for the response.
   - `:host` your account host, e.g. `http://abc.cloud.databricks.com`
   - `:body` is your data to the request, in this case use a cluster id information, as below:
  ```clojure
     {:cluster_id \"1x2e34r5t\"}
 ```
   **Output**
   
  ```clojure 
    {:result {:cluster_id \"123\"}}
  ``` "
  [options]
  (-> options
      (assoc :endpoint :clusters/terminate)
      impl/request!))

(defn grant-cluster-permission!
  "Grant cluster permissions for one or more users, groups, or service principals.
   This request only grants (adds) permissions. To revoke, use the replace all cluster permissions function.
   **Input**
   Must have at least [token timeout host body]
   - `:token` your databricks token to use the API
   - `:timeout` the time limit in milliseconds to wait for the response.
   - `:host` your account host, e.g. `http://abc.cloud.databricks.com`
   - `:body` is your data to the request, in this case use a new permission to a specific usr or group, as below:
  ```clojure
     {:access_control_list [{:user_name \"jsmith@example.com\"
                             :permission_level \"CAN_RESTART\"}]}
 ```
   **Output**
   
  ```clojure 
    {:result {:object_id \"/clusters/0712-200003-rail519\"
              :object_type \"cluster\"}
              :access_control_list []}
  ``` "
  [options]
  (-> options
      (assoc :endpoint :clusters/permissions)
      impl/request!))

(defn install-cluster-library!
  "Install libraries on a cluster. The installation is asynchronous - it completes in the background after the request.
   The installation only happens when the cluster is running.
   **Input**
   Must have at least [token timeout host body]
   - `:token` your databricks token to use the API
   - `:timeout` the time limit in milliseconds to wait for the response.
   - `:host` your account host, e.g. `http://abc.cloud.databricks.com`
   - `:body` is your data to the request, in this case use the paths for the libs being installed, as below:
  ```clojure
     {:cluster_id \"1x2e34r5t\"
      :libraries [{:jar \"dbfs:/mnt/libraries/library.jar\"}]}
 ```
   **Output**
   
  ```clojure 
    {:to-do}
  ``` "
  [options]
  (start-cluster! options)
  (-> options
      (assoc :endpoint :libraries/install)
      impl/request!))
