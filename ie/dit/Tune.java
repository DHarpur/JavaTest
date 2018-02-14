package ie.dit;

public class Tune implements Player
{
	private int x;
	private String title = null;
	private String altTitle = null;
	private String notation = null;

	public Tune(int x, String title, String altTitle, String notation)
	{
		this.x = x;
		this.title = title;
		this.altTitle = altTitle;
		this.notation = notation;
	}

	public Tune()
	{
		this.x = 0;
		this.title = null;
		this.notation = null;
		this.altTitle = null;
	}

	public void setX(int x)
	{
		this.x = x;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public void setAltTitle(String altTitle)
	{
		this.altTitle = altTitle;
	}
	public void setNotation(String notation)
	{
		this.notation = notation;
	}
	
	public String getTitle()
	{
		return this.title;
	}
	public String getAltTitle()
	{
		return this.altTitle;
	}
	public String getNotation()
	{
		return this.notation;
	}

	public String toString()
	{
		if(altTitle == null)
		{
			return "" + x + ", " + title;
		}
		else
		{
			return "" + x + ", " + title + ", " + altTitle;
		}
	}

	public void play()
	{
		System.out.println(getNotation() + "\n");
	}
}