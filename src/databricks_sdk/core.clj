(ns databricks-sdk.core
  (:require
   [databricks-sdk.impl.databricks :as impl]
   [clojure.string :as string]))

(defn ^:private check-auth [options]
  (and (:token options)
       (:host options)
       (:timeout options)))

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
  {:pre [(check-auth options)
         (:body options)]}
  (-> options
      (assoc :endpoint :clusters/list)
      impl/request!))

(defn clusters-events
  "Retrieve a list of events about the activity of a cluster. 
   You can retrieve events from active clusters (running, pending, or reconfiguring) and terminated clusters.
   **Input**
   Must have at least [token timeout host body]
   - `:token` your databricks token to use the API
   - `:timeout` the time limit in milliseconds to wait for the response.
   - `:host` your account host, e.g. `http://abc.cloud.databricks.com`
   - `:body` is your data to the request, in this case use a cluster information, as below:

  ```clojure
     {:cluster_id \"1x2e34r5t\"
      :start_time 1617238800000
      :end_time 1619485200000
      :order \"DESC\" \"(If empty, default is DESC)\"
      :offset 10
      :limit 25}
  ```
  **Output**
   
   ```clojure 
      {:result {:events [{:cluster_id \"1x2e34r5t\", 
                    :timestamp 1642016862695, 
                    :type \"DRIVER_HEALTHY\", 
                    :details {}}], 
          :next_page {:cluster_id \"1x2e34r5t\", 
                      :end_time 1642016862695, 
                      :offset 1, 
                      :limit 1}, 
          :total_count 2283}}
   ``` "
  [options]
  {:pre [(check-auth options)
         (:body options)]}
  (-> options
      (assoc :endpoint :clusters/events)
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
  {:pre [(check-auth options)
         (:body options)]}
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
  {:pre [(check-auth options)
         (:body options)]}
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
  {:pre [(check-auth options)
         (:body options)]}
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
  {:pre [(check-auth options)
         (-> options :body :cluster_id string?)]}
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
   - `:body` is your data to the request, in this case use a new permission to a specific user or group, as below:

  ```clojure
     {:cluster_id \"1x2e34r5t\"
      :access_control_list [{:user_name \"jsmith@example.com\"
                             :permission_level \"CAN_RESTART\"}]}
 ```
   **Output**
   
  ```clojure 
    {:result {:object_id \"/clusters/0712-200003-rail519\"
              :object_type \"cluster\"}
              :access_control_list []}
  ``` "
  [options]
  {:pre [(check-auth options)
         (:body options)]}
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
  {:pre [(check-auth options)
         (:body options)]}
  (-> options
      (assoc :endpoint :libraries/install)
      impl/request!))

(defn status-cluster-library
  "Get the status of libraries on a cluster. 
   A status will be available for all libraries installed on the cluster via the API or the libraries UI as well as libraries set to be installed on all clusters via the libraries UI.
   **Input**
   Must have at least [token timeout host body]
   - `:token` your databricks token to use the API
   - `:timeout` the time limit in milliseconds to wait for the response.
   - `:host` your account host, e.g. `http://abc.cloud.databricks.com`
   - `:body` is your data to the request, in this case use the cluster id as below:

   ```clojure 
    {:cluster_id \"1x2e34r5t\"}
  ```
   **Output**
   
  ```clojure 
    {:cluster_id \"1x2e34r5t\"
      :library_statuses [{:library {:jar \"dbfs:/mnt/libraries/library.jar\"}
                          :status \"INSTALLED\"
                          :messages []
                          :is_library_for_all_clusters false}]}
  ``` "
  [options]
  {:pre [(check-auth options)
         (-> options :body :cluster_id string?)]}
  (-> options
      (assoc :endpoint :libraries/status)
      impl/request!))

(defn list-users
  "Retrieve a list of all users in the Databricks workspace.
   **Input**
   Must have at least [token timeout host body]
   - `:token` your databricks token to use the API
   - `:timeout` the time limit in milliseconds to wait for the response.
   - `:host` your account host, e.g. `http://abc.cloud.databricks.com`
   - `:body` is your data to the request, in this case use empty {}
   **Output**
   
  ```clojure 
    {:to-do}
  ``` "
  [options]
  {:pre [(check-auth options)
         (:body options)]}
  (-> options
      (assoc :endpoint :scim/users)
      impl/request!))

(defn filter-user-by-user-name
  "Retrieve one single user by their username in the Databricks workspace.
   **Input**
   Must have at least [token timeout host body]
   - `:token` your databricks token to use the API
   - `:timeout` the time limit in milliseconds to wait for the response.
   - `:host` your account host, e.g. `http://abc.cloud.databricks.com`
   - `:body` is your data to the request, in this case use the body as below:

   ```clojure 
    {:email \"example@example.com\"}
  ```
   **Output**
   
  ```clojure 
    {:to-do}
  ``` "
  [options]
  {:pre [(check-auth options)
         (-> options :body :email string?)]}
  (-> options
      (assoc :endpoint  :scim/get-user)
      impl/request!))

(defn remove-role-from-user!
  "Remove a specif permission to a specifc user
   **Input**
   Must have at least [token timeout host body]
   - `:token` your databricks token to use the API
   - `:timeout` the time limit in milliseconds to wait for the response.
   - `:host` your account host, e.g. `http://abc.cloud.databricks.com`
   - `:body` is your data to the request, in this case use the body as below:

   ```clojure 
    {:databricks-user-id 1234567899
     :schemas {\"urn:ietf:params:scim:api:messages:2.0:PatchOp\"}
     :Operations [{:op \"remove\"
                   :path \"roles[value eq \"arn:aws:iam::123456789012:role/my-role\"]}]}
  ```
   **Output**
   
  ```clojure 
    {:to-do}
  ``` "
  [options]
  {:pre [(check-auth options)
         (:body options)]}
  (-> options
      (assoc :endpoint  :scim/remove-role-from-user)
      impl/request!))

(defn get-all-credentials!
  [options]
  {:pre [(check-auth options)
         (:body options)]}
  (-> options
      (assoc :endpoint :accounts/credentials)
      impl/request!))

(defn create-credential-config!
  [options]
  {:pre [(check-auth options)
         (-> options :body :account_id string?)
         (-> options :body :credentials_name string?)
         (-> options :body :aws_credentials :sts_role :role_arn string?)
         ]}
  (-> options
      (assoc :endpoint :accounts/credentials)
      impl/request!))