package figure;

import Information.Information;

import java.awt.*;
import java.util.ArrayList;

public abstract class PenType extends Figure {

    protected ArrayList<PenTypeTracePoint> points = new ArrayList<PenTypeTracePoint>();

    protected int preX, preY;


    public PenType(int firstX, int firstY)    //처음 찍은 점
    {

        preX = firstX;
        preY = firstY;

        points.add(new PenTypeTracePoint(firstX, firstY, firstX, firstY));    //??????????????????????????
    }

    @Override
    public void setFigureSize(int currentX, int currentY) {    //현재 점사이즈
        // TODO Auto-generated method stub

        points.add(new PenTypeTracePoint(preX, preY, currentX, currentY));    //왜??????????????????????????????
        preX = currentX;
        preY = currentY;
    }

    @Override
    public int getSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public abstract void drawFigure(Graphics2D g);    //그리기

    @Override
    public void calcFigure(int currentX, int currentY) {
        // TODO Auto-generated method stub
        if (Information.getCurrentMode() != Information.MODE_RESIZE) {
            setFigureSize(currentX, currentY);
        }

    }

    public void moveTo(int curX, int curY)    //이동
    {
        for (PenTypeTracePoint curPoints : points)    //???????????????????????????????????????
        {
            curPoints.prePoints.x += curX;
            curPoints.prePoints.y += curY;
            curPoints.LastPoints.x += curX;
            curPoints.LastPoints.y += curY;
        }
    }

    public abstract Figure clone();    //복사

    class PenTypeTracePoint//현재,과거 점 초기화
    {
        Point prePoints;    //현재 포인터
        Point LastPoints;    //과거 포인터

        PenTypeTracePoint(int x1, int y1, int x2, int y2) {
            prePoints = new Point(x1, y1);
            LastPoints = new Point(x2, y2);
        }
    }
}
