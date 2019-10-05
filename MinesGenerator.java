package minesweeper;

import java.util.Random;

class MinesGenerator{
	public static void main(String[] args) {
		int[][] mines = new int[8][10];
		int row,col;
		
		Random rand = new Random();
		
		for(int iter = 0;iter < 10;iter++) {
			row = rand.nextInt(8);
			col = rand.nextInt(10);
			
			mines[row][col] = 8;
			int rmove = 1,move = 1;
			if(row == 7)
				rmove = -1;
			if(row == 0 || row == 7) {
				if(col == 9)
					move = -1;
				if(col != 0 && col != 9) {
					if(mines[row][col-1] != 8)
						mines[row][col-1]++;
					if(mines[row + rmove][col-1] != 8)
						mines[row + rmove][col-1]++;
				}
				if(mines[row][col + move] != 8)
					mines[row][col + move]++;
				if(mines[row + rmove][col + move] != 8)
					mines[row + rmove][col + move]++;
				
				if(mines[row + rmove][col] != 8)
					mines[row + rmove][col]++;
			}
			else {
				if(col == 9) 
					move = -1;
				if(col != 0 && col != 9) {
					if(mines[row - 1][col - 1] != 8)
						mines[row - 1][col - 1]++;
					if(mines[row][col - 1] != 8)
						mines[row][col - 1]++;
					if(mines[row + 1][col - 1] != 8)
						mines[row + 1][col - 1]++;
				}
				
				if(mines[row - 1][col + move] != 8)
					mines[row - 1][col + move]++;
				if(mines[row][col + move] != 8)
					mines[row][col + move]++;
				if(mines[row + 1][col + move] != 8)
					mines[row + 1][col + move]++;
				
				if(mines[row - 1][col] != 8)
					mines[row - 1][col]++;
				if(mines[row + 1][col] != 8)
					mines[row + 1][col]++;
			}
		}
		
		for(row = 0;row < 8;row++) {
			for(col = 0;col < 10;col++) {
				System.out.print(mines[row][col]+" ");
			}
			System.out.println();
		}
		
//		return mines;
	}
}