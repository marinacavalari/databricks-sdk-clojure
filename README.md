[![Clojars Project](https://img.shields.io/clojars/v/org.clojars.marinacavalari/databricks-sdk.svg)](https://clojars.org/org.clojars.marinacavalari/databricks-sdk)

# Databricks-SDK

A Clojure SDK for Databricks usage, with most used functions.
This library is a way to do a easier access to the Databricks API 2.0 and its functions as list clusters, create, delete and even more. It will help people to create services to automatize requests and  make some intergrations.

## Installation

```clojure
[org.clojars.marinacavalari/databricks-sdk "0.1.2"]
```

## Usage

```clojure

(ns clojure-sample.core
  (:require [databricks-sdk.core :as sdk]))

(sdk/list-clusters {:token 1234
                    :timeout 30000    
                    :host "http://abc.cloud.databricks.com"
                    :context  {}})

```

## Contribution

Contributions are very welcome, please open an issue or a pull request whenever you may need.