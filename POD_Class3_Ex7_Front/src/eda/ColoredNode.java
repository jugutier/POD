package eda;

public class ColoredNode extends Node
{
	private String color;
	
	public ColoredNode(String aValue, String aColor)
	{
		super(aValue);
		color= aValue.toUpperCase().trim();
	}
	
	public String toString()
	{
		return String.format("%s with Color %s", super.toString(), color);
	}
	
	public boolean equals(Object obj)
	{
		if (! ( obj instanceof ColoredNode || obj == null) )
			return false;
		
		return super.equals(obj) && ((ColoredNode)obj).color.equals(color);
	}

	public int hashCode()
	{
		return super.hashCode() + color.hashCode();
	}

}
