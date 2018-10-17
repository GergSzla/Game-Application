package Game;
import java.util.*;
import Players.*;
import Boards.*;

public class GameSystem {
	private  Scanner input;						//Create a Scanner object called input that can be used by all methods in the class.
    private static PlayerList gamers;
    
    
    
    public GameSystem(){
    	input = new Scanner(System.in);
    	gamers = new PlayerList ();
    }
    
    public static void main(String args[]) throws Exception{
    	
		    	GameSystem app = new GameSystem();  
		    	try{
			   		gamers.load();                                             ///loads details when the application is run
			   		System.out.println("***Data Loaded Successfully***");
		   			}
		   			catch(Exception e){
		   			System.out.println("Error loading from file:  " + e);
		   			}
	        	app.runMenu();
                
	}
    
    private int mainMenu()
    { 
    	System.out.println("\n\n============================================================");
    	System.out.println("||                        Game Menu                       ||");
    	System.out.println("============================================================");
        System.out.println("||  1) New Player                                         ||");     
        System.out.println("||  2) Remove players                                     ||");
        System.out.println("||  3) Existing players                                   ||");
        System.out.println("||  4) Update Player                                      ||");
        System.out.println("||--------------------------------------------------------||"); 
        System.out.println("||  5) Play                                               ||");       
        System.out.println("||--------------------------------------------------------||"); 
        System.out.println("||  6) Save                                               ||");
        System.out.println("||  7) Load                                               ||");
        System.out.println("||  0) Exit                                               ||");
        System.out.print(  "||==>>");
         int option = input.nextInt();
         return option;
    }
    
    private void runMenu()
    {
        int option = mainMenu();
        do
        
        {

            switch (option)
            {
            case 1:    	addPlayer();
           				break;
            case 2:    	removePlayer();
            			break;
            case 3:    	System.out.println(gamers.listPlayers());
						break;
            case 4:    	updatePlayerInfo();         ///updates username and winner quote ONLY
			            break;
            case 5:    	runGameMenu(); 
                        break;
            case 6:     try{
				        gamers.save();
				        System.out.println("***Saved successfully***");
			            }
			   			catch(Exception e){
			   			System.out.println("Error writing to file:  " + e);
			   			}
			    		break;
            case 7:     try{
				   		gamers.load();
			   			}
			   			catch(Exception e){
			   			System.out.println("Error loading from file:  " + e);
			   			}
			    		break;
						
                default:   System.out.println("Invalid option entered: " + option);
                        
            }

            //display the main menu again
            option = mainMenu();
        }while (option != 0);

        //the user chose option 0, so exit the program
        System.out.println("Exiting... bye");
        System.exit(0);
    }
    
    private void addPlayer()
    {
    	System.out.println("\n\n================================================================");
    	System.out.println("||                    Player Registration                     ||");
    	System.out.println("================================================================");
        System.out.println("|| Please enter the following player details...");

        System.out.print("||\tName: ");
        String name = input.nextLine();
        name = input.nextLine();
        
        while (!name.matches("[a-zA-Z' ]*")|| (name.isEmpty())){           //if player name is empty or doesn't contain alphabetic characters or ' or space
        	System.out.println("||***Input contains invalid characters. Try again.");  //returns an error and repeats until valid
        	System.out.print("||\tName: ");
	        name = input.nextLine();
        }
        
        System.out.print("||\tUsername: ");
        String username = input.nextLine();
        while (username.length() > 10 || username.length() < 4|| username.isEmpty()){           ///minimum 4 characters, maximum 10 characters and cannot be empty
        	System.out.println("||***Username cannot be empty. Max. 10 characters and min. 4 characters");  //returns an error and repeats until valid
        	System.out.print("||\tUsername: ");
	        username = input.nextLine();
        }
        while (username.length() >= 4 && username.length() < 10) {  //fills up username space to 10 characters for organizational purposes
        	username += " ";
        }
        

        System.out.print("||\tWinner Quote: ");
        String winnerQuote= input.nextLine();
        if (winnerQuote.isEmpty()) {
        	winnerQuote = username + " is the best!"; //default winner quote if winner quote is left empty
        } 

        System.out.print("||\tToken: ");
        String token = input.nextLine();
        while (token.isEmpty() || token.length() > 2 || token.equalsIgnoreCase("g") || token.equalsIgnoreCase("gg") ){            //g/ gg/ G/ GG is reserved for guest players as token
        	System.out.println("||***Input contains invalid characters.\tToken can only be 2 characters, g/G is reserverd. \tTry again."); //cant be more than 2 chars and only prints out the first
        	System.out.print("||\tToken: ");
	        token = input.nextLine();
        }
		
      
        gamers.add(new Player(name, username, winnerQuote, token));
    }
    
