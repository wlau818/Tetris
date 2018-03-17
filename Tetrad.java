import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;
import java.util.List;
import java.util.ArrayList;

// Represents a Tetris piece.
public class Tetrad
{
	private Block[] blocks;	// The blocks for the piece.

	// Constructs a Tetrad.
	public Tetrad(BoundedGrid<Block> grid)
	{
		blocks = new Block[4];
		int rnd = ThreadLocalRandom.current().nextInt(0, 7);
		Color col = Color.RED;
		Location[] loc = new Location[4]; 
		if (rnd == 0) {			
			for (int i = 0; i < 4; i ++) {
				loc[i] = new Location (0, i + 3);
			}
		} else if (rnd == 1) {
			col = Color.GRAY;
			loc[1] = new Location(0, 3);
			loc[0] = new Location(0, 4);
			loc[2] = new Location(0, 5);
			loc[3] = new Location(1, 4);
		} else if (rnd == 2) {
			col = Color.CYAN;
			for (int i = 0; i < 2; i ++) {
				loc[i] = new Location (0, i + 4);
			}
			loc[2] = new Location(1, 4);
			loc[3] = new Location(1, 5);
		} else if (rnd == 3) {
			col = Color.YELLOW;
			loc[1] = new Location(0, 3);
			loc[0] = new Location(0, 4);
			loc[2] = new Location(0, 5);
			loc[3] = new Location(1, 3);
		} else if (rnd == 4) {
			col = Color.MAGENTA;
			loc[1] = new Location(0, 3);
			loc[0] = new Location(0, 4);
			loc[2] = new Location(0, 5);
			loc[3] = new Location(1, 5);
		} else if (rnd == 5) {
			col = Color.BLUE;
			loc[0] = new Location(0, 4);
			loc[1] = new Location(0, 5);
			loc[2] = new Location(1, 3);
			loc[3] = new Location(1, 4);
		} else if (rnd == 6) {
			col = Color.GREEN;
			loc[1] = new Location(0, 3);
			loc[0] = new Location(0, 4);
			loc[2] = new Location(1, 4);
			loc[3] = new Location(1, 5);
		}
		Block one = new Block();
		one.setColor(col);
		for (int i = 0; i < 4; i ++) {
			blocks[i] = one;
		}
		/*loc[1] = new Location(4, 4);
		loc[0] = new Location(4, 5);
		loc[2] = new Location(4, 6);
		loc[3] = new Location(5, 5);
		*/
		addToLocations(grid, loc);
		//throw new RuntimeException("INSERT MISSING CODE HERE");
	}


	// Postcondition: Attempts to move this tetrad deltaRow rows down and
	//						deltaCol columns to the right, if those positions are
	//						valid and empty.
	//						Returns true if successful and false otherwise.
	public boolean translate(int deltaRow, int deltaCol)
	{
		BoundedGrid<Block> grid = blocks[0].getGrid();
		Location[] oldLocs = removeBlocks();
		Location[] newLocs = new Location[4];
		for (int i = 0; i < 4; i++) {
			Location old = oldLocs[i];
			newLocs[i] = new Location(old.getRow() + deltaRow, 
					old.getCol() + deltaCol);
		}
		//newLocs[0] = new Location(oldLocs[0].getRow() + deltaRow, 
		//		oldLocs[0].getCol() + deltaCol);
		if (areEmpty(grid, newLocs)) {
			addToLocations(grid, newLocs);
			return true;
		} else {
			addToLocations(grid, oldLocs);
			return false;
		}
		//throw new RuntimeException("INSERT MISSING CODE HERE");
	}

	// Postcondition: Attempts to rotate this tetrad clockwise by 90 degrees
	//                about its center, if the necessary positions are empty.
	//                Returns true if successful and false otherwise.
	public boolean rotate()
	{
		boolean turn = false;
		int row = blocks[0].getLocation().getRow();
		int col = blocks[0].getLocation().getCol();
		for (int i = 0; i < 4; i ++) {
			int newRow = row - col + blocks[i].getLocation().getCol();
			int newCol = row + col - blocks[i].getLocation().getRow();
			if (blocks[0].getGrid().isValid(new Location(newRow, newCol))) {
				blocks[i].putSelfInGrid(blocks[i].getGrid(), new Location(newRow, newCol));	
			}
		}
		return turn;
		//throw new RuntimeException("INSERT MISSING CODE HERE");
	}


	// Precondition:  The elements of blocks are not in any grid;
	//                locs.length = 4.
	// Postcondition: The elements of blocks have been put in the grid
	//                and their locations match the elements of locs.
	private void addToLocations(BoundedGrid<Block> grid, Location[] locs)
	{
		for(int i = 0; i < 4; i ++) {
			blocks[i].putSelfInGrid(grid, locs[i]);
		}
		//throw new RuntimeException("INSERT MISSING CODE HERE");
	}

	// Precondition:  The elements of blocks are in the grid.
	// Postcondition: The elements of blocks have been removed from the grid
	//                and their old locations returned.
	private Location[] removeBlocks()
	{
		Location[] loc = new Location[4];
		for (int i = 0; i < 4; i ++) {
			loc[i] = blocks[i].getLocation();
			//blocks[i].removeSelfFromGrid();
			blocks[i].getGrid().remove(loc[i]);
		}
		return loc;
		//throw new RuntimeException("INSERT MISSING CODE HERE");
	}

	// Postcondition: Returns true if each of the elements of locs is valid
	//                and empty in grid; false otherwise.
	private boolean areEmpty(BoundedGrid<Block> grid, Location[] locs)
	{
		boolean empty = true;
		List<Location> occLoc = grid.getOccupiedLocations();
		for (int i = 0; i < 4; i ++) {
			for (Location loc : occLoc) {
				if (loc.equals(locs[i])) {
					empty = false;
				}
				if (!grid.isValid(locs[i])) {
					empty = false;
				}
			}
		}
		return empty;
		//throw new RuntimeException("INSERT MISSING CODE HERE");
	}
}
