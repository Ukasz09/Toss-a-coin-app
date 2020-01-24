package logic;

import java.util.Random;

public class CoinTosser {
    private int tailsCount;
    private int headsCount;
    private Random randomGenerator;

    public CoinTosser() {
        tailsCount = 0;
        headsCount = 0;
        randomGenerator=new Random();
    }

    public void tossACoin(){
        if(isHeadSide())
            headsCount++;
        else  tailsCount++;
    }

    private boolean isHeadSide(){
        return randomGenerator.nextBoolean();
    }

    public int getTailsCount() {
        return tailsCount;
    }

    public int getHeadsCount() {
        return headsCount;
    }
}