    public void removePlayer()
    {
    	
    	System.out.println("\n============================================================");
    	System.out.println("||                      Remove a Player                   ||");
    	System.out.println("============================================================");
    	//List the Members and ask the user to choose which account to delete
    	System.out.println(gamers.listPlayers());
    	if(gamers.getPlayers().size()!=0)
    	{
    		//Only process the delete if products exist in the Arraylist
    	   	System.out.println("||Index of player to remove ==>");
    	   	int index = input.nextInt();
        
    	   	if(index<gamers.getPlayers().size())
    	   	{
    	   		//If the index number exists in the Arraylist, delete it from the arraylist
    	   		gamers.getPlayers().remove(index);
    	   		System.out.println("||**Player Removed**");
    	   	}
    	   	else
    	   	{
    	   		System.out.println("||**There is no player for this index number");
    	   	}
       	}
    }
    
    private void updatePlayerInfo(){
    	System.out.println("\n============================================================");
    	System.out.println("||                      Update a Player                   ||");
    	System.out.println("============================================================");
		System.out.println(gamers.listPlayers());

		if (gamers.getPlayers().size() != 0){	
			System.out.print("Index of player to edit ==>");
			int index = input.nextInt();

			if (index < gamers.getPlayers().size()){	
				System.out.print("   Enter a new username: ");// updates name with the same restrictions as before 
				String un = input.nextLine();
				un = input.nextLine();
				while (un.length() > 10 || un.length() < 4|| un.isEmpty()){           
		        	System.out.println("||***Username cannot be empty. Max. 10 characters and min. 4 characters");  //returns an error and repeats until valid
		        	System.out.print("||\tUsername: ");
			        un = input.nextLine();
		        }
		        while (un.length() >= 4 && un.length() < 10) {  //fills up username space to 10 characters to organize
		        	un += " ";
		        }
				
				System.out.print("   Enter a new winner quote: ");  //updates winner quote 
				String wq = input.nextLine();

				
				Player player = gamers.getPlayers().get(index);
				player.setUsername(un);
				player.setWinnerQuote(wq);
			}
			else
			{
				System.out.println("There is no player for this index number");
			}
		}
    
    }
    
    
 
    
    private int gameMenu()
    { 
    	System.out.println("\n\n============================================================");
    	System.out.println("||                        Game Menu                       ||");
    	System.out.println("============================================================");
        System.out.println("||Choose a game to play:                                  ||");     
        System.out.println("||  1) Connect Four                                       ||");
        System.out.println("||  2) Tic Tac Toe                                        ||");
        System.out.println("||--------------------------------------------------------||"); // menu within the menu for choosing a game
        System.out.println("||  3) Back                                               ||"); 
        System.out.println("||--------------------------------------------------------||"); 
        System.out.print(  "||==>>");
         int gameOption = input.nextInt();
         return gameOption;
    }
    
