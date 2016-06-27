import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tester {
	
    /*Kunal Khatri          ID: 201501011*/
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(new File("C:\\Users\\Admin\\workspace\\Project\\abc"));
		int row = sc.nextInt(), column = sc.nextInt();//scans the rows and columns of the grid.
		Grid.setRows(row);
		Grid.setColumns(column);
		int noOfBomb = sc.nextInt();//scans the number of bombs t be placed in the grid.
		Grid.setCells();//sets the cells of the grid.
		for (int i = 0; i < noOfBomb; i++) {
			Grid.setBombs(sc.nextInt(), sc.nextInt());//sets the bombs in the grid.
		}
		int undosAvailable = sc.nextInt();//scans the number of available undos.
		Scanner sc1 = new Scanner(System.in);
		int noOfStepsUsed = 0, undosUsed = 0;//keeps a count of the number of undos and total steps.
		Grid.rules();//a set of rules defined for this game.
		System.out.println();
		Grid.print();
		while (true) {
			if (Grid.win()) {
				System.out.println("You win.");//checks if the user has won or not.
				break;
			}
			String str = sc1.nextLine();
			noOfStepsUsed++;
			if (str.equals("undo")) {
				undosAvailable--;
				undosUsed++;
			}
			if (undosAvailable < 0 && str.equals("undo")) {
				System.out.println("Sorry no unods left");//checks if undos are left or not.
				undosUsed--;
				noOfStepsUsed--;
				continue;
			}
			Grid.play(str);//the user plays the game by putting in numbers.
			/*to check if the user inputs undo after a bomb is called or not and the consequence of his input*/
			if (Grid.checkUndo(str)) { 
				noOfStepsUsed++;
				if (undosAvailable <= 0) {
					System.out.println("Sorry no undos left. You lose.");
					noOfStepsUsed--;
					break;
				}
				System.out.println("Undo or end the game");
				if (sc1.nextLine().equals("undo")) {
					undosAvailable--;
					undosUsed++;
					Grid.play("undo");
				} 
				else {
					System.out.println("Sorry You lose.");
					break;
				}
			}
		}
		System.out.println("Total undos used : " + undosUsed);//number of undos used.
		System.out.println("Total steps taken : " + noOfStepsUsed);//number of steps taken.
		sc.close();
		sc1.close();
	}
}
