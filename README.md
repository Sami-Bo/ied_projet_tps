# Médiateur Cinéma – Projet IED M1 IISC

## Présentation
Ce projet vise à concevoir un médiateur simple exploitant différentes sources de données sur le cinéma.  
Il permet de centraliser et interroger les informations provenant de plusieurs bases pour obtenir rapidement des détails sur les films et les acteurs.  

![Diagramme du médiateur](images/mediateur.png)

## Sources de données
1. **Base locale** : informations sur les films (titre, date de sortie, genre, distributeur, budget, revenus aux États-Unis et mondiaux).  
   - Données issues de `movieBudgets.csv` et complétées par extraction de pages HTML via Java + JSoup (pour le genre et le distributeur).  
   - Transformation et chargement via **Apache Hop**.
2. **DBpedia** : réalisateur, acteurs et producteurs, accessibles via SPARQL (`http://dbpedia.org/sparql/`) et l’API Jena.
3. **Open Movie Database (OMDb)** : résumé des films via API REST (`http://www.omdbapi.com/`), résultats en XML traités avec DOM + XPath.

## Modèle de données
- Une seule relation **Film** regroupant tous les attributs :  
  `titre`, `date de sortie`, `genre`, `distributeur`, `budget`, `revenus US`, `revenus mondiaux`, `réalisateur`, `acteur`, `producteur`, `résumé`.  
- Les informations sont fusionnées à partir des trois sources via le **titre du film**.

## Fonctionnalités du médiateur
1. **Requête par titre de film** : afficher toutes les informations disponibles (date, genre, distributeur, budget, revenus US et mondiaux, réalisateur, résumé, acteurs).  
2. **Requête par acteur** : afficher la liste des films joués par cet acteur, avec titre, date de sortie, genre, distributeur, réalisateur et producteur.

## Technologies utilisées
- **Java** avec interface textuelle simple  
- **JDBC** pour la base locale  
- **API Jena** pour SPARQL DBpedia  
- **REST + XML + XPath** pour OMDb  
- **JSoup** pour extraction HTML  
- **Apache Hop** pour ETL et transformation des données

## Points importants
- Gestion des informations incomplètes ou ambiguës (films avec le même titre, données manquantes)  
- Fusion des informations provenant de plusieurs sources

## Adaptation pour projet individuel
- Seul `movieBudgets.csv` est utilisé pour remplir la base locale  
- Le genre et le distributeur ne seront pas renseignés
