import java.util.Random;

class WhateverPlayer implements Player{
    Board.Mark mark;
    Random rand = new Random();

    /**
	* WhateverPlayer constuctor.
	*/
    public WhateverPlayer(String mark){
        if(mark.equals("x")){
            this.mark = Board.Mark.X;
        }else{
            this.mark = Board.Mark.O;
        }
    }

    /**
	* Play WhateverPlayer turn randomly.
	*/
    public void playTurn(Board board, Board.Mark mark) {
        while(true){
            int row = rand.nextInt(Board.SIZE) + 1;
            int col = rand.nextInt(Board.SIZE) + 1;
            if(board.putMark(mark, row-1, col-1)){
                break;
            }     
        }
    }  
    
    /**
	* return the Mark of the player.
	*/
    public Board.Mark GetMark(){
        return this.mark;
    }

    /**
	* Change the Mark of the player.
	*/
    public void Change_Mark() {
        if(this.mark == Board.Mark.O){
            this.mark = Board.Mark.X;
            return;
        }
        this.mark = Board.Mark.O;
    }
    
}