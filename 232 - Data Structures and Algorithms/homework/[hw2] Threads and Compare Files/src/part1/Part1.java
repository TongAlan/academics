package part1;
public class Part1 {
	
	public static class oneSec implements Runnable {
		@Override
		public void run() {
			int seconds = 0;
			while (true) {
	            try {
	                System.out.println(seconds);
	                seconds= seconds +1;
	                Thread.sleep(1000);
	            } catch (InterruptedException e1) {
	                e1.printStackTrace();
	            }
	        }
			
		}
	}
	
	public static class threeSec implements Runnable {
		public void run() {
			int seconds = 0;
			while (true) {
	            try {
	                System.out.println("\t" + seconds);
	                seconds= seconds +1;
	                Thread.sleep(3000);
	            } catch (InterruptedException e1) {
	                e1.printStackTrace();
	            }
	        }
		}
	}

	public static void main(String[] args) {
		Thread thread1 = new Thread(new oneSec());
		thread1.start();
		Thread thread2 = new Thread(new threeSec());
		thread2.start();

	}

}
