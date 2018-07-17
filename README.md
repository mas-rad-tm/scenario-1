# Scénario 1
Implémentation d'une architecture respectant les principes de l' "architecture hexagonale"

## Variante n° 1
### Prérequis
* Maven version 3.5.0
* JDK version 1.8

### Exécution
* Récupération du repository
* Se rendre dans le dossier `/rentes-services`
* lancer la commande `mvn clean install`
* Se rendre dans le dossier `/rentes-service-application`
* lancer l'application avec la commande `mvn spring-boot:run`

L'application est maintenant disponible via l'url suivante: `http://localhost:9020/rentes-service`. 

#### Parmétrage du port
> mvn -DPORT=1234 spring-boot:run 

#### Utilisation d'une base de données physique
Par défaut c'est une base de donnée 'H2' embarqué qui est utilisée. Il est possible d'utiliser une base de données physique. 
Un profil spring `db2` est paramétré pour l'utilisation d'une base de données db2. 

* Ouvrir le fichier `/rentes-service/rentes-service-application/src/main/resources/config/application-db2.yml`
* Adapter les paramètres en fonction de la base de données voulues
⋅⋅⋅Le projet importe uniquement le pilote jdbc pour db2
* Changer le profile dans le fichier `/rentes-service/rentes-service-application/src/main/resources/config/application.yml`
⋅⋅* changer 'h2' et remplacer par `db2` pour la clé `spring.profiles.active`
* Démarrer l'application normalement

#### Préequis

## Configuration centralisée

## Build du projet
###1. Avec maven, génération d'une archive web (war) et des sources
```
mvn clean install
```

### 2. Avec maven, génération d'une archive jar avec tomcat embarqué
```
mvn clean install -Pjar
```

## Démarrage du projet
### 1. Intelij, mode IDE
```
1.1 Cocher le profil Intelij dans la configuration maven
1.2 Démarrage habituel (clic-droit sur la classe à démarrer, idem pour debug)
```

### 2. Mode embarqué spring boot
```
cd spring-backend-application

mvn spring-boot:run
```

### 3. Mode tomcat
```
3.1 Configurer dans les paramètres intelij le serveur applicatif
3.2 Configurer le déploiemenent:
3.2.1 L'archive à déployer
3.2.2 le contexte
```
