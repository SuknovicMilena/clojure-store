(ns repository.korisnik
  (:require [mysql.connection]
            [domain.korisnik :refer :all]
            [utility.helpers :refer :all]
            [korma.core :refer :all]))

(defentity korisnik)

(defn get-korisnici []
  (select korisnik))
  
(defn get-korisnik [korisnikId]
  (first
  (select korisnik
    (where {:korisnikId [= korisnikId]} )
    (limit 1))))

(defn get-korisnik-by-korisnicko-ime [korisnickoIme]
  (first
  (select korisnik
    (where {:korisnickoIme [= korisnickoIme]} )
    (limit 1))))

(defn add-korisnik [newKorisnik]
  (def existingKorisnik (get-korisnik-by-korisnicko-ime (get newKorisnik :korisnickoIme)))
  (if existingKorisnik 
    "Korisnik sa tim korisnickim imenom vec postoji"
    ((def insertKorisnikResult (insert korisnik
      (values {
        :ime (get newKorisnik :ime)
        :prezime (get newKorisnik :prezime)
        :korisnickoIme (get newKorisnik :korisnickoIme)
        :korisnickaSifra (get newKorisnik :korisnickaSifra)
        })
      ))
      (def insertedKorisnikId (get insertKorisnikResult :generated_key))
      (get-korisnik insertedKorisnikId)
    )
  )
)

(defn update-korisnik [korisnikId updatedKorisnik]
  (def existingKorisnik (get-korisnik korisnikId))
  (def existingKorisnikByKorisnickoIme (get-korisnik-by-korisnicko-ime (get updatedKorisnik :korisnickoIme)))
  ; (println (not= (parse-int korisnikId) (get existingKorisnikByKorisnickoIme :korisnikId)))
  (if existingKorisnik 
    (if (and existingKorisnikByKorisnickoIme (not= (parse-int korisnikId) (get existingKorisnikByKorisnickoIme :korisnikId))) 
      "Korisnik sa tim korisnickim imenom vec postoji"
      (update korisnik
        (set-fields {
          :ime (get updatedKorisnik :ime)
          :prezime (get updatedKorisnik :prezime)
          :korisnickoIme (get updatedKorisnik :korisnickoIme)
          :korisnickaSifra (get updatedKorisnik :korisnickaSifra)
        })
        (where {:korisnikId [= korisnikId]}))
    )
    "Ne postoji korisnik sa tim ID-em"
  )
)
          
(defn delete-korisnik [korisnikId]
  (def existingKorisnik (get-korisnik korisnikId))
  (if existingKorisnik 
    (delete korisnik
      (where {:korisnikId [= korisnikId]}))
    "Ne postoji korisnik sa tim ID-em"
  )
)
