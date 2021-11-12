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


    public Partition(int partitonNumber) {
        this.partitionNumber=partitonNumber;
        this.lal= new LeaderAnnouncementListner(partitionNumber,this);
        this.le= new LeaderElection(partitionNumber,this);
        this.timer= new StopTimer(this);
    }

    public void setReplicaServers(ArrayList replicaServers) throws InterruptedException{
        this.replicaServers=replicaServers;
        this.timer.timing();
    }


}
