package minesweeper;

import java.util.Scanner;
import java.util.Random;

class Garden{
	private int[][][] mines = {{{8,2,0,0,2,8,3,8,8,1},
							{8,2,0,0,2,8,4,3,3,1},
							{1,1,0,0,1,1,2,8,1,0},
							{0,0,0,0,0,0,1,1,1,0},
							{1,1,0,0,0,0,0,0,0,0},
							{8,1,0,0,0,0,0,1,1,1},
							{2,2,1,0,0,0,0,1,8,1},
							{1,8,1,0,0,0,0,1,1,1}},
							
							{{1,1,1,1,1,1,0,2,8,8},
							{1,8,1,1,8,1,0,2,8,3},
							{1,1,1,1,1,1,0,1,1,1},
							{0,0,0,0,0,0,1,1,1,0},
							{0,0,0,0,0,1,2,8,2,1},
							{0,0,0,0,0,1,8,2,2,8},
							{1,2,2,1,0,1,1,1,1,1},
							{1,8,8,1,0,0,0,0,0,0}},
							
						   {{2,8,2,2,8,2,0,0,1,8},
							{3,8,2,2,8,2,0,0,1,1},
							{8,3,2,1,1,1,0,1,1,1},
							{3,8,2,0,0,0,0,1,8,1},
							{2,8,2,0,0,0,0,1,1,1},
							{1,1,2,1,1,0,0,0,0,0},
							{0,0,1,8,1,0,0,0,0,0},
							{0,0,1,1,1,0,0,0,0,0}},
						   
						   {{0,0,0,0,1,1,1,0,0,0},
							{0,0,0,0,1,8,2,1,0,0},
							{0,0,0,0,1,2,8,2,1,0},
							{0,0,0,0,0,2,3,8,1,0},
							{0,0,1,1,1,1,8,2,1,0},
							{0,0,1,8,3,3,3,2,1,0},
							{1,2,3,3,8,8,2,8,1,0},
							{1,8,8,2,2,2,2,1,1,0}}
	};
	
	private int mine;
	private boolean[][] mask = new boolean[8][10];
	
	Garden() {
		Random rand = new Random();
		mine = rand.nextInt(4);
	}
	
	public boolean isSafe(int xCoord , int yCoord) {
		if(mines[mine][xCoord][yCoord] == 8)
			return false;
		return true;
	}
	
	public void showGrid(int x,int y) {
		if(mines[mine][x][y] == 0)
			unmask(x,y);
		else
			mask[x][y] = true;
		printMines();
	}
	
	private void unmask(int x, int y) {
		if(x > 7 || y > 9 || x < 0 || y < 0)
			return;
		else if(mines[mine][x][y] !=8 && !mask[x][y]) {
			mask[x][y] = true;
			
			if(mines[mine][x][y] == 0) {			
				unmask(x+1,y);
				unmask(x-1,y);
				unmask(x,y+1);
				unmask(x,y-1);
			}
		}
		return;
	}
	
	private boolean onlyMinesHidden() {
		int minesCount = 0;
		for(int row = 0;row < 8;row++) {
			for(int col = 0;col < 10;col++)
				if(mask[row][col] == false)
					minesCount++;
			if(minesCount > 10) 
				return false;
		}
		return true;
	}
	
	public void printMines() {
		int row,col;
		
		for(row = 0;row < 8;row++) {
			for(col = 0;col < 10;col++) {
				if(mask[row][col])
					System.out.print(mines[mine][row][col]+" ");
				else
					System.out.print("_ ");
			}
		System.out.println();
	    }
		
		if(onlyMinesHidden()) {
			System.out.println("Hurray!!! You won the game.");
			System.exit(0);
		}
	} 
	
}

class MinesSweeper{
	public static void main(String[] arg){
		Scanner sc = new Scanner(System.in);
		
		Garden showel = new Garden();
		showel.printMines();
		
		do {
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			if(!showel.isSafe(x,y)) {
				System.out.println("\n\nBooM!!! Better luck next time.");
				sc.close();
				break;
			}
			
			showel.showGrid(x,y);
			
		}while(true);
	}
}