**Nom/Prénom Etudiant 1 : ROMANET Adrien**

**Nom/Prénom Etudiant 2 : TOURNIER Quentin**

# Rapport TP4

## Question 1
*Exprimer les données de test*

-La classe Segment pourrait entièrement être à part
-fonction logoinit qui affiche "avancer 45" est hardcodé et ne sera pas changé automatiquement si on modifie la valeur
-addmenuitem on l'appelle avec le paramètre -1
-proc1 proc2 proc3 ni pratique ni simple à comprendre
-fonction carre, poly et spiral pas très explicatives de ce qu'elles font
-nom de de variables très peu parlantes par moment (b21, p2...)
-une très longue suite de else if
-les dimensions et positions initaiales hardcodés dans les fonctions, cela posera problème si l'on veut changer ces nombres plus tard
-Aucun modèle d'architecture n'est suivi ce qui rend plus difficile de se repérer

Des éléments de la vue sont imbriqués avec des éléments du contrôleur



## Question 2
*Rien à rédiger*

## Question 3
*Rien à rédiger*

## Question 4
*Rien à rédiger*

## Question 5
On a chosit de faire l'ensemble de tortues qui se deplacent aléatoirement.

On créé un ensemble de tortues dans la partie modèle et que l'on controle depuis la partie contrôleur dans le fichier TortueMove
où l'on peut trouver les fonctions qui décident de leurs mouvements.



## Explications et partie bonus :

Pour le comportement de flocking des tortues, nos tortues se déplacent à la vitesse moyenne de l'ensemble des tortues dans son champ de vision
(c'est à dire à moins d'une certaine distance) et part dans la direction moyenne des autres tortues Tout en évitant de rentrer dans 
une autre tortue(c'est à dire refusera une position si une tortue s'y trouve déjà).
 Elles ont également l'interdiction de sortir de la carte
mise en place.

Après avoir mis en place ce comportement nous avons décidé d'ajouter des obstacles dans notre application que les tortues devront éviter.
Ces obstacles, soit des rectangles ou des carrés peuvent être placer de différentes façon :
soit un nombre déterminé d'obstacles choisis et placés de manière aléatoire soit l'utilisateur peut préférer une des dispositions
existantes que nous avons mis en place (comme le labyrinthe ou le mickey).

