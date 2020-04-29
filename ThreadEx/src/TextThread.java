class TimerThread extends Thread {
	int n = 0;

	@Override
	public void run() {
		while(true) {
			System.out.println("t1 >> "+n);
			n++;
			try {
				sleep(1000);
			} catch (Exception e) {
				return;
			}
		}
	}
	
}

class TimerThread2 implements Runnable {
	int n = 0;
	@Override
	public void run() {
		while(true) {
			System.out.println("t2 >> "+n);
			n++;
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				return;
			}
		}
		
	}
	
}


public class TextThread {
	public static void main(String[] args) {
		TimerThread th = new TimerThread();
		th.start();
		TimerThread2 th2 = new TimerThread2();
		Thread t = new Thread(th2);
		t.start();
		
		
	}
}
