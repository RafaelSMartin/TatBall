package com.rafaels.framework.util;

import java.util.Random;

public class RandomNumberGenerator {
	
	// Solo hay una variable estatica que no se puede instanciar, 
	// compartida por toda la aplicacion.
	private static Random rand = new Random();
	
	// Pueden ser invocados los metodos sin crear objeto Random 
	// o RandomNumberGenerator antes, por ser public static
	// En alguna parte del framework:
	// System.out.println(RandomNumberGenerator.getRandIntBetween(-10, 11));
	// Genera un aleatorio entre -10 y 11 exclusive sin instanciar ningun objeto.
	public static int getRandIntBetween(int lowerBound, int upperBound){
		return rand.nextInt(upperBound - lowerBound) + lowerBound;
	}
	
	public static int getRandInt(int upperBound){
		return rand.nextInt(upperBound);
	}

}
