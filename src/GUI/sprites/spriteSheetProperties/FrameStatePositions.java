package GUI.sprites.spriteSheetProperties;

public class FrameStatePositions {
    private int minX;
    private int maxX;
    private int minY;
    private int maxY;
    private int startIndex;
    private int endIndex;

    FrameStatePositions(int minX, int maxX, int minY, int maxY) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    public int getMinX() {
        return minX;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMinY() {
        return minY;
    }

    public int getMaxY() {
        return maxY;
    }

    protected void setIndexes(int startIndex, int endIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }
}