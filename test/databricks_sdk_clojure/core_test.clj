(ns databricks-sdk-clojure.core-test
  (:require
   [clojure.test :refer :all]
   [databricks-sdk.core :as sdk]
   [org.httpkit.client :as http]))

(defn success-cluster-list-response [test-function]
  (future
    {:body "{\"clusters\":[{\"cluster_id\":\"123\"}]}"
     :status 200}))

(deftest list-clusters-list
  (testing "success, returning the request's body"
    (with-redefs [http/request success-cluster-list-response]
      (is (= {:result {:clusters [{:cluster_id "123"}]}}
             (sdk/list-clusters {:token "bcfGe428JL09"
                                 :timeout 30000
                                 :host "https://example-account.cloud.databricks.com"
                                 :context {}})))))
  
  (testing "failed, returning the error as edn: First error - Host not known"
    (is (= {:error {:code 1
                    :message "example-account.cloud.databricks.com: nodename nor servname provided, or not known"}}
           (sdk/list-clusters {:token "bcfGe428JL09"
                               :timeout 30000
                               :host "https://example-account.cloud.databricks.com"
                               :context {}})))))