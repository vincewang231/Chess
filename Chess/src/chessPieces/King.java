package chessPieces;

import chess.ChessGame;
import chess.PieceLocation;


/**
 *
 * @author Tyler Falgiano and Vince Wang
 *
 */

public class King extends Piece {
	boolean inCheck;

	  /** Creates a new King piece.
     * @param owner Owner of piece
     * @param id The piece id
     * @param PieceLocation Location to put the king in
     * @param game Game that the king belongs to
     * @param inCheck Boolean to see if king is in check
     */

	public King(String owner,String id,PieceLocation PieceLocation,ChessGame game,boolean inCheck) {
		super(owner, id, PieceLocation,game);
		this.inCheck = inCheck;
	}




	public boolean isInCheck() {
		return inCheck;
	}

	public void setInCheck(boolean inCheck) {
		this.inCheck = inCheck;
	}


	/**
	 * Checks to see if a piece can reach a specific location on the board
	 * @param endLocation The specific end location
	 * @return Valid if it can reach location
	 */
	@Override
	public boolean canReach(PieceLocation endLocation) {
		if(this.getPieceLocation().getColumn() == endLocation.getColumn()){ // MOVING UP OR DOWN
			if(this.getPieceLocation().getRow() > endLocation.getRow() && Math.abs(this.getPieceLocation().getRow() - endLocation.getRow())==1){ //MOVING UP
				if(this.game.getChessBoard().getPieceAt(endLocation) == null){
					//CHECK TO SEE IF MOVE WILL PUT INTO CHECK OR NOT
					return true;
				}
				else{//PIECE IS THERE, CHECK IF OTHER TEAM
					if(!this.game.getChessBoard().getPieceAt(endLocation).owner.equals(this.owner)){
						//CHECK TO SEE IF MOVE WILL PUT INTO CHECK OR NOT
						return true;
					}
				}
			}
			else if(this.getPieceLocation().getRow() < endLocation.getRow() && Math.abs(this.getPieceLocation().getRow() - endLocation.getRow())==1){//MOVING DOWN
				if(this.game.getChessBoard().getPieceAt(endLocation) == null){
					//CHECK TO SEE IF MOVE WILL PUT INTO CHECK OR NOT
					return true;
				}
				else{//PIECE IS THERE, CHECK IF OTHER TEAM
					if(!this.game.getChessBoard().getPieceAt(endLocation).owner.equals(this.owner)){
						//CHECK TO SEE IF MOVE WILL PUT INTO CHECK OR NOT
						return true;
					}
				}
			}
		}
		else if(this.getPieceLocation().getRow() == endLocation.getRow()){ //LEFT OR RIGHT
			if(this.getPieceLocation().getColumn() > endLocation.getColumn() && Math.abs(this.getPieceLocation().getColumn() - endLocation.getColumn())==1){ //MOVING LEFT
				if(this.game.getChessBoard().getPieceAt(endLocation) == null){
					//CHECK TO SEE IF MOVE WILL PUT INTO CHECK OR NOT
					return true;
				}
				else{//PIECE IS THERE, CHECK IF OTHER TEAM
					if(!this.game.getChessBoard().getPieceAt(endLocation).owner.equals(this.owner)){
						//CHECK TO SEE IF MOVE WILL PUT INTO CHECK OR NOT
						return true;
					}
				}
			}
			else if(this.getPieceLocation().getColumn() < endLocation.getColumn() && Math.abs(this.getPieceLocation().getColumn() - endLocation.getColumn())==1){
				if(this.game.getChessBoard().getPieceAt(endLocation) == null){
					//CHECK TO SEE IF MOVE WILL PUT INTO CHECK OR NOT
					return true;
				}
				else{//PIECE IS THERE, CHECK IF OTHER TEAM
					if(!this.game.getChessBoard().getPieceAt(endLocation).owner.equals(this.owner)){
						//CHECK TO SEE IF MOVE WILL PUT INTO CHECK OR NOT
						return true;
					}
				}
			}
		}else if(Math.abs(this.getPieceLocation().getRow() - endLocation.getRow()) == 1 && Math.abs(this.getPieceLocation().getColumn() - endLocation.getColumn())==1){ //MOVING DIAG
			if(this.game.getChessBoard().getPieceAt(endLocation) == null){
				//CHECK TO SEE IF MOVE WILL PUT INTO CHECK OR NOT
				return true;
			}
			else{//PIECE IS THERE, CHECK IF OTHER TEAM
				if(!this.game.getChessBoard().getPieceAt(endLocation).owner.equals(this.owner)){
					//CHECK TO SEE IF MOVE WILL PUT INTO CHECK OR NOT

					return true;
				}
			}
		}

		return false;
	}



