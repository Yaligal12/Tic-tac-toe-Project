class Cordinate{
    int row;
    int col;
    int val;
    String type;

    /**
	* Cordinate constructor.
	*/
    public Cordinate(int row, int col, int val, int type){
        this.row = row;
        this.col = col;
        this.val = val;
        switch(type){
            case 0:
                this.type = "row";
                break;
            case 1:
                this.type = "col";
                break;
            case 2:
                this.type = "Main";
                break;
            case 3:
                this.type = "Second";
                break;
        }
    }
}