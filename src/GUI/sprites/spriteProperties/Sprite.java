package GUI.sprites.spriteProperties;

import javax.swing.*;
import java.awt.*;

public abstract class Sprite extends JPanel implements IDrawingGraphic {
    protected int positionX, positionY;
    protected int width, height;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Sprite(int width, int height, int positionX, int positionY) {
        this.width = width;
        this.height = height;
        this.positionX = positionX;
        this.positionY = positionY;

        setLayout(new FlowLayout(FlowLayout.CENTER));

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

//    public int getWidth() {
//        return width;
//    }
//
//    public int getHeight() {
//        return height;
//    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    protected static int getWidthAfterScaling(double widthToScreenProportion, int windowWidth) {
        return (int)(widthToScreenProportion * windowWidth);
    }

    protected static int getHeightAfterScaling(double heightToScreenProportion, int windowHeight) {
        return (int)(heightToScreenProportion * windowHeight);
    }
}
