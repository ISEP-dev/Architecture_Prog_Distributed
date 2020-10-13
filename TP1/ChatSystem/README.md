Coté serveur
--
- On commence par créer une liste de clients vide. 
- Ensuite on instancie un nouveau socket sur le port 5555
- Et dans la boucle infinie on instancie un nouveau thread à chaque nouvelle connexion
- À chaque nouvelle instance, dans la méthode `run`, on ajoute un nouveau client dans la listes des clients,  
puis dès qu'un client écrit un nouveau message, on va parcourir la liste de tous les clients pour envoyer le message à chaque client


Coté client
--
- On définit des noms pour les différents clients possibles
- On crée une nouvelle instance de `Mychat` sur un nouveau `Thread`
  - La nouvelle instance de `Mychat` va créer une interface graphique avec : 
    - Un champ de texte pour écrire un message
    - Un bouton pour envoyer un message
    - Une zone d'affichage pour voir les messages envoyés et reçus par les différents utilisateurs  


Ensuite on crée une nouvelle connexion sur le port 5555, pui quand on clique sur le bouton **Envoyer**, on envoie le message écrit dans le champ prévu à cet effet grâce au buffer de sortie
  
Et enfin dans la méthode `run`, dès qu'on reçoit un nouveau message depuis le socket, on l'ajoute dans la zone d'affichage


Utilisation
-- 
Avec intelliJ Vous pouvez instancier la classe `ServerChat`, puis ensuite instancier autant de classe `ClientChat` que vous le souhaitez.