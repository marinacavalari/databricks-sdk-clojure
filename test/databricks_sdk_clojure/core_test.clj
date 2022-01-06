(ns databricks-sdk-clojure.core-test
  (:require
   [clojure.test :refer :all]
   [databricks-sdk.core :as sdk]
   [org.httpkit.client :as http]))

(defn success-clusters-list-response [test-function]
  (future
    {:body "{\"clusters\":[{\"cluster_id\":\"123\"}]}"
     :status 200}))

(defn success-cluster-response [test-function]
  (future
    {:body "{\"cluster_id\":\"123\"}"
     :status 200}))

(defn failed-response [test-function]
  (future
    {:body "Too many requests"
     :status 429}))

(deftest list-clusters-list
  (testing "success, returning the request's body, in this case, the clusters list"
    (with-redefs [http/request success-clusters-list-response]
      (is (= {:result {:clusters [{:cluster_id "123"}]}}
             (sdk/list-clusters {:token "bcfGe428JL09"
                                 :timeout 30000
                                 :host "https://example-account.cloud.databricks.com"
                                 :body {}})))))
  
  (testing "failed, uknown status error"
    (is (= {:error {:code 1
                    :message "example-account.cloud.databricks.com: nodename nor servname provided, or not known"}}
           (sdk/list-clusters {:token "bcfGe428JL09"
                               :timeout 30000
                               :host "https://example-account.cloud.databricks.com"
                               :body {}}))))
  
  (testing "failed, known status error"
    (with-redefs [http/request failed-response]                      
     (is (= {:error {:code 429
                     :message  "Too many requests"}}
            (sdk/list-clusters {:token "bcfGe428JL09"
                                :timeout 30000
                                :host "https://example-account.cloud.databricks.com"
                                :body {}}))))))

(deftest edit-cluster-test
  (testing "success, returning the request's body, in this case, the cluster id"
    (with-redefs [http/request success-cluster-response]
      (is (= {:result {:cluster_id "123"}}
             (sdk/edit-cluster! {:token "bcfGe428JL09"
                                 :timeout 30000
                                 :host "https://example-account.cloud.databricks.com"
                                 :body {}})))))
  (testing "failed, uknown status error"
    (is (= {:error {:code 1
                    :message "example-account.cloud.databricks.com: nodename nor servname provided, or not known"}}
           (sdk/edit-cluster! {:token "bcfGe428JL09"
                               :timeout 30000
                               :host "https://example-account.cloud.databricks.com"
                               :body {}}))))
  (testing "failed, known status error"
    (with-redefs [http/request failed-response]
      (is (= {:error {:code 429
                      :message  "Too many requests"}}
             (sdk/edit-cluster! {:token "bcfGe428JL09"
                                 :timeout 30000
                                 :host "https://example-account.cloud.databricks.com"
                                 :body {}}))))))

(deftest create-cluster-test
  (testing "success, returning the request's body, in this case, the new cluster id"
    (with-redefs [http/request success-cluster-response]
      (is (= {:result {:cluster_id "123"}}
             (sdk/create-cluster! {:token "bcfGe428JL09"
                                 :timeout 30000
                                 :host "https://example-account.cloud.databricks.com"
                                 :body {}})))))
  (testing "failed, uknown status error"
    (is (= {:error {:code 1
                    :message "example-account.cloud.databricks.com: nodename nor servname provided, or not known"}}
           (sdk/create-cluster! {:token "bcfGe428JL09"
                               :timeout 30000
                               :host "https://example-account.cloud.databricks.com"
                               :body {}}))))
  (testing "failed, known status error"
    (with-redefs [http/request failed-response]
      (is (= {:error {:code 429
                      :message  "Too many requests"}}
             (sdk/create-cluster! {:token "bcfGe428JL09"
                                 :timeout 30000
                                 :host "https://example-account.cloud.databricks.com"
                                 :body {}}))))))

(deftest start-cluster-test
  (testing "success, returning the request's body, in this case, the started cluster id"
    (with-redefs [http/request success-cluster-response]
      (is (= {:result {:cluster_id "123"}}
             (sdk/start-cluster! {:token "bcfGe428JL09"
                                   :timeout 30000
                                   :host "https://example-account.cloud.databricks.com"
                                   :body {}})))))
  (testing "failed, uknown status error"
    (is (= {:error {:code 1
                    :message "example-account.cloud.databricks.com: nodename nor servname provided, or not known"}}
           (sdk/start-cluster! {:token "bcfGe428JL09"
                                 :timeout 30000
                                 :host "https://example-account.cloud.databricks.com"
                                 :body {}}))))
  (testing "failed, known status error"
    (with-redefs [http/request failed-response]
      (is (= {:error {:code 429
                      :message  "Too many requests"}}
             (sdk/start-cluster! {:token "bcfGe428JL09"
                                   :timeout 30000
                                   :host "https://example-account.cloud.databricks.com"
                                   :body {}}))))))

(deftest terminate-cluster-test
  (testing "success, returning the request's body, in this case, the terminated cluster id"
    (with-redefs [http/request success-cluster-response]
      (is (= {:result {:cluster_id "123"}}
             (sdk/terminate-cluster! {:token "bcfGe428JL09"
                                   :timeout 30000
                                   :host "https://example-account.cloud.databricks.com"
                                   :body {}})))))
  (testing "failed, uknown status error"
    (is (= {:error {:code 1
                    :message "example-account.cloud.databricks.com: nodename nor servname provided, or not known"}}
           (sdk/terminate-cluster! {:token "bcfGe428JL09"
                                 :timeout 30000
                                 :host "https://example-account.cloud.databricks.com"
                                 :body {}}))))
  (testing "failed, known status error"
    (with-redefs [http/request failed-response]
      (is (= {:error {:code 429
                      :message  "Too many requests"}}
             (sdk/terminate-cluster! {:token "bcfGe428JL09"
                                   :timeout 30000
                                   :host "https://example-account.cloud.databricks.com"
                                   :body {}}))))))