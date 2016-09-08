package com.rafaels.game.state;

//import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Font;

import com.rafaels.game.main.GameMain;
import com.rafaels.game.main.Resources;
import com.rafaels.game.model.Ball;
import com.rafaels.game.model.Paddle;

public class PlayState extends State {

	private Paddle paddleLeft, paddleRight; //paddleCenter;
	private static final int PADDLE_WIDTH = 84;
	private static final int PADDLE_HEIGHT = 112;
	
	private Ball ball;
	private static final int BALL_WIDTH = 128;
	private static final int BALL_HEIGHT = 72;
	
	private int playerScore = 0;
	private Font scoreFont;
	
	@Override
	public void init() {
		paddleLeft = new Paddle(0, 335, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddleRight = new Paddle(1196, 335, PADDLE_WIDTH, PADDLE_HEIGHT);
		//paddleCenter = new Paddle (580, 335, PADDLE_WIDTH, PADDLE_HEIGHT);
		scoreFont = new Font("SansSerif", Font.BOLD, 25);	
		ball = new Ball(300, 200, BALL_WIDTH, BALL_HEIGHT);
	}

	@Override
	public void update() {
		paddleLeft.update();
		paddleRight.update();
		//paddleCenter.update();
		ball.update();
		
		// Si hay colision de la bola incrementamos el marcador en 1
		// Llamamos a ball.onCollideWidth q maneja el rebote de la bola
		// Reproducimos el sonido de golpe
		// Si la bola colisiona lado izq o derecho de pantalla restamos 3 y ponemos
		// la bola en la posicion de origen.
		if (ballCollides(paddleLeft)){
			playerScore++;
			ball.onCollideWith(paddleLeft);
			Resources.hit.play();
		} else if (ballCollides(paddleRight)){
			playerScore++;
			ball.onCollideWith(paddleRight);
			Resources.hit.play();
		//} else if (ballCollides(paddleCenter)){
			//playerScore--;			
			//Resources.hit.play();
			//ball.reset();
		} else if (ball.isDead()){
			playerScore -= 3;
			ball.reset();
		}
	}

	@Override
	public void render(Graphics g) {
		//Dibuja el fondo
		//g.setColor(Resources.darkBlue);
		//g.fillRect(0, 0, GameMain.GAME_HEIGHT, GameMain.GAME_HEIGHT);
		//g.setColor(Resources.darkRed);
		//g.fillRect(GameMain.GAME_WIDTH / 2, 0, GameMain.GAME_WIDTH / 2, GameMain.GAME_HEIGHT);
		g.drawImage(Resources.fondo, 0, 0, GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT , null);
		
		//Dibuja la linea de separacion
		//g.drawImage(Resources.line,(GameMain.GAME_WIDTH / 2) - 2, 0, null);
		
		//Dibuja las palas
		//g.setColor(Color.white);
		//g.fillRect(paddleLeft.getX(), paddleLeft.getY(), paddleLeft.getWidth(), paddleLeft.getHeight());
		//g.fillRect(paddleRight.getX(), paddleRight.getY(), paddleRight.getWidth(), paddleRight.getHeight());
		g.drawImage(Resources.gargolaLeft, paddleLeft.getX(), paddleLeft.getY(), paddleLeft.getWidth(), paddleLeft.getHeight(), null);
		g.drawImage(Resources.gargolaRight, paddleRight.getX(), paddleRight.getY(), paddleRight.getWidth(), paddleRight.getHeight(), null);
		//g.setColor(Color.black);
		//g.fillRect(paddleCenter.getX(), paddleCenter.getY(), paddleCenter.getWidth(), paddleCenter.getHeight());
		
		//Dibuja la bola
		//g.setColor(Color.white);
		//g.drawRect(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
		g.drawImage(Resources.ball, ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight(), null);
		
		//Dibuja la interfaz de usuario IU
		g.setFont(scoreFont);
		g.drawString("" + playerScore, 350, 40);
		
	}

	@Override
	public void onClick(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyPress(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP){
			//paddleLeft.accelUp();
			paddleRight.accelUp();
			//paddleCenter.accelDown();
		} 
		else if (e.getKeyCode() == KeyEvent.VK_DOWN){
			//paddleLeft.accelDown();
			paddleRight.accelDown();
			//paddleCenter.accelUp();
		}
		else if (e.getKeyCode() == KeyEvent.VK_W){
			paddleLeft.accelUp();
		}
		else if (e.getKeyCode() == KeyEvent.VK_S){
			paddleLeft.accelDown();
		}
		
	}

	@Override
	public void onKeyRelease(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP){
			//paddleLeft.stop();
			paddleRight.stop();
			//paddleCenter.accelRandom();			
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN){
			//paddleLeft.stop();
			paddleRight.stop();
			//paddleCenter.accelRandom();
		}
		else if (e.getKeyCode() == KeyEvent.VK_W){
			paddleLeft.stop();
		}
		else if (e.getKeyCode() == KeyEvent.VK_S){
			paddleLeft.stop();
		}
	}
	
	// Comprueeba si la bola colisiona con las palas
	// Comprueba si se produce una interseccion en los boundingbox de la bola y palas
	private boolean ballCollides(Paddle p){
		return ball.getRect().intersects(p.getRect());
	}

}
