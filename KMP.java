package KMP;

public class KMP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print(KMP_algo("ababbaaa","aababbababbbababbaaabb"));
	}
static int KMP_algo(String S,String T) {
	int s = S.length();
	int t = T.length();
	int failure[] = new int[s+1];
	
	//initalization failure table
	failure[0] = -1;
	failure[1] = 0;
	for(int i = 2 ; i < s+1 ; i++) {
		failure[i] = 0;
	}
	//calculate failure table
	
	
	int slide = 1;//step size
	int match = 0;
	
	while(slide < s) {
		match = 0;
		for(int i = 0 ; i < s ; i++) {//
			if( S.charAt(i) == S.charAt(slide+i) ) {
				match++;
				if(match > failure[slide+i+1]) failure[slide+i+1] = match;
			}
			else{
				slide += (match-failure[match]);
				break;
			}
			if(slide+i == s-1){
				slide += (match-failure[match]);
				break;
			}
		}
	}
	
	for(int i = 0 ; i < s+1 ; i++) {
		System.out.print(failure[i]+" ");
	}
	System.out.println();

	slide = 1;
	while(slide <= t-s) {//check until t-s
		match = failure[match];
		for(int i = 0 ; i < s  ;i++) {
			if(T.charAt(slide+i) == S.charAt(i)) {
				match++;
				if(match == s) {
					return slide+1;
				}
			}
			else {
				slide+=(match-failure[match]);
				break;
			}
		}
		
	}
	return -1;
	
	
}
}

