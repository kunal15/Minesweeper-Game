
public class Cell {
	private char symbol;//the char value of the cell.
	private boolean bomb = false;//true if the cell contains bomb.
	Cell (char c){
		this.symbol = c;
	}
	public char getSymbol() {
		return symbol;
	}
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
	public boolean isBomb() {
		return bomb;
	}
	public void setBomb(boolean bomb) {
		this.bomb = bomb;
	}
}
