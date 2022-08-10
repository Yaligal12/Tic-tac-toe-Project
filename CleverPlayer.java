import java.util.LinkedList;
import java.util.Random;

class CleverPlayer implements Player{
    Board.Mark mark;
    Random rand = new Random();


    /**
	* CleverPlayer constuctor.
	*/
    public CleverPlayer(String mark){
        if(mark.equals("x")){
            this.mark = Board.Mark.X;
        }else{
            this.mark = Board.Mark.O;
        }
    }
    

    /**
	* Play CleverPlayer turn algorithm.
	*/
    public void playTurn(Board board, Board.Mark mark) {
        /*create two linkedlist with cordinate data type */
        LinkedList <Cordinate> playerlist = new LinkedList<Cordinate>();
        LinkedList <Cordinate> opositelist = new LinkedList<Cordinate>();
        /*save the oposite mark of the player */
        Board.Mark oposite = oposite(mark);
        /*go over the board */
        for(int row=0; row<Board.SIZE; row++){
            for(int col=0; col<Board.SIZE; col++){
                /*if the mark in the location is the player's mark adds the best cordinate to playerlist */
                if(board.space[row][col] == mark){
                playerlist.add(MoveCordinate(board, row, col, mark));
                }
                /*if the mark in the location is the player's oposite mark adds the best cordinate to opositelist */
                if(board.space[row][col] == oposite){
                    opositelist.add(MoveCordinate(board, row, col, oposite));
                }  
            }
        }
        /*if the move is the first move select randomly */
        if(playerlist.size()==0){
            ChoseRandomly(board, mark);
            return;
        }
        Cordinate Worst = null;
        /*iterate threw opositelist */
        while(opositelist.size()!=0){
            Worst = opositelist.removeLast();
            /*if other player is one move from winning */
            if(Worst.val == Board.SIZE-1){
                break;
            }
            Worst = null;
        }
        Cordinate best = playerlist.removeLast();
        Cordinate temp;
        /*iterate threw playerlist and gets the cordinate with highest val*/
        while(playerlist.size()>0){
            temp = playerlist.removeLast();
            if(temp.val>best.val){
                best = temp;
            }
        }
        /*if there isn't a good move chose randomly */
        if(best.val==0 && Worst == null){
           ChoseRandomly(board, mark);
           return;
        }
        /*if one from winning win the game */
        if(best.val == Board.SIZE-1){
            MakeMove(best, board, mark);
            return;
        } 
        /*if other player one from winning block him */
        if(Worst != null){
            MakeMove(Worst, board, mark);
            return;
        }
        /*make the best move possible */
        MakeMove(best, board, mark);
        return;
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


     /**
	* return the oposite Mark of the player.
	*/
    private Board.Mark oposite(Board.Mark mark) {
        if(mark == Board.Mark.O){
            return Board.Mark.X;
        }
        return Board.Mark.O;
    }

     /**
	* return an array with 0 cell is the val of the row and 1 cell is type of the structure- row,col,daigonal..
	*/
    private int[] ValRow(int row, int col, Board board, Board.Mark mark){
        int[] val = new int[2];
        val[1]=0;
        Board.Mark oposite = oposite(mark);
        for(int i=0; i<Board.SIZE; i++){
            if(board.space[row][i]==oposite){
                val[0]=0;
                return val;
            }
            if(board.space[row][i]==mark){
                val[0]++;
            }
        }
        if(FullRow(row, board)){
            val[0]=0;
        }
        return val;
    }

    /**
	* return an array with 0 cell is the val of the row and 1 cell is type of the structure- row,col,daigonal..
	*/
    private int[] ValCol(int row, int col, Board board, Board.Mark mark){
        int[] val = new int[2];
        val[1]=1;
        Board.Mark oposite = oposite(mark);
        for(int i=0; i<Board.SIZE; i++){
            if(board.space[i][col]==oposite){
                val[0]=0;
                return val;
            }
            if(board.space[i][col]==mark){
                val[0]++;
            }
        }
        if(FullCol(col, board)){
            val[0]=0;
        }
        return val;
    }

    /**
	* return an array with 0 cell is the val of the row and 1 cell is type of the structure- row,col,daigonal..
	*/
    private int[] ValMainDiognal(Board board, Board.Mark mark){
        int[] val = new int[2];
        val[1]=2;
        Board.Mark oposite = oposite(mark);
        for(int i=0; i<Board.SIZE; i++){
            if(board.space[i][i]==oposite){
                val[0]=0;
                return val;
            }
            if(board.space[i][i]==mark){
                val[0]++;
            }
        }
        if(FullMain(board)){
            val[0]=0;
        }
        return val;
    }
    
    /**
	* return an array with 0 cell is the val of the row and 1 cell is type of the structure- row,col,daigonal..
	*/
    private int[] ValSecondDiognal(Board board, Board.Mark mark){
        int[] val = new int[2];
        val[1]=3;
        Board.Mark oposite = oposite(mark);
        for(int i=0; i<Board.SIZE; i++){
            if(board.space[i][Board.SIZE-i-1]==oposite){
                val[0]=0;
                return val;
            }
            if(board.space[i][Board.SIZE-i-1]==mark){
                val[0]++;
            }
        }
        if(FullSecond(board)){
            val[0]=0;
        }
        return val;
    }

    /**
	* puts mark in desired location.
	*/
    private void PutInRow(int row, Board board, Board.Mark mark) {
        for(int col=0; col<Board.SIZE; col++){
            if(board.putMark(mark, row, col)){
                return;
            }
        }   
    }
    
    /**
	* puts mark in desired location.
	*/
    private void PutInCol(int col, Board board, Board.Mark mark) {
        for(int row=0; row<Board.SIZE; row++){
            if(board.putMark(mark, row, col)){
                return;
            }
        }   
    }

    /**
	* puts mark in desired location.
	*/
    private void PutInMainDaigonal(Board board, Board.Mark mark) {
        for(int row=0; row<Board.SIZE; row++){
            if(board.putMark(mark, row, row)){
                return;
            }
        }   
    }

    /**
	* puts mark in desired location.
	*/
    private void PutInSecondDaigonal(Board board, Board.Mark mark) {
        for(int row=0; row<Board.SIZE; row++){
            if(board.putMark(mark, row, Board.SIZE-row-1)){
                return;
            }
        }   
    }

    /**
	* return which array has larger value in cell 0.
	*/
    private int[] BiggerArr(int[] arr1, int[] arr2) {
        if(arr1[0]>arr2[0]){
            return arr1;
        }
        return arr2;
    }

    /**
	* Checks if the row is full.
	*/
    private boolean FullRow(int row, Board board) {
        int count=0;
        for(int col=0; col<Board.SIZE; col++){
            if(board.space[row][col]!=Board.Mark.BLANK){
                count ++;
            }
        }
        if(count == Board.SIZE){
            return true;
        }
        return false;
    }

    /**
	* Checks if the col is full.
	*/
    private boolean FullCol(int col, Board board) {
        int count=0;
        for(int row=0; row<Board.SIZE; row++){
            if(board.space[row][col]!=Board.Mark.BLANK){
                count ++;
            }
        }
        if(count == Board.SIZE){
            return true;
        }
        return false;
    }

    /**
	* Checks if the main diagonal is full.
	*/
    private boolean FullMain(Board board) {
        int count=0;
        for(int i=0; i<Board.SIZE; i++){
            if(board.space[i][i]!=Board.Mark.BLANK){
                count ++;
            }
        }
        if(count == Board.SIZE){
            return true;
        }
        return false;
    }

    /**
	* Checks if the second diagonal is full.
	*/
    private boolean FullSecond(Board board) {
        int count=0;
        for(int i=0; i<Board.SIZE; i++){
            if(board.space[i][Board.SIZE-i-1]!=Board.Mark.BLANK){
                count ++;
            }
        }
        if(count == Board.SIZE){
            return true;
        }
        return false;
    }



     /**
	* Return new best cordinate for some row,col.
	*/
    private Cordinate MoveCordinate(Board board, int row, int col, Board.Mark mark) {
        if(row==col && row+col+1==Board.SIZE){
            int[] temparr = BiggerArr(BiggerArr(ValRow(row, col, board, mark), ValCol(row, col, board, mark)), BiggerArr(ValMainDiognal(board, mark), ValSecondDiognal(board, mark)));
            Cordinate temp = new Cordinate(row, col, temparr[0], temparr[1]);
            return temp;
            }
        if(row==col){
            int[] temparr = BiggerArr(BiggerArr(ValCol(row, col, board, mark), ValRow(row, col, board, mark)),ValMainDiognal(board, mark));
            Cordinate temp = new Cordinate(row, col, temparr[0], temparr[1]);
            return temp;
        }
        if(row+col+1==Board.SIZE){
            int[] temparr = BiggerArr(BiggerArr(ValCol(row, col, board, mark), ValRow(row, col, board, mark)),ValSecondDiognal(board, mark));
            Cordinate temp = new Cordinate(row, col, temparr[0], temparr[1]);
            return temp;
        }
        int[] temparr = BiggerArr(ValRow(row, col, board, mark),ValCol(row, col, board, mark));
        Cordinate temp = new Cordinate(row, col, temparr[0], temparr[1]);
        return temp;
    }


    
    /**
	* Makes a move on the board.
	*/
    private void MakeMove(Cordinate cordinate, Board board, Board.Mark mark) {
        if(cordinate.type.equals("row")){
            PutInRow(cordinate.row, board, mark);
            return;
        }
        if(cordinate.type.equals("col")){
            PutInCol(cordinate.col, board, mark);
            return;
        }
        if(cordinate.type.equals("Main")){
            PutInMainDaigonal(board, mark);
            return;
        }
        if(cordinate.type.equals("Second")){
            PutInSecondDaigonal(board, mark);
            return;
        } 
    }

    /**
	* puts mark in random location.
	*/
    private void ChoseRandomly(Board board, Board.Mark mark) {
        while(true){
            int row = rand.nextInt(Board.SIZE) + 1;
            int col = rand.nextInt(Board.SIZE) + 1;
            if(board.putMark(mark, row-1, col-1)){
                return;
            }     
        }   
    }
        


}