    private void runGameMenu()  
    {
        int gameOption = gameMenu();
        do
        
        {

            switch (gameOption)
            {
            case 1:     GamePlayerMenu();   ///Menu for connect four
        	        	
           				break;
            case 2:    	GamePlayerMenuTTT();  /// menu for tictactoe
            			break;
            case 3:    	runMenu();  // returns back to the main menu
						break;
						
            default:   System.out.println("Invalid option entered: " + gameOption);
                        
            }

            //display the main menu again
            gameOption = gameMenu();
        }while (gameOption != 0);

        //the user chose option 0, so exit the program
        System.out.println("Exiting... bye");
        System.exit(0);
    }
    private int playerMenu() /// menu for connect four
    { 
    	System.out.println("\n\n============================================================");
    	System.out.println("||                        Pick Players                    ||");
    	System.out.println("============================================================");
        System.out.println("||Choose player to play:                                  ||");     
        System.out.println("||  1) Make A New Player                                  ||");
        System.out.println("||  2) Play As Guest                                      ||");
        System.out.println("||  3) Play As Existing Player                            ||");
        System.out.println("||--------------------------------------------------------||"); 
        System.out.println("||  4) Back                                               ||");       
        System.out.println("||--------------------------------------------------------||"); 
        System.out.print(  "||==>>");
         int playerOption = input.nextInt();
         return playerOption;
    }
    
    private void GamePlayerMenu()
    {
        int playerOption = playerMenu();
        do
        
        {

            switch (playerOption)
            {
            case 1:     addPlayer();   /// adds a player and returns to menu to allow to pick that player
           				break;
            case 2:     playAsGuest();  ///player1 is automatically picked as guest. Guest is not saved 
            			break;
            case 3:     playAsExistingPlayer();  ///pick a player from the arraylist of players
						break;
            case 4:    	runMenu();  // returns back to main menu
						break;
						
            default:   System.out.println("Invalid option entered: " + playerOption);
                        
            }

            //display the main menu again
            playerOption = playerMenu();
        }while (playerOption != 0);
        System.out.println("Exiting... bye");
        System.exit(0);
    }
    
