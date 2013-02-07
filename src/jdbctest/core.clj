(ns jdbctest.core
  (:require [clojure.java.jdbc :as sql]))

(def mysql-db {:subprotocol "mysql"
               :subname "//127.0.0.1:3306/uik"
               :user "root"
               :password ""})

(defn -main []
  (sql/with-connection mysql-db
    (sql/transaction
     (sql/with-query-results rows
       [{:fetch-size Integer/MIN_VALUE
         :concurrency :read-only
         :result-type :forward-only}
        "SELECT * FROM adresa"]
     (doseq [row rows]
       (println (:adresa_kod row)))))))
