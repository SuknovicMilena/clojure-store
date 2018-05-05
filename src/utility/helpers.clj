(ns utility.helpers)

(defn parse-int [s]
  (Integer/parseInt (re-find #"\A-?\d+" s)))