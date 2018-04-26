(defproject hexagram30/common "0.1.0-SNAPSHOT"
  :description "Code and utilities for use by all hexagram30 projects"
  :url "https://github.com/hexagram30/common"
  :license {
    :name "Apache License, Version 2.0"
    :url "http://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [
    [org.clojure/clojure "1.8.0"]]
  :profiles {
    :test {
      :plugins [
        [lein-ltest "0.3.0-SNAPSHOT"]]}}
  :aliases {
    "ltest" ["with-profile" "+test" "ltest"]})
