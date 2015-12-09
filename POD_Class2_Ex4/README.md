From root folder
untar doc/binarios.tar
export CLASSPATH=. 
rmic -keep uals.ServiceImpl

CLASSPATH is where util folder is.


The output is ServiceImple_Stub.java