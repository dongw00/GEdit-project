package figure;

import java.awt.*;

public class Circle extends RecType {


    public Circle(int startX, int startY, int width, int height) {
        super(startX, startY, width, height);
    }

    @Override
    public void drawFigure(Graphics2D g) {
        // TODO Auto-generated method stub
        //g.setColor(figureColor);	//색상
        g.setPaint(figurePaint);
        g.fillOval(X, Y, width, height);	//원그리기
        g.setColor(lineColor);
        g.setStroke(new BasicStroke(2));
        g.drawOval(X,Y,width,height);
    }

    @Override
    public String toString() {
        return "Circle [width=" + width + ", height=" + height + "]";
    }        //객체 정보

    @Override
    public Figure clone() {    //객체 복사
        Circle obj = new Circle(0, 0, 0, 0);
        obj.startX = startX;
        obj.startY = startY;
        obj.width = width;
        obj.height = height;
        obj.figurePaint = figurePaint;
        obj.X = X;
        obj.Y = Y;

        return obj;
    }

}
