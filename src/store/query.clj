(ns store.query
  (:require [store.database]
            [store.domain :refer :all]
            [korma.core :refer :all]))

(defentity korisnik)

(defn get-users []
  (select korisnik))
  
(defn get-user [korisnikId]
  (first
  (select korisnik
    (where {:korisnikId [= korisnikId]} )
    (limit 1))))

(defn add-user [newKorisnik]
  (def result (insert korisnik
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

(defn update-user [korisnikId updatedKorisnik]
  (update korisnik
    (set-fields {
      :ime (get updatedKorisnik :ime)
      :prezime (get updatedKorisnik :prezime)
      :korisnickoIme (get updatedKorisnik :korisnickoIme)
      :korisnickaSifra (get updatedKorisnik :korisnickaSifra)
    })
    (where {:korisnikId [= korisnikId]}))
  (get-user korisnikId)
)
          
(defn delete-user [korisnikId]
  (delete korisnik
    (where {:korisnikId [= korisnikId]})))