package Boards;
import java.util.Scanner;

public class TicTacToeBoard extends Board {  //shows the attributes that are needed to run the program

	public TicTacToeBoard(int x, int y,String player1, String player2, String wq1, String wq2, String token1, String token2 , int score1 , int score2) {
		super(x, y, player1, player2, wq1, wq2, token1,token2, score1, score2);
	}



	    Scanner input;
	    boardPiece[][] board =          {{new boardPiece(),new boardPiece(),new boardPiece()}, //prints out the table
	                                    {new boardPiece(),new boardPiece(),new boardPiece()},  // gets the board piece and puts it together creating a board for tic tac toe 
	                                    {new boardPiece(),new boardPiece(),new boardPiece()}};
	    char turn = 'X';
	    boolean win = false;
	    int count = 0;
	    String players = player1;
	    
	    public void playTTT() {
	    input = new Scanner(System.in);
	    play();
	    }  
	    
	    
	    public void play()
	    {
	    printBoard();
	    while(!win)
	    move();
	    }
	    
	    public void printBoard()
	    {
	    	System.out.println("***Use Numbers 1-9 To Select A Square***");  // this prints out a table that gives you a guide how to play the game
		    System.out.println("_1_|_2_|_3_|");
		    System.out.println("_4_|_5_|_6_|");
		    System.out.println("_7_|_8_|_9_|");
		    System.out.println();
	    for(int x=0; x<3; x++){
	    for(int y=0; y<3; y++){
	    System.out.print(board[x][y].piece);
	    }
	    System.out.println();
	    }
	    }
	    
	    public void move()  //the movement is in turns so that when the first player have selected a place on the table then it goes to player number 2 and so on until someone wins or draws   
	    {
	    int move = 0;
	    String valid = "";
	    System.out.println(players +"("+ turn +")"+ ", enter move (1 - 9): ");
	    move = input.nextInt();
	    valid = checkMove(move);
	    while(valid != "ok")
	    {
	    System.out.println("INVALID MOVE: "+ valid);
	    move = input.nextInt();
	    valid = checkMove(move);
	    }
	      
	    count++;
	    board[(move-1)/3][(move-1)%3].piece = "_"+turn+"_|"; //this is the code for the movement of "X" & "O".
	    board[(move-1)/3][(move-1)%3].player = turn;
	    board[(move-1)/3][(move-1)%3].used = true;
	        
	    printBoard();
	     
	    if(count >= 5)
	    checkWin(move);
	        
	    if(turn == 'X') {
	    turn = 'O';
	    players = player2;
	    }
	    else{
	    turn = 'X';
	    players = player1;
	    }
	    }
	    
	    public String checkMove(int move)
	    {
	    if(move < 1 || move > 9)
	    return "Enter number 1 - 9 only: ";
	    else
	    if(board[(move-1)/3][(move-1)%3].used)
	    return "That move has been used. Enter another move (1 - 9): ";
	    else
	    return "ok";
	    }
	    
	    public void checkWin(int move) //this looks at the code and if someone has matched 3 "x" or 3"o" in a Row that player wins. whichever player wins, his/her nickname,quote and the score he or her got rewarded with gets printed out.
	    {
	    for(int x = 0; x<3; x++){ //check for win in Columns
	    if((board[x][0].used && board[x][1].used && board[x][2].used) && 
	    (board[x][0].player == board[x][1].player && board[x][0].player == board[x][2].player)){
	    System.out.println(players+"(" + turn + ") wins!");
	    win = true;
	    if (players == player1 && win == true) {
	    	inGameWon1 += 1;
	    }
    	if ( players == player2 && win == true) {
	    	inGameWon2 += 1;
	    }
	    if (player1Winner()) {
        	inGameLost2+=1;
        	score1 += inGameWon1;
        	System.out.println(player1 +" says: "+wq1);
        }
	    if (player2Winner()) {
        	inGameLost1+=1;
        	score2 += inGameWon2;
        	System.out.println(player2 +" says: "+wq2);

        }
	    return;
	    }
	    }
	    for(int y = 0; y<3; y++)  //this looks at the code and if someone has matched 3 "x" or 3"o" in a columns that player wins. whichever player wins, his/her nickname,quote and the score he or her got rewarded with gets printed out.
	    {
	    if((board[0][y].used && board[1][y].used && board[2][y].used) && 
	    (board[0][y].player == board[1][y].player && board[0][y].player == board[2][y].player)){
		    System.out.println(players+"(" + turn + ") wins!");
	    win = true;
	    if (players == player1 && win == true) {
	    	inGameWon1 += 1;
	    }
    	if ( players == player2 && win == true) {
	    	inGameWon2 += 1;
	    }
    	if (player1Winner()) {
        	inGameLost2+=1;
        	score1 += inGameWon1;
        	System.out.println(player1 +" says: "+wq1);
        }
	    if (player2Winner()) {
        	inGameLost1+=1;
        	score2 += inGameWon2;
        	System.out.println(player2 +" says: "+wq2);

        }
	    return;
	    }
	    }
	        //Check for win in Diagonals
	    
	    if((board[0][0].used && board[1][1].used && board[2][2].used) && 
	    (board[0][0].player == board[1][1].player && board[0][0].player == board[2][2].player)){
		    System.out.println(players+"(" + turn + ") wins!");
	    win = true;
	    if (players == player1 && win == true) {
	    	inGameWon1 += 1;
	    }
    	if ( players == player2 && win == true) {
	    	inGameWon2 += 1;
	    }
    	if (player1Winner()) {
        	inGameLost2+=1;
        	score1 += inGameWon1;
        	System.out.println(player1 +" says: "+wq1);
        }
	    if (player2Winner()) {
        	inGameLost1+=1;
        	score2 += inGameWon2;
        	System.out.println(player2 +" says: "+wq2);

        }
	    return;
	    }
	  //this looks at the code and if someone has matched 3 "x" or 3"o" in a Diagonals "/" that player wins. whichever player wins, his/her nickname,quote and the score he or her got rewarded with gets printed out. 
	    if((board[2][0].used && board[1][1].used && board[0][2].used) && 
	    (board[2][0].player == board[1][1].player && board[2][0].player == board[0][2].player))
	    {
		    System.out.println(players+"(" + turn + ") wins!");
		 win = true;
		 if (players == player1 && win == true) {
		    	inGameWon1 += 1;
		    }
	    	if ( players == player2 && win == true) {
		    	inGameWon2 += 1;
		    }
	    	if (player1Winner()) {
	        	inGameLost2+=1;
	        	score1 += inGameWon1;
	        	System.out.println(player1 +" says: "+wq1);
	        }
		    if (player2Winner()) {
	        	inGameLost1+=1;
	        	score2 += inGameWon2;
	        	System.out.println(player2 +" says: "+wq2);
	        }
	    return;
	    }       
	        
	    if(count==9){
	    System.out.println("Draw! Nobody Wins");  //if the all the boxes are full and none had 3 in a row/col or diag then its a draw and no one wins.
	    win = true;
	    
	    return;
	    }
	    }
	    
	    public void init() //The board is created 
	    {
	    for(int x=0;x<3;x++){
	    for(int y=0;y<3;y++){
	    board[x][y] = new boardPiece();
	    }          
	    }
	    turn = 'X';
	    win = false;
	    count = 0;
	    }
	    
	    class boardPiece{
	    public String piece;
	    public char player;
	    public boolean used;
	        
	    boardPiece(){
	    piece = "___|";
	    used = false;
	    }
	    }
	    
	    
	    
	}



	
