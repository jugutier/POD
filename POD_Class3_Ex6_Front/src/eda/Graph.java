package eda;

import java.io.Serializable; 
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Graph implements Serializable
{
    private HashMap<Node, Set<Node>> adjList;
    private String country;
    
   public Graph(String aCountry)
   {
	   adjList= new HashMap<>();
	   country= aCountry;
   }
   
   synchronized public void addNode(Node aNode)
   {
	   Set<Node> n = adjList.get(aNode);
	   if ( n == null)
		   adjList.put(aNode, new HashSet<Node>());
	   // else ignore   
   }
   
   synchronized public void addNode(String label)
   {
	   Set<Node> n = adjList.get(new Node(label));
	   if ( n == null)
		   adjList.put(new Node(label), new HashSet<Node>());
	   // else ignore
   }
   
   synchronized public void addEdge(String source, String target)
   {
	   Set<Node> n = adjList.get(new Node(source));
	   if ( n == null)
	   {
		   n = new HashSet<Node>();
	   }
	   n.add(new Node(target));
	   adjList.put(new Node(source), n);
   }
   
   public String toString() 
   {
	   StringBuilder auxi= new StringBuilder();

	   auxi.append(String.format("%s Graph: ", country));
	   for (Node p1 : adjList.keySet()) 
	   {
		   auxi.append(String.format("From %s ", p1) );
		   auxi.append(String.format("To %s\n", adjList.get(p1)));
	   }
	   
	   return auxi.toString();
   }
   
   public int inDegree(String label)
   {
	   int nro= 0;
	   for (Node p1 : adjList.keySet()) 
	   {
		   nro+= adjList.get(p1).contains(new Node(label))? 1 : 0;
	   }
   
	   return nro;
}
   
   public int outDegree(String label)
   {
	   Set<Node> s = adjList.get(new Node(label));
	   return s == null? 0 : s.size();
   }
   
//   public static void main(String[] args) 
//   {
//	   Graph myG = new Graph("AR");
//	   myG.addNode("A");
//	   myG.addEdge("C", "D");
//	   myG.addEdge("D", "E");
//	   myG.addEdge("E", "D");
//	   myG.addEdge("C", "A");
//	   myG.addEdge("A", "A");
//	   myG.addEdge("C", "D");
//	   myG.addEdge("A", "A");
//	   myG.addNode("C");
//	   myG.addNode("C");
//	   myG.addNode("F");  //suelto
//	   System.out.println(myG);
//	   
//	   String letras= "ABCDEFGH";
//	   for (int i = 0; i < letras.length(); i++) 
//	   {
//		   String label= letras.substring(i, i+1);
//		   System.out.println(String.format("InDegree(%s) is %d", label, myG.inDegree(label)));
//		   System.out.println(String.format("OutDegree(%s) is %d", label, myG.outDegree(label)));
//	   }
//	}
   

}
