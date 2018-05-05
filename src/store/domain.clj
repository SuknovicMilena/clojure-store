(ns store.domain
  (:require [schema.core :as s]
            [ring.swagger.schema :refer [coerce!]]))

;; Domain

(s/defschema Korisnik {
  :korisnikId      Integer
  :ime             String
  :prezime         String
  :korisnickoIme   String
  :korisnickaSifra String
})

(s/defschema NewKorisnik (dissoc Korisnik :korisnikId))


(s/defschema Proizvodjac {
  :proizvodjacId   Integer
  :naziv           String
  :zemlja          String
})

(s/defschema NewProzvodjac (dissoc Proizvodjac :proizvodjacId))


(s/defschema Proizvod {
  :proizvodId      Integer
  :nazivProizvoda  String
  :cena            Double
  :boja            String
  :tip             String
  :proizvodjacId   String
})

(s/defschema NewProizvod (dissoc Proizvod :proizvodId))


(s/defschema Racun {
  :brojRacuna      Integer
  :datumKupovine   String
  :ukupanIznos     Double
  :korisnikId      Integer
})

(s/defschema Racun (dissoc Racun :brojRacuna))


(s/defschema StavkaRacuna {
  :redniBrojStavke Integer
  :brojRacuna      Integer
  :iznosStavke     Double
  :proizvodId      Integer
  :kolicina        Integer
})

(s/defschema Racun (dissoc Racun :brojRacuna))
