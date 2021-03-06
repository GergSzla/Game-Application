package Players;


public class Player {
	private String name;
	private String username;
	private String winnerQuote;
	private String token;
	private int score;
	private int gamesWon;
	private int gamesLost;
	int tempScore;
	int tempScoreLost;
	
	

	
	public Player(String name,String username,String winnerQuote,String token) {
		this.name = name;
		this.username = username;
		this.winnerQuote = winnerQuote;
		this.token = token;
		score = 0;
		gamesWon = 0;
		gamesLost = 0;
		tempScore = 0; // temporary score , resets eventually
		tempScoreLost = 0; // temporary lost score  , resets eventually 
	
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getWinnerQuote() {
		return winnerQuote;
	}

	public void setWinnerQuote(String winnerQuote) {
		this.winnerQuote = winnerQuote;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getGamesWon() {
		return gamesWon;
	}

	public void setGamesWon(int gamesWon) {
		this.gamesWon = gamesWon;
	}

	public int getGamesLost() {
		return gamesLost;
	}

	public void setGamesLost(int gamesLost) {
		this.gamesLost = gamesLost;
	}
	
	public void addScore(int amountIn)
    {
        score = score + amountIn;
        
    }
	
	public void addTempScore(int amountIn) {
		tempScore += + amountIn;  // takes in 1 from the gamesystem after the game is finished
		gamesWon = gamesWon + amountIn; // ""  "  "   "     "        "      "   "    "    "" 
	}
	
	public void lostScore(int amountIn) {
		gamesLost = gamesLost + amountIn;
		tempScoreLost = tempScoreLost + amountIn;
	}
	
	
	
	public String toString() {
		if (tempScoreLost >= 8) {  // if the temporary lost score reaches 8 , 3 is taken away from the overall score and temporary lost score resets to 0 and starts again 
			score = score - 3;
			tempScoreLost = 0;
		}
		
		if (tempScore >= 10) {  // if the temporary score reaches 10 , 1 score is added to the overall score and the temporary score resets to 0 and starts again 
			score = score +  1;
			tempScore = 0;
		}
		
		return username + "    " + score+"       "+gamesWon+"     "+gamesLost;
		
	}
	
	
}
