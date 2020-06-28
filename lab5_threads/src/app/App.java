package app;

public class App {
    public static void main(String[] args) throws Exception {
        Thread thread1 = new Thread(new RunnableImp());
        Thread thread2 = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println("Wątek 2 " + i*10);
            }
        });
        thread1.start();
        thread2.start();
        
    }
}