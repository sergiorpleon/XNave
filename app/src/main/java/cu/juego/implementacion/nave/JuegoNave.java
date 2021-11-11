package cu.juego.implementacion.nave;


import cu.juego.implementacion.Pantalla;
import cu.juego.implementacion.android.AndroidJuego;

import android.app.Activity;

public class JuegoNave extends AndroidJuego {

	@Override
	public Pantalla getStartScreen() {
		// TODO Auto-generated method stub
		return new LoadingScreen(this);
	}
  
	
	
}

























