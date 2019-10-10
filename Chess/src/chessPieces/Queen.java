package chessPieces;
import chess.ChessGame;
import chess.PieceLocation;

/**
 *
 * @author Tyler Falgiano and Vince Wang
 *
 */
public class Queen extends Piece {

	  /** Creates a new Queen piece.
     * @param owner Owner of piece
     * @param id the piece id
     * @param PieceLocation Location to put the queen in
     * @param game Game that the queen belongs to
     */

	public Queen(String owner, String id,PieceLocation PieceLocation,ChessGame game) {
		super(owner, id, PieceLocation,game);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Checks to see if a piece can reach a specific location on the board
	 * @param endLocation The specific end location
	 * @return Valid if it can reach location
	 */

	@Override
	public boolean canReach(PieceLocation endLocation){
		PieceLocation temp = new PieceLocation(endLocation.getRow(), endLocation.getColumn());
		if(this.game.getChessBoard().getPieceAt(temp) != null){
			if(this.game.getChessBoard().getPieceAt(temp).getOwner().equals(owner)){
				return false;
			}
		}
		if(endLocation.getRow() < this.getPieceLocation().getRow() && endLocation.getColumn() > this.getPieceLocation().getColumn()){ //TOP RIGHT
			while(temp.getRow() < this.getPieceLocation().getRow() && temp.getColumn() > this.getPieceLocation().getColumn()){
				temp.decCol();
				temp.incRow();
				if(temp.getRow() == this.getPieceLocation().getRow() && temp.getColumn() == this.getPieceLocation().getColumn()){
					return true;
				}
				if(this.game.getChessBoard().getPieceAt(temp) != null){
					return false;
				}
			}
		}
		else if(endLocation.getRow() < this.getPieceLocation().getRow() && endLocation.getColumn() < this.getPieceLocation().getColumn()){ //TOP LEFT
			while(temp.getRow() < this.getPieceLocation().getRow() && temp.getColumn() < this.getPieceLocation().getColumn()){
				temp.incCol();
				temp.incRow();
				if(temp.getRow() == this.getPieceLocation().getRow() && temp.getColumn() == this.getPieceLocation().getColumn()){
					return true;
				}
				if(this.game.getChessBoard().getPieceAt(temp) != null){
					return false;
				}
			}
		}
		else if(endLocation.getRow() > this.getPieceLocation().getRow() && endLocation.getColumn() > this.getPieceLocation().getColumn()){ //BOTTOM RIGHT
			while(temp.getRow() > this.getPieceLocation().getRow() && temp.getColumn() > this.getPieceLocation().getColumn()){
				temp.decCol();
				temp.decRow();
				if(temp.getRow() == this.getPieceLocation().getRow() && temp.getColumn() == this.getPieceLocation().getColumn()){
					return true;
				}
				if(this.game.getChessBoard().getPieceAt(temp) != null){
					return false;
				}
			}
		}
		else if(endLocation.getRow() > this.getPieceLocation().getRow() && endLocation.getColumn() < this.getPieceLocation().getColumn()){ //BOTTOM LEFT
			while(temp.getRow() > this.getPieceLocation().getRow() && temp.getColumn() < this.getPieceLocation().getColumn()){
				temp.incCol();
				temp.decRow();
				if(temp.getRow() == this.getPieceLocation().getRow() && temp.getColumn() == this.getPieceLocation().getColumn()){
					return true;
				}
				if(this.game.getChessBoard().getPieceAt(temp) != null){
					return false;
				}
			}
		}
		else if(endLocation.getRow() == this.getPieceLocation().getRow() && endLocation.getColumn() != this.getPieceLocation().getColumn()){ //LEFT OR RIGHT
			if(this.getPieceLocation().getColumn() < endLocation.getColumn()){ //RIGHT
				while(temp.getRow() == this.getPieceLocation().getRow() && temp.getColumn() > this.getPieceLocation().getColumn()){
					temp.decCol();
					if(temp.getRow() == this.getPieceLocation().getRow() && temp.getColumn() == this.getPieceLocation().getColumn()){
						return true;
					}
					if(this.game.getChessBoard().getPieceAt(temp) != null){
						return false;
					}
				}
			}
			else{
				while(temp.getRow() == this.getPieceLocation().getRow() && temp.getColumn() < this.getPieceLocation().getColumn()){
					temp.incCol();
					if(temp.getRow() == this.getPieceLocation().getRow() && temp.getColumn() == this.getPieceLocation().getColumn()){
						return true;
					}
					if(this.game.getChessBoard().getPieceAt(temp) != null){
						return false;
					}
				}
			}
		}
		else if(endLocation.getRow() != this.getPieceLocation().getRow() && endLocation.getColumn() == this.getPieceLocation().getColumn()){
			if(this.getPieceLocation().getRow() > endLocation.getRow()){
				while(temp.getRow() < this.getPieceLocation().getRow() && temp.getColumn()== this.getPieceLocation().getColumn()){
					temp.incRow();
					if(temp.getRow() == this.getPieceLocation().getRow() && temp.getColumn() == this.getPieceLocation().getColumn()){
						return true;
					}
					if(this.game.getChessBoard().getPieceAt(temp) != null){
						return false;
					}
				}
			}
			else{
				while(temp.getRow() > this.getPieceLocation().getRow() && temp.getColumn()== this.getPieceLocation().getColumn()){
					temp.decRow();
					if(temp.getRow() == this.getPieceLocation().getRow() && temp.getColumn() == this.getPieceLocation().getColumn()){
						return true;
					}
					if(this.game.getChessBoard().getPieceAt(temp) != null){
						return false;
					}
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

		if(!canReach(endLocation)){
			return false;
		}
		PieceLocation temp = new PieceLocation(this.PieceLocation.getRow(), this.PieceLocation.getColumn());
		if(endLocation.getRow() < this.getPieceLocation().getRow() && endLocation.getColumn() > this.getPieceLocation().getColumn()){ //TOP RIGHT
			temp.decRow();
			temp.incCol();
			while(temp.getRow() != endLocation.getRow() && temp.getColumn() != endLocation.getColumn()){ //MOVING UP ONE BY ONE TO ENSURE NO OTHER PIECE CONFLICT
				if(this.game.getChessBoard().getPieceAt(temp) != null){
					return false;
				}
				temp.decRow();
				temp.incCol();
			}
			if(this.game.getChessBoard().getPieceAt(temp) == null){//PLACE PIECE IF NOTHING AT THAT LOCATION
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
			else{
				if(!(this.game.getChessBoard().getPieceAt(endLocation).owner.equals(this.owner))){ //PLACE PIECE IF OPPOSITE PIECE OCCUPIES
					this.game.getChessBoard().removePieceAt(endLocation);
					this.game.getChessBoard().removePieceAt(this.PieceLocation);
					this.game.getChessBoard().putPieceAt(endLocation, this);
					this.PieceLocation = endLocation;
					return true;
				}
			}
		}
		else if(endLocation.getRow() < this.getPieceLocation().getRow() && endLocation.getColumn() < this.getPieceLocation().getColumn()){ //TOP LEFT
			temp.decRow();
			temp.decCol();
			while(temp.getRow() != endLocation.getRow() && temp.getColumn() != endLocation.getColumn()){ //MOVING UP ONE BY ONE TO ENSURE NO OTHER PIECE CONFLICT
				if(this.game.getChessBoard().getPieceAt(temp) != null){
					return false;
				}
				temp.decRow();
				temp.decCol();
			}
			if(this.game.getChessBoard().getPieceAt(temp) == null){//PLACE PIECE IF NOTHING AT THAT LOCATION
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
			else{
				if(!(this.game.getChessBoard().getPieceAt(endLocation).owner.equals(this.owner))){ //PLACE PIECE IF OPPOSITE PIECE OCCUPIES
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
		else if(endLocation.getRow() > this.getPieceLocation().getRow() && endLocation.getColumn() > this.getPieceLocation().getColumn()){ //BOTTOM RIGHT
			temp.incRow();
			temp.incCol();
			while(temp.getRow() != endLocation.getRow() && temp.getColumn() != endLocation.getColumn()){ //MOVING UP ONE BY ONE TO ENSURE NO OTHER PIECE CONFLICT
				if(this.game.getChessBoard().getPieceAt(temp) != null){
					return false;
				}
				temp.incRow();
				temp.incCol();
			}
			if(this.game.getChessBoard().getPieceAt(temp) == null){//PLACE PIECE IF NOTHING AT THAT LOCATION
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
			else{
				if(!(this.game.getChessBoard().getPieceAt(endLocation).owner.equals(this.owner))){ //PLACE PIECE IF OPPOSITE PIECE OCCUPIES
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
		else if(endLocation.getRow() > this.getPieceLocation().getRow() && endLocation.getColumn() < this.getPieceLocation().getColumn()){ //BOTTOM LEFT
			temp.incRow();
			temp.decCol();
			while(temp.getRow() != endLocation.getRow() && temp.getColumn() != endLocation.getColumn()){ //MOVING UP ONE BY ONE TO ENSURE NO OTHER PIECE CONFLICT
				if(this.game.getChessBoard().getPieceAt(temp) != null){
					return false;
				}
				temp.incRow();
				temp.decCol();
			}
			if(this.game.getChessBoard().getPieceAt(temp) == null){//PLACE PIECE IF NOTHING AT THAT LOCATION
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
			else{
				if(!(this.game.getChessBoard().getPieceAt(endLocation).owner.equals(this.owner))){ //PLACE PIECE IF OPPOSITE PIECE OCCUPIES
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

		else if(this.getPieceLocation().getColumn() == endLocation.getColumn()){ // MOVING UP OR DOWN

			if(this.getPieceLocation().getRow() < endLocation.getRow()){ //MOVING UP

				temp.incRow();
				while(temp.getRow() != endLocation.getRow()){ //MOVING UP ONE BY ONE TO ENSURE NO OTHER PIECE CONFLICT
					if(this.game.getChessBoard().getPieceAt(temp) != null){
						return false;
					}
					temp.incRow();
				}

				if(this.game.getChessBoard().getPieceAt(temp) == null){//PLACE PIECE IF NOTHING AT THAT LOCATION
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
				else{
					if(!(this.game.getChessBoard().getPieceAt(endLocation).owner.equals(this.owner))){ //PLACE PIECE IF OPPOSITE PIECE OCCUPIES
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
			}else{   //MOVING DOWN
				temp.decRow();
				while(temp.getRow() != endLocation.getRow()){ //MOVING UP ONE BY ONE TO ENSURE NO OTHER PIECE CONFLICT
					if(this.game.getChessBoard().getPieceAt(temp) != null){
						return false;
					}
					temp.decRow();

				}
				if(this.game.getChessBoard().getPieceAt(temp) == null){//PLACE PIECE IF NOTHING AT THAT LOCATION
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
				else{
					if(!(this.game.getChessBoard().getPieceAt(endLocation).owner.equals(this.owner))){ //PLACE PIECE IF OPPOSITE PIECE OCCUPIES
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
		else if(this.getPieceLocation().getRow() == endLocation.getRow()){ //MOVING LEFT OR RIGHT

			if(this.getPieceLocation().getColumn() < endLocation.getColumn()){ //MOVING RIGHT

				temp.incCol();
				while(temp.getRow() != endLocation.getRow()){ //MOVING UP ONE BY ONE TO ENSURE NO OTHER PIECE CONFLICT
					if(this.game.getChessBoard().getPieceAt(temp) != null){
						return false;
					}
					temp.incCol();
				}

				if(this.game.getChessBoard().getPieceAt(temp) == null){//PLACE PIECE IF NOTHING AT THAT LOCATION
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
				else{
					if(!(this.game.getChessBoard().getPieceAt(endLocation).owner.equals(this.owner))){ //PLACE PIECE IF OPPOSITE PIECE OCCUPIES
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
			else{ //MOVING LEFT
				temp.decCol();
				while(temp.getRow() != endLocation.getRow()){ //MOVING UP ONE BY ONE TO ENSURE NO OTHER PIECE CONFLICT
					if(this.game.getChessBoard().getPieceAt(temp) != null){
						return false;
					}
					temp.decCol();
				}

				if(this.game.getChessBoard().getPieceAt(temp) == null){//PLACE PIECE IF NOTHING AT THAT LOCATION
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
				else{
					if(!(this.game.getChessBoard().getPieceAt(endLocation).owner.equals(this.owner))){ //PLACE PIECE IF OPPOSITE PIECE OCCUPIES
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

		return false;
	}



}
