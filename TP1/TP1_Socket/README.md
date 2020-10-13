### **- Côté server on a :**
- La création d'un socket sur le port 5555
- Une boucle infinie qui : 
    - Ecoute les requêtes qui lui sont destinées
    - Récupère ce qui a été envoyé dans la requête
    - Ecrit dans un nouveau fichier `transfered_test.txt` les données qu'il a récupéré du fichier récupéré à la base


### **- Coté client on a :**
- La création d'un objet `File` avec les données du fichier `test.txt`
- La création d'un socket sur le port 5555
- On créé un buffer d'entrée contenant le fichier créé précédemment
- On créé un buffer de sortie qui va contenir le fichier à envoyer 
- On lit les données du buffer d'entrée et les envoie  
