# k-mean
-src code have 5 packages 
-package Data : file wine-clustering.csv 
-package Model : class Point ( mean data Point ) , class Clust , every class have get/set function and some relate calculating functions .
-package Controller  : class DAO ( read data from file csv , return a list of Point )
-package View : class Kmeans ( it should be in package Model ) have function Clustering and some relate functions ( RandomPoint , InitKmeanPlus , CompareClust ) 
. Class RunView out the result of Clustering function on the console window .
- package Bisecting : class Bisecting - it use Clustering function of Class Kmeans , compare SSE and out the best result . 
