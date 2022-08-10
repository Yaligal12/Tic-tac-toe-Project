import java.util.Scanner;

class RendererFactory{

    private Scanner scanner = new Scanner(System.in);

    /**
	* Renderer Factory constructor.
	*/
    public RendererFactory(){

    }


    /**
	* builds Renderer.
	*/
    public Renderer buildRenderer(String args){
        Renderer renderer;
        System.out.println("Creating new renderer");
        switch(args){
            case "console":
                renderer = new ConsoleRenderer();
                return renderer;
            case "void":
                renderer = new VoidRenderer();
                return renderer;
            default:
                return null;
        }
    }
}