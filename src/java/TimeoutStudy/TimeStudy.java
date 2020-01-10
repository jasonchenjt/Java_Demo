package TimeoutStudy;

import java.util.Timer;
import java.util.TimerTask;

public class TimeStudy {

    //计时器
    public static void main(String[] args) {
        Timer timer = new Timer();
        for (int i =2;i<20;i++){
            int finalJ = i;
            timer.schedule(new TimerTask() {
                public void run() {
                    System.out.println(finalJ);
                    System.out.println(System.currentTimeMillis());
                }
            }, i*1000);
        }
    }
}
