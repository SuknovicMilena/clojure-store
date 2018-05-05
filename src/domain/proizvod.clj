(ns domain.proizvod
  (:require [schema.core :as s]
            [ring.swagger.schema :refer [coerce!]]))

(s/defschema Proizvod {
  :proizvodId      Integer
  :nazivProizvoda  String
  :cena            Double
  :boja            String
  :tip             String
  :proizvodjacId   String
})

(s/defschema NewProizvod (dissoc Proizvod :proizvodId))
