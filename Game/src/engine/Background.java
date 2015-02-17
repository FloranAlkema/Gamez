package engine;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Background extends Panel {

	public Image img;

	public Background(Graphics g) {
		img = Toolkit.getDefaultToolkit().createImage("Background2.png");
		paint(g);
	}

	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

}
