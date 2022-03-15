import java.util.Scanner;

public class LCSS {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		
		int n = input.nextInt();
		
		for(int i = 0 ; i < n ; i++) {
			String a = input.next();
			String b= input.next();
			
			int matrix[][] = new int[a.length()+1][b.length()+1];
			char way[][] = new char[a.length()+1][b.length()+1];
			
			
			
			for(int j = 1 ; j < a.length()+1 ; j++) {
				for(int k = 1 ; k < b.length()+1 ; k++) {
					if( a.charAt(j-1) == b.charAt(k-1) ) {
						matrix[j][k] = matrix[j-1][k-1] + 1;
						way[j][k] = 'p';
					}
					else if( matrix[j-1][k] > matrix[j][k-1] ) {
						matrix[j][k] = matrix[j-1][k];
						way[j][k] = 'u';
					}
					else {
						matrix[j][k] = matrix[j][k-1];
						way[j][k] = 'l';
					}
				}
			}
			
			System.out.println(matrix[a.length()][b.length()] + "\t" +trace(way,a));
			
		}
		
	}
	
	public static String trace(char[][] way,String A) {
		String re = "";
		
		int i = way.length-1;
		int j = way[0].length-1;
		
		
		while(  i != 0 && j != 0  ) {
			
			
			if(way[i][j] == 'p') {
				
				
				
				re += A.charAt(i-1);
				i--;
				j--;
			}
			
			else if(way[i][j] == 'u') {
				i--;
			}
			else{
				j--;
			}
		}
		return inverse(re);
	}
	
	public static String inverse(String re) {
		String inver = "";
		for(int i = re.length()-1 ; i >= 0 ; i--) {
			inver += re.charAt(i);
		}
		
		return inver;
	}

}
