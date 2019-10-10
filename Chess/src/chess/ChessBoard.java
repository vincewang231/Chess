package chess;

import java.util.Arrays;

import chessPieces.Piece;
import chessPieces.*;


/**
 *
 * @author Tyler Falgiano and Vince Wang
 *
 * object class for creating the chessboard
 */
public class ChessBoard {
	private Piece[][] board;

	public ChessBoard(){

		board = new Piece[8][8];

	}

	/**
	 *
	 * @param pieceLocation a given location on board
	 * @return valid if a piece is located at that location
	 *
	 */
	public boolean isPieceAt(PieceLocation pieceLocation){
		if(board[pieceLocation.getRow()][pieceLocation.getColumn()]==null){
			return false;
		}
		return true;
	}

	/**
	 *
	 * @param pieceLocation a given location on board
	 * @return piece at that location
	 *
	 */

	public Piece getPieceAt(PieceLocation pieceLocation){
		return board[pieceLocation.getRow()][pieceLocation.getColumn()];
	}

	/**
	 *
	 * @param pieceLocation a given location on board
	 * @param piece a given piece to place
	 *
	 * Puts a given piece on a given location on the board
	 */

	public void putPieceAt(PieceLocation pieceLocation, Piece piece){
		board[pieceLocation.getRow()][pieceLocation.getColumn()] = piece;
	}

	/**
	 *
	 * @param pieceLocation A given location on board
	 * @param piece A given piece to place
	 *
	 * Removes a given piece on a given location on the board
	 */

	public void removePieceAt(PieceLocation pieceLocation){
		board[pieceLocation.getRow()][pieceLocation.getColumn()] = null;
	}

	/**
	 *
	 * @param oldLocation The old location of the piece before it was moved to the current location
	 * @param currentLocation The location the piece moved to
	 *
	 * Reverts a move that was made due to a check
	 */

	public void revertMove(PieceLocation oldLocation,PieceLocation currentLocation){
		putPieceAt(oldLocation, getPieceAt(currentLocation));
		removePieceAt(currentLocation);
	}

	/**
	 *
	 * @param oldLocation The old location of the piece before it was moved to the current location
	 * @param currentLocation The location the piece moved to
	 * @param oldEnemy The enemy that was taken by the original piece
	 *
	 * Reverts an attack that was made due to a check
	 */

	public void revertAttack(PieceLocation oldLocation, PieceLocation currentLocation,Piece oldEnemy){
		putPieceAt(oldLocation, getPieceAt(currentLocation));
		removePieceAt(currentLocation);
		putPieceAt(currentLocation, oldEnemy);
	}


	public boolean checkScan(String owner,ChessGame game){
		PieceLocation eachLoc;
		Piece tempKing;
		int rowCount = 0;
		int colCount;
		while(rowCount <= 7){
			colCount = 0;
			while(colCount <= 7){
				eachLoc = new PieceLocation(rowCount, colCount);
				if(!(isPieceAt(eachLoc))){ //NO PIECE HERE
					colCount++;
					continue;
				}
				else{ //THERE IS A PIECE HERE
					Piece temp1 = getPieceAt(eachLoc);
					if(owner.equals("White") && temp1.getOwner().equals("Black")){ //ONE OF CURRPLAYER'S PIECES
						tempKing = game.getWhiteKing();
					}
					else if(owner.equals("Black") && temp1.getOwner().equals("White")){ //ONE OF CURRPLAYER'S PIECES
						tempKing = game.getBlackKing();
					}
					else{ //OPPOSING TEAM'S PIECE
						colCount++;
						continue;
					}
					if(temp1.canReach(tempKing.getPieceLocation())){
						return true;
					}
				}
				colCount++;
			}
			rowCount++;
		}



		return false;
	}


	@Override
	public String toString() {
		String s = "";
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (board[row][col] != null) {
					s += " " + board[row][col].getId();
				} else {
					if(row%2 ==0 ){
						if(col%2 !=0){
							s += " ##";
						}
						else{
							s+="   ";
						}
					}
					else if(row%2!=0){
						if(col%2 ==0){
							s += " ##";
						}
						else{
							s+="   ";
						}
					}
				}

			}
			s+=" " + (8-row);
			s += "\n";
		}
		s+= "  a  b  c  d  e  f  g  h \n";
		s+= "\n";
		return s;
	}





}
