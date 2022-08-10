import java.lang.ProcessBuilder.Redirect.Type;
import java.util.Scanner;


class Tournament{
    int rounds;
    Renderer renderer;
    Player player1;
    Player player2;




    /**
	* Tournament constructor.
	*/
    public Tournament(int rounds, Renderer renderer, Player player1, Player player2){
        this.rounds = rounds;
        this.renderer = renderer;
        this.player1 = player1;
        this.player2 = player2;
    }




    /**
	* Plays tournament and return an array such that in 0 cell the number of times wich player one won, in 1 cell the number of times wich player one won, in 2 cell the number of draws.
	*/
    public int[] playTournament(){
        int[] scores = new int[3];
        for(int i =0; i<this.rounds; i++){
            Game game = new Game(this.player1, this.player2, this.renderer);
            int win = game.run();
            scores[win]++;
            this.player1.Change_Mark();
            this.player2.Change_Mark();
        }
        return scores;
    }






    public static void main(String[] args) {
        if(args.length!=5){
            System.out.println("need to recieve 4 arguments!");
            return;    
        }
        int rounds = Integer.parseInt(args[0]);
        if(rounds<1){
            System.out.println("need more then 0 rounds!");
            return;
        }
        int boardSize = Integer.parseInt(args[1]);
        if(boardSize<3 || boardSize>9){
            System.out.println("the board size needs to be between 3-9!");
            return;
        }
        Board.SIZE = boardSize;
        PlayerFactory factory = new PlayerFactory();
        RendererFactory rendfactory = new RendererFactory();
        Renderer rend = rendfactory.buildRenderer(args[2]);
        if(rend == null){
            System.out.println("renderer needs to be legal!");
            return;
        }
        Player player1 = factory.buildPlayer(args[3],"x");
        Player player2 = factory.buildPlayer(args[4],"o");
        if(player1==null || player2==null){
            System.out.println("both players needs to be legal!");
            return;
        }
        Tournament tour1 = new Tournament(rounds, rend, player1, player2);
        int [] score = tour1.playTournament();
        System.out.println("player 1 won " + score[0] + " times");
        System.out.println("player 2 won " + score[1] + " times");
        System.out.println("it was a draw " + score[2] + " times");
    }




    
}