package GUI.sprites.spriteSheetProperties;

import GUI.sprites.spriteProperties.KindOfStateEnum;

public class SpritesProperties {
    public static ImageSheetProperty coinSheetProperty() {
        String spritePath = "src/resources/coinRotateSheet.png";
        ImageSheetProperty sheetProperty = ImageSheetProperty.builder()
                .withImagePath(spritePath)
                .withSizeOfOneFrame(334, 338)
                .withDefaultDurationPerOneFrame(1)
                .withAddActionState(KindOfStateEnum.COIN_HEADS, 0, 44)
                .build();
        return sheetProperty;
    }
}
