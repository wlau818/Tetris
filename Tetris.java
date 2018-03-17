public class Tetris implements ArrowListener
{
	private BoundedGrid<Block> grid;	// The grid containing the Tetris pieces.
	private BlockDisplay display;		// Displays the grid.
	private Tetrad activeTetrad;		// The active Tetrad (Tetris Piece).

	// Constructs a Tetris Game
	public Tetris()
	{
		grid = new BoundedGrid<Block>(20, 10);
		display = new BlockDisplay(grid);
		display.setTitle("Tetris");
		activeTetrad = new Tetrad(grid);
		//activeTetrad.translate(5,5);
		display.showBlocks();
		display.setArrowListener(this);
		//throw new RuntimeException("INSERT MISSING CODE HERE");
	}

	// Play the Tetris Game
	public void play()
	{
		while (true) {
		sleep(1);
		if (!activeTetrad.translate(1, 0)) {
			clearCompletedRows();
			activeTetrad = new Tetrad(grid);
		}
		display.showBlocks();
		//   throw new RuntimeException("INSERT MISSING CODE HERE");
	}
	}


	// Precondition:  0 <= row < number of rows
	// Postcondition: Returns true if every cell in the given row
	//                is occupied; false otherwise.
	private boolean isCompletedRow(int row)
	{
		boolean full = true;
		for (int i = 0; i < grid.getNumCols(); i ++) {
			if (grid.get(new Location(row, i)) == null) {
				full = false;
			}
		}
		return full;
		//throw new RuntimeException("INSERT MISSING CODE HERE");
	}

	// Precondition:  0 <= row < number of rows;
	//                The given row is full of blocks.
	// Postcondition: Every block in the given row has been removed, and
	//                every block above row has been moved down one row.
	private void clearRow(int row)
	{
		for (int i = 0; i < grid.getNumCols(); i ++) {
			grid.get(new Location(row, i)).removeSelfFromGrid();
		}
		//throw new RuntimeException("INSERT MISSING CODE HERE");
	}

	// Postcondition: All completed rows have been cleared.
	private void clearCompletedRows()
	{
		for (int i = 0; i < grid.getNumRows(); i ++) {
			if (isCompletedRow(i)) {
				clearRow(i);
			}
		}
		//throw new RuntimeException("INSERT MISSING CODE HERE");
	}

	// Sleeps (suspends the active thread) for duration seconds.
	private void sleep(double duration)
	{
		final int MILLISECONDS_PER_SECOND = 1000;

		int milliseconds = (int)(duration * MILLISECONDS_PER_SECOND);

		try
		{
			Thread.sleep(milliseconds);
		}
		catch (InterruptedException e)
		{
			System.err.println("Can't sleep!");
		}
	}


	// Creates and plays the Tetris game.
	public static void main(String[] args)
	{
		Tetris t = new Tetris();
		System.out.println("Tried testing other ways, don't know what is wrong.");
		t.play();
		
		/*BoundedGrid<Block> board = new BoundedGrid<Block>(15, 15);
		Tetrad te = new Tetrad(board);
		BlockDisplay dis = new BlockDisplay(board);
		
		dis.showBlocks();
		te.rotate();
		dis.showBlocks();
		*/
		
		//throw new RuntimeException("INSERT MISSING CODE HERE");
	}

	@Override
	public void upPressed() {
		activeTetrad.rotate();
		display.showBlocks();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void downPressed() {
		activeTetrad.translate(1, 0);
		display.showBlocks();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void leftPressed() {
		activeTetrad.translate(0, -1);
		display.showBlocks();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rightPressed() {
		activeTetrad.translate(0, 1);
		display.showBlocks();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void spacePressed() {
		// TODO Auto-generated method stub
		
	}
}
