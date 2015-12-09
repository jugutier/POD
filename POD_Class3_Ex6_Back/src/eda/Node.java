package eda;

import java.io.Serializable;

public class Node implements Serializable
{
	private String value;
	
	public Node(String aValue)
	{
		value= aValue.toUpperCase().trim();
	}
	
	public String toString()
	{
		return String.format("Node %s", value);
	}
	
	public boolean equals(Object obj)
	{
		if (! ( obj instanceof Node) || obj == null)
			return false;
		
		return ((Node)obj).value.equals(value);
	}

	public int hashCode()
	{
		return value.hashCode();
	}
}