package com.rafaels.game.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import com.rafaels.framework.util.InputHandler;
import com.rafaels.game.state.LoadState;
import com.rafaels.game.state.State;

@SuppressWarnings("serial")

public class Game extends JPanel implements Runnable{
	
	private int gameWidth;
	private int gameHeight;	
	private Image gameImage;
	
	private Thread gameThread;
	private volatile boolean running;	
	private volatile State currentState;
	
	private InputHandler inputHandler;
	
	public Game(int gameWidth, int gameHeight){
		this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
		setPreferredSize(new Dimension(gameWidth, gameHeight));
		setBackground(Color.BLACK);
		setFocusable(true);
		requestFocus();		
	}

	public void setCurrentState(State newState){
		System.gc();
		newState.init();
		currentState = newState;
		inputHandler.setCurrentState(currentState);
	}
	
	@Override
	public void addNotify(){
		// invoca al metodo addNotify de la clase padre JPanel, por eso tb @Override
		super.addNotify();
		initInput();
		setCurrentState(new LoadState());
		initGame();
	}
	
	private void initGame(){
		running = true;
		gameThread = new Thread(this, "Game Thread");
		gameThread.start();
	}
	
	@Override
	public void run() {
		
		while(running){
			currentState.update();
			prepareGameImage();
			currentState.render(gameImage.getGraphics());
			repaint();
			try {
				Thread.sleep(14);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//El juego finaliza cuando la condicion del while es falso
		System.exit(0);		
	}
	
	private void prepareGameImage(){
		if(gameImage == null)
			gameImage = createImage(gameWidth, gameHeight);
		
		Graphics g = gameImage.getGraphics();
		g.fillRect(0, 0, gameWidth, gameHeight);		
	}
	
	public void exit(){
		running = false;
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		if (gameImage == null){
			return;
		}
		g.drawImage(gameImage, 0, 0, null);
	}
	
	private void initInput(){
		inputHandler = new InputHandler();
		addKeyListener(inputHandler);
		addMouseListener(inputHandler);		
	}
}
