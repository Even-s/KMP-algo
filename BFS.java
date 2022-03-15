import java.util.Scanner;

class queue {
	public node head;
	public node rare;
	public int size;
	
	public queue(node head) {
		this.head = head;
		this.size = 1;
		this.rare = head;
	}
	
	public boolean isEmpty() {
		if(this.size == 0) return true;
		return false;
	}
	
	public void addNode(node newNode) {
		size++;
		rare.next = newNode;
		newNode.prev = rare;
		rare = newNode;
	}
	
	public void popNode() {
		if(this.size == 0) {
			System.exit(1);
		}
		this.head = this.head.next;
		size--;

	}
	
}

class node {
	public int[][] array = new int[3][3];
	public char from;
	public node next;
	public node prev;
	
	public node(int arr[][]) {
		for(int i = 0 ; i < arr.length ; i++) {
			for(int j = 0 ; j < arr[0].length ; j++) {
				this.array[i][j] = arr[i][j];
			}
		}
		this.from = 'n';
		
		this.next = null;
		this.prev = null;
	}
	
	public node(node n , char from) {
		for(int i = 0 ; i < n.array.length ; i++) {
			for(int j = 0 ; j < n.array[0].length ; j++) {
				this.array[i][j] = n.array[i][j];
			}
		}
		
		this.from = from;
	}
	
	public boolean compare(int[][] arr) {
		boolean ans = true;
		
		for(int i = 0 ; i < 3 ; i++) {
			for(int j = 0 ; j < 3 ; j++) {
				if(this.array[i][j] != arr[i][j]) {
					ans = false; 
					break;
				}
			}
		}
		return ans;
	}
	
	public void printMatrix() {
		for(int i = 0 ; i < 3 ; i++) {
			for(int j = 0 ; j < 3 ; j++) {
				if(j != 2) {
					System.out.print(this.array[i][j]+" ");
				}
				else {
					System.out.print(this.array[i][j]);
				}
				
			}
			System.out.println();
		}
	}
	
	public void swap(int oi,int oj,int ti,int tj) {
		int temp = this.array[ti][tj];
		this.array[ti][tj] = 0;
		this.array[oi][oj] = temp;
	}
	public void swapUp(int oi,int oj) {
		this.swap(oi, oj, oi-1, oj);
	}
	public void swapDown(int oi,int oj) {
		this.swap(oi, oj, oi+1, oj);
	}
	public void swapRight(int oi,int oj) {
		this.swap(oi, oj, oi, oj+1);
	}
	public void swapLeft(int oi,int oj) {
		this.swap(oi, oj, oi, oj-1);
	}
	
}


public class BFS {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int[][] origin = new int[3][3];
		int[][] target = new int[3][3];
		
		for(int i = 0 ; i < 3 ; i++) {
			for(int j = 0 ; j < 3 ; j++) {
				origin[i][j] = input.nextInt();
			}
		}
		
		for(int i = 0 ; i < 3 ; i++) {
			for(int j = 0 ; j < 3 ; j++) {
				target[i][j] = input.nextInt();
			}
		}
		
		
		queue Q = new queue( new node(origin) );
		
		boolean hasAns = false;
		
		while( !Q.isEmpty() && !hasAns ) {
			Q.head.printMatrix();
			
			if( Q.head.compare(target)) {
				hasAns = true;
				
				break;
			}
			else {
				int zeroLocation[] = new int [2];
				
				for(int i = 0 ; i < 3 ; i++) {//find 0
					for(int j = 0 ; j < 3 ; j++) {
						if(Q.head.array[i][j] == 0) {
							zeroLocation[0] = i;
							zeroLocation[1] = j;
							break;						}
					}
				}
				
				
				if(goUp(zeroLocation , Q.head.from) ) {
					node t = new node(Q.head,'u');
					t.swapUp(zeroLocation[0], zeroLocation[1]);
					Q.addNode(t);
				}
				if(goRight(zeroLocation , Q.head.from) ) {
					node t = new node(Q.head,'r');
					t.swapRight(zeroLocation[0], zeroLocation[1]);
					Q.addNode(t);
				}
				if(goDown(zeroLocation , Q.head.from) ) {
					node t = new node(Q.head,'d');
					t.swapDown(zeroLocation[0], zeroLocation[1]);
					Q.addNode(t);
				}
				if(goLeft(zeroLocation , Q.head.from) ) {
					node t = new node(Q.head,'l');
					t.swapLeft(zeroLocation[0], zeroLocation[1]);
					Q.addNode(t);
				}

				Q.popNode();
			}
			
			
		}//while
		
		

		
	}
	
	
	
	public static boolean goUp(int[] zeroLocation , char prevMove) {
		if(prevMove == 'd' || zeroLocation[0] == 0) {
			return false;
		}
		return true;
	}
	public static boolean goDown(int[] zeroLocation , char prevMove) {
		if(prevMove == 'u' || zeroLocation[0] == 2) {
			return false;
		}
		return true;
	}
	public static boolean goRight(int[] zeroLocation , char prevMove) {
		if(prevMove == 'l' || zeroLocation[1] == 2) {
			return false;
		}
		return true;
	}
	public static boolean goLeft(int[] zeroLocation , char prevMove) {
		if(prevMove == 'r' || zeroLocation[1] == 0) {
			return false;
		}
		return true;
	}
	
	
}


