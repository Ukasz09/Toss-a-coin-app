package GUI.sprites;

import GUI.sprites.spriteProperties.AnimatedSprite;
import GUI.sprites.spriteProperties.KindOfStateEnum;
import GUI.sprites.spriteSheetProperties.ImageSheetProperty;
import GUI.sprites.spriteSheetProperties.SpritesProperties;
import logic.Logger;
import logic.observerPattern.EventKind;
import logic.observerPattern.IObservable;
import logic.observerPattern.IObserver;

import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class CoinSprite extends AnimatedSprite implements IObserver, IObservable {
    private static final ImageSheetProperty DEFAULT_SHEET_PROPERTY = SpritesProperties.coinSheetProperty();
    private static final double WIDTH_TO_FRAME_PROPORTION = 0.17;
    private static final int DEFAULT_COIN_ANIMATION_LENGTH = 150;
    protected static final long ROTATING_ANIMATION_FRAME_PERIOD_INTERVAL = 8;
    protected static final long SHINING_ANIMATION_FRAME_PERIOD_INTERVAL = 125;

    private Timer timer;
    private int actualCoinAnimationLength = 0;
    private Set<IObserver> observers;
    private boolean playerTossTheCoin = false;

    public CoinSprite(int windowWidth, int windowHeight) {
        super(getWidthAfterScaling(WIDTH_TO_FRAME_PROPORTION, windowWidth), getWidthAfterScaling(WIDTH_TO_FRAME_PROPORTION, windowWidth),
                windowWidth / 2 - getWidthAfterScaling(WIDTH_TO_FRAME_PROPORTION, windowWidth) / 2,
                (int)(windowHeight / 2 - getHeightAfterScaling(WIDTH_TO_FRAME_PROPORTION, windowHeight) * 1.5), DEFAULT_SHEET_PROPERTY, KindOfStateEnum.COIN_HEADS, SHINING_ANIMATION_FRAME_PERIOD_INTERVAL);
        observers = new HashSet<>();
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), ANIMATION_FRAME_TIMER_DELAY, ROTATING_ANIMATION_FRAME_PERIOD_INTERVAL);
        runAnimation();
    }

    private class ScheduleTask extends TimerTask {
        @Override
        public void run() {
            if (!needToStopAnimation())
                reduceCoinAnimationLength();
            if(needToDisplayResults()){
                notifyObservers(EventKind.COIN_STOP_ROTATING);
                restoreCoinAnimationLength();
                runAnimation();
                playerTossTheCoin=false;
            }
        }
    }

    private void reduceCoinAnimationLength(){
        if(actualCoinAnimationLength>0)
            actualCoinAnimationLength--;
    }

    @Override
    protected boolean needToStopAnimation() {
        return (actualCoinAnimationLength <= 0 && getActualAnimationStateEnum()==KindOfStateEnum.COIN_ROTATING);
    }

    private boolean needToDisplayResults() {
        return (needToStopAnimation() && playerTossTheCoin);
    }

    @Override
    public void updateObserver(EventKind eventKind) {
        if (eventKind == EventKind.TOSS_COIN_BUTTON_CLICKED) {
            playerTossTheCoin=true;
            restoreCoinAnimationLength();
            changeState(KindOfStateEnum.COIN_ROTATING);
            runAnimation();
        } else Logger.logError(getClass(), "Unknown event");
    }

    private void restoreCoinAnimationLength() {
        actualCoinAnimationLength = DEFAULT_COIN_ANIMATION_LENGTH;
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
        if(state==KindOfStateEnum.COIN_ROTATING)
            setAnimationPeriodInterval(ROTATING_ANIMATION_FRAME_PERIOD_INTERVAL);
        else setAnimationPeriodInterval(SHINING_ANIMATION_FRAME_PERIOD_INTERVAL);

    }
}