	/**
	 * Moving the piece to a specified location
	 * @param endLocation The specified end location to move the piece
	 * @return valid if the piece moved
	 *
	 */

	@Override
	public boolean move(PieceLocation endLocation) {

		//CASTLING
		if(owner.equals("White") && this.getPieceLocation().getRow() == 7 && this.getPieceLocation().getColumn() == 4){
			if(endLocation.getRow() == 7 && endLocation.getColumn() == 6){
				PieceLocation rookLoc = new PieceLocation(7,7);
				if(game.getChessBoard().getPieceAt(rookLoc).getId().equals("wR")){
					if(!(game.getChessBoard().isPieceAt(new PieceLocation(7,5))) && !(game.getChessBoard().isPieceAt(new PieceLocation(7,6)))){
						if(!(this.inCheck) && !(this.hasMoved) && !(game.getChessBoard().getPieceAt(rookLoc).hasMoved) ){//Castle, Move King and Rook
							game.getChessBoard().putPieceAt(endLocation, this);
							game.getChessBoard().putPieceAt(new PieceLocation(7,5), game.getChessBoard().getPieceAt(rookLoc));
							game.getChessBoard().removePieceAt(PieceLocation);
							this.PieceLocation = endLocation;
							game.getChessBoard().getPieceAt(rookLoc).setHasMoved(true);
							game.getChessBoard().removePieceAt(rookLoc);
							game.getChessBoard().getPieceAt(new PieceLocation(7,5)).setPieceLocation(new PieceLocation(7,5));
							return true;
						}
					}
				}
			}
			else if(endLocation.getRow() == 7 && endLocation.getColumn() == 2){
				PieceLocation rookLoc = new PieceLocation(7,0);
				if(game.getChessBoard().getPieceAt(rookLoc).getId().equals("wR")){
					if(!(game.getChessBoard().isPieceAt(new PieceLocation(7,1))) && !(game.getChessBoard().isPieceAt(new PieceLocation(7,2))) && !(game.getChessBoard().isPieceAt(new PieceLocation(7,3)))){
						if(!(this.inCheck) && !(this.hasMoved) && !(game.getChessBoard().getPieceAt(rookLoc).hasMoved) ){//Castle, Move King and Rook
							game.getChessBoard().putPieceAt(endLocation, this);
							game.getChessBoard().putPieceAt(new PieceLocation(7,3), game.getChessBoard().getPieceAt(rookLoc));
							game.getChessBoard().removePieceAt(PieceLocation);
							this.PieceLocation = endLocation;
							game.getChessBoard().getPieceAt(rookLoc).setHasMoved(true);
							game.getChessBoard().removePieceAt(rookLoc);
							game.getChessBoard().getPieceAt(new PieceLocation(7,3)).setPieceLocation(new PieceLocation(7,3));
							return true;
						}
					}
				}
			}
		}
		else if(owner.equals("Black") && this.getPieceLocation().getRow() == 0 && this.getPieceLocation().getColumn() == 4){
			if(endLocation.getRow() == 0 && endLocation.getColumn() == 6){
				PieceLocation rookLoc = new PieceLocation(0,7);
				if(game.getChessBoard().getPieceAt(rookLoc).getId().equals("bR")){
					if(!(game.getChessBoard().isPieceAt(new PieceLocation(0,5))) && !(game.getChessBoard().isPieceAt(new PieceLocation(0,6)))){
						if(!(this.inCheck) && !(this.hasMoved) && !(game.getChessBoard().getPieceAt(rookLoc).hasMoved) ){//Castle, Move King and Rook
							game.getChessBoard().putPieceAt(endLocation, this);
							game.getChessBoard().putPieceAt(new PieceLocation(0,5), game.getChessBoard().getPieceAt(rookLoc));
							game.getChessBoard().removePieceAt(PieceLocation);
							this.PieceLocation = endLocation;
							game.getChessBoard().getPieceAt(rookLoc).setHasMoved(true);
							game.getChessBoard().removePieceAt(rookLoc);
							game.getChessBoard().getPieceAt(new PieceLocation(0,5)).setPieceLocation(new PieceLocation(0,5));
							return true;
						}
					}
				}
			}
			else if(endLocation.getRow() == 0 && endLocation.getColumn() == 2){
				PieceLocation rookLoc = new PieceLocation(0,0);
				if(game.getChessBoard().getPieceAt(rookLoc).getId().equals("bR")){
					if(!(game.getChessBoard().isPieceAt(new PieceLocation(0,1))) && !(game.getChessBoard().isPieceAt(new PieceLocation(0,2))) && !(game.getChessBoard().isPieceAt(new PieceLocation(0,3)))){
						if(!(this.inCheck) && !(this.hasMoved) && !(game.getChessBoard().getPieceAt(rookLoc).hasMoved) ){//Castle, Move King and Rook
							game.getChessBoard().putPieceAt(endLocation, this);
							game.getChessBoard().putPieceAt(new PieceLocation(0,3), game.getChessBoard().getPieceAt(rookLoc));
							game.getChessBoard().removePieceAt(PieceLocation);
							this.PieceLocation = endLocation;
							game.getChessBoard().getPieceAt(rookLoc).setHasMoved(true);
							game.getChessBoard().removePieceAt(rookLoc);
							game.getChessBoard().getPieceAt(new PieceLocation(0,3)).setPieceLocation(new PieceLocation(0,3));
							return true;
						}
					}
				}
			}
		}


		if(this.getPieceLocation().getColumn() == endLocation.getColumn()){ // MOVING UP OR DOWN
			if(this.getPieceLocation().getRow() > endLocation.getRow() && Math.abs(this.getPieceLocation().getRow() - endLocation.getRow())==1){ //MOVING UP
				if(this.game.getChessBoard().getPieceAt(endLocation) == null){
					//CHECK TO SEE IF MOVE WILL PUT INTO CHECK OR NOT
					PieceLocation oldLocation = new PieceLocation(this.getPieceLocation().getRow(),this.getPieceLocation().getColumn());
					this.game.getChessBoard().removePieceAt(this.PieceLocation);
					this.game.getChessBoard().putPieceAt(endLocation, this);
					this.PieceLocation = endLocation;
					if(!(game.getChessBoard().checkScan(this.owner,this.game))){
						return true;
					}
					else{
						game.getChessBoard().revertMove(oldLocation,this.getPieceLocation());
						this.PieceLocation = oldLocation;
					}
				}
				else{//PIECE IS THERE, CHECK IF OTHER TEAM
					if(!this.game.getChessBoard().getPieceAt(endLocation).owner.equals(this.owner)){
						//CHECK TO SEE IF MOVE WILL PUT INTO CHECK OR NOT
						PieceLocation oldLocation = new PieceLocation(this.getPieceLocation().getRow(),this.getPieceLocation().getColumn());
						Piece oldEnemy = game.getChessBoard().getPieceAt(endLocation);
						this.game.getChessBoard().removePieceAt(endLocation);
						this.game.getChessBoard().removePieceAt(this.PieceLocation);
						this.game.getChessBoard().putPieceAt(endLocation, this);
						this.PieceLocation = endLocation;
						if(!(game.getChessBoard().checkScan(this.owner,this.game))){
							return true;
						}
						else{
							game.getChessBoard().revertAttack(oldLocation, this.getPieceLocation(), oldEnemy);
							this.PieceLocation = oldLocation;
						}
					}
				}
			}
			else if(this.getPieceLocation().getRow() < endLocation.getRow() && Math.abs(this.getPieceLocation().getRow() - endLocation.getRow())==1){//MOVING DOWN
				if(this.game.getChessBoard().getPieceAt(endLocation) == null){
					//CHECK TO SEE IF MOVE WILL PUT INTO CHECK OR NOT
					PieceLocation oldLocation = new PieceLocation(this.getPieceLocation().getRow(),this.getPieceLocation().getColumn());
					this.game.getChessBoard().removePieceAt(this.PieceLocation);
					this.game.getChessBoard().putPieceAt(endLocation, this);
					this.PieceLocation = endLocation;
					if(!(game.getChessBoard().checkScan(this.owner,this.game))){
						return true;
					}
					else{
						game.getChessBoard().revertMove(oldLocation,this.getPieceLocation());
						this.PieceLocation = oldLocation;
					}
				}
				else{//PIECE IS THERE, CHECK IF OTHER TEAM
					if(!this.game.getChessBoard().getPieceAt(endLocation).owner.equals(this.owner)){
						//CHECK TO SEE IF MOVE WILL PUT INTO CHECK OR NOT
						PieceLocation oldLocation = new PieceLocation(this.getPieceLocation().getRow(),this.getPieceLocation().getColumn());
						Piece oldEnemy = game.getChessBoard().getPieceAt(endLocation);
						this.game.getChessBoard().removePieceAt(endLocation);
						this.game.getChessBoard().removePieceAt(this.PieceLocation);
						this.game.getChessBoard().putPieceAt(endLocation, this);
						this.PieceLocation = endLocation;
						if(!(game.getChessBoard().checkScan(this.owner,this.game))){
							return true;
						}
						else{
							game.getChessBoard().revertAttack(oldLocation, this.getPieceLocation(), oldEnemy);
							this.PieceLocation = oldLocation;
						}
					}
				}
			}
		}
		else if(this.getPieceLocation().getRow() == endLocation.getRow()){ //LEFT OR RIGHT
			if(this.getPieceLocation().getColumn() > endLocation.getColumn() && Math.abs(this.getPieceLocation().getColumn() - endLocation.getColumn())==1){ //MOVING LEFT
				if(this.game.getChessBoard().getPieceAt(endLocation) == null){
					//CHECK TO SEE IF MOVE WILL PUT INTO CHECK OR NOT
					PieceLocation oldLocation = new PieceLocation(this.getPieceLocation().getRow(),this.getPieceLocation().getColumn());
					this.game.getChessBoard().removePieceAt(this.PieceLocation);
					this.game.getChessBoard().putPieceAt(endLocation, this);
					this.PieceLocation = endLocation;
					if(!(game.getChessBoard().checkScan(this.owner,this.game))){
						return true;
					}
					else{
						game.getChessBoard().revertMove(oldLocation,this.getPieceLocation());
						this.PieceLocation = oldLocation;
					}
				}
				else{//PIECE IS THERE, CHECK IF OTHER TEAM
					if(!this.game.getChessBoard().getPieceAt(endLocation).owner.equals(this.owner)){
						//CHECK TO SEE IF MOVE WILL PUT INTO CHECK OR NOT
						PieceLocation oldLocation = new PieceLocation(this.getPieceLocation().getRow(),this.getPieceLocation().getColumn());
						Piece oldEnemy = game.getChessBoard().getPieceAt(endLocation);
						this.game.getChessBoard().removePieceAt(endLocation);
						this.game.getChessBoard().removePieceAt(this.PieceLocation);
						this.game.getChessBoard().putPieceAt(endLocation, this);
						this.PieceLocation = endLocation;
						if(!(game.getChessBoard().checkScan(this.owner,this.game))){
							return true;
						}
						else{
							game.getChessBoard().revertAttack(oldLocation, this.getPieceLocation(), oldEnemy);
							this.PieceLocation = oldLocation;
						}
					}
				}
			}
			else if(this.getPieceLocation().getColumn() < endLocation.getColumn() && Math.abs(this.getPieceLocation().getColumn() - endLocation.getColumn())==1){
				if(this.game.getChessBoard().getPieceAt(endLocation) == null){
					//CHECK TO SEE IF MOVE WILL PUT INTO CHECK OR NOT
					PieceLocation oldLocation = new PieceLocation(this.getPieceLocation().getRow(),this.getPieceLocation().getColumn());
					this.game.getChessBoard().removePieceAt(this.PieceLocation);
					this.game.getChessBoard().putPieceAt(endLocation, this);
					this.PieceLocation = endLocation;
					if(!(game.getChessBoard().checkScan(this.owner,this.game))){
						return true;
					}
					else{
						game.getChessBoard().revertMove(oldLocation,this.getPieceLocation());
						this.PieceLocation = oldLocation;
					}
				}
				else{//PIECE IS THERE, CHECK IF OTHER TEAM
					if(!this.game.getChessBoard().getPieceAt(endLocation).owner.equals(this.owner)){
						//CHECK TO SEE IF MOVE WILL PUT INTO CHECK OR NOT
						PieceLocation oldLocation = new PieceLocation(this.getPieceLocation().getRow(),this.getPieceLocation().getColumn());
						Piece oldEnemy = game.getChessBoard().getPieceAt(endLocation);
						this.game.getChessBoard().removePieceAt(endLocation);
						this.game.getChessBoard().removePieceAt(this.PieceLocation);
						this.game.getChessBoard().putPieceAt(endLocation, this);
						this.PieceLocation = endLocation;
						if(!(game.getChessBoard().checkScan(this.owner,this.game))){
							return true;
						}
						else{
							game.getChessBoard().revertAttack(oldLocation, this.getPieceLocation(), oldEnemy);
							this.PieceLocation = oldLocation;
						}
					}
				}
			}
		}else if(Math.abs(this.getPieceLocation().getRow() - endLocation.getRow()) == 1 && Math.abs(this.getPieceLocation().getColumn() - endLocation.getColumn())==1){ //MOVING DIAG
			if(this.game.getChessBoard().getPieceAt(endLocation) == null){
				//CHECK TO SEE IF MOVE WILL PUT INTO CHECK OR NOT
				PieceLocation oldLocation = new PieceLocation(this.getPieceLocation().getRow(),this.getPieceLocation().getColumn());
				this.game.getChessBoard().removePieceAt(this.PieceLocation);
				this.game.getChessBoard().putPieceAt(endLocation, this);
				this.PieceLocation = endLocation;
				if(!(game.getChessBoard().checkScan(this.owner,this.game))){
					return true;
				}
				else{
					game.getChessBoard().revertMove(oldLocation,this.getPieceLocation());
					this.PieceLocation = oldLocation;
				}
			}
			else{//PIECE IS THERE, CHECK IF OTHER TEAM
				if(!this.game.getChessBoard().getPieceAt(endLocation).owner.equals(this.owner)){
					//CHECK TO SEE IF MOVE WILL PUT INTO CHECK OR NOT
					PieceLocation oldLocation = new PieceLocation(this.getPieceLocation().getRow(),this.getPieceLocation().getColumn());
					Piece oldEnemy = game.getChessBoard().getPieceAt(endLocation);
					this.game.getChessBoard().removePieceAt(endLocation);
					this.game.getChessBoard().removePieceAt(this.PieceLocation);
					this.game.getChessBoard().putPieceAt(endLocation, this);
					this.PieceLocation = endLocation;
					if(!(game.getChessBoard().checkScan(this.owner,this.game))){
						return true;
					}
					else{
						game.getChessBoard().revertAttack(oldLocation, this.getPieceLocation(), oldEnemy);
						this.PieceLocation = oldLocation;
					}
				}
			}
		}


		return false;
	}
}


