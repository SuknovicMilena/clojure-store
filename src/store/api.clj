(ns store.api
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]
            [store.domain :refer :all]
            [store.query :refer :all]))

(def app
  (api
    {:swagger
     {:ui "/"
      :spec "/swagger.json"
      :data {:info {:title "GlassesStore API"
                    :description "API for a college project made with clojure"}
             :tags [{:name "users", :description "users api."}]}}}

    (context "/users" []
      :tags ["users"]

      (GET "/" []
        :return [Korisnik]
        :summary "Gets all users"
        (ok (get-users)))

      (POST "/" []
        :summary "Creates a new user"
        :body [newKorisnik NewKorisnik]
        (ok (add-user newKorisnik)))

    )
  )
)