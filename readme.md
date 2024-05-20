
# Objectifs du projet

Vous devez réaliser un projet qui consiste à créer une plateforme à l'image de Pinterest

# Les entités

Pour gérer ce projet vous aurez besoin de quelques entités ...

## User

Un **User** sera capable de s'identifier sur la plateforme et pourra créer son profil (**User**) et renseigner les informations sur son profil

### Rôles

Un **User** pourra avoir un ou des rôles parmi les suivants : ADMIN, MODO, USER

Notez que le rôle ADMIN autorise toutes les opérations de CRUD sur toutes les entités

Le rôle MODO autorisera les opérations READ / UPDATE sur toutes les entités

Un USER pourra uniquement consulter les informations et éditer les données liées à son compte.

## File

Il s'agit simplement d'un fichier image identifiée par un simple nom de fichier, ce nom de fichier sera utilisé en conjonction avec des chemins stockés dans les fichiers de configuration pour composer le chemin interne (path) et le chemin externe (url)

## Post

Un post est une publication d'un **User**, il contiendra un (des?) **File** et pourra être placé dans un **Album** spécifique

## Album

Il s'agit d'une simple entité permettant de regrouper les **File** sous un même thème

## Comment

Il s'agit d'un commentaire qu'un **User** peut faire sur un **Post**

## Tag

Pour chaque **Post**, on pourra associer des tags permettant de qualifier le contenu

# Ce qui est attendu

## Backend

Vous devez produire une plateforme permettant de ...
- Créer
- Voir de manière unitaire ou en liste
- Modifier
- Supprimer

... des **Users**, **Posts**, **Album**, **Comments** et des **Tags**

Ces pages devront être ergonomiquement respectables, présenter toutes les informations utiles et donner la possibilité de modifier les **File** au travers du formulaire des **Posts**

Pour les fichiers, un formulaire d'upload est attendu.

## Api

Vous devrez produire une API REST répondant aux requêtes suivantes :
- GET, PUT, PATCH et DELETE sur /api/user/{id}
- GET, POST sur /api/users
- GET, PUT, PATCH et DELETE sur /api/post/{id}
- GET, POST sur /api/posts
- GET, PUT, PATCH et DELETE sur /api/comment/{id}
- GET, POST sur /api/comments
- GET, PUT, PATCH et DELETE sur /api/tag/{id}
- GET, POST sur /api/tags

Les requêtes seront faites avec un corps en JSON et la réponse pourra être alternativement du JSON ou du XML selon l'encodage demandé lors de l'appel

Les réponses devront retourner les codes d'erreur appropriés accompagnés de messages lorsque le problème n'est pas trivial

### Tests
Vous devrez implémenter une série de tests pour l'API que vous aurez produit, au minimum un test valide et invalide pour chaque point d'entrée

Le format des tests est à votre discrétion (curl, Postman, ...) mais vous devrez les jouer pendant la démonstration

### Documentation

Votre API peut être documentée sur le modèle suivant. Vous pouvez alternativement utiliser Swagger si vous le souhaitez.

Vous devrez impérativement spécifier tous les codes d'état que l'on peut obtenir sur chaque point d'entrée, ainsi que l'encodage attendu s'il y a un body.

#### Get un user

```http
  GET /api/user/{id}
```

| Parameter | Type     | Description                                  |
| :-------- | :------- |:---------------------------------------------|
|   `id`    | `Long`   | **Required**. L'identifiant de l'utilisateur |


# Livraison

La livraison se fera **impérativement** sur un Github Classroom qui vous sera communiqué, la date de rendu sera précisée dans l'assignment que vous rejoindrez, aucune livraison ne sera acceptée par mail ou autre

La livraison contiendra :
- Le code de votre projet incluant le backend et l'API
- Les tests d'API que vous aurez réalisé
- Une vidéo de démonstration qui présentera :
    - Le backend et ses fonctionnalités sous différents rôles
    - L'API au travers des tests que vous aurez réalisé
- Un readme clair qui expliquera ce que vous avez fait, ce que vous n'avez pas pu faire et qui donnera des précisions sur le projet (versions des outils utilisées) ainsi que les manipulations à effectuer afin que je puisse faire tourner votre projet sans problème

# Notation

Vous partez d'un capital de 20 points et vous pourrez en perdre pour plusieurs raison :
- Le code est de mauvaise qualité (jusqu'à -5 points)
- L'ergonomie est franchement mauvaise / le rendu est franchement mauvais (jusqu'à -3 points)
- Vous n'avez pas fourni de readme avec votre projet (-5 points)
- Vous n'avez pas fourni un des éléments demandés dans la section du dessus (-1 à -5 points par élément)
- Plagiat (-10 à -25 points), vous avez le droit de prendre du code en ligne tant que vous le comprenez mais ne prenez pas le code d'un camarade, la sentence vaut pour les deux groupes

Si vous réalisez des fonctionnalités non demandées, je rajoute des bonus, par exemple :
- Un formulaire d'upload drag & drop en ajax avec preview
- ...
