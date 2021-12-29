(defproject com.github.marinacavalari/databricks-sdk "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [http-kit "2.5.3"]
                 [org.clojure/data.json "2.2.1"]]
  :plugins [[com.github.clojure-lsp/lein-clojure-lsp "1.1.5"]]
  :repl-options {:init-ns databricks-sdk.core})