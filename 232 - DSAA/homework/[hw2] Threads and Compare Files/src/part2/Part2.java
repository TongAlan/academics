package part2;
import java.util.Random;

public class Part2 {
	static int array[] = new int[4];
	static int counter = 0;
	
	public static class addArray implements Runnable{
		@Override
		public void run() {
			
			Random rand = new Random();
			int n = rand.nextInt(100) + 1;
			array[counter] = n;
			counter++;
		
			System.out.println("Thread " + Thread.currentThread().getId() + " Inserted " + n);
		}

	}
	
	public static void main(String[] args) {
		Thread thread1 = new Thread(new addArray());
		Thread thread2 = new Thread(new addArray());
		Thread thread3 = new Thread(new addArray());
		Thread thread4 = new Thread(new addArray());
		
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		
		try {
			thread1.join(10000);
			thread2.join(10000);
			thread3.join(10000);
			thread4.join(10000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		

	}

}
