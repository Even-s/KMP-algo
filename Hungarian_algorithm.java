import java.util.Scanner;

public class Hungarian_algorithm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int Name = input.nextInt();
		int Event = input.nextInt();
		
		int origin[][] = new int[Name][Event];
		int working[][] = new int[Name][Event];
		int isLined[][] = new int[Name][Event];
		int max = 0;
		int Task[] = new int[Name];
		
		for(int i = 0 ; i < Name ; i++) {
			for (int j = 0 ; j < Event ; j++) {
				origin[i][j] = input.nextInt();
				working[i][j] = origin[i][j];
				if(origin[i][j] > max) max = origin[i][j];
			}
		}
		changetoMinQ(working,max);
		
		
		rowReducing(working);
		
		if(countLineNum(working,isLined) < Name) {
			
			recovMatrix(isLined);
			columnReducing(working);
			
			if(countLineNum(working,isLined) < Name) {
				addElement(working,isLined);
				
				assignTask(working,Task);//完成
				
				//System.out.print(countLineNum(working,isLined));
				
			}
			else {
				assignTask(working,Task);//完成
			}
			
		}
		else {
			assignTask(working,Task);//完成
		}
		
		
		System.out.println(getScore(origin,Task));
		
		
		
		
	}
	
	
	public static int getScore(int[][] origin , int Task[]) {
		int Score = 0;
		
		for(int i = 0 ; i < Task.length ; i++) {
			Score += origin[i][ Task[i] ];
		}
		
		
		
		return Score;
	}
	
	
	
	public static void assignTask(int working[][], int Task[]) {
		boolean TaskisChosen[] = new boolean[working[0].length];
		boolean PersonisChosen[] = new boolean[working.length];
		
		for(int i = 0 ; i < working.length ; i++) {
			int counter = 0;
			int taskid = 0;
			for(int j = 0 ; j < working[0].length ; j++) {
				if(working[i][j] == 0) {
					taskid = j;
					counter++;
				}
			}
			
			if(counter == 1) {
				Task[i] = taskid;
				TaskisChosen[taskid] = true;
				PersonisChosen[i] = true;
			}
		}
		
		for(int i = 0 ; i < working[0].length ; i++) {
			int counter = 0;
			int personid = 0;
			
			for(int j = 0 ; j < working.length ; j++) {
				if(working[j][i] == 0) {
					counter++;
					personid = j;
				}
			}
			
			if(counter == 1 &&  !TaskisChosen[i] && !PersonisChosen[personid]) {
				TaskisChosen[i] = true;
				PersonisChosen[personid] = true;
				
				Task[personid] = i;
			}
		}
		
		for(int i = 0 ; i < working.length ; i++) {
			if(!PersonisChosen[i]) {
				for(int j = 0 ; j < working[i].length ; j++) {
					if(!TaskisChosen[j]) {
						Task[i] = j;
						PersonisChosen[i] = true;
						TaskisChosen[j] = true;
					}
				}
			}
		}

	}
	
	
	public static void showMatrix(int[][] arr) {
		for(int i = 0 ; i < arr.length ; i++) {
			for(int j = 0 ; j < arr[0].length; j++) {
				System.out.print(arr[i][j]+"\t");
			}
			System.out.println();
		}
	}
	
	
	public static void rowReducing(int[][] array) {
		for(int i = 0 ; i < array.length ; i++) {
			int little = Integer.MAX_VALUE;
			
			for(int j = 0 ; j < array[i].length ; j++) {
				if(array[i][j] < little) little = array[i][j];
			}
			
			if( little !=0 ) {
				for(int j = 0 ; j < array[i].length  ; j++) {
					array[i][j] = array[i][j] - little;
				}
			}
		}
	}
	
	public static void columnReducing(int[][] array) {
		for(int i = 0 ; i < array[0].length ; i++) {
			
			int little = Integer.MAX_VALUE;
			
			for(int j = 0 ; j < array.length ; j++) {
				if(array[j][i] < little) little = array[j][i];
			}
			
			
			if(little != 0) {
				for(int j = 0 ; j < array.length ; j++) {
					array[j][i] = array[j][i] - little;
				}
			}
		}
	}
	
	public static void recovMatrix(int array[][]) {
		for(int i = 0 ; i < array.length ; i++) {
			for(int j = 0 ; j < array[0].length ; j++) {
				array[i][j] = 0;
			}
		}
	}
	
	public static int countLineNum(int arr[][],int isLine[][]) {
		boolean isZero[][] = new boolean[arr.length][arr[0].length];
		int ans = 0;
		
		for(int i = 0 ; i < arr.length ; i++) {
			for(int j = 0 ; j < arr[0].length ; j++) {
				if(arr[i][j] == 0) isZero[i][j] = true;
			}
		}
		
		for(int i = 0 ; i < isZero.length ; i++) {
			for(int j = 0 ; j < isZero[0].length ; j++) {
				if(isZero[i][j]) {
					int row = 0;
					int column = 0;
					
					for(int k = 0 ; k < isZero[i].length ; k++) {
						if(isZero[i][k]) row++;
					}
					for(int k = 0 ; k < isZero.length ; k++) {
						if(isZero[k][j]) column++;
					}
					
					if(row >= column) {
						for(int k = 0 ; k < isZero[i].length ; k++) {
							isZero[i][k] = false;
							isLine[i][k]++;
						}
					}
					else {
						for(int k = 0 ; k < isZero.length ; k++) {
							isZero[k][j] = false;
							isLine[k][j]++;
						}
					}
					
					ans++;
				}
			}
		}
		
		
		return ans;
	}
	public static void changetoMinQ(int arr[][] , int max) {
		for(int i = 0 ; i < arr.length ; i++) {
			for(int j = 0 ; j < arr[0].length; j++) {
				arr[i][j] = max - arr[i][j];
			}
		}
	}
	
	public static void addElement(int working[][],int isLined[][]) {
		int min = Integer.MAX_VALUE;
		
		for(int i = 0 ; i < working.length ; i++) {
			for(int j = 0 ; j < working[0].length ; j++) {
				if(isLined[i][j] == 0 && working[i][j] < min)  min = working[i][j];
			}
		}
		
		for(int i = 0 ; i < working.length ; i++) {
			for(int j = 0 ; j < working[0].length ; j++) {
				if(isLined[i][j] == 0) working[i][j] -= min;
				if(isLined[i][j] > 1 ) working[i][j] += min;
			}
		}
		
	}
}
