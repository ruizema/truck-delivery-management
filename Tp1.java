import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tp1 {

    public static void main(String[] args) throws FileNotFoundException {
	    String input = args[0];
	    String output = args[1];

	    // TODO: Parsing input file
        Scanner scanner = new Scanner(new File(input));
        System.out.println(scanner.nextLine());

        // TODO: Pathfinding algo: nearest location?
        // TODO: Output file
    }
}
