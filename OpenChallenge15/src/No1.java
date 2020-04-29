import java.util.ArrayList;
import java.util.Vector;

public class No1 {
	public static void main(String[] args) {
		Solution s = new Solution();
		
		int[][] board = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
		int[] moves = {1,5,3,5,1,2,1,4};
		
		int flag =0;
		int answer =0;
		
		ArrayList<Integer> temp = new ArrayList<>();
		
		for(int i=0; i<moves.length; i++) {
        	for(int j=0; j<board.length; j++) {
        		if(board[j][moves[i]-1] != 0) {
        			temp.add(board[j][moves[i]-1]);
        			board[j][moves[i]-1] = 0;
        			break;
        		}
        		
        	}
        }
		
		 
	        	for(int i=0; i<temp.size(); i++) {
	        		if(i != temp.size()-1&&temp.get(i) == temp.get(i+1)) {
	        			temp.remove(i);
	        			temp.remove(i);
	        			answer = answer +2;
	        			i = 0;
	        		} 
	        	}
	        	
	        
		
		
		
		for(int k=0; k<temp.size(); k++) {
		System.out.println(temp.get(k));
		}
		System.out.println("temp size => "+temp.size());
		
//		temp.remove(2);
//		temp.remove(2);
//		
//		System.out.println("temp size => "+temp.size());
		
		
		for(int i=0; i<temp.size(); i++) {
    		if(temp.get(i) == temp.get(i+1)) {
    			temp.remove(i);
    			temp.remove(i);
    			break;
    		}
    		
    	
    	}
		
		for(int k=0; k<temp.size(); k++) {
			System.out.println(temp.get(k));
			}
			System.out.println("temp size => "+temp.size());
		
		
		
		
		for(int i =0; i<board.length; i++) {
			for(int j = 0; j<board[0].length; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
		
		
	}
}

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int flag = 0;
        ArrayList<Integer> temp = new ArrayList<>();
        
        for(int i=0; i<moves.length; i++) {
        	for(int j=0; j<board.length; j++) {
        		if(board[j][moves[i]-1] != 0) {
        			temp.add(board[j][moves[i]-1]);
        			board[j][moves[i]-1] = 0;
        			break;
        		}
        		
        	}
        }
        
        while(flag == 0) {
        	for(int i=0; i<temp.size(); i++) {
        		if(i != temp.size()-1&&temp.get(i) == temp.get(i+1)) {
        			temp.remove(i);
        			temp.remove(i);
        			answer = answer +2;
        			break;
        		} else if(i == temp.size()-1) {
        			flag = 1;
        		}
        		
        	}
        	
        }
        
        
        return answer;
    }
}