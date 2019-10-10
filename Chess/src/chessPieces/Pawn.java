package chessPieces;
import chess.PieceLocation;
import chess.*;

/**
 *
 * @author Tyler Falgiano and Vince Wang
 *
 */

public class Pawn extends Piece {

	  /** Creates a new pawn piece.
     * @param owner Owner of piece
     * @param id The piece id
     * @param PieceLocation Location to put the pawn in
     * @param game Game that the pawn belongs to
     */

	public Pawn(String owner, String id, PieceLocation PieceLocation,ChessGame game) {
		super(owner, id,PieceLocation,game);
	}


	/**
	 * Checks to see if a piece can reach a specific location on the board
	 * @param endLocation The specific end location
	 * @return Valid if it can reach location
	 */

	@Override
	public boolean canReach(PieceLocation endLocation) {

		if(this.getPieceLocation().getColumn() == endLocation.getColumn()){
			if(this.getPieceLocation().getRow() - endLocation.getRow() == 1 && this.owner.equals("White")){
				if(this.game.getChessBoard().getPieceAt(endLocation) == null){
					return true;
				}else{
					return false;
				}
			}
			//Owner is black
			else if(endLocation.getRow() - this.getPieceLocation().getRow() == 1 && this.owner.equals("Black")){
				if(this.game.getChessBoard().getPieceAt(endLocation) == null){
						return true;
				}else{
					return false;
				}
			}
			else if(endLocation.getRow() - this.getPieceLocation().getRow() == 2 && this.owner.equals("Black") && this.PieceLocation.getRow() == 1){
				if(this.game.getChessBoard().getPieceAt(endLocation) == null && (this.game.getChessBoard().getPieceAt(new PieceLocation(endLocation.getRow()-1,endLocation.getColumn()))== null)){
						return true;
				}else{
					return false;
				}
			}
			else if(this.getPieceLocation().getRow() - endLocation.getRow() == 2 && this.owner.equals("White") && this.PieceLocation.getRow() == 6){
				if(this.game.getChessBoard().getPieceAt(endLocation) == null && (this.game.getChessBoard().getPieceAt(new PieceLocation(endLocation.getRow()+1,endLocation.getColumn()))== null)){
						return true;
				}else{
					return false;
				}
			}
			else{
				return false;
			}
		}
		else if(Math.abs(this.getPieceLocation().getRow() - endLocation.getRow()) == 1 && Math.abs(this.getPieceLocation().getColumn() - endLocation.getColumn())==1){
			if(!this.game.getChessBoard().getPieceAt(endLocation).owner.equals(this.owner)){
				if(this.owner.equals("White")){
					if(this.getPieceLocation().getRow() - endLocation.getRow() == 1 && Math.abs(this.getPieceLocation().getColumn() - endLocation.getColumn())==1 ){
						return true;
					}
				}
				//Black
				else{
					if( endLocation.getRow()- this.getPieceLocation().getRow() == 1 && Math.abs(this.getPieceLocation().getColumn() - endLocation.getColumn())==1 ){
						return true;
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

		//moving straight up
		if(this.getPieceLocation().getColumn() == endLocation.getColumn()){


			if(this.getPieceLocation().getRow() - endLocation.getRow() == 1 && this.owner.equals("White")){
				PieceLocation oldLocation = new PieceLocation(this.getPieceLocation().getRow(),this.getPieceLocation().getColumn());
				if(this.game.getChessBoard().getPieceAt(endLocation) == null){
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
				}else{
					return false;
				}
			}
			//Owner is black
			else if(endLocation.getRow() - this.getPieceLocation().getRow() == 1 && this.owner.equals("Black")){
				if(this.game.getChessBoard().getPieceAt(endLocation) == null){
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
				}else{
					return false;
				}
			}
			else if(endLocation.getRow() - this.getPieceLocation().getRow() == 2 && this.owner.equals("Black") && this.PieceLocation.getRow() == 1){
				if(this.game.getChessBoard().getPieceAt(endLocation) == null && (this.game.getChessBoard().getPieceAt(new PieceLocation(endLocation.getRow()-1,endLocation.getColumn()))== null)){
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
				}else{
					return false;
				}
			}
			else if(this.getPieceLocation().getRow() - endLocation.getRow() == 2 && this.owner.equals("White") && this.PieceLocation.getRow() == 6){
				if(this.game.getChessBoard().getPieceAt(endLocation) == null && (this.game.getChessBoard().getPieceAt(new PieceLocation(endLocation.getRow()+1,endLocation.getColumn()))== null)){
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
				}else{
					return false;
				}
			}
			else{
				return false;
			}
		}


		//MOVING DIAGONALLY
		else{
			if(this.game.getChessBoard().getPieceAt(endLocation) != null){
				if(!this.game.getChessBoard().getPieceAt(endLocation).owner.equals(this.owner)){
					if(this.owner.equals("White")){
						if(this.getPieceLocation().getRow() - endLocation.getRow() == 1 && Math.abs(this.getPieceLocation().getColumn() - endLocation.getColumn())==1 ){
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
					//Black
					else{
						if( endLocation.getRow()- this.getPieceLocation().getRow() == 1 && Math.abs(this.getPieceLocation().getColumn() - endLocation.getColumn())==1 ){
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
		}
		return false;
	}


}


