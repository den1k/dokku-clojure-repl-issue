(ns dokku-clojure.repl-issue
  (:require [org.httpkit.server :as http]
            [clojure.core.server]))

(defn run [_]
  (println :Starting)
  (defonce server
    (http/run-server
      (constantly {:status  200
                   :headers {"content-type" "text/html"}
                   :body    "ok 4444"})
      {:port                 4000
       :legacy-return-value? false}))
  (println "started server on port 4000")
  (defonce socket-repl
    (clojure.core.server/start-server
      {:name          "socket-repl"
       ;; :address is important for docker!
       :address       "0.0.0.0"
       :port          4001
       :accept        'clojure.core.server/repl
       ;; todo only on prod
       :server-daemon true}))
  (println "started socket-repl on port 4001")

  )
