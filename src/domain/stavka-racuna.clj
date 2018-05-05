(ns domain.stavka-racuna
  (:require [schema.core :as s]
            [ring.swagger.schema :refer [coerce!]]))

(s/defschema StavkaRacuna {
  :redniBrojStavke Integer
  :brojRacuna      Integer
  :iznosStavke     Double
  :proizvodId      Integer
  :kolicina        Integer
})

(s/defschema Racun (dissoc Racun :brojRacuna))
            
