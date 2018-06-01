package Frame;

import Information.Information;
import SubFrame.*;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
	private static MainFrame instance;
	private LeftPanel leftPanel;
	private BottomPanel bottomPanel;
	private TopPanel topPanel;
	private TopMenu menuBar;

	public MainFrame(String name) {
		super(name);
		leftPanel = new LeftPanel();
		bottomPanel = new BottomPanel(" 시작");
		topPanel = new TopPanel();
		
		bottomPanel.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
		//하단 패널 설정
		this.add(leftPanel, BorderLayout.WEST);
		this.add(bottomPanel, BorderLayout.SOUTH);
		this.add(topPanel, BorderLayout.NORTH);
		menuBar = new TopMenu();
		this.setJMenuBar(menuBar);
		this.add(MainDesktopPane.getInstance());
	}

	public static MainFrame getInstance() {
		if (instance == null)
			instance = new MainFrame(Information.PROGRAM_NAME);
		return instance;
	}

	public void addDrawFrame(String name) {
		DrawInternalFrame newFrame = new DrawInternalFrame(name);
		newFrame.setSize(500, 500);
		MainDesktopPane.getInstance().addDrawFrameToSet(name, newFrame);
		MainDesktopPane.getInstance().add(newFrame);
	}

	public void setBottomPanel(String text) {
		Color color = Information.getCurrentColor();
		String mode = Information.getCurrentModeToString();
		bottomPanel.setText("현재 모드: " + mode + "   현재 좌표 : " + text + "    R : " + color.getRed() + " G : "
				+ color.getGreen() + " B : " + color.getBlue());
	}

	public void setBottomPanel(Color color) {
		String mode = Information.getCurrentModeToString();
		bottomPanel.setText("현재 모드: " + mode + "   현재 좌표 : Out of frame" + "    R : " + color.getRed() + " G : "
				+ color.getGreen() + " B : " + color.getBlue());
	}

	public void callColorFrame() {
		GetSpecifcColorInternalFrame getSpecifcColorInternalFrame = new GetSpecifcColorInternalFrame();
		MainDesktopPane.getInstance().add(getSpecifcColorInternalFrame);
	}
}
