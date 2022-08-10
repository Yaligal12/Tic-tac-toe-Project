import java.util.Scanner;


class HumanPlayer implements Player{
    Board.Mark mark;


    /**
	* Human constructor.
	*/
    public HumanPlayer(String mark){
        if(mark.equals("x")){
            this.mark = Board.Mark.X;
        }else{
            this.mark = Board.Mark.O;
        }
    }

    /**
	* Play human turn.
	*/
    public void playTurn(Board board, Board.Mark mark) {
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println(this.mark + " enter a row: 1-" + Board.SIZE);
            int row = sc.nextInt();
            System.out.println(this.mark + " enter a collumn: 1-" + Board.SIZE);
            int col = sc.nextInt();
            if(board.putMark(mark, row-1, col-1)){
                break;
            }
            System.out.println("bad row or collumn please try again!");
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