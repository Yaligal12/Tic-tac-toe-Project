

class Board{ 
    public static int SIZE = 3;
    public static final int WIN_STREAK = 0;
    public enum Mark {BLANK, X, O};
    Mark[][] space;
    int free;


    

    /**
	* Board constructor.
	*/
    public Board(){
        this.space = new Mark[SIZE][SIZE];
        this.free = SIZE*SIZE;
        ResSpace(space);
    }

    /**
	* Put desired Mark on the board.
	*/
    public boolean putMark(Mark mark, int row, int col){
        if(row<0 || row>=SIZE || col<0 || col>=SIZE){
            return false;
        }
        if(this.space[row][col] != Mark.BLANK){
            return false;
        }
        this.space[row][col] = mark;
        this.free--;
        return true;
    }




    /**
	* return Mark from location on the board.
	*/
    public Mark getMark(int row, int col){
        if(row<0 || row>=SIZE || col<0 || col>=SIZE){
            return Mark.BLANK;
        }
        return this.space[row][col];
    }


    /**
	* Prints space field of board.
	*/
    public void PrintSpace(){
        for(int row=0; row<SIZE; row++){
            for(int col=0; col<SIZE; col++){
                System.out.print(this.space[row][col]);
            }
            System.out.println("");
        }
    }

    /**
	* return how much empty spots are left on the board.
	*/
    private void ResSpace(Mark[][] space){
        for(int row=0; row<SIZE; row++){
            for(int col=0; col<SIZE; col++){
                space[row][col] = Mark.BLANK;
            }
        }
    }

    /**
	* Check if the game ended and return the Mark of the winner.
	*/
    public Mark CheckGameEnd() {
        if(rowWin()!=Mark.BLANK){
            return rowWin();
        }
        if(colWin()!=Mark.BLANK){
            return colWin();
        }
        if(MainDiognalWin()!=Mark.BLANK){
            return MainDiognalWin();
        }
        if(SecondDiognalWin()!=Mark.BLANK){
            return SecondDiognalWin();
        }
        return Mark.BLANK;
    }


    /**
	* Check if some won with row win.
	*/
    private Mark rowWin() {
        int Ocount;
        int Xcount;
        for(int row=0; row<SIZE; row++){
            Ocount=0;
            Xcount=0;
            for(int col=0; col<SIZE; col++){
                if(this.space[row][col] == Mark.O){
                    Ocount++;
                }
                if(this.space[row][col] == Mark.X){
                    Xcount++;
                }
            }
            if(Xcount==SIZE){
                return Mark.X;
            }
            if(Ocount==SIZE){
                return Mark.O;
            }
        }
        return Mark.BLANK;
    }

    /**
	* Check if some won with col win.
	*/
    private Mark colWin() {
        int Ocount;
        int Xcount;
        for(int col=0; col<SIZE; col++){
            Ocount=0;
            Xcount=0;
            for(int row=0; row<SIZE; row++){
                if(this.space[row][col] == Mark.O){
                    Ocount++;
                }
                if(this.space[row][col] == Mark.X){
                    Xcount++;
                }
            }
            if(Xcount==SIZE){
                return Mark.X;
            }
            if(Ocount==SIZE){
                return Mark.O;
            }
        }
        return Mark.BLANK;
    }

    /**
	* Check if some won with main diagonal win.
	*/
    private Mark MainDiognalWin() {
        int Ocount=0;
        int Xcount=0;
        for(int i=0; i<SIZE; i++){
            if(this.space[i][i] == Mark.O){
                Ocount++;
            }
            if(this.space[i][i] == Mark.X){
                Xcount++;
            }
        }
        if(Xcount==SIZE){
            return Mark.X;
        }
        if(Ocount==SIZE){
            return Mark.O;
        }
        return Mark.BLANK;
    }

    /**
	* Check if some won with second diagonal win.
	*/
    private Mark SecondDiognalWin() {
        int Ocount=0;
        int Xcount=0;
        for(int i=0; i<SIZE; i++){
            if(this.space[SIZE-i-1][i] == Mark.O){
                Ocount++;
            }
            if(this.space[SIZE-i-1][i] == Mark.X){
                Xcount++;
            }
        }
        if(Xcount==SIZE){
            return Mark.X;
        }
        if(Ocount==SIZE){
            return Mark.O;
        }
        return Mark.BLANK;
    }

}