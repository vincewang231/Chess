package chessPieces;

import chess.PieceLocation;
/**
 *
 * @author Tyler Falgiano and Vince Wang
 *
 */
public interface PieceInterface {
	boolean move(PieceLocation endLocation);
	boolean canReach(PieceLocation endLocation);
}
