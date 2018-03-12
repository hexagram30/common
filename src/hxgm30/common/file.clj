(ns hxgm30.common.file
  (:require
   [clojure.edn :as edn]
   [clojure.java.io :as io]))

(defn read-edn-resource
  [filename]
  (with-open [rdr (io/reader (io/resource filename))]
    (edn/read (new java.io.PushbackReader rdr))))
