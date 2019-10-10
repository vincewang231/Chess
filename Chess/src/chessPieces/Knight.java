package chessPieces;
import chess.ChessGame;
import chess.PieceLocation;

import java.sql.SQLOutput;

/**
 *
 * @author Tyler Falgiano and Vince Wang
 *
 */

public class Knight extends Piece {

	  /** Creates a new Knight piece.
     * @param owner Owner of piece
     * @param id The piece id
     * @param PieceLocation Location to put the knight in
     * @param game Game that the knight belongs to
     */

	public Knight(String owner, String id,PieceLocation PieceLocation,ChessGame game) {
		super(owner, id, PieceLocation,game);
	}

	/**
	 * Checks to see if a piece can reach a specific location on the board
	 * @param endLocation The specific end location
	 * @return Valid if it can reach location
	 */

	@Override
	public boolean canReach(PieceLocation endLocation) {

		if((endLocation.getRow() == this.getPieceLocation().getRow() - 2) && (endLocation.getColumn() == this.getPieceLocation().getColumn() + 1)){ //UP 2 RIGHT 1
			if(this.game.getChessBoard().getPieceAt(endLocation) != null){ //THERE IS ANOTHER PIECE HERE
				if(!this.game.getChessBoard().getPieceAt(endLocation).owner.equals(this.owner)){ //OPPONENT'S PIECE
					return true;
				}
				else{ //OWN COLOR'S PIECE AT THE LOCATION
					return false;
				}
			}
			else{ //MOVE HERE
				this.game.getChessBoard().removePieceAt(this.PieceLocation);
				this.game.getChessBoard().putPieceAt(endLocation, this);
				this.PieceLocation = endLocation;
				return true;
			}
		}
		else if((endLocation.getRow() == this.getPieceLocation().getRow() - 1) && (endLocation.getColumn() == this.getPieceLocation().getColumn() + 2)){ //UP 1 RIGHT 2
			if(this.game.getChessBoard().getPieceAt(endLocation) != null){ //THERE IS ANOTHER PIECE HERE
				if(!this.game.getChessBoard().getPieceAt(endLocation).owner.equals(this.owner)){ //OPPONENT'S PIECE
					return true;
				}
				else{ //OWN COLOR'S PIECE AT THE LOCATION
					return false;
				}
			}
			else{ //MOVE HERE
				return true;
			}
		}
		else if((endLocation.getRow() == this.getPieceLocation().getRow() + 1) && (endLocation.getColumn() == this.getPieceLocation().getColumn() + 2)){ //RIGHT 2 DOWN 1
			if(this.game.getChessBoard().getPieceAt(endLocation) != null){ //THERE IS ANOTHER PIECE HERE
				if(!this.game.getChessBoard().getPieceAt(endLocation).owner.equals(this.owner)){ //OPPONENT'S PIECE
					return true;
				}
				else{ //OWN COLOR'S PIECE AT THE LOCATION
					return false;
				}
			}
			else{ //MOVE HERE
				return true;
			}
		}
		else if((endLocation.getRow() == this.getPieceLocation().getRow() + 2) && (endLocation.getColumn() == this.getPieceLocation().getColumn() + 1)){ //DOWN 2 RIGHT 1
			if(this.game.getChessBoard().getPieceAt(endLocation) != null){ //THERE IS ANOTHER PIECE HERE
				if(!this.game.getChessBoard().getPieceAt(endLocation).owner.equals(this.owner)){ //OPPONENT'S PIECE
					return true;
				}
				else{ //OWN COLOR'S PIECE AT THE LOCATION
					return false;
				}
			}
			else{ //MOVE HERE
				return true;
			}
		}
		else if((endLocation.getRow() == this.getPieceLocation().getRow() + 2) && (endLocation.getColumn() == this.getPieceLocation().getColumn() - 1)){ //DOWN 2 LEFT 1
			if(this.game.getChessBoard().getPieceAt(endLocation) != null){ //THERE IS ANOTHER PIECE HERE
				if(!this.game.getChessBoard().getPieceAt(endLocation).owner.equals(this.owner)){ //OPPONENT'S PIECE
					return true;
				}
				else{ //OWN COLOR'S PIECE AT THE LOCATION
					return false;
				}
			}
			else{ //MOVE HERE
				return true;
			}
		}
		else if((endLocation.getRow() == this.getPieceLocation().getRow() + 1) && (endLocation.getColumn() == this.getPieceLocation().getColumn() - 2)){ //LEFT 2 DOWN 1
			if(this.game.getChessBoard().getPieceAt(endLocation) != null){ //THERE IS ANOTHER PIECE HERE
				if(!this.game.getChessBoard().getPieceAt(endLocation).owner.equals(this.owner)){ //OPPONENT'S PIECE
					return true;
				}
				else{ //OWN COLOR'S PIECE AT THE LOCATION
					return false;
				}
			}
			else{ //MOVE HERE
				return true;
			}
		}
		else if((endLocation.getRow() == this.getPieceLocation().getRow() - 1) && (endLocation.getColumn() == this.getPieceLocation().getColumn() - 2)){ //LEFT 2 UP 1
			if(this.game.getChessBoard().getPieceAt(endLocation) != null){ //THERE IS ANOTHER PIECE HERE
				if(!this.game.getChessBoard().getPieceAt(endLocation).owner.equals(this.owner)){ //OPPONENT'S PIECE
					return true;
				}
				else{ //OWN COLOR'S PIECE AT THE LOCATION
					return false;
				}
			}
			else{ //MOVE HERE
				return true;
			}
		}
		else if((endLocation.getRow() == this.getPieceLocation().getRow() - 2) && (endLocation.getColumn() == this.getPieceLocation().getColumn() - 1)){ //UP 2 LEFT 1
			if(this.game.getChessBoard().getPieceAt(endLocation) != null){ //THERE IS ANOTHER PIECE HERE
				if(!this.game.getChessBoard().getPieceAt(endLocation).owner.equals(this.owner)){ //OPPONENT'S PIECE
					return true;
				}
				else{ //OWN COLOR'S PIECE AT THE LOCATION
					return false;
				}
			}
			else{ //MOVE HERE
				return true;
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
		if((endLocation.getRow() == this.getPieceLocation().getRow() - 2) && (endLocation.getColumn() == this.getPieceLocation().getColumn() + 1)){ //UP 2 RIGHT 1
			if(this.game.getChessBoard().getPieceAt(endLocation) != null){ //THERE IS ANOTHER PIECE HERE
				if(!this.game.getChessBoard().getPieceAt(endLocation).owner.equals(this.owner)){ //OPPONENT'S PIECE
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
				else{ //OWN COLOR'S PIECE AT THE LOCATION
					return false;
				}
			}
			else{ //MOVE HERE
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
		}
		else if((endLocation.getRow() == this.getPieceLocation().getRow() - 1) && (endLocation.getColumn() == this.getPieceLocation().getColumn() + 2)){ //UP 1 RIGHT 2
			if(this.game.getChessBoard().getPieceAt(endLocation) != null){ //THERE IS ANOTHER PIECE HERE
				if(!this.game.getChessBoard().getPieceAt(endLocation).owner.equals(this.owner)){ //OPPONENT'S PIECE
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
				else{ //OWN COLOR'S PIECE AT THE LOCATION
					return false;
				}
			}
			else{ //MOVE HERE
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
		}
		else if((endLocation.getRow() == this.getPieceLocation().getRow() + 1) && (endLocation.getColumn() == this.getPieceLocation().getColumn() + 2)){ //RIGHT 2 DOWN 1
			if(this.game.getChessBoard().getPieceAt(endLocation) != null){ //THERE IS ANOTHER PIECE HERE
				if(!this.game.getChessBoard().getPieceAt(endLocation).owner.equals(this.owner)){ //OPPONENT'S PIECE
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
				else{ //OWN COLOR'S PIECE AT THE LOCATION
					return false;
				}
			}
			else{ //MOVE HERE
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
		}
		else if((endLocation.getRow() == this.getPieceLocation().getRow() + 2) && (endLocation.getColumn() == this.getPieceLocation().getColumn() + 1)){ //DOWN 2 RIGHT 1
			if(this.game.getChessBoard().getPieceAt(endLocation) != null){ //THERE IS ANOTHER PIECE HERE
				if(!this.game.getChessBoard().getPieceAt(endLocation).owner.equals(this.owner)){ //OPPONENT'S PIECE
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
				else{ //OWN COLOR'S PIECE AT THE LOCATION
					return false;
				}
			}
			else{ //MOVE HERE
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
		}
		else if((endLocation.getRow() == this.getPieceLocation().getRow() + 2) && (endLocation.getColumn() == this.getPieceLocation().getColumn() - 1)){ //DOWN 2 LEFT 1
			if(this.game.getChessBoard().getPieceAt(endLocation) != null){ //THERE IS ANOTHER PIECE HERE
				if(!this.game.getChessBoard().getPieceAt(endLocation).owner.equals(this.owner)){ //OPPONENT'S PIECE
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
				else{ //OWN COLOR'S PIECE AT THE LOCATION
					return false;
				}
			}
			else{ //MOVE HERE
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
		}
		else if((endLocation.getRow() == this.getPieceLocation().getRow() + 1) && (endLocation.getColumn() == this.getPieceLocation().getColumn() - 2)){ //LEFT 2 DOWN 1
			if(this.game.getChessBoard().getPieceAt(endLocation) != null){ //THERE IS ANOTHER PIECE HERE
				if(!this.game.getChessBoard().getPieceAt(endLocation).owner.equals(this.owner)){ //OPPONENT'S PIECE
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
				else{ //OWN COLOR'S PIECE AT THE LOCATION
					return false;
				}
			}
			else{ //MOVE HERE
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
		}
		else if((endLocation.getRow() == this.getPieceLocation().getRow() - 1) && (endLocation.getColumn() == this.getPieceLocation().getColumn() - 2)){ //LEFT 2 UP 1
			if(this.game.getChessBoard().getPieceAt(endLocation) != null){ //THERE IS ANOTHER PIECE HERE
				if(!this.game.getChessBoard().getPieceAt(endLocation).owner.equals(this.owner)){ //OPPONENT'S PIECE
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
				else{ //OWN COLOR'S PIECE AT THE LOCATION
					return false;
				}
			}
			else{ //MOVE HERE
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
		}
		else if((endLocation.getRow() == this.getPieceLocation().getRow() - 2) && (endLocation.getColumn() == this.getPieceLocation().getColumn() - 1)){ //UP 2 LEFT 1
			if(this.game.getChessBoard().getPieceAt(endLocation) != null){ //THERE IS ANOTHER PIECE HERE
				if(!this.game.getChessBoard().getPieceAt(endLocation).owner.equals(this.owner)){ //OPPONENT'S PIECE
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
				else{ //OWN COLOR'S PIECE AT THE LOCATION
					return false;
				}
			}
			else{ //MOVE HERE
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
		}
		else{
			return false;
		}
		return false;
	}
}
