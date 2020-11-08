import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;

public class BigBrother implements Runnable {

    private int goal;
    final CountDownLatch countDownLatch;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public BigBrother(int goal) {
        this.goal = goal;
        countDownLatch = new CountDownLatch(goal);
    }

    public void recount(String name, int money) {
        System.out.printf("%s - %s added %d€ in total\n", LocalTime.now().format(dateTimeFormatter), name, money);
        for (int i = 0; i < money; i++){
            countDownLatch.countDown();
        }
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();
            System.out.printf("%s - Big brother has %d for the present and " +
                    "goes to the shop\n", LocalTime.now().format(dateTimeFormatter), goal);
        } catch (InterruptedException e) {
            System.out.printf("%s - Big brother has been interrupted\n", LocalTime.now().format(dateTimeFormatter));
        }

    }
}
