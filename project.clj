(defproject hexagram30/common "0.2.1"
  :description "Code and utilities for use by all hexagram30 projects"
  :url "https://github.com/hexagram30/common"
  :license {
    :name "Apache License, Version 2.0"
    :url "http://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [
    [clj-wrap-indent "1.0.0"]
    [clojusc/twig "0.4.1"]
    [com.stuartsierra/component "0.4.0"]
    [environ "1.1.0"]
    [org.clojure/clojure "1.10.1"]]
  :plugins [
    [org.clojure/core.rrb-vector "0.1.1"]]
  :profiles {
    :ubercompile {:aot :all}
    :lint {
      :source-paths ^:replace ["src"]
      :test-paths ^:replace []
      :plugins [
        [jonase/eastwood "0.3.6"]
        [lein-ancient "0.6.15"]
        [lein-kibit "0.1.8"]]}
    :test {
      :plugins [
        [lein-ltest "0.4.0"]]}}
  :aliases {
    ;; Dev Aliases
    "repl" ["do"
      ["clean"]
      ["repl"]]
    "ubercompile" ["do"
      ["clean"]
      ["with-profile" "+ubercompile" "compile"]]
    "check-vers" ["with-profile" "+lint" "ancient" "check" ":all"]
    "check-jars" ["with-profile" "+lint" "do"
      ["deps" ":tree"]
      ["deps" ":plugin-tree"]]
    "check-deps" ["do"
      ["check-jars"]
      ["check-vers"]]
    "kibit" ["with-profile" "+lint" "kibit"]
    "eastwood" ["with-profile" "+lint" "eastwood" "{:namespaces [:source-paths]}"]
    "lint" ["do"
      ["kibit"]
      ; ["eastwood"]
      ]
    "ltest" ["with-profile" "+test" "ltest"]
    "ltest-clean" ["do"
      ["clean"]
      ["ltest"]]
    "build" ["do"
      ["clean"]
      ["check-vers"]
      ["lint"]
      ["clean"]
      ["ubercompile"]
      ["ltest" ":all"]
      ["uberjar"]]
    ;; Script Aliases
    "roll" ["run"]})
