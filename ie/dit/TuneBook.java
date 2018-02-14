package ie.dit;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.Integer;

public class TuneBook
{
	ArrayList<Tune> tunes = new ArrayList<Tune>();

	public TuneBook(String file)
	{
		loadTuneBook(file);
	}

	public void loadTuneBook(String file)
	{
		BufferedReader inputStream = null;
		try {
    		inputStream = new BufferedReader(new FileReader(file));
    
   			String l;
   			String title, altTitle; 
   			StringBuffer notation = new StringBuffer();
   			int x;
   			int count = 1;
   	 		while ((l = inputStream.readLine()) != null) 
    		{
    			Tune t = new Tune();
    			while(l != "\n" && l != null)
    			{
	        		if(l.startsWith("X:"))
	        		{
	        			x = Integer.parseInt(l.substring(2), 10);
	        			t.setX(x);
	        		}
	        		else if(l.startsWith("T:") && count == 1)
	        		{
	        			title = l.substring(2);
	        			count++;
	        			t.setTitle(title);
	        		}
	        		else if(l.startsWith("T:") && count == 2)
	        		{
	        			altTitle = l.substring(2);
	        			t.setAltTitle(altTitle);
	        		}
	        		else
	        		{
	        			notation.append(l);
	        			
        			}
        			l = inputStream.readLine();
        		}
        		//t = new Tune(x, title, altTitle, notation.toString());
        		count = 1;
        		t.setNotation(notation.toString());
        		tunes.add(t);
    		}
		}
		catch (IOException e)
		{
    		e.printStackTrace();
		} 
		finally 
		{	
    		if (inputStream != null) {
        		try
        		{
            		inputStream.close();
        		}
        		catch(Exception e)
        		{
            		e.printStackTrace();
        		}
    		}
		}
	}
	

	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		for(Tune t : tunes)
		{
			sb.append(t.toString() + "\n");
		}
		return sb.toString();
	}

	public Tune findTune(String title)
	{
		for(Tune t:tunes)
		{
			if((t.getTitle()).equals(title)||(t.getAltTitle()).equals(title))
			{
				return t;
			}
		}
		System.out.println("No tune found");
		return null;
	}

	public static void main(String[] args)
    {
        TuneBook tb = new TuneBook("hnj0.abc");
        System.out.println(tb);

        Tune t = tb.findTune("Scotsman over the Border");
        if(t != null)
        	t.play();
    }
}