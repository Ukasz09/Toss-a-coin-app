package GUI.sprites;

import GUI.sprites.spriteProperties.AnimatedSprite;
import GUI.sprites.spriteProperties.KindOfStateEnum;
import GUI.sprites.spriteSheetProperties.ImageSheetProperty;
import GUI.sprites.spriteSheetProperties.SpritesProperties;
import logic.Logger;
import logic.observerPattern.EventKind;
import logic.observerPattern.IObservable;
import logic.observerPattern.IObserver;

import java.awt.event.*;
import java.util.*;

public class CoinSprite extends AnimatedSprite implements IObserver, IObservable {
    private static final ImageSheetProperty DEFAULT_SHEET_PROPERTY = SpritesProperties.coinSheetProperty();
    private static final double SIZE_TO_FRAME_PROPORTION = 0.17;
    private static final double SHRINK_COIN_PROPORTION =0.992;
    private static final double SHRINK_ANIMATION_TIME_FOR_SCALING =0.00052;
    private static final int DEFAULT_COIN_ANIMATION_LENGTH = 150;
    protected static final long ROTATING_ANIMATION_FRAME_PERIOD_INTERVAL = 8;
    protected static final long SHINING_ANIMATION_FRAME_PERIOD_INTERVAL = 125;

    private Timer timer;
    private int actualCoinAnimationLength = 0;
    private Set<IObserver> observers;
    private boolean coinIsTossing = false;
    private int windowWidth;
    private int windowHeight;

    public CoinSprite(int windowWidth, int windowHeight) {
        super(getCoinNormalSize(windowWidth), getCoinNormalSize(windowWidth), getCoinNormalPositionX(windowWidth), getCoinNormalPositionY(windowHeight),
                DEFAULT_SHEET_PROPERTY, KindOfStateEnum.COIN_HEADS, SHINING_ANIMATION_FRAME_PERIOD_INTERVAL);
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        observers = new HashSet<>();
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), ANIMATION_FRAME_TIMER_DELAY, ROTATING_ANIMATION_FRAME_PERIOD_INTERVAL);
        addMouseListener();
        runAnimation();
    }

    private class ScheduleTask extends TimerTask {
        @Override
        public void run() {
            if (!needToStopAnimation()) {
                reduceCoinAnimationLength();
                shrinkSize();
            }
            if (needToDisplayResults()) {
                notifyObservers(EventKind.COIN_STOP_ROTATING);
                restoreCoinAnimationLength();
                runAnimation();
                coinIsTossing = false;
            }
        }
    }

    private void addMouseListener() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!coinIsTossing){
                    int x = e.getX();
                    int y = e.getY();
                    if (x >= getPositionX() && x < getPositionX() + getSpriteWidth() && y >= getPositionY() && y <= getPositionY() + getSpriteHeight()){
                        setCoinStartedProperties();
                        notifyObservers(EventKind.COIN_READY_TO_TOSS);
                    }
                }

            }
        });
    }

    private void setCoinStartedProperties(){
        int normalSize=getCoinNormalSize(windowWidth);
        int startedPosX=getCoinNormalPositionX(windowWidth);
        int startedPosY=getCoinNormalPositionY(windowHeight);
        setWidth(normalSize);
        setHeight(normalSize);
        setPositionX(startedPosX);
        setPositionY(startedPosY);
    }

    private void shrinkSize() {
        if (getActualAnimationStateEnum() == KindOfStateEnum.COIN_ROTATING) {
            int randomOffset=(int)(Math.random()*3)-1;
            //for proper scaling (including small resolutions)
            int newSize = (int) (getSpriteWidth() * SHRINK_COIN_PROPORTION);
            System.out.println(newSize);
            System.out.println("LEN:"+actualCoinAnimationLength);
            double translateDiff = (getSpriteWidth() - newSize);
            int newPosX = (int) (getPositionX() + translateDiff / 2)+randomOffset;
            int newPosY = (int) (getPositionY() + translateDiff)+randomOffset;
            setWidth(newSize);
            setHeight(newSize);
            setPositionX(newPosX);
            setPositionY(newPosY);
        }
    }

    private static int getCoinNormalSize(int windowWidth) {
        return getCoinSizeAfterScaling(SIZE_TO_FRAME_PROPORTION, windowWidth);
    }

    private static int getCoinNormalPositionX(int windowWidth) {
        return (windowWidth / 2 - getCoinSizeAfterScaling(SIZE_TO_FRAME_PROPORTION, windowWidth) / 2);
    }

    private static int getCoinNormalPositionY(int windowHeight) {
        return ((int) (windowHeight / 2 - getHeightAfterScaling(SIZE_TO_FRAME_PROPORTION, windowHeight) * 1.5));
    }


    private void reduceCoinAnimationLength() {
        if (actualCoinAnimationLength > 0)
            actualCoinAnimationLength--;
    }

    @Override
    protected boolean needToStopAnimation() {
        return (actualCoinAnimationLength <= 0 && getActualAnimationStateEnum() == KindOfStateEnum.COIN_ROTATING);
    }

    private boolean needToDisplayResults() {
        return (needToStopAnimation() && coinIsTossing);
    }

    @Override
    public void updateObserver(EventKind eventKind) {
        if (eventKind == EventKind.TOSS_COIN_BUTTON_CLICKED) {
            coinIsTossing = true;
            restoreCoinAnimationLength();
            changeState(KindOfStateEnum.COIN_ROTATING);
            runAnimation();
        } else Logger.logError(getClass(), "Unknown event");
    }

    private void restoreCoinAnimationLength() {
        actualCoinAnimationLength =(int)(DEFAULT_COIN_ANIMATION_LENGTH*SHRINK_ANIMATION_TIME_FOR_SCALING*windowWidth);
    }

    @Override
    public void attachObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detachObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(EventKind eventKind) {
        for (IObserver observer : observers)
            observer.updateObserver(eventKind);
    }

    @Override
    public void changeState(KindOfStateEnum state) {
        super.changeState(state);
        if (state == KindOfStateEnum.COIN_ROTATING)
            setAnimationPeriodInterval(ROTATING_ANIMATION_FRAME_PERIOD_INTERVAL);
        else setAnimationPeriodInterval(SHINING_ANIMATION_FRAME_PERIOD_INTERVAL);
    }
}
