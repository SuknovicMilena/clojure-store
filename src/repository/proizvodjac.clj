(ns repository.proizvodjac
  (:require [mysql.connection]
            [domain.proizvodjac :refer :all]
            [utility.helpers :refer :all]
            [korma.core :refer :all]))

(defentity proizvodjac)

(defn get-proizvodjaci []
  (select proizvodjac))
  
(defn get-proizvodjac [proizvodjacId]
  (first
    (select proizvodjac
      (where {:proizvodjacId [= proizvodjacId]} )
      (limit 1)
    )
  )
)

(defn get-proizvodjac-by-naziv [naziv]
  (first
    (select proizvodjac
      (where {:naziv [= naziv]} )
      (limit 1)
    )
  )
)

(defn add-proizvodjac [newProizvodjac]
  (def existingProizvodjac (get-proizvodjac-by-naziv (get newProizvodjac :naziv)))
  (if existingProizvodjac 
    "Proizvodjac sa tim imenom vec postoji"
    ((def insertProizvodjacResult 
      (insert proizvodjac
        (values {
          :naziv (get newProizvodjac :naziv)
          :zemlja (get newProizvodjac :zemlja)
        })
      ))
      (def insertedProizvodjacId (get insertProizvodjacResult :generated_key))
      (get-proizvodjac insertedProizvodjacId)
    )
  )
)

(defn update-proizvodjac [proizvodjacId updatedProizvodjac]
  (def existingProizvodjac (get-proizvodjac proizvodjacId))
  (def existingProizvodjacByNaziv (get-proizvodjac-by-naziv (get updatedProizvodjac :naziv)))
  (if existingProizvodjac 
    (if (and existingProizvodjacByNaziv (not= (parse-int proizvodjacId) (get existingProizvodjacByNaziv :proizvodjacId))) 
      "Proizvodjac sa tim imenom vec postoji"
      (update proizvodjac
        (set-fields {
          :naziv (get updatedProizvodjac :naziv)
          :zemlja (get updatedProizvodjac :zemlja)
        })
        (where {:proizvodjacId [= proizvodjacId]}))
    )
    "Ne postoji proizvodjac sa tim ID-em"
  )
)
          
(defn delete-proizvodjac [proizvodjacId]
  (def existingProizvodjac (get-proizvodjac proizvodjacId))
  (if existingProizvodjac 
    (delete proizvodjac
      (where {:proizvodjacId [= proizvodjacId]}))
    "Ne postoji proizvodjac sa tim ID-em"
  )
)