    private void GamePlayerMenuTTT()   /// menu for tictactoe
    {
        int playerOption = playerMenu();
        do
        
        {

            switch (playerOption)
            {
            case 1:     addPlayer();
           				break;
            case 2:     playAsGuestTTT();
            			break;
            case 3:     playAsExistingPlayerTTT();
						break;
            case 4:    	runMenu();
						break;
						
            default:   System.out.println("Invalid option entered: " + playerOption);
                        
            }

            //display the main menu again
            playerOption = playerMenu();
        }while (playerOption != 0);
        System.out.println("Exiting... bye");
        System.exit(0);
    }
    
    
    public void playAsExistingPlayer() {     //playing as existing player 
    	System.out.println(gamers.listPlayers());   //lists all available players 
		System.out.println("Choose a Player (1)");
		int index1 = input.nextInt();    //choose the players by index
		String player1 = gamers.getPlayers().get(index1).getUsername();  // assigns the chosen users un to player1 
		String wq1 = gamers.getPlayers().get(index1).getWinnerQuote();  // assigns the winner quote 
		String t1 = gamers.getPlayers().get(index1).getToken();  // assigns the token
		int score1 = gamers.getPlayers().get(index1).getScore();  // assigns the score 
		System.out.println("Choose a Player (2)");
	    int index2 = input.nextInt();
		String player2 = gamers.getPlayers().get(index2).getUsername();
		String wq2 = gamers.getPlayers().get(index2).getWinnerQuote();
		String t2 = gamers.getPlayers().get(index2).getToken();
		int score2 = gamers.getPlayers().get(index2).getScore();
		
		//game difficulty
		// difficulties 1-4 automatically assign dimensions 
		// difficulty 5 is personalised 
		
		boolean isTrue = false;
		do {
			System.out.println("Difficulty:");
			System.out.println("1.) Easy (10x10) (+1Score/round)");
			System.out.println("2.) Medium (8x8) (+2Score/round)");
			System.out.println("3.) Extreme (5x5) (+4Score/round)");
			System.out.println("4.) Complicated (15x15) (+1Score/round)");
			System.out.println("5.) Custom (4x4 - 15x15) (Score Depends on Dimensions)");   //the  score depends on the chosen dimension 
					int option = input.nextInt();
							if (option == 1) { ///Easy
								int x = 10; //width of board
								int y = 10; // height of board 
								ConnectFourBoard game1 = new ConnectFourBoard(x,y,player1, player2, wq1, wq2, t1, t2,score1 , score2); // creates the ConnectFour Board with the players details 
								game1.playC4();  // allows game to run
								if (game1.player1Winner()) { //  if player1Winner() returns true (if the player1 wins) 
						    		int score = 1 ;      
						    		int tempScore = 1;  // this keeps track of how many times the player wins. if that player wins 10 times , its set back to 0 and starts again (+ adds 1 score)
									int gamesLost = 1;  // ""     ""    ""   ""  "" ""    ""   ""   ""   loses. if that player loses 8 times , its set back to 0 and starts again (+ takes away 3 scores)
									 gamers.getPlayers().get(index1).addScore(score);  // adds the score to the player's score 
									 gamers.getPlayers().get(index1).addTempScore(tempScore); //adds the temp score to the player's temp score to keep track of the games 
									 gamers.getPlayers().get(index2).lostScore(gamesLost); // ""     "    game lost ""   ""  ""      games lost ""   ""    ""   ""    "" 
									 System.out.println(player1 +"'s reward is: "+ score);  //prints out how many scores the player won ( it varies from difficulty to difficulty )
								}
						    	if (game1.player2Winner()) {  //  if player1Winner() returns true (if the player1 wins) 
									int score = 1;
									int tempScore = 1; // this keeps track of how many times the player wins. if that player wins 10 times , its set back to 0 and starts again (+ adds 1 score)
									int gamesLost = 1; // ""     ""    ""   ""  "" ""    ""   ""   ""   loses. if that player loses 8 times , its set back to 0 and starts again (+ takes away 3 scores)
									 gamers.getPlayers().get(index2).addScore(score);// adds the score to the player's score 
									 gamers.getPlayers().get(index2).addTempScore(tempScore); //adds the temp score to the player's temp score to keep track of the games 
									 gamers.getPlayers().get(index1).lostScore(gamesLost);// ""     "    game lost ""   ""  ""      games lost ""   ""    ""   ""    "" 
									 System.out.println(player2 +"'s reward is: "+ score);
								}
						    	try{
							        gamers.save();  // automatically saves after the game is finnito 
							        System.out.println("***Autosave: Successful***");
						            }
						   			catch(Exception e){
						   			System.out.println("Autosave: Failed. Error:  " + e);
						   			}
								isTrue = true;
							}
							else if (option == 2 ) { // *********Medium Difficulty
								int x = 8; // width automatically set to 8 
								int y = 8; // height ""    ""  "" "" 
 								ConnectFourBoard game1 = new ConnectFourBoard(x,y,player1, player2, wq1, wq2, t1, t2,score1 , score2);
								game1.playC4();
								if (game1.player1Winner()) {
						    		int score = 2 ;  // adds 2 scores on this difficulty
						    		int tempScore = 1;
									int gamesLost = 1;
									 gamers.getPlayers().get(index1).addScore(score);
									 gamers.getPlayers().get(index1).addTempScore(tempScore);
									 gamers.getPlayers().get(index2).lostScore(gamesLost);
									 System.out.println(player1 +"'s reward is: "+ score);
								}
						    	if (game1.player2Winner()) {
									int score = 2;
									int tempScore = 1;
									int gamesLost = 1;
									 gamers.getPlayers().get(index2).addScore(score);
									 gamers.getPlayers().get(index2).addTempScore(tempScore);
									 gamers.getPlayers().get(index1).lostScore(gamesLost);
									 System.out.println(player2 +"'s reward is: "+ score);
								}
						    	
						    		try{
								        gamers.save();
								        System.out.println("***Autosave: Successful***");
							            }
							   			catch(Exception e){
							   			System.out.println("Autosave: Failed. Error:  " + e);
							   			}
								isTrue = true;
							}
							else if (option == 3 ) {   /// Extreme
								int x = 5; 
								int y = 5;
								ConnectFourBoard game1 = new ConnectFourBoard(x,y,player1, player2, wq1, wq2, t1, t2,score1 , score2);
								game1.playC4();
								if (game1.player1Winner()) {
						    		int score = 4 ;  // adds 4 scores on this difficulty
						    		int tempScore = 1;
									int gamesLost = 1;
									 gamers.getPlayers().get(index1).addScore(score);
									 gamers.getPlayers().get(index1).addTempScore(tempScore);
									 gamers.getPlayers().get(index2).lostScore(gamesLost);
									 System.out.println(player1 +"'s reward is: "+ score);
								}
						    	if (game1.player2Winner()) {
									int score = 4;
									int tempScore = 1;
									int gamesLost = 1;
									 gamers.getPlayers().get(index2).addScore(score);
									 gamers.getPlayers().get(index2).addTempScore(tempScore);
									 gamers.getPlayers().get(index1).lostScore(gamesLost);
									 System.out.println(player2 +"'s reward is: "+ score);
								}
						    	try{
							        gamers.save();
							        System.out.println("***Autosave: Successful***");
						            }
						   			catch(Exception e){
						   			System.out.println("Autosave: Failed. Error:  " + e);
						   			}
								isTrue = true;
								
							}
							else if (option == 4 ) {  ///Complicated
								int x = 15;
								int y = 15;
								ConnectFourBoard game1 = new ConnectFourBoard(x,y,player1, player2, wq1, wq2, t1, t2,score1 , score2);
								game1.playC4();
								if (game1.player1Winner()) {
						    		int score = 1 ;
						    		int tempScore = 1;
									int gamesLost = 1;
									 gamers.getPlayers().get(index1).addScore(score);
									 gamers.getPlayers().get(index1).addTempScore(tempScore);
									 gamers.getPlayers().get(index2).lostScore(gamesLost);
									 System.out.println(player1 +"'s reward is: "+ score);
								}
						    	if (game1.player2Winner()) {
									int score = 1;
									int tempScore = 1;
									int gamesLost = 1;
									 gamers.getPlayers().get(index2).addScore(score);
									 gamers.getPlayers().get(index2).addTempScore(tempScore);
									 gamers.getPlayers().get(index1).lostScore(gamesLost);
									 System.out.println(player2 +"'s reward is: "+ score);
								}
						    	try{
							        gamers.save();
							        System.out.println("***Autosave: Successful***");
						            }
						   			catch(Exception e){
						   			System.out.println("Autosave: Failed. Error:  " + e);
						   			}
						    	isTrue = true;
							}
							else if (option == 5) {     ///Custom difficulty
								System.out.println("What size do you want the board to be?");
								System.out.println("Width:");
								int x = input.nextInt(); //takes input from user for width ((((((doesnt work if its a rectangle in shape )))))) 
								while (x < 4 || x > 15) { //maximum (15x15) min (4x4)
									System.out.println("Invalid size. Min: 4. Max 15.");
									System.out.println("Height:");
									x = input.nextInt();
								}
								System.out.println("Height:");
								int y = input.nextInt();
								while (y < 4 || y > 15) {
									System.out.println("Invalid size. Min: 4. Max 15.");
									System.out.println("Height:");
									y = input.nextInt();
								}
								ConnectFourBoard game1 = new ConnectFourBoard(x,y,player1, player2, wq1, wq2, t1, t2,score1 , score2);
								game1.playC4();
								/////score system varies for different dimensions
						    	if (game1.player1Winner()) {
						    		int score = 1 ;
						    		int tempScore = 1;
						    		if (x == 4 && y == 4) {   ///4x4 = 5 score 
						    		    score = 5;
						    		}  
						    		if (x == 5 && y == 5) {  //5x5 = 4 score 
						    			score = 4;
						    		}  
						    		if ((x > 5 && y > 5)&&(x <= 7 && y <=7 )){  //6x6 or 7x7 = 3 score 
						    				score = 3;
						    		}  
						    		if (x == 8 && y == 8) {  //8x8 = 2 score 
						    			score = 2;
						    		}
						    		if (x > 8 && y > 8) {  // anything over 8x8 = 1 score
						    			score = 1 ;
						    		}
									//int score = 1;
									int gamesLost = 1;
									 gamers.getPlayers().get(index1).addScore(score);
									 gamers.getPlayers().get(index1).addTempScore(tempScore);
									 gamers.getPlayers().get(index2).lostScore(gamesLost);
									 System.out.println(player1 +"'s reward is: "+ score);
								}
						    	if (game1.player2Winner()) {
									int score = 1;
									int tempScore = 1;
									int gamesLost = 1;
									 gamers.getPlayers().get(index2).addScore(score);
									 gamers.getPlayers().get(index2).addTempScore(tempScore);
									 gamers.getPlayers().get(index1).lostScore(gamesLost);
									 System.out.println(player2 +"'s reward is: "+ score);
								}
						    	try{
							        gamers.save();
							        System.out.println("***Saved successfully***");
						            }
						   			catch(Exception e){
						   			System.out.println("Error writing to file:  " + e);
						   			}
						    	isTrue = true;
							}
		
		} while (isTrue == false);

    }
    
