package figure;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Vector;

import Information.Information;

public class Polygon extends Figure {

	private int[] xPoints = new int[20];
	private int[] yPoints = new int[20];
	private int count = 0;

	public Polygon(int[] startX, int[] startY, int count) {
		super();
		this.count = count;
		for (int i = 0; i < count; i++) {
			this.xPoints[i] = startX[i];
			this.yPoints[i] = startY[i];
		}
	}

	public Polygon(Vector<Point> point) {
		super();
		this.count = point.size();
		xPoints = new int[this.count];
		yPoints = new int[this.count];
		for (int i = 0; i < count; i++) {

		}
	}

	@Override
	public void setFigureSize(int xPoint, int yPoint) {
	}

	@Override
	public int getSize() {
		return 0;
	}

	@Override
	public void drawFigure(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setPaint(figurePaint);
		if (count > 2)
			g.fillPolygon(xPoints, yPoints, this.count);
		else
			g.drawLine(xPoints[0], yPoints[0], xPoints[1], yPoints[1]);
	}

	@Override
	public void calcFigure(int currentX, int currentY) {
		// TODO Auto-generated method stub
		setFigureSize(currentX, currentY);
	}

	public void moveTo(int curX, int curY) {
		for (int i = 0; i < 20; i++) {
			xPoints[i] += curX;
			yPoints[i] += curY;
		}

	}

	@Override
	public String toString() {
		return "Polygon [xPoints=" + Arrays.toString(xPoints) + ", yPoints=" + Arrays.toString(yPoints) + "]";
	}

	@Override
	public Figure clone() {
		Polygon obj = new Polygon(xPoints, yPoints, count);
		obj.xPoints = xPoints.clone();
		obj.yPoints = yPoints.clone();
		return obj;
	}
}
