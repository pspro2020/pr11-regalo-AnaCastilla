import java.util.concurrent.ThreadLocalRandom;

public class Main {

    private static final int QUORUM = 10;

    public static void main(String[] args) {

        BigBrother bigBrother = new BigBrother(QUORUM);

        new Thread(bigBrother).start();

        for (int i = 0; i < 5; i++) {
            new Thread(new Alumn(bigBrother), "Alumn " + (i+1)).start();
        }


    }
}
