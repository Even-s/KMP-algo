import java.util.Scanner;

public class Floyd_Warshall {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int n = input.nextInt();
		
		int[][] arr = new int[n][n];
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < n ; j++) {
				if(i!=j) {
					arr[i][j] = 200;
				}
			}
		}
		int m = input.nextInt();
		
		char[][] inp = new char[m][2];
		int[] weight = new int[m];
		boolean dict[] = new boolean[52];
		
		for(int i = 0 ; i < m ; i ++) {
			inp[i][0] = input.next().charAt(0);
			addDict(dict,inp[i][0]);
			inp[i][1] = input.next().charAt(0);
			addDict(dict,inp[i][1]);
			weight[i] = input.nextInt();
		}
		
		
		//PrintDict(dict);
		//System.out.print(getNumber(dict,'e'));
		
		for(int i = 0 ; i < m ; i++) {
			arr[getNumber(dict,inp[i][0])-1][getNumber(dict,inp[i][1])-1] = weight[i];
		}
		

		
		
		

		
		for(int k = 0 ; k < n ; k++) {//through k
			for(int i = 0 ; i < n ; i++) {
				for(int j = 0 ; j < n ; j++) {
					if(arr[i][j] > arr[i][k]+arr[k][j] && arr[i][k] != 200 && arr[k][j] != 200) {
						arr[i][j] = arr[i][k]+arr[k][j];
					}
				}
			}
			
		}
		
		PrintMatrix(arr);
		
	
		
		
	}

	public static void PrintMatrix(int[][] arr) {
		for(int i = 0 ; i < arr.length ; i++) {
			for(int j = 0 ; j < arr.length ; j++) {
				if(arr[i][j] != 200) {
					if(j == arr.length-1) {
						System.out.print(arr[i][j]);
					}
					else {
						System.out.print(arr[i][j]+" ");
					}
				}
				else {
					if(j == arr.length-1) {
						System.out.print("INF");
					}
					else {
						System.out.print("INF ");
					}
				}
			}
			if(i != arr.length-1) {
				System.out.println();
			}
		}
	}
	
	public static void PrintDict(boolean[] dict) {
		for(int i = 0 ; i < dict.length ; i++) {
			if(dict[i]) {
				if(i<26) {
					System.out.print( (char)('a'+i) );
				}
				else {
					
					System.out.print( (char)(65+i-26) );
				}
			}
		}
		System.out.println();
	}
	
	public static void addDict(boolean[] dict,char c) {
		int index = 0;
		if( c > 64 && c < 91) {//大寫
			index = c - 65 + 26;
		}
		else {
			index = c - 97;
		}
		dict[index] = true;
	}
	
	public static int getNumber(boolean[] dict,char c) {
		int counter = 0;
		for(int i = 0 ; i < 56 ; i++) {
			if(dict[i]) {
				counter++;
			}
			if(i == exchange(c) ) {
				break;
			}
		}
		
		return counter;
	}
	
	public static int exchange(char c) {
		if(c > 64 && c < 91) {//大寫
			return c - 65 + 26;
		}
		else {
			return c -97;
		}
	}


}
