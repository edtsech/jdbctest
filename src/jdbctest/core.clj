(ns jdbctest.core)

(require '[clojure.java.jdbc :as sql])











(def mysql-db {:subprotocol "mysql"
               :subname "//127.0.0.1:3306/uik"
               :user "root"
               :password ""})

(defn -main []
  (let [query {:sql-str "SELECT * FROM adresa"}]
    (clojure.java.jdbc/with-connection mysql-db
      (clojure.java.jdbc/transaction
       (clojure.java.jdbc/with-query-results rows
         (into [{:fetch-size Integer/MIN_VALUE
                 :concurrency :read-only
                 :result-type :forward-only} (:sql-str query)]
               (:params query))
         (doseq [row rows]
           (println (:adresa_kod row))))))))
