package com.rafaels.framework.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.rafaels.game.state.State;

public class InputHandler implements KeyListener, MouseListener {

	private State currentState;
	
	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		currentState.onClick(e);		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		currentState.onKeyPress(e);
		//System.out.println("on keypressed!! de InputHandler");		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		currentState.onKeyRelease(e);
		//System.out.println("on keyreleased!! de InputHandler");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub		
	}

}