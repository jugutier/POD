Since this project will receive colored node class during runtime, we add the java.policy saying we are open to get classes through the network AND a securityManager to change the default policy. 


 java -Djava.security.policy=java.policy -Djava.rmi.server.useCodebaseOnly=false publish.Server
 
 rmiregistry -J-Djava.rmi.server.logCalls=true