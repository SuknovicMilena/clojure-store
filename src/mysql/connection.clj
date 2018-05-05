(ns mysql.connection
  (:require [korma.db :as korma]))

(def db-connection-info (korma/mysql 
  {:classname "com.mysql.jdbc.Driver"
   :subprotocol "mysql"
   :user "store"
   :password "2#$o6PZGAbUvjm!T$ztP"
   :subname "//localhost:3306/naocaredb"}))

; set up korma
(korma/defdb db db-connection-info)