package SubFrame;

import SubPanel.ColorPanel;

import javax.swing.*;
import java.awt.*;

public class ColorChooser extends JInternalFrame {
    public ColorChooser() {
        super("색 선택", true, false, false, true);
        setSize(300, 350);
        setBackground(Color.WHITE);
        ColorPanel colorPanel = new ColorPanel();
        this.add(colorPanel);
        setVisible(true);
    }

}
