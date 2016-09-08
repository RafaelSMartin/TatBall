package com.rafaels.game.model;

import java.awt.Rectangle;

import com.rafaels.framework.util.RandomNumberGenerator;
import com.rafaels.game.main.GameMain;
import com.rafaels.game.main.Resources;

public class Ball {

	private int x, y, width, height, velX, velY;
	private Rectangle rect;
	
	// Constructor parametrizado
	public Ball(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		velX = 5;
		velY = RandomNumberGenerator.getRandIntBetween(-4, 5);
		rect = new Rectangle(x, y, width, height);
	}
	
	// Actualiza los valores de x e y, y los dos metodos
	public void update(){
		x += velX;
		y += velY;
		correctYCollisions();
		updateRect();
	}
	
	// Vemos si la bola ha rebasado la parte superior o inferior y la corregimos
	// Si no ha salido de la ventana solo llamamos a return que abandona el metodo
	// Las dos ultimas lineas solo se hacen si la bola rebasa los limites 
	// y saldrá rebotada y ejecutando un sonido
	private void correctYCollisions(){
		if (y < 0){
			y = 0;		
		} else if (y + height > GameMain.GAME_HEIGHT){
			y = GameMain.GAME_HEIGHT - height;
		} else {
			return;
		}
		
		velY = -velY;
		Resources.bounce.play();
	}
	
	// Acepta las coordenadas actualizadas de la pala y mueve el boundinbox 
	// a la misma posicion.
	private void updateRect(){
		rect.setBounds(x, y, width, height);
	}
	
	// Recibimos un objeto Paddle
	// Comprobamos si la bola esta en la parte izquierda de la pantalla o derecha,
	// para determinar si golpea la pala izquierda o derecha
	// Resolvemos la colisión moviendo la bola fuera del boundingbox de la parte
	// derecha de la pala izquierda y a la inversa.
	// Rebotamos la bola horizontalmente y la velY aleatoria.
	public void onCollideWith(Paddle p){
		if (x < GameMain.GAME_WIDTH / 2){
			x = p.getX() + p.getWidth();
		} else {
			x = p.getX() - width;
		}
		
		velX = -velX;
		velY += RandomNumberGenerator.getRandIntBetween(-2,  3); 
	}
	
	// Comprobamos si la bola toca la parte izquierda o derecha de la pantalla
	// Devuelve true en tal caso.
	public boolean isDead(){
		return (x < 0 || x + width > GameMain.GAME_WIDTH);
	}
	
	// Pone la bola en la posicion de origen y la dota de mov aleatorio.
	public void reset(){
		x = 300;
		y = 200;
		velX = 5;
		velY = RandomNumberGenerator.getRandIntBetween(-4, 5);
	}
	
	// getters
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public Rectangle getRect(){
		return rect;
	}
	
}
