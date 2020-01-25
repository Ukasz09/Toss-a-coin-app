package GUI.sprites.spriteProperties;

import GUI.sprites.spriteSheetProperties.FrameStatePositions;
import GUI.sprites.spriteSheetProperties.ImageSheetProperty;

import java.awt.*;

public abstract class AnimatedSprite extends ImageSprite implements IAnimatedSpriteGraphic {
    private ImageSheetProperty spriteSheetProperty;
    private FrameStatePositions actualAnimationState;
    private double actualCooldownOnFrame;
    private int actualFramePositionX;
    private int actualFramePositionY;

    public AnimatedSprite(int width, int height, int positionX, int positionY, ImageSheetProperty sheetProperty, FrameStatePositions startedAnimationState) {
        super(width, height, sheetProperty.getSheet(), positionX, positionY);
        this.spriteSheetProperty = sheetProperty;
        this.actualAnimationState = startedAnimationState;
        actualCooldownOnFrame = 0;
        actualFramePositionX = 0;
        actualFramePositionY = 0;
    }

    @Override
    public void update() {
        updateSpriteSheetFrame();
    }

    private void updateSpriteSheetFrame() {
        updateActualCooldownOnFrame();
        if (needToChangeFrame()) {
            int minPositionX = actualAnimationState.getMinX();
            int maxPositionX = actualAnimationState.getMaxX();
            int minPositionY = actualAnimationState.getMinY();
            int maxPositionY = actualAnimationState.getMaxY();
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
                positionX+width, positionY+ height, actualFramePositionX, actualFramePositionY, actualFramePositionX+ widthOfOneFrame, actualFramePositionY+ heightOfOneFrame, null);
    }

    @Override
    public void changeState(KindOfStateEnum state) {
        actualAnimationState = spriteSheetProperty.getAction(state);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.repaint();
        render(g);
    }
}
