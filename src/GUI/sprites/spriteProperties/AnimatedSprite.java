package GUI.sprites.spriteProperties;

import GUI.sprites.spriteSheetProperties.FrameStatePositions;
import GUI.sprites.spriteSheetProperties.ImageSheetProperty;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public abstract class AnimatedSprite extends ImageSprite implements IAnimatedSpriteGraphic {
    protected static final int ANIMATION_FRAME_TIMER_DELAY = 0;

    private ImageSheetProperty spriteSheetProperty;
    private FrameStatePositions actualAnimationStateFrames;
    private KindOfStateEnum actualAnimationStateEnum;
    private double actualCooldownOnFrame;
    private int actualFramePositionX;
    private int actualFramePositionY;
    private java.util.Timer timer;
    private boolean animationIsRunning = false;

    public AnimatedSprite(int width, int height, int positionX, int positionY, ImageSheetProperty sheetProperty, KindOfStateEnum startedAnimationState, long animationIntervalPerFrame) {
        super(width, height, sheetProperty.getSheet(), positionX, positionY);
        this.spriteSheetProperty = sheetProperty;
        this.actualAnimationStateEnum=startedAnimationState;
        this.actualAnimationStateFrames = sheetProperty.getAction(actualAnimationStateEnum);
        actualCooldownOnFrame = 0;
       setActualFramesPositionToMinFromState();
        timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), ANIMATION_FRAME_TIMER_DELAY, animationIntervalPerFrame);
    }

    private class ScheduleTask extends TimerTask {

        @Override
        public void run() {
            if (animationIsRunning)
                update();
            if (needToStopAnimation())
                stopAnimation();
        }
    }

    private void setActualFramesPositionToMinFromState(){
        actualFramePositionX = actualAnimationStateFrames.getMinX();
        actualFramePositionY = actualAnimationStateFrames.getMinY();
    }

    private void updateSpriteSheetFrame() {
        updateActualCooldownOnFrame();
        if (needToChangeFrame()) {
            int minPositionX = actualAnimationStateFrames.getMinX();
            int maxPositionX = actualAnimationStateFrames.getMaxX();
            int minPositionY = actualAnimationStateFrames.getMinY();
            int maxPositionY = actualAnimationStateFrames.getMaxY();
            setPositionOfNextFrame(minPositionX, maxPositionX, minPositionY, maxPositionY, spriteSheetProperty.getSheetWidth());
            restoreActualCooldown();
        }
    }

    private void updateActualCooldownOnFrame() {
        actualCooldownOnFrame -= 1;
    }

    private boolean needToChangeFrame() {
        return (actualCooldownOnFrame <= 0);
    }

    private void setPositionOfNextFrame(int minXPosition, int maxXPosition, int minYPosition, int maxYPosition, int sheetWidth) {
        //Finished one cycle
        actualFramePositionX += spriteSheetProperty.getWidthOfOneFrame();
        if (actualFramePositionX >= maxXPosition && actualFramePositionY >= maxYPosition) {
            actualFramePositionX = minXPosition;
            actualFramePositionY = minYPosition;
        }
        //Stepped out of sheet
        else if (actualFramePositionX >= sheetWidth) {
            actualFramePositionX = 0;
            actualFramePositionY += spriteSheetProperty.getHeightOfOneFrame();
        }
    }

    private void restoreActualCooldown() {
        actualCooldownOnFrame = spriteSheetProperty.getTimeOnFrameInAnimation();
    }

    @Override
    public void render(Graphics g) {
        int widthOfOneFrame = spriteSheetProperty.getWidthOfOneFrame();
        int heightOfOneFrame = spriteSheetProperty.getHeightOfOneFrame();
        g.drawImage(spriteSheetProperty.getSheet(), positionX, positionY,
                positionX + width, positionY + height, actualFramePositionX, actualFramePositionY, actualFramePositionX + widthOfOneFrame, actualFramePositionY + heightOfOneFrame, null);
    }

    @Override
    public void update() {
        updateSpriteSheetFrame();
    }

    @Override
    public void changeState(KindOfStateEnum state) {
        actualAnimationStateEnum=state;
        actualAnimationStateFrames = spriteSheetProperty.getAction(state);
        setActualFramesPositionToMinFromState();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.repaint();
        render(g);
    }

    public void runAnimation() {
        animationIsRunning = true;
    }

    public void stopAnimation() {
        animationIsRunning = false;
    }

    protected abstract boolean needToStopAnimation();

    public KindOfStateEnum getActualAnimationStateEnum() {
        return actualAnimationStateEnum;
    }

    protected void setAnimationPeriodInterval(long animationPeriodInterval) {
        timer.cancel();
        timer=new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), ANIMATION_FRAME_TIMER_DELAY, animationPeriodInterval);
    }
}
