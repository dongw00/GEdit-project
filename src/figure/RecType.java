package figure;

import java.awt.*;

public abstract class RecType extends Figure {
    protected int width, height;
    protected int startX, startY;
    protected int X, Y;


    public RecType(int startX, int startY, int width, int height)    //X,Y,Width,Height초기화
    {
        super();
        X = this.startX = startX;
        Y = this.startY = startY;
        this.width = width;
        this.height = height;
    }

    @Override
    public void setFigureSize(int width, int height) {    //너비와 높이 정의
        // TODO Auto-generated method stub
        this.width = width;
        this.height = height;

    }

    @Override
    public int getSize() {            //면적
        // TODO Auto-generated method stub
        return width * height;
    }

    @Override
    public abstract void drawFigure(Graphics2D g);        //추상

    @Override
    public void calcFigure(int currentX, int currentY) {        //계산????????????????????????????????????????????????????
        // TODO Auto-generated method stub


        int wantWidth = currentX - startX;    //원하는 너비
        int wantHeight = currentY - startY;    //원하는 높이

        setFigureSize(Math.abs(wantWidth), Math.abs(wantHeight));    //절대 값으로 값 변환

        if (wantWidth < 0 && wantHeight < 0) {
            X = startX + wantWidth;
            Y = startY + wantHeight;
        } else if (wantWidth < 0 && wantHeight >= 0) {

            X = startX + wantWidth;
        } else if (wantWidth >= 0 && wantHeight < 0) {
            Y = startY + wantHeight;

        } else if (wantWidth >= 0 && wantHeight >= 0) {
            X = startX;
            Y = startY;
        }
    }


    public void moveTo(int curX, int curY)        //이동
    {
        X += curX;
        Y += curY;
    }

    public abstract Figure clone();        //추상 복사
}
