(ns databricks-sdk-clojure.core-test
  (:require
   [clojure.test :refer :all]
   [databricks-sdk.core :as sdk]
   [org.httpkit.client :as http]))

(defn success-cluster-list-response [test-function]
  (future
    {:body "{\"clusters\":[{\"cluster_id\":\"123\"}]}"
     :status 200}))

(defn failed-cluster-list-response [test-function]
  (future
    {:opts {} 
     :error #error {:cause "idle timeout: 3ms"}}))

(deftest list-clusters-list
  (testing "success, returning the request's body"
    (with-redefs [http/request success-cluster-list-response]
      (is (= {:clusters [{:cluster_id "123"}]}(sdk/list-clusters {:token "bcfGe428JL09"
                                                                  :timeout 30000
                                                                  :host "https://example-account.cloud.databricks.com"
                                                                  :context {}})))))
  
  (testing "failed, returning the error as edn: First error - Host not known"
    (is (= {:error "example-account.cloud.databricks.com: nodename nor servname provided, or not known"}
           (sdk/list-clusters {:token "bcfGe428JL09"
                               :timeout 30000
                               :host "https://example-account.cloud.databricks.com"
                               :context {}}))))
  
  (testing "failed, returning the error as edn: Second error - Timeout"
    (with-redefs [http/request failed-cluster-list-response]
      (is (= {:error "idle timeout: 3ms"}
                        (sdk/list-clusters {:token "bcfGe428JL09"
                                            :timeout 3
                                            :host "https://example-account.cloud.databricks.com"
                                            :context {}}))))))