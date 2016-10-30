# UDP_JAVA

Developed an overlay based solution that allows nodes to search for contents among each other through UDP.
Implementation was done in Java and was tested in PlanetLab overlay network.


*In UDP_JAVA
there are UDP file Rcmd(client) and Rcmdd(server)


*Compiling and testing
 For compiling
->javac filename.java
filename is Rcmd or Rcmdd

*For Running

->Running a Server(Rcmdd)
After entering this line "java Rcmdd portnumber" press enter and run the clients.
Note:Dont forget to give the portnumber.


->Running a client (Rcmd)
After entering this "java Rcmd hostname portnumber exec_count delay command" press enter
and put command in "" example:  java Rcmd localhost 5000 4 2000 "ls"