    public void playAsGuest() {  // allows to play as guest
    	System.out.println("***Player 1 is set to 'guest'.");
    	String guestUn = "Guest";   // automatically sets un to guest 
    	String guestWq = guestUn + " is the best!"; // uses default winnerquote
    	String guestToken = "g"; // sets token to G/g 
    	int guestScore = 0; 
    	
    	String player1 = guestUn; 
    	
    	System.out.println(gamers.listPlayers());
    	System.out.println("Choose a Player (2)");
	    int index2 = input.nextInt();
		String player2 = gamers.getPlayers().get(index2).getUsername();
		String wq2 = gamers.getPlayers().get(index2).getWinnerQuote();
		String t2 = gamers.getPlayers().get(index2).getToken();
		int score2 = gamers.getPlayers().get(index2).getScore();
		boolean isTrue = false;
		do {
			System.out.println("Difficulty:");
			System.out.println("1.) Easy (10x10) (+1Score/round)");
			System.out.println("2.) Medium (8x8) (+2Score/round)");
			System.out.println("3.) Extreme (5x5) (+4Score/round)");
			System.out.println("4.) Complicated (15x15) (+1Score/round)");
			System.out.println("5.) Custom (4x4 - 15x15) (Score Depends on Dimensions)");
					int option = input.nextInt();
							if (option == 1) { ///Easy
								int x = 10;
								int y = 10;
								ConnectFourBoard game1 = new ConnectFourBoard(x,y,player1, player2, guestWq, wq2, guestToken, t2,guestScore , score2);
								game1.playC4();
								if (game1.player1Winner()) {
									int gamesLost = 1;
									 gamers.getPlayers().get(index2).lostScore(gamesLost);
									 System.out.println("Guest players do not receive scores. Make a player to get scores.");
								}
						    	if (game1.player2Winner()) {
									int score = 1;
									int tempScore = 1;
									 gamers.getPlayers().get(index2).addScore(score);
									 gamers.getPlayers().get(index2).addTempScore(tempScore);
									 System.out.println(player2 +"'s reward is: "+ score);
								}
						    	try{
							        gamers.save();
							        System.out.println("***Autosave: Successful***");
						            }
						   			catch(Exception e){
						   			System.out.println("Autosave: Failed. Error:  " + e);
						   			}
								isTrue = true;
							}
							else if (option == 2 ) { ///Medium
								int x = 8;
								int y = 8;
								ConnectFourBoard game1 = new ConnectFourBoard(x,y,player1, player2, guestWq, wq2, guestToken, t2,guestScore , score2);
								game1.playC4();
								if (game1.player1Winner()) {
									int gamesLost = 1;
									 gamers.getPlayers().get(index2).lostScore(gamesLost);
									 System.out.println("Guest players do not recieve scores. Make a player to get scores.");
									 }
						    	if (game1.player2Winner()) {
									int score = 2;
									int tempScore = 1;
									 gamers.getPlayers().get(index2).addScore(score);
									 gamers.getPlayers().get(index2).addTempScore(tempScore);
									 System.out.println(player2 +"'s reward is: "+ score);
								}
						    	
						    		try{
								        gamers.save();
								        System.out.println("***Autosave: Successful***");
							            }
							   			catch(Exception e){
							   			System.out.println("Autosave: Failed. Error:  " + e);
							   			}
								isTrue = true;
							}
							else if (option == 3 ) {   /// Extreme
								int x = 5;
								int y = 5;
								ConnectFourBoard game1 = new ConnectFourBoard(x,y,player1, player2, guestWq, wq2, guestToken, t2,guestScore , score2);
								game1.playC4();
								if (game1.player1Winner()) {
									int gamesLost = 1;
									 gamers.getPlayers().get(index2).lostScore(gamesLost);
									 System.out.println("Guest players do not recieve scores. Make a player to get scores.");
									 }
						    	if (game1.player2Winner()) {
									int score = 4;
									int tempScore = 1;
									 gamers.getPlayers().get(index2).addScore(score);
									 gamers.getPlayers().get(index2).addTempScore(tempScore);

									 System.out.println(player2 +"'s reward is: "+ score);
								}
						    	try{
							        gamers.save();
							        System.out.println("***Autosave: Successful***");
						            }
						   			catch(Exception e){
						   			System.out.println("Autosave: Failed. Error:  " + e);
						   			}
								isTrue = true;
								
							}
							else if (option == 4 ) {  ///Complicated
								int x = 15;
								int y = 15;
								ConnectFourBoard game1 = new ConnectFourBoard(x,y,player1, player2, guestWq, wq2, guestToken, t2,guestScore , score2);
								game1.playC4();
								if (game1.player1Winner()) {
									int gamesLost = 1;
									 gamers.getPlayers().get(index2).lostScore(gamesLost);
									 System.out.println("Guest players do not recieve scores. Make a player to get scores.");
									 }
						    	if (game1.player2Winner()) {
									int score = 1;
									int tempScore = 1;
									 gamers.getPlayers().get(index2).addScore(score);
									 gamers.getPlayers().get(index2).addTempScore(tempScore);
									 System.out.println(player2 +"'s reward is: "+ score);
								}
						    	try{
							        gamers.save();
							        System.out.println("***Autosave: Successful***");
						            }
						   			catch(Exception e){
						   			System.out.println("Autosave: Failed. Error:  " + e);
						   			}
						    	isTrue = true;
							}
							else if (option == 5) {     ///Custom difficulty
								System.out.println("What size do you want the board to be?");
								System.out.println("Width:");
								int x = input.nextInt();
								while (x < 4 || x > 15) {
									System.out.println("Invalid size. Min: 4. Max 15.");
									System.out.println("Height:");
									x = input.nextInt();
								}
								System.out.println("Height:");
								int y = input.nextInt();
								while (y < 4 || y > 15) {
									System.out.println("Invalid size. Min: 4. Max 15.");
									System.out.println("Height:");
									y = input.nextInt();
								}
								ConnectFourBoard game1 = new ConnectFourBoard(x,y,player1, player2, guestWq, wq2, guestToken, t2,guestScore , score2);
								game1.playC4();
						    	if (game1.player1Winner()) {
									int gamesLost = 1;

									 gamers.getPlayers().get(index2).lostScore(gamesLost);
									 System.out.println("Guest players do not recieve scores. Make a player to get scores.");
									 }
						    	if (game1.player2Winner()) {
									int score = 1;
									int tempScore = 1;

									 gamers.getPlayers().get(index2).addScore(score);
									 gamers.getPlayers().get(index2).addTempScore(tempScore);
									 System.out.println(player2 +"'s reward is: "+ score);
								}
						    	try{
							        gamers.save();
							        System.out.println("***Saved successfully***");
						            }
						   			catch(Exception e){
						   			System.out.println("Error writing to file:  " + e);
						   			}
						    	isTrue = true;
							}
		
		} while (isTrue == false);
    }
    
