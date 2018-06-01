package Frame;

import SubFrame.ColorChooser;
import SubFrame.DrawInternalFrame;
import SubFrame.StackFrame;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class MainDesktopPane extends JDesktopPane {
	private static MainDesktopPane instance;
	private HashMap<String, DrawInternalFrame> drawInternalFrameSet;
	private ColorChooser colorChooser;
	private StackFrame stackFrame;
	private ImageIcon backgrountIcon;
	private int previousWidth = -1;
	private Color myColor = new Color(245, 246, 247);

	public MainDesktopPane() {
		drawInternalFrameSet = new HashMap<String, DrawInternalFrame>();
		this.setVisible(true);
		this.setBackground(myColor);
		this.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);

		backgrountIcon = new ImageIcon("resource/1UI.png");

		colorChooser = new ColorChooser();
		stackFrame = StackFrame.getInstance();

		colorChooser.setLocation(1190, 0);
		stackFrame.setLocation(1190, 360);

		this.add(colorChooser);
		this.add(stackFrame);
	}

	public static MainDesktopPane getInstance() {
		if (instance == null)
			instance = new MainDesktopPane();
		return instance;
	}

	@Override
	public void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();

		g.drawImage(backgrountIcon.getImage(), width / 2 - 400, height / 2 - 350, null);
		if (width != previousWidth) {
			colorChooser.setLocation(width - 300, 0);
			stackFrame.setLocation(width - 300, 350);
		}
		previousWidth = width;
	}

	public void addDrawFrameToSet(String name, DrawInternalFrame newFrame) {
		drawInternalFrameSet.put(name, newFrame);
	}

	public DrawInternalFrame getDrawFrame(String key) {
		System.out.println("패스 : " + key);
		return drawInternalFrameSet.get(key);
	}
}
