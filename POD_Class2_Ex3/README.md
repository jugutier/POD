Make sure your rmiregistry port matches the one used in Client.java (1099)


You'll need 3 terminals, all of this will ONLY work if `pwd` is the project's root.

1)===rmiregistry's terminal 

export CLASSPATH=bin/

rmiregistry -J-Djava.rmi.server.logCalls=true


2)==server==
export CLASSPATH=bin/
java server.Server

3)==client==
export CLASSPATH=bin/
java client.Client