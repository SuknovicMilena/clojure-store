(ns store.api
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]
            [domain.korisnik :refer :all]
            [repository.korisnik :refer :all]))

(def app
  (api
    {:swagger
     {:ui "/"
      :spec "/swagger.json"
      :data {:info {:title "GlassesStore API"
                    :description "API prodavnice naocara"}
             :tags [{:name "korisnici", :description "Korisnik API."}]}}}

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
  )
)