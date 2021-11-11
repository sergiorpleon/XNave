package cu.juego.implementacion.nave;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.util.Log;
import android.view.KeyEvent.DispatcherState;
import android.widget.Toast;

public class Mundo {
	static final int INCREMENTO_PUNTUACION = 1;
	static final int INCREMENTO_DIAMANTE = 10;
	static final float TICK_INICIAL = 0.2f;
	static final float TICK_DECREMENTO = 0.05f;

	int increm_puntos;
	int llamar;

	public ListParticle lparticle;
	
	public Nave nave;
	public Enemigo enemigo;
	public Asteroiedes asteroides;
	public LisBolas lbolas;
	public int xdisparo = -10;
	public int ydisparo = -10;
	// public Locomotora locomotora;
	// public Bagon bagon;
	public boolean finalJuego = false;
	public int puntuacion = 0;

	Random random = new Random();
	float tiempoTick = 0;
	static float tick = TICK_INICIAL;
	int velocidad;

	public Mundo() {
		// locomotora = new Locomotora();
		// velocidad nave
		synchronized (this) {
			increm_puntos = 50;
			llamar = increm_puntos;
			puntuacion = 0;

			velocidad = 10;
			
			lparticle = new ListParticle();

			nave = new Nave();

			enemigo = new Enemigo();
			asteroides = new Asteroiedes();
			lbolas = new LisBolas();

			tiempoTick = 0;
			tick = TICK_INICIAL;
		}
	}

	public void update(float deltaTime) {
		int blanco;
		if (finalJuego) {
			return;
		}

		// boolean boom;
		tiempoTick += deltaTime;
		while (tiempoTick > tick) {
			tiempoTick -= tick;

			// locomotora.avance();
			// if (locomotora.comprobarChoque()) {
			// Log.d("Entro", "Choque BOOM");
			// finalJuego = true;
			// return;
			// }
			// nave.xnave = nave.newposx;
			// nave.ynave = nave.newposy;

			// Log.d("Entro", "BOT " + botin.x + " _ " + botin.y);

			// Tripulacion head = locomotora.partes.get(0);
			// if (head.x == bagon.x && head.y == bagon.y) {
			// Log.d("Entro", "Botin facho");
			// puntuacion += INCREMENTO_PUNTUACION;
			// locomotora.abordaje();
			// if (locomotora.partes.size() == MUNDO_ANCHO * MUNDO_ALTO) {
			// finalJuego = true;
			// return;
			// } else {
			// colocarBotin();
			// }

			// if (puntuacion % 100 == 0 && tick - TICK_DECREMENTO > 0) {
			// tick -= TICK_DECREMENTO;
			// }
			// }
			lparticle.updateLista();
			
			
			asteroides.disparo1(enemigo.posx, enemigo.posy, lbolas, nave.xnave,
					nave.ynave);

			synchronized (this) {
				//dISPARO
				if (xdisparo > 0 && ydisparo > 0) {
					if (Math.sqrt(Math.pow((xdisparo - enemigo.posx), 2)
							+ Math.pow((ydisparo - enemigo.posy), 2)) < 15) {

						enemigo.disparo(this);
					}
				}
			}
			//INCREMENTO DE PUNTACION
			if (xdisparo >= 0) {
				blanco = asteroides.disparo(xdisparo, ydisparo, lbolas,
						nave.xnave, nave.ynave);

				if (blanco > 0) {
					puntuacion += INCREMENTO_PUNTUACION * blanco;
				}
				// crear las bolas luego de impacto
				// if(boom){
				// lbolas.adicionar(xdisparo, ydisparo);
				// }

				xdisparo = -10;
				ydisparo = -10;

			}

			synchronized (this) {

				if (asteroides.d1.estado != Diamante.INACTIVO)
					if (asteroides.d1.tomado(nave.xnave, nave.ynave)) {
						puntuacion += INCREMENTO_DIAMANTE;
						asteroides.d1.estado = Diamante.INACTIVO;
					}
				if (asteroides.d2.estado != Diamante.INACTIVO)
					if (asteroides.d2.tomado(nave.xnave, nave.ynave)) {
						puntuacion += INCREMENTO_DIAMANTE;
						asteroides.d2.estado = Diamante.INACTIVO;
					}
			}
			synchronized (this) {
				enemigo.update(this);
			
			lbolas.updateLista();
			asteroides.updateMeteorito();
			if (Math.abs(nave.newposx - nave.xnave) > velocidad
					|| Math.abs(nave.newposy - nave.ynave) > velocidad) {
				if (Math.abs(nave.newposx - nave.xnave) > Math.abs(nave.newposy
						- nave.ynave)) {
					if (nave.newposx > nave.xnave) {
						nave.xnave += +velocidad;
					} else {
						nave.xnave += -velocidad;
					}
					nave.ynave += (nave.newposy - nave.ynave)
							* (velocidad / (Math.abs(nave.newposx - nave.xnave) + 0.1));
				} else {
					if (nave.newposy > nave.ynave) {
						nave.ynave += +velocidad;
					} else {
						nave.ynave += -velocidad;
					}
					nave.xnave += (nave.newposx - nave.xnave)
							* (velocidad / (Math.abs(nave.newposy - nave.ynave) + 0.1));
				}
			} else {
				nave.xnave = nave.newposx;
				nave.ynave = nave.newposy;
			}

			// chequeo de que llamar sea alcanzable en cada nivel (cada 50
			// puntos)
			//if (puntuacion + increm_puntos < llamar) {
			//	llamar -= increm_puntos;
			//}
			//if (puntuacion > llamar) {
			//	llamar += increm_puntos;
			//}
			}
		}
	}

	//comprueba choque de nave contra meteoritos y bolas rojas
	public boolean comprobarChoque() {
		// TODO Auto-generated method stub
		if (asteroides.comprobarChoque(nave.xnave, nave.ynave)
				|| lbolas.comprobarChoque(nave.xnave, nave.ynave)) {
			finalJuego = true;
		}
		return finalJuego;
	}
/*
	public void regresarUna() {
		// TODO Auto-generated method stub
		if (Math.abs(nave.newposx - nave.xnave) > 5
				|| Math.abs(nave.newposy - nave.ynave) > 5) {
			if (Math.abs(nave.newposx - nave.xnave) > Math.abs(nave.newposy
					- nave.ynave)) {
				if (nave.newposx > nave.xnave) {
					nave.xnave += -velocidad;
				} else {
					nave.xnave += +velocidad;
				}
				nave.ynave -= (nave.newposy - nave.ynave)
						* (velocidad / (Math.abs(nave.newposx - nave.xnave) + 0.1));

			} else {
				if (nave.newposy > nave.ynave) {
					nave.ynave += -velocidad;
				} else {
					nave.ynave += +velocidad;
				}
				nave.xnave -= (nave.newposx - nave.xnave)
						* (velocidad / (Math.abs(nave.newposy - nave.ynave) + 0.1));

			}
		}
	}
	*/
}
