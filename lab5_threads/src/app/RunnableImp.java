package app;

public class RunnableImp implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Wątek 1 " + i);
        }

    }
    
}