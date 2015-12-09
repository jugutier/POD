package core;

public class Holder {
	private int value = 10;
	private void change(){ // 3.  a possible solution would be to synchronize this method
		int x = value;
		
		for (int i = 0; i < 2000; i++) {
			//some extensive operation.
		}
		value=x*2;
	}
	public static void main(String[] args) throws InterruptedException{
        for (int j = 0; j < 20; j++) {//1 . simulate running the program 20 times
            Holder objA = new Holder();
            Thread[] pool= new Thread[10];
            for (int i = 0; i < pool.length; i++)
            {
                pool[i]= new Thread(  )
                {
                    public void run()
                    {    objA.change();   }
                } ;
                pool[i].start();
            }
            for (int i = 0; i < pool.length; i++)
                pool[i].join();
            System.out.println(objA.value);// 2. most of them print 10240, but sometimes it can be 5120 or 2560
        }

    }

}
