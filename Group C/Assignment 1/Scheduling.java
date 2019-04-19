import java.util.Scanner;

public class Scheduling {

	public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	System.out.println("Enter: \n\t1: FCFS\n\t2: SJF\n\t3: Priority\n\t4: Round Robin");
	int choice = sc.nextInt();
	switch(choice) {
	case 1: 
	FCFS fcfs=new FCFS();
	fcfs.execute();
	break;
	case 2:
	SJF sjf=new SJF();
	sjf.execute();
	break;
	case 3:
	PriorityNonPreemptive pr=new PriorityNonPreemptive();
	pr.execute();
	break;
	case 4:
	RoundRobin rr=new RoundRobin();
	rr.execute();
	break;
	}
	}
}
