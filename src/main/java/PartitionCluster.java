import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PartitionCluster {
    List<Partition> replicaServers = new ArrayList();
    List<Partition> downServers = new ArrayList();
    Partition leader;

    public PartitionCluster() {
        System.out.println("====================Raft Implementation====================");
        for (int i = 0; i <= 5; i += 1) {
            Partition partiton = new Partition(i, this);
            this.replicaServers.add(partiton);
        }

        System.out.println(downServers);
    }

    public void setLeader(Partition leader) {
        this.leader = leader;
    }

    public void killLeader() {
        System.out.println("=============================================Leader has gone down=============================================");
        downServers.add(leader);
        leader = null;
        triggerElection();
        for (int i = 0; i <= 5; i++) {
            this.replicaServers.get(i).termLeaderElected = false;
        }
    }

    void triggerElection() {
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        try {
            int numThreads = this.replicaServers.size() - this.downServers.size()-1;
            for (int i = 0; i <= numThreads; i++) {
                System.out.println(this.replicaServers);
                Partition partitionConsider = this.replicaServers.get(i);
                if (!this.downServers.contains(partitionConsider))
                    executor.execute(new MyThread(i, partitionConsider, this.replicaServers));
            }
            executor.shutdown(); // once you are done with ExecutorService
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("=============================Leader has been elected=============================");
    }
}
