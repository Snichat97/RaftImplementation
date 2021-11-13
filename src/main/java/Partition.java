import java.util.ArrayList;
import java.util.List;


public class Partition {
    int partitionNumber;
    List <Partition> replicaServers = new ArrayList ();
    int currentTerm;
    LeaderAnnouncementListner lal;
    LeaderElection le;
    StopTimer timer;
    boolean termLeaderElected = false;
    PartitionCluster partitionCluster;


    public Partition(int partitonNumber, PartitionCluster partitionCluster) {
        this.partitionNumber=partitonNumber;
        this.lal= new LeaderAnnouncementListner(partitionNumber,this);
        this.le= new LeaderElection(partitionNumber,this);
        this.partitionCluster = partitionCluster;
        this.timer= new StopTimer(this);
    }

    public void setReplicaServers(ArrayList replicaServers) throws InterruptedException{
        this.replicaServers=replicaServers;
        this.timer.induceSleepTime();
    }


}
