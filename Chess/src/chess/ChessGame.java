package chess;

import chessPieces.Bishop;
import chessPieces.King;
import chessPieces.Knight;
import chessPieces.Pawn;
import chessPieces.Piece;
import chessPieces.Queen;
import chessPieces.Rook;


/**
 *
 * @author Tyler Falgiano and Vince Wang
 *
 * creates an instance of a chess game and sets up the pieces to start a new game
 */

public class ChessGame {

	ChessBoard chessBoard;
	Piece WhiteKing;
	Piece BlackKing;
	public ChessGame() {
		chessBoard = new ChessBoard();
		setupBoard();
	}


	/**
	 *
	 * Placing all the pieces in the correct starting spot
	 *
	 */
	private void setupBoard() {
		Piece r1 = new Rook("Black","bR",new PieceLocation(0,0),this);
		Piece r2 = new Rook("Black","bR",new PieceLocation(0,7),this);
		Piece r3 = new Rook("White","wR",new PieceLocation(7,0),this);
		Piece r4 = new Rook("White","wR",new PieceLocation(7,7),this);
		chessBoard.putPieceAt(r1.getPieceLocation(), r1);
		chessBoard.putPieceAt(r2.getPieceLocation(), r2);
		chessBoard.putPieceAt(r3.getPieceLocation(), r3);
		chessBoard.putPieceAt(r4.getPieceLocation(), r4);
		Piece n1 = new Knight("Black","bN",new PieceLocation(0,1),this);
		Piece n2 = new Knight("Black","bN",new PieceLocation(0,6),this);
		Piece n3 = new Knight("White","wN",new PieceLocation(7,1),this);
		Piece n4 = new Knight("White","wN",new PieceLocation(7,6),this);
		chessBoard.putPieceAt(n1.getPieceLocation(), n1);
		chessBoard.putPieceAt(n2.getPieceLocation(), n2);
		chessBoard.putPieceAt(n3.getPieceLocation(), n3);
		chessBoard.putPieceAt(n4.getPieceLocation(), n4);
		Piece b1 = new Bishop("Black","bB",new PieceLocation(0,2),this);
		Piece b2 = new Bishop("Black","bB",new PieceLocation(0,5),this);
		Piece b3 = new Bishop("White","wB",new PieceLocation(7,2),this);
		Piece b4 = new Bishop("White","wB",new PieceLocation(7,5),this);
		chessBoard.putPieceAt(b1.getPieceLocation(), b1);
		chessBoard.putPieceAt(b2.getPieceLocation(), b2);
		chessBoard.putPieceAt(b3.getPieceLocation(), b3);
		chessBoard.putPieceAt(b4.getPieceLocation(), b4);
		Piece q1 = new Queen("Black","bQ",new PieceLocation(0,3),this);
		Piece q2 = new Queen("White","wQ",new PieceLocation(7,3),this);
		chessBoard.putPieceAt(q1.getPieceLocation(), q1);
		chessBoard.putPieceAt(q2.getPieceLocation(), q2);
		BlackKing = new King("Black","bK",new PieceLocation(0,4),this,false);
		WhiteKing = new King("White","wK",new PieceLocation(7,4),this,false);
		chessBoard.putPieceAt(BlackKing.getPieceLocation(), BlackKing);
		chessBoard.putPieceAt(WhiteKing.getPieceLocation(), WhiteKing);
		int i =0;
		for(i=0; i < 8; i++){
			Piece p = new Pawn("Black","bp",new PieceLocation(1,i),this);
			chessBoard.putPieceAt(p.getPieceLocation(), p);
		}
		for(i=0; i < 8; i++){
			Piece p = new Pawn("White","wp",new PieceLocation(6,i),this);
			chessBoard.putPieceAt(p.getPieceLocation(), p);
		}

	}


	public ChessBoard getChessBoard() {
		return chessBoard;
	}

	public Piece getWhiteKing() {
		return WhiteKing;
	}

	public Piece getBlackKing() {
		return BlackKing;
	}

}
