import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Driver {
    public static void main(String[] args) throws InterruptedException {
        PartitionCluster pc =new PartitionCluster();
        pc.triggerElection();
        Thread.sleep(12000);

        pc.killLeader();
    }

}
