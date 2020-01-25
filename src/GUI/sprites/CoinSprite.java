package GUI.sprites;

import GUI.sprites.spriteProperties.AnimatedSprite;
import GUI.sprites.spriteProperties.KindOfStateEnum;
import GUI.sprites.spriteSheetProperties.ImageSheetProperty;
import GUI.sprites.spriteSheetProperties.SpritesProperties;

public class CoinSprite extends AnimatedSprite {
    private final static ImageSheetProperty DEFAULT_SHEET_PROPERTY = SpritesProperties.coinSheetProperty();
    private final static double WIDTH_TO_FRAME_PROPORTION = 0.17;
//    public final static double HEIGHT_TO_FRAME_PROPORTION = 0.1;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public CoinSprite(int windowWidth, int windowHeight) {
        super(getWidthAfterScaling(WIDTH_TO_FRAME_PROPORTION, windowWidth), getWidthAfterScaling(WIDTH_TO_FRAME_PROPORTION, windowWidth),
                windowWidth/2-getWidthAfterScaling(WIDTH_TO_FRAME_PROPORTION, windowWidth)/2,
                windowHeight/2-getHeightAfterScaling(WIDTH_TO_FRAME_PROPORTION,windowHeight)*2, DEFAULT_SHEET_PROPERTY, DEFAULT_SHEET_PROPERTY.getAction(KindOfStateEnum.COIN_HEADS));
    }
}
