import java.util.Scanner;

class PlayerFactory{
    private Scanner scanner = new Scanner(System.in);

    /**
	* Player Factory constructor.
	*/
    public PlayerFactory(){

    }


    /**
	* builds player.
	*/
    public Player buildPlayer(String args , String mark){
        Player player;
        System.out.println("Creating new Player");
        switch(args){
            case "human":
                player = new HumanPlayer(mark);
                return player;
            case "clever":
                player = new CleverPlayer(mark);
                return player;
            case "whatever":
                System.out.println("enter the mark of the player: ");
                player = new WhateverPlayer(mark);
                return player;
            default:
                return null;
        }
    }
}

    
