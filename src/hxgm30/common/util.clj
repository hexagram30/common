(ns hxgm30.common.util
  (:require
    [clojure.walk :as walk]))

(defn deep-merge
  "Merge maps recursively."
  [& maps]
  (if (every? #(or (map? %) (nil? %)) maps)
    (apply merge-with deep-merge maps)
    (last maps)))

(defn keys->strs
  "Recursively transforms all map keys from keywords to strings."
  [m]
  (let [f (fn [[k v]] (if (keyword? k) [(name k) v] [k v]))]
    ;; only apply to maps
    (walk/postwalk (fn [x] (if (map? x) (into {} (map f x)) x)) m)))

(defn tuple?
  [data]
  (and (= 2 (count data))
       (not (coll? (first data)))))

(defn tuple->map
  [tuple]
  (into {} [(vec tuple)]))

(defn tuples->map
  [tuples]
  (into {} (vec (map vec tuples))))

(defn import-class
  [package-and-class]
  (try (.importClass
        (the-ns *ns*)
        (clojure.lang.RT/classForName (str package-and-class)))
    (catch Exception _
      nil)))

(defn require-ns
  [an-ns]
  (find-ns an-ns))

(defn list->map
  "Given a flat list of pairs, convert them to a map."
  [items]
  (->> items
       (partition 2)
       (map (fn [[k v]] [(keyword k) v]))
       vec
       (into {})))

(defn frequency->percent-range
  "A reducer that, given a frequency and an accumulator, returns an updated
  accumulator with the given frequency converted to a percent range (appended
  to the accumulator). The first argument is expectred to be provided as a
  partial."
  [total acc [k v]]
  (let [last-value (or (last (first (last acc))) 0)
        v-percent (/ v total)]
    (conj acc [[last-value (+ v-percent last-value)] k])))

(defn frequencies->percent-ranges
  "Create a lookup of key/value pairs where the key is the range of percentages
  where a syllable count occurs, and the value is the syllable count."
  [freqs]
  (let [total (reduce + 0.0 (vals freqs))]
    (->> freqs
         (reduce (partial frequency->percent-range total) [])
         (into {}))))

(defn percent->
  "Given a percent (as a float between 0.0 and 1.0) and a lookup table of
  percent ranges, find the range in which the given percent falls, and return
  the associated value."
  [percent percent-ranges]
  (->> percent-ranges
       (map (fn [[[low high] syll-count]]
             (when (and (< low percent) (< percent high))
               syll-count)))
       (remove nil?)
       first))
