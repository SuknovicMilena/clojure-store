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
                    :description "API for a college project made with clojure"}
             :tags [{:name "users", :description "users api."}]}}}

    ; Users API
    (context "/users" []
      :tags ["users"]

      (GET "/" []
        :return [Korisnik]
        :summary "Gets all users"
        (ok (get-users)))

      (GET "/:id" []
        :path-params [id :- s/Any]
        :summary "Get user by ID specified"
        (def userFromDb (get-user id))
        (if userFromDb (ok userFromDb) (not-found))
      )

      (POST "/" []
        :summary "Creates a new user"
        :body [newKorisnik NewKorisnik]
        (def createResult (add-user newKorisnik))
        (if (= (type createResult) java.lang.String) 
          (bad-request createResult)
          (ok createResult) 
        )
      )

      (PUT "/:id" []
        :summary "Updates existing user"
        :path-params [id :- s/Any]
        :body [updatedKorisnik NewKorisnik]
        (def updateResult (update-user id updatedKorisnik))
        (if (= (type updateResult) java.lang.Integer) 
          (ok nil) 
          (bad-request updateResult)
        )
      )

      (DELETE "/:id" []
        :summary "Deletes existing user"
        :path-params [id :- s/Any]
        (def deleteResult (delete-user id))
        (if (= (type deleteResult) java.lang.String) 
          (bad-request deleteResult)
          (ok nil) 
        )
      )
    )
  )
)