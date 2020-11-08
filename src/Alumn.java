import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Alumn implements Runnable {

    private BigBrother bigBrother;
    private final DateTimeFormatter dateTimeFormatter  = DateTimeFormatter.ofPattern("HH:mm:ss");

    public Alumn(BigBrother bigBrother) {
        this.bigBrother = bigBrother;
    }

    @Override
    public void run() {
        int money;
        money = getMoney();
        if (bigBrother.countDownLatch.getCount() < 1){
            System.out.printf("%s - %s got %d€ \n",
                    LocalTime.now().format(dateTimeFormatter ), Thread.currentThread().getName(), money);
        } else {
            bigBrother.recount(Thread.currentThread().getName(), money);
        }
    }

    private int getMoney() {
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10)+3);
        } catch (InterruptedException e) {
            System.out.printf("%s - Alumn has been interrupted while getting money\n",
                    LocalTime.now().format(dateTimeFormatter));
        }
        return ThreadLocalRandom.current().nextInt(5)+2; //el alumno devuelve entre 5 y 2€
    }

}
