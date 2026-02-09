# SAE31_2024

Étudiants :

    BENBRAHIM Yassin
    GHOUAR-TOUSSAINT Rafael
    ODDOS-MARCEL Gaël

## Contexte du Projet

Ce projet est réalisé dans le cadre de notre deuxième année de BUT Informatique, dans le but de concevoir un jeu en Java inspiré de Dorfromantik, où le joueur doit assembler des tuiles hexagonales pour former un paysage et maximiser son score.

## Description du Projet

Dans ce jeu de placement de tuiles hexagonales, le joueur crée un paysage harmonieux en plaçant des tuiles qui représentent cinq types de terrains : mer, champ, pré, forêt et montagne. Une partie se termine après le placement de 50 tuiles, et les points sont calculés selon les règles suivantes :

    Connexion de terrains : chaque tuile connectée à une autre du même terrain forme une "poche" de terrain. Plus la poche est grande, plus elle rapporte de points.
    Serveur de score : le jeu utilise un serveur pour stocker les séries de tuiles et les scores des joueurs de manière anonyme, permettant de comparer les scores pour chaque série de tuiles.

## Fonctionnalités
Fonctionnalités Principales

    Placement de tuiles : chaque tour, le joueur place une tuile en choisissant son orientation et son emplacement, de façon à connecter les terrains.
    Calcul des points : les poches de terrains connectées rapportent des points en fonction de leur taille.
    Comparaison des scores : en fin de partie, le score du joueur est comparé aux autres scores sur la même série de tuiles, via un serveur de base de données.

Règles du Jeu

    Début de Partie : La première tuile est posée automatiquement sur la grille.
    Déroulement des Tours : À chaque tour, une tuile aléatoire est proposée, et le joueur choisit où la poser.
    Fin de Partie : La partie se termine après avoir posé 50 tuiles, avec affichage du score final.
    Scoring : Les points sont attribués pour chaque poche de terrains connectés, calculés en fonction du carré de la taille de chaque poche.

## Structure du Projet

Le projet est organisé en plusieurs classes au sein d’un package unique. Voici un aperçu des classes principales :

    Tuile : représente une tuile hexagonale et les terrains qu'elle contient.
    Terrain : définit les types de terrains possibles (mer, champ, pré, forêt, montagne).
    Grille : gère la grille hexagonale et les règles de connexion entre les tuiles.
    Score : calcule le score en fonction des poches de terrains connectés.
    Serveur : permet de stocker et récupérer les séries de tuiles et les scores.
    Jeu : gère le déroulement d’une partie et les interactions avec le joueur.


Un rapport d’avancement est fourni en format PDF dans le dépôt Gitea, contenant les éléments suivants :

    Introduction : Présentation générale du projet et des objectifs
    Fonctionnalités : Description des fonctionnalités du jeu, accompagnée de captures d’écran
    Structure du Programme : Diagramme de classes pour illustrer l’architecture du code
    Algorithme des Poches de Terrains : Analyse avec un diagramme d’activité
    Améliorations ergonomiques : Explication des choix d’interface pour une meilleure expérience utilisateur
    Évaluation des scores : Méthode de comparaison des scores des joueurs via le serveur
    Conclusions personnelles : Retours des membres de l’équipe sur le projet