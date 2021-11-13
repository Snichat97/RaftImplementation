import java.util.Timer;
import java.util.TimerTask;

class Helper extends TimerTask
{
    public static int i = 0;
    public void run()
    {
        StopTimer.obj.notify();
    }

}


public class  StopTimer
{
    public static StopTimer obj;
    Partition partition;
    public StopTimer(Partition partition){
        this.partition=partition;
        obj=this;
    }
    public void induceSleepTime() throws InterruptedException
    {
        int rand = (int) (Math.random() * 10000) + 1000;
        System.out.println(rand);
        Thread.sleep(rand);
        this.partition.le.askVotes();
    }
}
