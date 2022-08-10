

class Game{
    Renderer renderer;
    Player player1;
    Player player2;
    Board board;

    /**
	* Game constructor.
	*/
    public Game(Player player1, Player player2, Renderer renderer){
        this.renderer = renderer;
        this.player1 = player1;
        this.player2 = player2;
        this.board = new Board();
    }

    /**
	* Run game between two players.
	*/
    public int run() {
        this.renderer.renderBoard(board);
        int i;
        if(player1.GetMark() == Board.Mark.X){
            i=0;
        }else{
            i=1;
        }
        while(true){
            if(board.CheckGameEnd() == Board.Mark.X){
                if(player1.GetMark() == Board.Mark.X){
                    System.out.println("Player 1 WINS!!");
                    return 0;
                }
                System.out.println("Player 2 WINS!!");
                return 1;
            }
            if(board.CheckGameEnd() == Board.Mark.O){
                if(player1.GetMark() == Board.Mark.O){
                    System.out.println("Player 1 WINS!!");
                    return 0;
                }
                System.out.println("Player 2 WINS!!");
                return 1;
            }
            if(board.free==0){
                System.out.println("it's a draw!");
                return 2;
            }
            if(i%2==0){
                this.player1.playTurn(this.board, player1.GetMark());
            }else{
                this.player2.playTurn(this.board, player2.GetMark());
            }
            this.renderer.renderBoard(this.board);
            i++;
        }        
    }

}