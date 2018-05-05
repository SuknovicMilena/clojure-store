(ns domain.racun
  (:require [schema.core :as s]
            [ring.swagger.schema :refer [coerce!]]))

(s/defschema Racun {
  :brojRacuna      Integer
  :datumKupovine   String
  :ukupanIznos     Double
  :korisnikId      Integer
})

(s/defschema Racun (dissoc Racun :brojRacuna))
