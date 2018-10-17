package Boards;


public abstract class Board {

	 int x;
	 int y;
     int inGameWon1;
	 int inGameLost1;
	 int inGameWon2;
	 int inGameLost2;
	 int[][] board;
	 int totalMovesPlayed = 0;
	 String player1;
	 String player2;
	 String wq1;
	 String wq2;
	 String token1;
	 String token2;
	 int score1;
	 int score2;



	
	public Board (int x, int y, String player1, String player2,String wq1,String wq2, String token1,String token2, int score1, int score2){
		this.x = x;
		this.y = y;
		this.player1 = player1;
		this.player2 = player2;
		this.wq1 = wq1;
		this.wq2 = wq2;
		this.token1 = token1;
		this.token2 = token2;
		this.score1 = score1;
		this.score2 = score2;

    }
	
	public boolean player1Winner() {
        if (inGameWon1  > 0 ) 
        	return true;

		return false;
        

    	
    }
    
    public boolean player2Winner() {
        if (inGameWon2  > 0 )
        	return true;

		return false;
    	
    }
	
	
	
	
	
}
