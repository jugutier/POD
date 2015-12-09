package back;

public final class Movie 
{
	private final String name;
	private final String genre;
	
	public Movie(String aName, String aGenre)
	{
    	name= aName;
    	genre= aGenre;
	}

	public String getName() 
	{
		return name;
	}
	
	public String getGenre() 
	{
		return genre;
	}

	public int hashCode() 
	{
		return name.hashCode();
	}

	public boolean equals(Object obj) 
	{
		if (! (obj instanceof Movie) || obj == null)
			return false;
		if (this == obj)
			return true;
		
		Movie auxi= (Movie) obj;	
		return auxi.name.equals(name);
    }
	
	public String toString() 
	{
		return name+" "+genre;
	}
}
