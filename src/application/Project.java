package application;

public class Project
	{
	    int start, finish, profit,id;
	 
	    // Constructor
	    Project(int start, int finish, int profit,int id)
	    {
	        this.id = id;
	        this.start = start;
	        this.finish = finish;
	        this.profit = profit;
	    }

		public int getStart() {
			return start;
		}

		public void setStart(int start) {
			this.start = start;
		}

		public int getFinish() {
			return finish;
		}

		public void setFinish(int finish) {
			this.finish = finish;
		}

		public int getProfit() {
			return profit;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public void setProfit(int profit) {
			this.profit = profit;
		}
	    
	    
	}