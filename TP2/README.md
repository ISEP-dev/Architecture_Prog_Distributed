# Partie 1 (Hello World RMI)
- Du côté du serveur, on va dans un premier temps créer un nouveau serveur `HelloServeur` avec un message associé puis implémenter la méthode `say` qui sera appelée côté client.  
Et ensuite on va lier le service à une adresse
- Du côté client, on va regarder à l'adresse définie dans le serveur (`lookup`), puis appeler la méthode implémentée côté serveur (`say()`)


# Partie 2 (Student Score RMI)
- Au niveau du serveur comme pour la partie 1 on instancie un nouveau serveur qui est lié à l'adresse créée dans `Naming.rebind()`.  
Toutes les méthodes des différentes interfaces sont implémentées dans la classe `Serveur` 

- Pour ce qui est du client on récupère juste l'interface à travers `lookup`, on peut ensuite utiliser toutes les méthodes implémentées du côté serveur dans la partie client.  
On va pouvoir ajouter des étudiants, ajouter des examens aux étudiants ajoutés puis calculer la moyenne d'un étudiant ou la moyenne de la promotion 


# Partie 3 (Matrix Multiplication)
- La partie client récupère juste l'objet calculator à travers la fonction `lookup()`, ensuite on crée 2 matrices, puis on les multiplie.

- Le serveur instancie la classe **_MatrixMultiplication_** qui étend de **UnicastRemoteObject** puis lie l'ojet calculator à l'adresse du serveur pour que le client puisse avoir accès à l'objet permettant de réaliser les calculs de matrices