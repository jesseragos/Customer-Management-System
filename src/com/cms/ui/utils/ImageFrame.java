package com.cms.ui.utils;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class ImageFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private Image img;
	
	public ImageFrame()
	{
		super();
	}
	
	public ImageFrame(String img)
	{ 
		this(new ImageIcon(img).getImage());
	}
	
	public ImageFrame(Image img) {
		setImage(img);
	}

	public void setImage(String img){
		setImage(new ImageIcon(img).getImage());
	}
	
	public void setImage(Image img){
		if(img != null){
			Container pane = getContentPane();
			
			this.img = img; 
			Dimension size = new Dimension(img.getWidth(null), img.getHeight(null)); 
			pane.setPreferredSize(size); 
			pane.setMinimumSize(size); 
			pane.setMaximumSize(size); 
			pane.setSize(size);
		}
	}

	public void paintComponent(Graphics g)
	{ 
//		g.drawImage(img, 0, 0, null);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		
		
	}
}
