# Scénario 1
Implémentation d'une architecture respectant les principes de l' "architecture hexagonale"

## Variante n° 1
### Prérequis
* Maven version 3.5.0
* JDK version 1.8
* base de données IBM DB2 (*par défaut, utilisation d'une base de données H2 embarquée*)

### Exécution de l'application
* Récupération du repository
* Se rendre dans le dossier `/rentes-services`
* lancer la commande `mvn clean install`
* Se rendre dans le dossier `/rentes-service-application`
* lancer l'application avec la commande `mvn spring-boot:run`

L'application est maintenant disponible via l'url suivante: `http://localhost:9020/rentes-service`. 

#### Parmétrage du port
Le port par déaut de l'application est le **9020**. Il est possible de paramétrer un autre port d'écoute pour l'application. Il suffit pour cela de passer la varaiable d'environnement PORT à la commande de démarrage de l'application:
> **mvn -DPORT=1234 spring-boot:run** (*remplacez 1234 par le numéro de port désiré*)

#### Utilisation d'une base de données physique
Par défaut c'est une base de donnée `H2` embarquée qui est utilisée. Il est possible d'utiliser une base de données physique. 
Un profil spring `db2` est paramétré pour l'utilisation d'une base de données IBM DB2. 

* Ouvrir le fichier `/rentes-service/rentes-service-application/src/main/resources/config/application-db2.yml`
* Adapter les paramètres en fonction de la base de données voulues
* Ouvrir le fichier `/rentes-service/rentes-service-application/src/main/resources/config/application.yml`
* Modifier `h2`  par `db2` pour la clé `spring.profiles.active`
* Démarrer l'application 

### Fonctionnalités et tests
L'application est une application Spring en mode `embeded`. Au lieu de déployer une archive web (.war) dans un container applicatif JEE, c'est l'application qui va embrquer un container applicatif, dans notre cas il s'agit de Apache Tomcat.

#### API Rest
L'application embarque une documentation des API disponible à cette url:
> {url application}/swagger-ui.html

Cette documentation est basé sur l'outil `Swagger`, fournissant une documentation, mais également une interface permettant de tester les différentes API.

Les API Rest suivantes sont implémentés et peuvent être utilisées pour tester l'application:
> **/dossiers**, méthode http GET, fournit la liste des dossiers

> **/dossiers**, méthode http POST, permet la création d'un dossier

> **/dossiers/{id}**, méthode http GET, fournit le détail d'un dossier 

> **/dossiers/{id}/valider**, méthode http PUT, permet la validation d'un dossier

> **/dossiers/{id}/clore**, méthode http PUT, permet la cloture d'un dossier


