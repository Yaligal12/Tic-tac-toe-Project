
/**
* interface of player.
*/
interface Player{
    void playTurn(Board board, Board.Mark mark);
    Board.Mark GetMark();
    void Change_Mark();
}