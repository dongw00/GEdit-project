package SubPanel;

import Frame.MainFrame;
import Information.Information;
import SubFrame.StackFrame;
import figure.*;
import figure.Polygon;
import figure.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;
import java.util.Stack;
import java.util.Vector;

public class DrawPanel extends JPanel implements MouseMotionListener, MouseListener, Serializable {
	public Vector<Figure> figureSet = new Vector<Figure>();
	private String filepath = null;
	private Stack<Vector<Figure>> figureStack = new Stack<Vector<Figure>>();
	private Stack<Vector<Figure>> figureCancelStack = new Stack<Vector<Figure>>();
	private int dragStartX, dragStartY;
	private Vector<Point> point = new Vector<>();
	private int[] X_point = new int[10];
	private int[] Y_point = new int[10];
	private int count = 0;

	public DrawPanel() {
		Information.setCurrentpanel(this);
		this.setBackground(new Color(255, 255, 255));
		setVisible(true);
		this.setFocusable(true);
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g2);

		for (Figure current : figureSet) {
			current.drawFigure(g2);
		}
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		MainFrame.getInstance().setBottomPanel("X : " + e.getX() + " Y : " + e.getY());

		if (Information.getCurrentMode() == Information.MODE_MOVE) {
			if (Information.getCurrentFigure() != null) {
				int moveX = e.getX() - dragStartX;
				int moveY = e.getY() - dragStartY;
				Information.getCurrentFigure().moveTo(moveX, moveY);
				dragStartX = e.getX();
				dragStartY = e.getY();
				repaint();
			}
		} else if (Information.getCurrentMode() == Information.MODE_PAINT) {
		} else if (Information.getCurrentMode() == Information.MODE_RESIZE) {
			if (Information.getCurrentFigure() != null) {
				Information.getCurrentFigure().calcFigure(e.getX(), e.getY());
				repaint();
			}
		} else {
			Figure current = figureSet.lastElement();
			figureSet.remove(figureSet.lastElement());
			drawCurrentFigureFunc(e, current);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		MainFrame.getInstance().setBottomPanel("X : " + e.getX() + " Y : " + e.getY());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Information.setCurrentpanel(this);
		StackFrame.getInstance().setListItems(figureSet);
		if (Information.getCurrentMode() == Information.MODE_MOVE) {
			if (Information.getCurrentFigure() != null) {
				dragStartX = e.getX();
				dragStartY = e.getY();
			} else {
				JOptionPane.showMessageDialog(null, "Error : Cant' find figure", "ERROR", JOptionPane.ERROR_MESSAGE);
			}

		} else if (Information.getCurrentMode() == Information.MODE_PAINT) {

			if (Information.getCurrentFigure() != null) {
				Information.getCurrentFigure().setFigurePaint(Information.getCurrentColor());
				repaint();
			} else {
				this.setBackground(Information.getCurrentColor());//////////////////////////////////
				Information.backgroundColor = Information.getCurrentColor();
				repaint();
			}

		} else if (Information.getCurrentMode() == Information.MODE_GRA) {

			if (Information.getCurrentFigure() != null) {
				Information.setGra(Information.getBeforeColor(), Information.getCurrentColor());
				Information.getCurrentFigure().setFigurePaint(Information.getGra());
				repaint();
			} else {
				this.setBackground(Information.getCurrentColor());
				repaint();
			}

		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
		MainFrame.getInstance().setBottomPanel("Out of Frame ");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Information.setCurrentpanel(this);

		if (Information.getCurrentMode() == Information.MODE_MOVE) {
			if (Information.getCurrentFigure() != null) {
				dragStartX = e.getX();
				dragStartY = e.getY();
			} else {
				JOptionPane.showMessageDialog(null, "Error : Cant' find figure", "ERROR", JOptionPane.ERROR_MESSAGE);
			}

		} else if (Information.getCurrentMode() == Information.MODE_PAINT) {

			if (Information.getCurrentFigure() != null) {
			} else {
				this.setBackground(Information.getCurrentColor());
				repaint();
			}
		}

		else if (Information.getCurrentMode() == Information.MODE_GRA) {

			if (Information.getCurrentFigure() != null) {
				Information.setGra(Information.getBeforeColor(), Information.getCurrentColor());
				Information.getCurrentFigure().setFigurePaint(Information.getGra());
				repaint();
			} else {
			}

		}

		else if (Information.getCurrentMode() == Information.MODE_RESIZE) {

		} else {
			drawFigureFunc(e);

		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		StackFrame.getInstance().setListItems(figureSet);
		figureStack.push((Vector<Figure>) figureSet.clone());
		figureCancelStack.clear();
	}

	private void drawFigureFunc(MouseEvent e) {
		int mode = Information.getCurrentMode();
		switch (mode) {
		case Information.MODE_PEN:
			Pen pen = new Pen(e.getX(), e.getY());
			figureSet.addElement(pen);
			break;
		case Information.MODE_DRAW_REC:
			Rectangle rec = new Rectangle(e.getX(), e.getY(), 0, 0);
			figureSet.addElement(rec);
			break;
		case Information.MODE_DRAW_CIRCLE:
			Circle circle = new Circle(e.getX(), e.getY(), 0, 0);
			figureSet.addElement(circle);
			break;
		case Information.MODE_DRAW_LINE:
			Line line = new Line(e.getX(), e.getY(), e.getX(), e.getY());
			figureSet.addElement(line);
			break;
		case Information.MODE_DRAW_POLYGON:
			if (count != 10) {
				if (e.getClickCount() == 2) {
					Polygon polygon = new Polygon(X_point, Y_point, count);
					figureSet.addElement(polygon);
					count = 0;
				} else {
					System.out.println(String.format("%d %d", e.getX(), e.getY()));
					point.add(new Point(e.getX(), e.getY()));
					X_point[count] = e.getX();
					Y_point[count] = e.getY();
					count++;
				}
			} else {
				Polygon polygon = new Polygon(X_point, Y_point, count);
				figureSet.addElement(polygon);
				count = 0;
			}
			break;

		case Information.MODE_ERAGE:
			Eraser eraser = new Eraser(e.getX(), e.getY());
			figureSet.addElement(eraser);
			break;
		case Information.MODE_TEXT:
			Text text = new Text(e.getX(), e.getY());
			figureSet.addElement(text);
			StackFrame.getInstance().setListItems(figureSet);
			break;
		default:
			return;
		}
		repaint();
	}

	public void drawCurrentFigureFunc(MouseEvent e, Figure temp) {
		temp.calcFigure(e.getX(), e.getY());
		figureSet.addElement(temp);
		repaint();
	}

	public void changeVector(Vector<Figure> vector) {
		figureSet = vector;
	}

	public Vector<Figure> getVector() {
		return figureSet;
	}

	public void addVector(Vector<Figure> addData) {
		figureSet.addAll(addData);
		StackFrame.getInstance().setListItems(figureSet);

		figureStack.push((Vector<Figure>) figureSet.clone());
		figureCancelStack.clear();
		repaint();
	}

	public void addVector(Figure addData) {
		figureSet.add((Figure) addData.clone());

		StackFrame.getInstance().setListItems(figureSet);
		figureStack.push((Vector<Figure>) figureSet.clone());
		figureCancelStack.clear();
		repaint();
	}

	public void claearFigure() {
		figureSet.clear();
		figureStack.clear();
		figureCancelStack.clear();
		StackFrame.getInstance().setListItems(figureSet);
		repaint();
	}

	public void deleteFigure(int idx) {
		figureStack.push((Vector<Figure>) figureSet.clone());
		figureCancelStack.clear();
		figureSet.remove(idx);
		StackFrame.getInstance().setListItems(figureSet);

		repaint();
	}

	public void popStackTrace() {
		if (figureStack.isEmpty()) {

			figureCancelStack.push((Vector<Figure>) figureSet.clone());
			figureSet.clear();
			repaint();
			StackFrame.getInstance().setListItems(figureSet);
			JOptionPane.showMessageDialog(null, "Error : Cant' find More Action", "ERROR", JOptionPane.ERROR_MESSAGE);

			return;
		} else {

			figureCancelStack.push((Vector<Figure>) figureSet.clone());

			if (figureSet.equals(figureStack.peek()))
				figureStack.pop();
			if (!figureStack.empty()) {
				figureSet = (Vector<Figure>) figureStack.peek().clone();
				figureStack.pop();
			} else {
				figureSet.clear();
			}
			StackFrame.getInstance().setListItems(figureSet);
			repaint();
		}

	}

	public void popStackCaneStack() {
		if (figureCancelStack.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Error : Cant' find More Action", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			figureStack.push((Vector<Figure>) figureSet.clone());
			figureSet = (Vector<Figure>) figureCancelStack.pop().clone();

			StackFrame.getInstance().setListItems(figureSet);
			repaint();
		}
	}
}
