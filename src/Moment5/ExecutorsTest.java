package Moment5;

public class ExecutorsTest 
{
	
	Runnable task = () -> {
	    String threadName = Thread.currentThread().getName();
	    System.out.println("�Greetings from " + threadName);
	};
	
	public void start()
	{
		task.run();
		
		Thread thread = new Thread(task);
		thread.run();
	
		System.out.println("Done!");
	}

}
