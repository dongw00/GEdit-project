package SubFrame;

import Frame.MainDesktopPane;
import Frame.MainFrame;
import Information.Information;
import figure.Picture;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.Buffer;

public class TopMenu extends JMenuBar {
	private JMenu fileMenu;
	private JMenuItem mkDrawPanel, open, save, exit;
	private JMenu info;
	private JMenuItem inform;

	public BufferedImage createImage(JPanel panel) {
		int w = panel.getWidth();
		int h = panel.getHeight();
		BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bi.createGraphics();
		panel.paint(g);
		return bi;
	}

	public TopMenu() {
		fileMenu = new JMenu("FILE");
		fileMenu.setMnemonic('F');
		mkDrawPanel = new JMenuItem("New");
		mkDrawPanel.setMnemonic('N');
		mkDrawPanel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int DrawFrameCnt = Information.getDrawFrame_Count();
				Information.addDrawFrame_Count();
				MainFrame.getInstance().addDrawFrame("" + DrawFrameCnt + "번째 그림판");
			}
		});

		open = new JMenuItem("Open");
		open.setMnemonic('O');
		open.addActionListener(e -> {
			JFileChooser fileChooser = new JFileChooser();	//Open할 파일 찾는 탐색창 열기

			FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, PNG", "jpg", "png");
			fileChooser.setFileFilter(filter);
			//Open할 확장자 제한

			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int judge = fileChooser.showOpenDialog(this);

			switch (judge) {
			case JFileChooser.APPROVE_OPTION:
				File x = fileChooser.getSelectedFile();
				try {
					BufferedImage image = ImageIO.read(x);

					MainFrame.getInstance().addDrawFrame(x.getPath());
					Picture picture = new Picture(0, 0, getWidth(), getHeight());
					picture.setGraphics(image);
					MainDesktopPane.getInstance().getDrawFrame(x.getPath()).draw.figureSet.addElement(picture);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;

			case JFileChooser.CANCEL_OPTION:
				break;
			}
		});

		save = new JMenuItem("Save");
		save.setMnemonic('S');
		save.addActionListener(e -> {
			File x = null;
			JFileChooser jfilechooser = new JFileChooser();

			jfilechooser.setFileFilter(new FileNameExtensionFilter("PNG", "png"));
			jfilechooser.addChoosableFileFilter(new FileNameExtensionFilter("JPG", "jpg"));

			jfilechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int judge = jfilechooser.showSaveDialog(null);
			ObjectOutputStream out = null;

			switch (judge) {
			case JFileChooser.APPROVE_OPTION:
				x = jfilechooser.getSelectedFile();
				if (jfilechooser.getFileFilter().getDescription().equals("JPG") && !x.getName().endsWith("jpg")) {
					x = new File(x.getPath() + ".JPG");
				}
				if (jfilechooser.getFileFilter().getDescription().equals("PNG") && !x.getName().endsWith("png")) {
					x = new File(x.getPath() + ".PNG");
				}
				System.out.println(jfilechooser.getFileFilter().getDescription());
				try {
					ImageIO.write(createImage(Information.getCurrentJPanel()),
							jfilechooser.getFileFilter().getDescription(), x);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				break;
			case JFileChooser.CANCEL_OPTION:
				return;
			}
		});

		exit = new JMenuItem("Exit");
		exit.setMnemonic('E');
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		fileMenu.add(mkDrawPanel);
		fileMenu.add(open);
		fileMenu.add(save);
		fileMenu.add(exit);
		this.add(fileMenu);

		info = new JMenu("Help");
		info.setMnemonic('H');
		inform = new JMenuItem("Information");
		inform.setMnemonic('I');

		inform.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "      만든이 : 서동우, 안종화, 노신영 ", "시바 그림판", JOptionPane.CLOSED_OPTION);
			}
		});
		info.add(inform);
		this.add(info);
	}
}
