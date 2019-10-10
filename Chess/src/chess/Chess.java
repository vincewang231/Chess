package chess;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.math.*;

import chessPieces.*;

/**
 *
 * @author Tyler Falgiano and Vince Wang
 *
 * The main driver that handles input and
 * logic for the chess game
 *
 */
public class Chess {
	public static void main(String[] args) {
		ChessGame game = new ChessGame();
		boolean finished = false;
		String currPlayer = "White";
		Scanner scanner = new Scanner(System.in);
		String input;
		boolean askingForDraw = false;
		Piece currentPiece;
		ArrayList<Character> colLetters = new ArrayList();
		Character[] letters = {'a','b','c','d','e','f','g','h'};
		colLetters.addAll(Arrays.asList(letters));

		System.out.print(game.getChessBoard().toString());
		while(finished == false){
			System.out.print(currPlayer + "'s move: ");
			input = scanner.nextLine();
			if(input.length() <5){
				System.out.println("Illegal move, try again\n");
				continue;
			}
			if(!(Character.isLetter(input.charAt(0))) || !(Character.isLetter(input.charAt(3)))){
				System.out.println("Illegal move, try again\n");
				continue;
			}
			if(!(Character.isDigit(input.charAt(1))) || !(Character.isDigit(input.charAt(4)))){
				System.out.println("Illegal move, try again\n");
				continue;
			}

			if(input.equals("resign")){
				finished = true;
				if(currPlayer.equals("White")){
					System.out.println("Black wins");
				}
				else{
					System.out.println("White wins");
				}
				continue;
			}

			if(input.equals("draw") && askingForDraw ==true){
				finished = true;
				continue;
			}
			else if(input.equals("draw") && askingForDraw == false){
				askingForDraw = false;
				System.out.println("Illegal move, try again\n");
				continue;
			}
			else if((!(input.equals("draw")) && askingForDraw == true)){
				askingForDraw = false;
			}

			int beginColMov = colLetters.indexOf(input.charAt(0));
			int beginRowMov = Character.getNumericValue(input.charAt(1));
			beginRowMov = Math.abs(beginRowMov-8);
			PieceLocation startingLocation = new PieceLocation(beginRowMov,beginColMov);


			//Checking if a piece exists at the location
			if(!(game.getChessBoard().isPieceAt(startingLocation))){
				System.out.println("Illegal move, try again\n");
				continue;

			}else{

				currentPiece = game.getChessBoard().getPieceAt(startingLocation);
				//Ensuring that the correct player is accessing their piece
				if(!(currentPiece.getOwner().equals(currPlayer))){
					System.out.println("Illegal move, try again\n");
					continue;
				}
			}

			int endColMov  = colLetters.indexOf(input.charAt(3));

			int endRowMov = Character.getNumericValue(input.charAt(4));
			if(endRowMov > 8 || endRowMov < 1){
				System.out.println("Illegal move, try again\n");
				continue;
			}
			endRowMov = Math.abs(endRowMov-8);
			PieceLocation endLocation = new PieceLocation(endRowMov,endColMov);


			//return true if valid move
			if(!(currentPiece.move(endLocation))){
				System.out.println("Illegal move, try again\n");
				continue;
			}
			else{
				if(currPlayer.equals("Black")){
					King blackKing = (King) game.getBlackKing();
					blackKing.setInCheck(false);
				}
				else{
					King whiteKing = (King) game.getWhiteKing();
					whiteKing.setInCheck(false);
				}
				Piece temp = game.getChessBoard().getPieceAt(endLocation);
				temp.setHasMoved(true);
				String restOfInput = input.substring(5);

				//Checking for a pawn promotion
				if(temp.getId().charAt(1)=='p' && (temp.getPieceLocation().getRow() == 0 || temp.getPieceLocation().getRow()==7)){
					if(restOfInput.length()==0){
						game.getChessBoard().removePieceAt(endLocation);
						Piece newPiece = new Queen(currPlayer,currPlayer.toLowerCase().charAt(0)+ "Q" , endLocation,game);
						game.getChessBoard().putPieceAt(endLocation, newPiece);
					}
					else{
						if(restOfInput.charAt(1) == 'R'){
							game.getChessBoard().removePieceAt(endLocation);
							Piece newPiece = new Rook(currPlayer,currPlayer.toLowerCase().charAt(0)+ "R" , endLocation,game);
							game.getChessBoard().putPieceAt(endLocation, newPiece);
						}
						else if(restOfInput.charAt(1) == 'N'){
							game.getChessBoard().removePieceAt(endLocation);
							Piece newPiece = new Knight(currPlayer,currPlayer.toLowerCase().charAt(0)+ "N" , endLocation,game);
							game.getChessBoard().putPieceAt(endLocation, newPiece);
						}
						else if(restOfInput.charAt(1) == 'B'){
							game.getChessBoard().removePieceAt(endLocation);
							Piece newPiece = new Bishop(currPlayer,currPlayer.toLowerCase().charAt(0)+ "B" , endLocation,game);
							game.getChessBoard().putPieceAt(endLocation, newPiece);
						}
						else{
							game.getChessBoard().removePieceAt(endLocation);
							Piece newPiece = new Queen(currPlayer,currPlayer.toLowerCase().charAt(0)+ "Q" , endLocation,game);
							game.getChessBoard().putPieceAt(endLocation, newPiece);
						}
					}
				}


				System.out.println(game.getChessBoard().toString());
				Boolean checkBool;
				if(currPlayer.equals("White")){
					checkBool = game.getChessBoard().checkScan("Black", game);
				}
				else{
					checkBool = game.getChessBoard().checkScan("White", game);
				}
				if(checkBool){ //KING IS IN CHECK, CHECK FOR CHECKMATE
					boolean checkmateBool = true;
					int pieceRowCount = 0;
					int pieceColCount = 0;
					while(pieceRowCount <= 7 && checkmateBool != false){ //CHECKING FOR EACH PIECE OF OPPONENT
						pieceColCount = 0;
						while(pieceColCount <= 7 && checkmateBool != false){ //CHECKING FOR EACH PIECE OF OPPONENT
							PieceLocation testLocation = new PieceLocation(pieceRowCount, pieceColCount);
							if(!(game.getChessBoard().isPieceAt(testLocation))){ //IS THERE A PIECE HERE
								pieceColCount++;
								continue;
							}
							if(game.getChessBoard().getPieceAt(testLocation).getOwner().equals("White") && currPlayer.equals("White")){
								pieceColCount++;
								continue;
							}
							if(game.getChessBoard().getPieceAt(testLocation).getOwner().equals("Black") && currPlayer.equals("Black")){
								pieceColCount++;
								continue;
							}
							Piece testPiece = game.getChessBoard().getPieceAt(testLocation);
							int moveRowCount = 0;
							int moveColCount = 0;
							boolean canMove;
							while(moveRowCount <= 7 && checkmateBool != false){
								moveColCount = 0;
								while(moveColCount <= 7 && checkmateBool != false){
									PieceLocation oldLocation = new PieceLocation(testPiece.getPieceLocation().getRow(),testPiece.getPieceLocation().getColumn());
									PieceLocation currentLocation = new PieceLocation(moveRowCount, moveColCount);
									Piece oldEnemy = null;
									if(game.getChessBoard().isPieceAt(currentLocation)){
										oldEnemy = game.getChessBoard().getPieceAt(currentLocation);
									}
									canMove = testPiece.move(currentLocation);
									if(canMove){ //THERE IS A PIECE THE OPPONENT CAN MOVE TO BLOCK THE CHECK
										if(oldEnemy != null){
											game.getChessBoard().revertAttack(oldLocation, currentLocation, oldEnemy);
											testPiece.setPieceLocation(oldLocation);
										}
										else{
											game.getChessBoard().revertMove(oldLocation, currentLocation);
											testPiece.setPieceLocation(oldLocation);
										}
										checkmateBool = false;
									}
									moveColCount++;
								}
								moveRowCount++;
							}
							pieceColCount++;
						}
						pieceRowCount++;
					}

					if(checkmateBool){
						System.out.println("Checkmate");
						finished = true;
						System.out.println(currPlayer + " wins");
						continue;
					}
					else{
						System.out.println("Check");
						if(currPlayer.equals("Black")){
							King whiteKing = (King) game.getWhiteKing();
							whiteKing.setInCheck(true);
						}
						else{
							King blackKing = (King) game.getBlackKing();
							blackKing.setInCheck(true);
						}
					}
				}


				else{ //CHECK FOR STALEMATE
					boolean stalemateBool = true;
					int pieceRowCount = 0;
					int pieceColCount = 0;
					while(pieceRowCount <= 7 && stalemateBool != false){ //CHECKING FOR EACH PIECE OF OPPONENT
						pieceColCount = 0;
						while(pieceColCount <= 7 && stalemateBool != false){ //CHECKING FOR EACH PIECE OF OPPONENT
							PieceLocation testLocation = new PieceLocation(pieceRowCount, pieceColCount);
							if(!(game.getChessBoard().isPieceAt(testLocation))){ //IS THERE A PIECE HERE
								pieceColCount++;
								continue;
							}
							if(game.getChessBoard().getPieceAt(testLocation).getOwner().equals("White") && currPlayer.equals("White")){
								pieceColCount++;
								continue;
							}
							if(game.getChessBoard().getPieceAt(testLocation).getOwner().equals("Black") && currPlayer.equals("Black")){
								pieceColCount++;
								continue;
							}
							Piece testPiece = game.getChessBoard().getPieceAt(testLocation);
							int moveRowCount = 0;
							int moveColCount = 0;
							boolean canMove;
							while(moveRowCount <= 7 && stalemateBool != false){
								moveColCount = 0;
								while(moveColCount <= 7 && stalemateBool != false){
									PieceLocation oldLocation = new PieceLocation(testPiece.getPieceLocation().getRow(),testPiece.getPieceLocation().getColumn());
									PieceLocation currentLocation = new PieceLocation(moveRowCount, moveColCount);
									Piece oldEnemy = null;
									if(game.getChessBoard().isPieceAt(currentLocation)){
										oldEnemy = game.getChessBoard().getPieceAt(currentLocation);
									}
									canMove = testPiece.move(currentLocation);
									if(canMove){ //THERE IS A PIECE THE OPPONENT CAN MOVE TO BLOCK THE CHECK
										if(oldEnemy != null){
											game.getChessBoard().revertAttack(oldLocation, currentLocation, oldEnemy);
											testPiece.setPieceLocation(oldLocation);
										}
										else{
											game.getChessBoard().revertMove(oldLocation, currentLocation);
											testPiece.setPieceLocation(oldLocation);
										}
										stalemateBool = false;
									}
									moveColCount++;
								}
								moveRowCount++;
							}
							pieceColCount++;
						}
						pieceRowCount++;
					}

					if(stalemateBool) {
						System.out.println("Stalemate");
						finished = true;
						System.out.println("Draw");
						continue;
					}
				}

				if(restOfInput.contains("draw?")){
					askingForDraw = true;
				}

			}
			if(currPlayer == "Black"){
				currPlayer = "White";
			}else{
				currPlayer = "Black";
			}


		}

	}

}
