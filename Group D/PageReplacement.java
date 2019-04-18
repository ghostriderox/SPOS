import java.util.Scanner;

public class PageReplacement {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("1: FCFS \n2: LRU \n3: Optimal");
		int choice = sc.nextInt();
		switch(choice){
			case 1:
			
		FCFS fcfs=new FCFS();
		fcfs.execute();
		break;
			case 2:
			
		LRU lru=new  LRU();
		lru.execute();
		break;
			case 3:
			
		Optimal optimal=new Optimal();
		optimal.execute();
		break;
		}
	}

}
