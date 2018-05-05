(ns repository.proizvod
  (:require [mysql.connection]
            [domain.proizvod :refer :all]
            [domain.proizvodjac :refer :all]
            [repository.proizvodjac :refer :all]
            [utility.helpers :refer :all]
            [korma.core :refer :all]))

(defentity proizvod)

(defn get-proizvodi []
  (select proizvod))
  
(defn get-proizvod [proizvodId]
  (first
    (select proizvod
      (where {:proizvodId [= proizvodId]} )
      (limit 1)
    )
  )
)

(defn get-proizvod-by-naziv [naziv]
  (first
    (select proizvod
      (where {:nazivProizvoda [= naziv]} )
      (limit 1)
    )
  )
)

(defn add-proizvod [newProizvod]
  (def existingProizvod (get-proizvod-by-naziv (get newProizvod :nazivProizvoda)))
  (def existingProizvodjacById (get-proizvodjac (get newProizvod :proizvodjacId)))
  (if existingProizvod 
    "Proizvod sa tim imenom vec postoji"
    (if existingProizvodjacById 
      (if (> (get newProizvod :cena) 0) 
        ((def insertProizvodResult 
          (insert proizvod
            (values {
              :nazivProizvoda (get newProizvod :nazivProizvoda)
              :cena (get newProizvod :cena)
              :boja (get newProizvod :boja)
              :tip (get newProizvod :tip)
              :proizvodjacId (get newProizvod :proizvodjacId)
            })
          ))
          (def insertedProizvodId (get insertProizvodResult :generated_key))
          (get-proizvod insertedProizvodId)
        )
        "Cena mora biti veca od nule"
      )
      "Proizvodjac sa tim ID-em ne postoji"
    )
  )
)

(defn update-proizvod [proizvodId updatedProizvod]
  (def existingProizvod (get-proizvod proizvodId))
  (def existingProizvodByNaziv (get-proizvod-by-naziv (get updatedProizvod :nazivProizvoda)))
  (def existingProizvodjacById (get-proizvodjac (get updatedProizvod :proizvodjacId)))
  (if existingProizvod 
    (if (and existingProizvodByNaziv (not= (parse-int proizvodId) (get existingProizvodByNaziv :proizvodId))) 
      "Proizvod sa tim imenom vec postoji"
      (if existingProizvodjacById 
        (update proizvod
          (set-fields {
            :nazivProizvoda (get updatedProizvod :nazivProizvoda)
            :cena (get updatedProizvod :cena)
            :boja (get updatedProizvod :boja)
            :tip (get updatedProizvod :tip)
            :proizvodjacId (get updatedProizvod :proizvodjacId)
          })
          (where {:proizvodId [= proizvodId]}))
          "Proizvodjac sa tim ID-em ne postoji"
        )
    )
    "Ne postoji proizvod sa tim ID-em"
  )
)
          
(defn delete-proizvod [proizvodId]
  (def existingProizvod (get-proizvod proizvodId))
  (if existingProizvod 
    (delete proizvod
      (where {:proizvodId [= proizvodId]}))
    "Ne postoji proizvod sa tim ID-em"
  )
)
