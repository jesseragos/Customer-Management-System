package com.cms.ui.utils;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Image img;
	
	public ImagePanel()
	{
		super();
	}
	
	public ImagePanel(String img)
	{ 
		setImage(img);
	}
	
	public ImagePanel(Image img) {
		setImage(img);
	}
	
	public void setImage(String img){
		setImage(new ImageIcon(img).getImage());
	}
	
	public void setImage(Image img){
		if(img != null){
			this.img = img; 
			Dimension size = new Dimension(img.getWidth(null), img.getHeight(null)); 
			setPreferredSize(size); 
			setMinimumSize(size); 
			setMaximumSize(size); 
			setSize(size);
		}
	}

	public void paintComponent(Graphics g)
	{ 
//		g.drawImage(img, 0, 0, null);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
}
