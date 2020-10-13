Server 
--
On créé dans un premier temps un nouveau socket sur le port 5555.  
  
Ensuite à chaque fois qu'une personne se connecte au socket on va créer une nouvelle instance de la classe `ServerClass` et la lancer dans un nouveau `Thread`.  
  
La méthode `run` sera ensuite exécuté à chaque instance de la classe `ServerClass`
pour effectuer l'addition. 