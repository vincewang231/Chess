package chessPieces;
import chess.ChessGame;
import chess.PieceLocation;

/**
 *
 * @author Tyler Falgiano and Vince Wang
 *
 */


public class Rook extends Piece {

	  /** Creates a new Rook piece.
     * @param owner Owner of piece
     * @param id the piece id
     * @param PieceLocation Location to put the rook in
     * @param game Game that the rook belongs to
     */

	public Rook(String owner,String id, PieceLocation PieceLocation,ChessGame game) {
		super(owner,id, PieceLocation, game);
	}


	/**
	 * Checks to see if a piece can reach a specific location on the board
	 * @param endLocation The specific end location
	 * @return Valid if it can reach location
	 */

	@Override
	public boolean canReach(PieceLocation endLocation) {
		PieceLocation temp = new PieceLocation(endLocation.getRow(), endLocation.getColumn());
		if(this.game.getChessBoard().getPieceAt(temp) != null){
			if(this.game.getChessBoard().getPieceAt(temp).getOwner().equals(owner)){
				return false;
			}
		}
		if(this.getPieceLocation().getColumn() == endLocation.getColumn()){
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
		else if(this.getPieceLocation().getRow() == endLocation.getRow()){
			if(this.getPieceLocation().getColumn() < endLocation.getColumn()){
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
		if(this.getPieceLocation().getColumn() == endLocation.getColumn()){ // MOVING UP OR DOWN

			if(this.getPieceLocation().getRow() < endLocation.getRow()){ //MOVING DOWN

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
			}else{   //MOVING UP
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
