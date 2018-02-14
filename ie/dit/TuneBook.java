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
   			boolean done = false;
   			int xCounter = 1;
   	 		while ((l = inputStream.readLine()) != null) 
    		{
    			Tune t = new Tune();
    			while(l != null && !done)
    			{
	        		if(l.startsWith("X:"))
	        		{
	        			x = Integer.parseInt(l.substring(2), 10);
	        			t.setX(x);
	        			xCounter++;
	        		}
	        		if(l.startsWith("T:") && count == 1)
		        	{
		        		title = l.substring(2);
		        		count++;
		        		t.setTitle(title);
		        	}
	        		if(l.startsWith("T:") && count == 2)
	        		{
	        			altTitle = l.substring(2);
	        			t.setAltTitle(altTitle);
	        		}
	        		else if(xCounter == 2)
	        		{
	        			notation.append(l);	
        			}
        			if(l.startsWith("X:") && xCounter == 2)
        			{
        				done = true;
        				t.setNotation(notation.toString());
        			}
        			l = inputStream.readLine();


        		}
        		//t = new Tune(x, title, altTitle, notation.toString());
        		count = 1;
        		
        		tunes.add(t);
        		done = false;
        		xCounter = 1;
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
       	t.play();
    }
}