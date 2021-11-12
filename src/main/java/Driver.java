import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Driver {
    List<Partition> replicaServers = new ArrayList();
    public static void main(String[] args) throws InterruptedException {
        List<Partition> replicaServers = new ArrayList();
        System.out.println("====================Raft Implementation====================");
        for (int i=0;i<=5;i+=1){
            Partition partiton=new Partition(i);
            replicaServers.add(partiton);
        }

        System.out.println(replicaServers);

//        for (int i=0;i<=5;i+=1){
//            MyThread thread=new MyThread(replicaServers.get(i),replicaServers);
//            thread.start();
//            System.out.println("Donnnnneeee!!");
//        }
        ExecutorService executor= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        try{
            for ( int i=0; i < 5; i++){
                executor.execute(new MyThread(i,replicaServers.get(i),replicaServers));
            }
        }catch(Exception err){
            err.printStackTrace();
        }
        executor.shutdown(); // once you are done with ExecutorService
    }
}
