package com.rafaels.game.state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.rafaels.game.main.Resources;

public class MenuState extends State{

	@Override
	public void init() {
		System.out.println("dentro de MenuState");		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		//Dibuja en pantalla welcome.png en (0,0)
		g.drawImage(Resources.welcome, 0, 0, null);		
	}

	@Override
	public void onClick(MouseEvent e) {
		setCurrentState(new PlayState());
		
	}

	@Override
	public void onKeyPress(KeyEvent e) {
		System.out.println("on KeyPress!! de MenuState");		
	}

	@Override
	public void onKeyRelease(KeyEvent e) {
		System.out.println("on KeyRelease!! de MenuState");
		
	}

}
