import java.util.ArrayList;
import java.util.List;
//
//public class MyThread extends Thread {
//    Partition partition;
//    public MyThread(Partition partition, List<Partition> replicaServers) throws InterruptedException {
//        this.partition=partition;
//        this.partition.setReplicaServers((ArrayList) replicaServers);
//    }
//    public void run(){
//        System.out.println("Print thread "+ partition.partitionNumber);
//    }
//}

class MyThread implements Runnable{
    int id;
    Partition partition;
    ArrayList replicas;
    public MyThread(int i,Partition partition, List<Partition> replicaServers) throws InterruptedException {
        this.id = i;
        this.partition=partition;
        this.replicas=(ArrayList) replicaServers;
    }
    public void run(){
        try{
            System.out.println("Runnable started id:"+id);
            this.partition.setReplicaServers((ArrayList) this.replicas);
            System.out.println("Run: "+ Thread.currentThread().getName());
            System.out.println("Runnable ended id:"+id);
        }catch(Exception err){
            err.printStackTrace();
        }
    }
}