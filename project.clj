(defproject hexagram30/common "0.2.0-SNAPSHOT"
  :description "Code and utilities for use by all hexagram30 projects"
  :url "https://github.com/hexagram30/common"
  :license {
    :name "Apache License, Version 2.0"
    :url "http://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [
    [clj-wrap-indent "1.0.0"]
    [org.clojure/clojure "1.10.0"]]
  :plugins [
    [org.clojure/core.rrb-vector "0.0.13"]]
  :profiles {
    :ubercompile {:aot :all}
    :lint {
      :source-paths ^:replace ["src"]
      :test-paths ^:replace []
      :plugins [
        [jonase/eastwood "0.3.4"]
        [lein-ancient "0.6.15"]
        [lein-kibit "0.1.6"]]}
    :test {
      :plugins [
        [lein-ltest "0.3.0"]]}}
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
      ["ltest" ":all"]
      ["uberjar"]
      ["build-cli"]]
    "clean-cljs" ["with-profile" "+cljs" "do"
      ["clean"]
      ["shell" "rm" "-f" "bin/roll"]]
    "build-cli" ["with-profile" "+cljs" "do"
      ["cljsbuild" "once" "cli"]
      ["shell" "chmod" "755" "bin/roll"]]
    "clean-build-cli" ["with-profile" "+cljs" "do"
      ["clean-cljs"]
      ["build-cli"]]
    ;; Script Aliases
    "roll" ["run"]})
