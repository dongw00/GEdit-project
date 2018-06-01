package figure;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Picture extends RecType {
    private BufferedImage bufferedImage;

    public Picture(int startX, int startY, int width, int height) {
        super(startX, startY, width, height);
    }

    public void setGraphics(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    @Override
    public void drawFigure(Graphics2D g) {
        if (bufferedImage == null) {
            System.out.println("Error: bufferedImage is null; call setGraphics function before call this function!");
            return;
        }
        g.drawImage(bufferedImage, 0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), null);
    }

    @Override
    public String toString() {
        return "Picture [width=" + width + ", height=" + height + "]";
    }

    @Override
    public Figure clone() {
        return null;
    }
}
