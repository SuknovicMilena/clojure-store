# ClojureStore


ClojureStore by Milena Suknovic (index no. 2017/3705)

---

ClojureStore is a [Leiningen][1] project written in Clojure that provides a REST API for an imaginary optical shop, dealing with users and products. It uses [composure-api][2] as a REST API framework, [korma][3] to communicate with MySQL, [ring][4] as a web server that understands json requests/responses ([ring/json][5]), and [lein/ring][6] profile to bind it together with Leiningen. The API uses [Swagger][7] as a means of API testing and documenting.


## Prerequisites to run and use

You will need Clojure 1.8.0 with Leiningen 2.8.1 on Java 1.8.0 or above installed. Tip: If you haven't worked with Leiningen before follow [this overview][8] to get a sense of what Leiningen is and how it works.

The app is developed in the Windows OS and ecosystem, so a few steps need to be taken in order to enable the development.

Run the following on Windows to install [Chocolatey package manager][9]:

    Set-ExecutionPolicy Bypass -Scope Process -Force; iex ((New-Object System.Net.WebClient).DownloadString('https://chocolatey.org/install.ps1'))


Try running `choco install lein` and afterwards `lein self-install`. If any of those don't work just download the latest release of Leiningen from [their github][10]. Change the extension to .jar and place in `~/.lein/self-installs` folder and you should be able to run `lein -v` and `lein repl` afterwards if it all works.

If you're using Visual Studio Code as your IDE of choice (and that's a recommendation) make sure you install Clojure and Clojure Code plugins.

### Database

On the root of the repo, in the db/ folder you'll find an .sql file that you will have to use to restore the MySQL database. MySQL Community bundle with the actual db server and Workbench can be downloaded from the [official website][11].


## Running

Run `lein ring server` in the root of the project to run the actual app. This will start a web server and open the home page of Swagger API documentation.

## Project architecture

`/db` holds latest database restore.

`/src/domain` holds the domain entities that provide an image of the DB tables.

`/src/mysql` contains code to provide a connection to the MySQL server

`/src/repository` abstracts the individual entities SQL related logic - data layer

`/src/store` contains the api.clj file that provides the app logic - API routes. This layer responds to HTTP requests and calls the data layer to eventually return HTTP responses with the results gotten from data layer.

`/src/utility` contains helpers

## Libraries with versions

`[metosin/compojure-api "2.0.0-alpha19"]` - Library on top of Composure that brings the functionalities for development of REST APIs

`[korma "0.4.3"]` - SQL helper library for Clojure 

`[ring/ring-core "1.6.3"] ,[ring/ring-jetty-adapter "1.6.3"], [ring/ring-json "0.4.0"], [ring/ring-defaults "0.3.1"]` - Ring web server dependent libraries - HTTP server abstraction

`[mysql/mysql-connector-java "5.1.6"]` - Standard MySQL connector for java

`[clj-http "2.0.0"]` - Standard set of HTTP utilities for Clojure - used as a lower-level dependency of composure and swagger.

## Plugins with versions

`[lein-ring "0.12.4"]` - Ring server plugin for leiningen

`[compojure "1.6.1"]` - Composure - concise routing library for Ring/Clojure

## License

Copyright © 2018 Milena Suknovic. 

Issued as-is with MIT Licence. For more details check out LICENCE file on the root of the repository.

[1]: https://github.com/technomancy/leiningen
[2]: https://github.com/metosin/compojure-api
[3]: https://github.com/korma/Korma
[4]: https://github.com/ring-clojure/ring
[5]: https://github.com/ring-clojure/ring-json
[6]: https://github.com/weavejester/lein-ring
[7]: https://swagger.io/
[8]: https://github.com/technomancy/leiningen/blob/stable/doc/TUTORIAL.md
[9]: https://chocolatey.org/
[10]: https://github.com/technomancy/leiningen/releases
[11]: https://dev.mysql.com/downloads/windows/installer/5.7.html