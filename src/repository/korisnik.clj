(ns repository.korisnik
  (:require [mysql.connection]
            [domain.korisnik :refer :all]
            [korma.core :refer :all]))

(defentity korisnik)

(defn get-users []
  (select korisnik))
  
(defn get-user [korisnikId]
  (first
  (select korisnik
    (where {:korisnikId [= korisnikId]} )
    (limit 1))))

(defn get-user-by-username [korisnickoIme]
  (first
  (select korisnik
    (where {:korisnickoIme [= korisnickoIme]} )
    (limit 1))))

(defn add-user [newKorisnik]
  (def existingUser (get-user-by-username (get newKorisnik :korisnickoIme)))
  (if existingUser 
    "User with that username already exists"
    ((def result (insert korisnik
      (values {
        :ime (get newKorisnik :ime)
        :prezime (get newKorisnik :prezime)
        :korisnickoIme (get newKorisnik :korisnickoIme)
        :korisnickaSifra (get newKorisnik :korisnickaSifra)
        })
      ))
      (def insertedId (get result :generated_key))
      (get-user insertedId)
    )
  )
)

(defn parse-int [s]
  (Integer/parseInt (re-find #"\A-?\d+" s)))

(defn update-user [korisnikId updatedKorisnik]
  (def existingUser (get-user korisnikId))
  (def existingUserByUsername (get-user-by-username (get updatedKorisnik :korisnickoIme)))
  ; (println (not= (parse-int korisnikId) (get existingUserByUsername :korisnikId)))
  (if existingUser 
    (if (and existingUserByUsername (not= (parse-int korisnikId) (get existingUserByUsername :korisnikId))) 
      "User with that username already exists"
      (update korisnik
        (set-fields {
          :ime (get updatedKorisnik :ime)
          :prezime (get updatedKorisnik :prezime)
          :korisnickoIme (get updatedKorisnik :korisnickoIme)
          :korisnickaSifra (get updatedKorisnik :korisnickaSifra)
        })
        (where {:korisnikId [= korisnikId]}))
    )
    "Cannot find user with that id"
  )
)
          
(defn delete-user [korisnikId]
  (def existingUser (get-user korisnikId))
  (if existingUser 
    (delete korisnik
      (where {:korisnikId [= korisnikId]}))
    "User with that id does not exist"
  )
)
