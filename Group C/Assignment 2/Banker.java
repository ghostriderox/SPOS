import java.util.*;

public class Banker {

	int n, m; // n - number of processes and m - number of resources types
	int available[];  // m - no of available resources of each type
	int max[][]; // n*m - max demand of each process
	int allocation[][]; // n*m - current allocation
	int need[][];  // n*m - need

	boolean isAllTrue(boolean[] arr)
	{
		for(boolean bool : arr)
		{
			if(!bool)
			{
				return false;
			}

		}

		return true;
	}

	boolean isNeedCanBeFulfilled(int[] need, int[] work)
	{
		if(need.length != work.length)
		{
			System.out.println("Array size should be same");
			return false;
		}

		for(int i=0;i<need.length;i++)
		{
			if(need[i]>work[i])
			{
				return false;
			}
		}

		return true;
	}

	boolean isAllLessThan(int[] a, int[] b)
	{
		// check if all elements of a are less than corresponding elements of b
		if(a.length != b.length)
		{
			return false;
		}

		for(int i=0;i<a.length;i++)
		{
			if(a[i]>b[i])
			{
				return false;
			}
		}
		return true;
	}

	Banker()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Number of processes and resources types");
		n = sc.nextInt();
		m = sc.nextInt();

		available	= new int[m];
		max		= new int[n][m];
		allocation	= new int[n][m];
		need		= new int[n][m];
		//============================ADDED BY PS=================================================================
		System.out.println("Enter max no of resources of each type:");
		for(int i=0;i<m;i++)
		{
			available[i] = sc.nextInt();//this is total no of resources present in CPU some of which may have already been allocated to the processes...

		}
		//============================ADDED BY PS=================================================================
		/*
		System.out.println("Enter the available resources");
		for(int i=0;i<m;i++)
		{
			available[i] = sc.nextInt();
		}
		*/
		System.out.println("Enter the maximum resources for each type");
		for(int i=0;i<n;i++)
		{
			System.out.println("For process P"+Integer.toString(i));
			for(int j=0;j<m;j++)
			{
				max[i][j] = sc.nextInt();
			}
		}

		System.out.println("Enter the allocated resources for each type");
		for(int i=0;i<n;i++)
		{
			System.out.println("For process P"+Integer.toString(i));
			for(int j=0;j<m;j++)
			{
				allocation[i][j] = sc.nextInt();


			}
		}
		//============================ADDED BY PS=================================================================
		for(int i=0;i<m;i++){//m=no of resources
			for(int  j=0;j<n;j++){//For every process
				available[i]-=allocation[j][i];
			}

			//System.out.println("");
		}
		//Above loop uses available_resources[i]=Total_Resources[i]-total_allocated_resources[i]
		//============================ADDED BY PS=================================================================
		System.out.println("Available Matrix is: ");
		for(int i = 0; i < m; i++) {
			System.out.print(available[i] + " ");
		}
		System.out.println("");
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<m;j++)
			{
				need[i][j] = max[i][j] - allocation[i][j];
			}
		}
		System.out.println("Need matrix is: ");
		for(int i = 0; i < n; i++)
		{
			for(int j = 0; j < m; j++) {
				System.out.print(need[i][j] + " ");
			}
			System.out.println("");
		}

	}

	boolean safty_algorithm()
	{
		boolean progress = true;
		int work[] = new int[m];
		boolean finish[] = new boolean[n];  // default value is false
		//finish array tells if process is finished
		for(int i=0;i<m;i++)
		{
			work[i] = this.available[i];
		}
		
 		System.out.println("Safe state sequence = ");
		while(progress)
		{
			progress = false;

			if(isAllTrue(finish))
			{
				return true;
			}
			
			for(int i=0;i<n;i++)
			{
				if(!finish[i] && isNeedCanBeFulfilled(need[i], work))
				{
					progress = true;
					// add allocation[i] to work
					for(int j=0;j<m;j++)
					{
						work[j] = work[j] + allocation[i][j];
					}
					finish[i] = true;
					System.out.print("{P" + Integer.toString(i) + " ");
 				}
			}
			System.out.println("}");
		}

		return false;

	}


	void resource_request(int[] request,int process)
	{
		// request(m) resources req. for process process
		if(!this.isAllLessThan(request, need[process]))
		{
			System.out.println("Request is greater than actual actual resources needed by process P"+Integer.toString(process));
			return;
		}

		if(!this.isAllLessThan(request, available))
		{
			System.out.println("Not enough resources available");
			return;
		}

		for(int i=0;i<m;i++)
		{
			this.available[i] = this.available[i] - request[i];
			this.allocation[process][i] = this.allocation[process][i] + request[i];
			this.need[process][i] = this.need[process][i] - request[i];
		}
	}



	static public void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		Banker b = new Banker();
		if(b.safty_algorithm())
		{
			System.out.println("In safe state");
			int[] request = new int[b.m];
			for(int i=0;i<b.m;i++)
			{
				request[i] = sc.nextInt();
			}
			System.out.println("Process no.");
			int process = sc.nextInt();
			b.resource_request(request, process);
		}
		else
		{
			System.out.println("In deadlock state");
			System.out.println("Exiting...");
			return;
		}
	}
}
