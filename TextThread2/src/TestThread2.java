
class Thread1 extends Thread{
	public void run() {
		for(int i=0; i<100; i++) {
			System.out.println("Thread1 : " + i);
			try {
				Thread.sleep(00);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class Thread2 extends Thread {
	public void run() {
		for(int i=0; i<100; i++) {
			System.out.println("Thread2 : " + i);
			try {
				Thread.sleep(00);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

public class TestThread2 extends Thread{
	public static void main(String[] args) throws InterruptedException {
		
		Thread1 t1 = new Thread1();
		//t1.setPriority(10);
		t1.start();
		Thread2 t2 = new Thread2();
		t2.start();
		
	}
}
