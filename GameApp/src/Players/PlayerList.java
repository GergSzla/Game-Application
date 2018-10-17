package Players;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class PlayerList {
	
ArrayList<Player> players;
Scanner input = new Scanner (System.in);	

    public PlayerList()
    {
    	players = new ArrayList<Player>();
    }
	
	public void add(Player player)
	{
		players.add(player);
	}

	public ArrayList<Player> getPlayers()
	{
		return players;
	}

	/**
	 * listBooks() - This method prints the index number and the title of each 
	 * element in the Books ArrayList to the console.
	 */
	public String listPlayers()
	{
		System.out.println("\n\n============================================================");
    	System.out.println("||                      List Of Players                   ||");
    	System.out.println("============================================================");
    	
		if (players.size() == 0)
		{
			return "No Players Exist Yet, Make a player.";
		}
		else
		{
			System.out.println("No.    Username     Score   Won   Lost");
			String listPlayers = "";
			for (int i = 0; i < players.size(); i++)
			{
				listPlayers = listPlayers + (i + ":     " + players.get(i)) + "\n";
			}
			return listPlayers;
		}
		
		
	}
	
	
	public int numberOfPlayers(){
		return players.size();
    }
	
	
	
   @SuppressWarnings("unchecked")
    public void load() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("players.xml"));
        players = (ArrayList<Player>) is.readObject();
        is.close();
    }
    
    public void save() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("players.xml"));
        out.writeObject(players);
        out.close();    
    }
    
}
