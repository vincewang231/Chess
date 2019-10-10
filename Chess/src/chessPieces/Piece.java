package chessPieces;

import chess.ChessGame;
import chess.PieceLocation;

/**
 *
 * @author Tyler Falgiano and Vince Wang
 *
 */

public abstract class Piece implements PieceInterface {
	String owner;
	String id;
	PieceLocation PieceLocation;
	ChessGame game;
	boolean hasMoved = false;

	  /** Sets the fields to each piece
     * @param owner Owner of piece
     * @param id The piece id
     * @param PieceLocation Location to put the piece in
     * @param game Game that the piece belongs to
     */

	public Piece(String owner,String id, PieceLocation PieceLocation, ChessGame game){
		this.owner = owner;
		this.id = id;
		this.PieceLocation = PieceLocation;
		this.game = game;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PieceLocation getPieceLocation() {
		return PieceLocation;
	}

	public void setPieceLocation(PieceLocation pieceLocation) {
		PieceLocation = pieceLocation;
	}

	public boolean isHasMoved() {
		return hasMoved;
	}

	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}



}
