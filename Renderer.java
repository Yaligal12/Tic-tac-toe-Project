
/**
* interface of Renderer.
*/
interface Renderer{
    void drawMarkInBuffer(int rowStart, int colStart, Board.Mark mark);
	void renderBoard(Board board);
}