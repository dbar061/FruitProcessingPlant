package systemj.time;

public class TickManager{

	public void tickHook(){
		try{
			//Thread.sleep(100);
			Thread.sleep(20);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
	
	}

}