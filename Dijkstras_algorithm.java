import java.util.Scanner;


class Dict{
	public boolean[] dict;
	public int size;
	
	public Dict(){
		this.dict = new boolean[128];
		this.size = 128;
		for(int i = 0 ; i < 128 ;i++) {
			this.dict[i] = false;
		}
	}
	
	public void setDict(char c) {
		this.dict[(int)c] = true;
	}
	
	public boolean isExist(char c) {
		if(this.dict[(int)c]) {
			return true;
		}
		return false;
	}
}
class DQueue {
	public DNode head;
	public DNode rare;
	public int size;
	public boolean[] dict;
	
	public DQueue(DNode first) {
		this.head = first;
		this.rare = first;
		size = 1;
		dict = new boolean[128];
		for(int i = 0 ; i < 128 ;i++) {
			dict[i] = false;
		}
		dict[(int)(first.NodeName)] = true;
	}
	
	public boolean isExist(char c) {
		
		
		
		if( dict[(int)c] ) {
			return true;
		}
		return false;
	}
	
	public void add(DNode node) {
		/*
		System.out.print(node.NodeName+" ");
		System.out.println((int)node.NodeName);
		*/
		
		if(!isExist(node.NodeName)) {
			this.rare.Qnext = node;
			this.rare = node;
			size++;
			
			
			
			dict[(int)node.NodeName] = true;
		}
	}
	public boolean isEmpty() {
		if(size == 0) return true;
		return false;
	}
	public void remove() {
		if(isEmpty()) {
			System.exit(1);
		}
		//dict[(int)head.NodeName] = false;
		head = head.Qnext;
		this.size--;
		
	}
	public DNode getHead() {
		return this.head;
	}
}

class list{
	public DNode head;
	
	public list(DNode head){
		this.head = head;
	}
	
	public DNode rareNode() {
		DNode temp = this.head;
		while(temp.down != null) {
			temp = temp.down;
		}
		return temp;
	}
	
	public void show() {
		DNode temp = this.head;
		while(temp != null) {
			DNode Front = temp;
			
			
			while(temp != null) {
				System.out.print( temp.NodeName + " " + temp.length + "|");
				temp = temp.next;
			}
			System.out.println();
			
			temp = Front.down;
		}
	}
	
	public boolean existNode(char c) {
		DNode temp = this.head;
		while(temp != null) {
			if(temp.NodeName == c) {
				return true;
			}
			temp = temp.down;
		}
		return false;
	}
	
	public DNode findNode(char in) {
		DNode temp = this.head;
		
		while(temp != null) {
			if(temp.NodeName == in) {
				break;
			}
			temp = temp.down;
		}
		
		return temp;
	}

	
}

class DNode{
	//Queue
	public DNode Qnext;
	
	//point
	public char NodeName;
	public DNode down;
	public int distance;
	public boolean isGo;

	public DNode(char name) {
		this.NodeName = name;
		down = null;
		this.distance = Integer.MAX_VALUE;
		this.isGo = false;
		this.Qnext = null;
	}
	
	public void setPointNext(DNode next) {
		this.down = next;
	}
	
	//line
	public DNode next;
	public int length;
	
	

	public void Line_setNext(DNode next) {
		this.next = next;
	}
	public void Line_insertNode(char name,int length) {
		DNode t = new DNode(name);
		t.length = length;
		
		DNode temp =this.next;
		this.next = t;
		t.next = temp;
	}
	public void Line_addNode(char name,int length) {
		DNode t = new DNode(name);
		t.length = length;
		
		this.next = t;
	}
	
	
	
}

public class Dijkstras_algorithm {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		char start = input.next().charAt(0);
		char terminal = input.next().charAt(0);
		Dict D = new Dict();
		
		int n = input.nextInt();
		
		list l = new list( new DNode(start) );
		
		for(int i = 0 ; i < n ; i++) {
			char s = input.next().charAt(0);
			char t = input.next().charAt(0);
			int length = input.nextInt();
			boolean isFinded = false;
			
			DNode changing = l.head;
			
			while(true) {
				if(changing.NodeName == s) {
					isFinded = true;
					break;
				}
				else if(changing.down != null) {
					changing = changing.down;
				}
				else {
					break;
				}
			}
			
			if(!isFinded) {//new Node
				DNode newNode = new DNode(s);
				changing.down = newNode;
				changing = changing.down;
			}
			
			while(true) {
				if(changing.next != null) {
					if(changing.next.length > length) {
						changing.Line_insertNode(t, length);
						D.setDict(t);
						break;
					}
					changing = changing.next;
				}
				else {
					changing.Line_addNode(t , length);
					D.setDict(t);
					break;
				}	
			}
			
		}
		
		for(int i = 0 ; i < D.size ; i++) {
			if(D.dict[i] == true && !l.existNode((char)i)) {
				DNode newNode = new DNode((char)i);
				l.rareNode().down = newNode;
			}
		}
		
		
		
		
		
		
		
		//input over
		
		
		
		l.findNode(start).distance = 0;
		/*
		System.out.print(l.findNode(start).NodeName);
		*/
		DQueue Q = new DQueue( l.findNode(start) );
		
		while(!Q.isEmpty()) {
			Q.getHead().isGo = true;
			
			DNode temp = Q.getHead().next;
			
			while(temp != null && !temp.isGo) {//update distance
				
				DNode real = l.findNode(temp.NodeName);
				Q.add(real);
				
				if( real.distance > Q.getHead().distance + temp.length) {
					real.distance = Q.getHead().distance + temp.length;
				}
				temp = temp.next;
			}
			Q.remove();
			
		}

		System.out.println( l.findNode(terminal).distance );

	}
}