    public void playAsExistingPlayerTTT() {   // existing players for tic tac toe
    	System.out.println(gamers.listPlayers());
		System.out.println("Choose a Player (1)");
		int index1 = input.nextInt();
		String player1 = gamers.getPlayers().get(index1).getUsername();
		String wq1 = gamers.getPlayers().get(index1).getWinnerQuote();
		String t1 = gamers.getPlayers().get(index1).getToken();
		int score1 = gamers.getPlayers().get(index1).getScore();
		System.out.println("Choose a Player (2)");
	    int index2 = input.nextInt();
		String player2 = gamers.getPlayers().get(index2).getUsername();
		String wq2 = gamers.getPlayers().get(index2).getWinnerQuote();
		String t2 = gamers.getPlayers().get(index2).getToken();
		int score2 = gamers.getPlayers().get(index2).getScore();
								int x = 3;
								int y = 3;
								TicTacToeBoard game1 = new TicTacToeBoard(x,y,player1, player2, wq1, wq2, t1, t2,score1 , score2);
								game1.playTTT();
								if (game1.player1Winner()) {
						    		int score = 1 ;
						    		int tempScore = 1;
									int gamesLost = 1;
									 gamers.getPlayers().get(index1).addScore(score);
									 gamers.getPlayers().get(index1).addTempScore(tempScore);
									 gamers.getPlayers().get(index2).lostScore(gamesLost);
									 System.out.println(player1 +"'s reward is: "+ score);
								}
						    	if (game1.player2Winner()) {
									int score = 1;
									int tempScore = 1;
									int gamesLost = 1;
									 gamers.getPlayers().get(index2).addScore(score);
									 gamers.getPlayers().get(index2).addTempScore(tempScore);
									 gamers.getPlayers().get(index1).lostScore(gamesLost);
									 System.out.println(player2 +"'s reward is: "+ score);
								}
						    	try{
							        gamers.save();
							        System.out.println("***Autosave: Successful***");
						            }
						   			catch(Exception e){
						   			System.out.println("Autosave: Failed. Error:  " + e);
						   			}
    }
    
