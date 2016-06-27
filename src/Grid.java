
public class Grid {
	private static int rows, columns;
	static Cell[][] cell;
	static int[] x_cordinate;
	static int[] y_cordinate;
	static int i = 0;

	public int getRows() {
		return rows;
	}

	public static void setRows(int r) {
		rows = r;
	}

	public static int getColumns() {
		return columns;
	}

	public static void setColumns(int c) {
		columns = c;
	}
	/*set the cells in the grid*/
	public static void setCells() {
		x_cordinate = new int[10000];
		y_cordinate = new int[10000];
		cell = new Cell[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				cell[i][j] = new Cell('-');
			}
		}
	}
	
	public static void setBombs(int x, int y) {
		cell[x][y].setBomb(true);
	}

	/*to play the game*/
	public static void play(String str) {
		String[] s = str.split(" ");
		if (s[0].equals("undo")) {
			i--;
			cell[x_cordinate[Math.max(0, i)]][y_cordinate[Math.max(0, i)]].setSymbol('-');
			print();
			return;
		}
		x_cordinate[Math.max(0, i)] = Integer.parseInt(s[0]);//stores previous positions for 'undo' input.
		y_cordinate[Math.max(0, i)] = Integer.parseInt(s[1]);//stores previous positions for 'undo' input.
		if (cell[x_cordinate[Math.max(0, i)]][y_cordinate[Math.max(0, i)]].getSymbol() == '*' && !str.equals("undo")) {
			System.out.println("You lose!");
			return;
		} 
		i++;
		if (s[2].equals("0"))
			cell[Integer.parseInt(s[0])][Integer.parseInt(s[1])].setSymbol('|');
		else if (s[2].equals("1"))
			getBombCount(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
		else {
			System.out.println("Invalid input");
			return;
		}
		print();
	}

	/*count the number of bombs in the neighbourhood of the clicked cell*/
	public static void getBombCount(int x, int y) {
		int count = 0;
		if (cell[x][y].isBomb()) {
			cell[x][y].setSymbol('*');
			return;
		}
		for (int i = Math.max(0, x - 1); i <= Math.min(rows - 1, x + 1); i++) {
			for (int j = Math.max(0, y - 1); j <= Math.min(columns - 1, y + 1); j++) {
				if (cell[i][j].isBomb())
					count++;
			}
		}
		cell[x][y].setSymbol((char) (count + 48));
	}

	public static void print() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				System.out.print(cell[i][j].getSymbol());
			}
			System.out.println();
		}
	}

	/*to check if the player wins or not*/
	public static boolean win() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (!cell[i][j].isBomb() && cell[i][j].getSymbol() == '-') {
					return false;
				}
				if (cell[i][j].isBomb() && cell[i][j].getSymbol() != '|') {
					return false;
				}
			}
		}
		return true;
	}
	
	/*to check if the entered input is undo or not*/
	public static boolean checkUndo(String str) {
		String[] s = str.split(" ");
		if (s[0].equals("undo"))
			return false;
		return cell[Integer.parseInt(s[0])][Integer.parseInt(s[1])].getSymbol() == '*';
	}

	public static void rules() {
		System.out.println("Rules");
		System.out.println("1) Enter the cordinates first.");
		System.out.println(
				"2) If you want to flag the cordinate, enter a 0 after the cordinates or enter 1 to get the bomb count of the cordinate.Other inputs would result in an invalid input.");
		System.out.println("3) You have certain amount of undos available. Use them wisely.");
		System.out.println(
				"4) If you open a bombed cell and you Either don't undo it OR you don't have any undos left, you lose.");
		System.out.println("5) If all the non bombed cells are checked then you win.");
	}

}
