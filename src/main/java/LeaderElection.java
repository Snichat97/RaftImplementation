public class LeaderElection{
    int term;
    int majorityThreshold;
    int votes;
    Partition partition;

    public LeaderElection(int partitionNumber ,Partition partition){
        this.partition = partition;
        System.out.println("Print replica servers "+ this.partition.replicaServers);
    }

    public void askVotes(){
        majorityThreshold=(int) ((this.partition.replicaServers.size()-1)/2);
        partition.replicaServers.forEach(partition -> {
            if(partition.partitionNumber!=this.partition.partitionNumber) {
                boolean b = partition.lal.leaderAccept(this.partition.currentTerm+1,this.partition.partitionNumber);
            if(b) {
                votes += 1;
            }
            }
        });
        if(votes>=majorityThreshold){
            System.out.println(this.partition.partitionNumber+ " has been elected as leader.");
            this.partition.partitionCluster.setLeader(this.partition);
            this.partition.currentTerm+=1;
        }
    }
}
