(ns domain.proizvodjac
  (:require [schema.core :as s]
            [ring.swagger.schema :refer [coerce!]]))

(s/defschema Proizvodjac {
  :proizvodjacId   Integer
  :naziv           String
  :zemlja          String
})

(s/defschema NewProzvodjac (dissoc Proizvodjac :proizvodjacId))
            
            