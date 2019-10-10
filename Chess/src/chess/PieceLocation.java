package chess;

/**
 *
 * @author Tyler Falgiano and Vince Wang
 *
 * Holds the row and column for a given piece
 */

public class PieceLocation {
	int row;
	int column;
	public PieceLocation(int row, int column) {
		this.row = row;
		this.column = column;
	}
	public int getRow() {
		return row;
	}
	public void incRow(){
		this.row = row + 1;
	}
	public void decRow(){
		this.row = row - 1;
	}
	public void incCol(){
		this.column = column + 1;
	}
	public void decCol(){
		this.column = column - 1;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}


}
