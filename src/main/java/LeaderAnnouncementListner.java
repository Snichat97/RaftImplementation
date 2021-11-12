public class LeaderAnnouncementListner{
    Partition partition;

    public LeaderAnnouncementListner(int partitionNumber ,Partition partition){
        this.partition = partition;
    }

    public synchronized boolean leaderAccept(int term, int partitionIncoming){
        System.out.println(this.partition.partitionNumber + " is being requested vote by "+partitionIncoming);
        if(term > this.partition.currentTerm && this.partition.termLeaderElected==false){
            System.out.println(this.partition.partitionNumber + " partition term "+this.partition.currentTerm+" leader term "+term);
            this.partition.currentTerm = term;
            this.partition.termLeaderElected = true;
            return true;
        }
        return false;
    }

    public boolean listenToHearbeat(Partition leader){
        return true;
    }
}
