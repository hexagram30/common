(ns hxgm30.common.components.core
  (:require
    [hxgm30.common.components.config :as config]
    [hxgm30.common.components.logging :as logging]
    [hxgm30.common.config :as config-lib]
    [com.stuartsierra.component :as component]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;   Common Configuration Components   ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def cfg
  {:config (config/create-component
            (config-lib/data))})

(def log
  {:logging (component/using
             (logging/create-component)
             [:config])})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;   Component Initializations   ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn initialize-without-logging
  []
  (component/map->SystemMap
    cfg))

(defn initialize
  []
  (component/map->SystemMap
    (merge cfg
           log)))

(def init-lookup
  {:no-logging #'initialize-without-logging
   :main #'initialize})

(defn init
  ([]
    (init :main))
  ([mode]
    ((mode init-lookup))))
