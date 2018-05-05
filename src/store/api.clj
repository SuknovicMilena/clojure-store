(ns store.api
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]
            [domain.korisnik :refer :all]
            [domain.proizvodjac :refer :all]
            [domain.proizvod :refer :all]
            [repository.korisnik :refer :all]
            [repository.proizvodjac :refer :all]
            [repository.proizvod :refer :all]
  )
)

(def app
  (api 
    ; Swagger documentation details
    {
      :swagger {
        :ui "/"
        :spec "/swagger.json"
        :data {
          :info { :title "GlassesStore API" :description "API prodavnice naocara"}
          :tags [
            {:name "korisnici", :description "Korisnik API endpoints."}
            {:name "proizvodjaci", :description "Proizvodjac API endpoints."}
            {:name "proizvodi", :description "Proizvod API endpoints."}
          ]
        }
      }
    }

    ; Korisnik API
    (context "/korisnici" []
      :tags ["korisnici"]

      (GET "/" []
        :return [Korisnik]
        :summary "Vrati sve korisnike"
        (ok (get-korisnici)))

      (GET "/:id" []
        :path-params [id :- s/Any]
        :summary "Vrati korisnika po ID-u"
        (def korisnikFromDb (get-korisnik id))
        (if korisnikFromDb (ok korisnikFromDb) (not-found))
      )

      (POST "/" []
        :summary "Kreiraj novog korisnika"
        :body [newKorisnik NewKorisnik]
        (def createResult (add-korisnik newKorisnik))
        (if (= (type createResult) java.lang.String) 
          (bad-request createResult)
          (ok createResult) 
        )
      )

      (PUT "/:id" []
        :summary "Azuriraj postojeceg korisnika"
        :path-params [id :- s/Any]
        :body [updatedKorisnik NewKorisnik]
        (def updateResult (update-korisnik id updatedKorisnik))
        (if (= (type updateResult) java.lang.Integer) 
          (ok nil) 
          (bad-request updateResult)
        )
      )

      (DELETE "/:id" []
        :summary "Obrisi korisnika"
        :path-params [id :- s/Any]
        (def deleteResult (delete-korisnik id))
        (if (= (type deleteResult) java.lang.String) 
          (bad-request deleteResult)
          (ok nil) 
        )
      )
    )

    
    ; Proizvodjac API
    (context "/proizvodjaci" []
      :tags ["proizvodjaci"]

      (GET "/" []
        :return [Proizvodjac]
        :summary "Vrati sve proizvodjace"
        (ok (get-proizvodjaci)))

      (GET "/:id" []
        :path-params [id :- s/Any]
        :summary "Vrati proizvodjaca po ID-u"
        (def proizvodjacFromDb (get-proizvodjac id))
        (if proizvodjacFromDb (ok proizvodjacFromDb) (not-found))
      )

      (POST "/" []
        :summary "Kreiraj novog proizvodjaca"
        :body [newProizvodjac NewProizvodjac]
        (def createResult (add-proizvodjac newProizvodjac))
        (if (= (type createResult) java.lang.String) 
          (bad-request createResult)
          (ok createResult) 
        )
      )

      (PUT "/:id" []
        :summary "Azuriraj postojeceg proizvodjaca"
        :path-params [id :- s/Any]
        :body [updatedProizvodjac NewProizvodjac]
        (def updateResult (update-proizvodjac id updatedProizvodjac))
        (if (= (type updateResult) java.lang.Integer) 
          (ok nil) 
          (bad-request updateResult)
        )
      )

      (DELETE "/:id" []
        :summary "Obrisi proizvodjaca"
        :path-params [id :- s/Any]
        (def deleteResult (delete-proizvodjac id))
        (if (= (type deleteResult) java.lang.String) 
          (bad-request deleteResult)
          (ok nil) 
        )
      )
    )


    ; Proizvod API
    (context "/proizvodi" []
      :tags ["proizvodi"]

      (GET "/" []
        :return [Proizvod]
        :summary "Vrati sve proizvode"
        (ok (get-proizvodi)))

      (GET "/:id" []
        :path-params [id :- s/Any]
        :summary "Vrati proizvod po ID-u"
        (def proizvodFromDb (get-proizvod id))
        (if proizvodFromDb (ok proizvodFromDb) (not-found))
      )

      (POST "/" []
        :summary "Kreiraj novi proizvod"
        :body [newProizvod NewProizvod]
        (def createResult (add-proizvod newProizvod))
        (if (= (type createResult) java.lang.String) 
          (bad-request createResult)
          (ok createResult) 
        )
      )

      (PUT "/:id" []
        :summary "Azuriraj postojeci proizvod"
        :path-params [id :- s/Any]
        :body [updatedProizvod NewProizvod]
        (def updateResult (update-proizvod id updatedProizvod))
        (if (= (type updateResult) java.lang.Integer) 
          (ok nil) 
          (bad-request updateResult)
        )
      )

      (DELETE "/:id" []
        :summary "Obrisi proizvod"
        :path-params [id :- s/Any]
        (def deleteResult (delete-proizvod id))
        (if (= (type deleteResult) java.lang.String) 
          (bad-request deleteResult)
          (ok nil) 
        )
      )
    )
  )
)