package com.rafaels.game.main;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.image.BufferedImage;
//import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;



public class Resources {

	public static BufferedImage welcome, iconimage, line, fondo, ball, gargolaLeft, gargolaRight;
	public static AudioClip hit, bounce;
	
	public static Color darkBlue, darkRed;
	
	public static void load(){
		welcome = loadImage("welcome.png");
		iconimage = loadImage("iconimage.png");
		line = loadImage("line.png");
		fondo = loadImage("fondo.png");
		ball = loadImage("ball1.png");
		gargolaLeft = loadImage("gargolaLeft.png");
		gargolaRight = loadImage("gargolaRight.png");
		hit = loadSound("hit.wav");
		bounce = loadSound("bounce.wav");
		darkBlue = new Color(25, 83, 105);
		darkRed = new Color(105, 13, 13);
	}
	
	private static AudioClip loadSound(String filename){
		URL fileURL = Resources.class.getResource("/resources/" + filename);
		return Applet.newAudioClip(fileURL);		
	}
	
	private static BufferedImage loadImage(String filename){
		BufferedImage img = null;
		try {
			img = ImageIO.read(Resources.class.getResourceAsStream("/resources/" + filename));
		} catch (Exception e) {
			System.out.println("Error de lectura: " + filename);
			e.printStackTrace();
		}
		return img;
	}
}