    public void playAsGuestTTT() {   
    	System.out.println("***Player 1 is set to 'guest'.");
    	String guestUn = "Guest";
    	String guestWq = guestUn + " is the best!";
    	String guestToken = "g";
    	int guestScore = 0;
    	
    	String player1 = guestUn;
    	
    	System.out.println(gamers.listPlayers());
    	System.out.println("Choose a Player (2)");
	    int index2 = input.nextInt();
		String player2 = gamers.getPlayers().get(index2).getUsername();
		String wq2 = gamers.getPlayers().get(index2).getWinnerQuote();
		String t2 = gamers.getPlayers().get(index2).getToken();
		int score2 = gamers.getPlayers().get(index2).getScore();
		
								int x = 3;
								int y = 3;
								TicTacToeBoard game1 = new TicTacToeBoard(x,y,player1, player2, guestWq, wq2, guestToken, t2,guestScore , score2); // makes the tic tac toe board 
								game1.playTTT();
								if (game1.player1Winner()) {
									int gamesLost = 1;
									 gamers.getPlayers().get(index2).lostScore(gamesLost);
									 System.out.println("Guest players do not receive scores. Make a player to get scores.");
								}
						    	if (game1.player2Winner()) {
									int score = 1;
									int tempScore = 1;
									 gamers.getPlayers().get(index2).addScore(score);
									 gamers.getPlayers().get(index2).addTempScore(tempScore);
									 System.out.println(player2 +"'s reward is: "+ score);
								}
						    	try{
							        gamers.save();
							        System.out.println("***Autosave: Successful***");
						            }
						   			catch(Exception e){
						   			System.out.println("Autosave: Failed. Error:  " + e);
						   			}
    }
}
