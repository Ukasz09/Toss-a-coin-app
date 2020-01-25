package GUI.sprites.spriteProperties;

import java.awt.*;

public abstract class ImageSprite extends Sprite {
    private Image spriteImage;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ImageSprite(int width, int height, Image spriteImage, int positionX, int positionY) {
        super(width, height, positionX, positionY);
        this.spriteImage = spriteImage;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void render(Graphics g) {
        renderSprite(g);
    }

    private void renderSprite(Graphics g) {
        g.drawImage(spriteImage, positionX, positionY, width, height, null);
    }

    protected void setSpriteImage(Image spriteImage) {
        this.spriteImage = spriteImage;
    }

    public Image getSpriteImage() {
        return spriteImage;
    }
}
