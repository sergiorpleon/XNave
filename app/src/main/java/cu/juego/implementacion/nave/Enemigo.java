package cu.juego.implementacion.nave;

import java.util.LinkedList;
import java.util.List;

import android.util.Log;

public class Enemigo {
	public static final int ESTATICO = 0;
	public static final int MOVIMIENTO = 1;
	public static final int DESTRUIDO = 2;

	int vidainicial;
	int velocidad;
	int vida;

	public int estado;

	public int posx;
	public int posy;

	public int incx;
	public int incy;

	public int objx;
	public int objy;

	public Enemigo() {
		synchronized (this) {
			vidainicial = 0;
			velocidad = 5;
			vida = vidainicial;

			posx = -100;
			posy = -100;

			objx = posx;
			objx = posy;

			incx = 0;
			incy = 0;

			estado = DESTRUIDO;
		}
	}

	public void update(Mundo mundo) {
		synchronized (this) {
			Log.d("objetivo", "O= x: " + objx + " - y: " + objy);
			Log.d("posicion", "P= x: " + posx + " - y: " + posy);
			Log.d("incremento", "I= x: " + incx + " - y: " + incy);
			Log.d("estado", "E= " + estado);
			if (estado == MOVIMIENTO) {
				posx += incx;
				posy += incy;
				if (Math.abs(posx - objx) < 10 || Math.abs(posy - objy) < 10) {
					estado = ESTATICO;
					incx = 0;
					incy = 0;
					objx = posx;
					objy = posy;
				}
			}

			if (estado == ESTATICO) {
				incx = 0;
				incy = 0;
				objx = posx;
				objy = posy;
			}

			if (estado == DESTRUIDO) {
				if (mundo.puntuacion > mundo.llamar && mundo.puntuacion != 0) {
					mundo.llamar += mundo.increm_puntos;

					vidainicial++;
					vida = vidainicial;

					estado = MOVIMIENTO;

					posx = 160;
					objx = (int) (Math.random() * 300) + 10;
					if (mundo.nave.ynave < 240) {
						posy = 530;
						objy = (int) (Math.random() * 230) + 10;
					} else {
						posy = -50;
						objy = (int) (Math.random() * 230) + 240;
					}

					if (Math.abs(objx - posx) > Math.abs(objy - posy)) {
						if (objx > posx) {
							incx = +velocidad;
						} else {
							incx = -velocidad;
						}
						incy = (int) ((objy - posy) * (velocidad / (Math
								.abs(objx - posx) + 0.1)));
					} else {
						if (objy > posy) {
							incy = +velocidad;
						} else {
							incy = -velocidad;
						}
						incx = (int) ((objx - posx) * (velocidad / (Math
								.abs(objy - posy) + 0.1)));
					}
				}
			}
			// else {

			// }
			// updateEstado();
		}
	}

	public void cambioEstado() {

	}

	public void disparo(Mundo mundo) {
		synchronized (this) {
			if (vida != 0 && estado == ESTATICO) {

				vida--;
				estado = MOVIMIENTO;
				objx = (int) (Math.random() * 300) + 10;
				if (posy < 240) {
					objy = (int) (Math.random() * 230) + 240;
				} else {
					objy = (int) (Math.random() * 230) + 10;
				}

				if (Math.abs(objx - posx) > Math.abs(objy - posy)) {
					if (objx > posx) {
						incx = +velocidad;
					} else {
						incx = -velocidad;
					}
					incy = (int) ((objy - posy) * (velocidad / (Math.abs(objx
							- posx) + 0.1)));
				} else {
					if (objy > posy) {
						incy = +velocidad;
					} else {
						incy = -velocidad;
					}
					incx = (int) ((objx - posx) * (velocidad / (Math.abs(objy
							- posy) + 0.1)));
				}
			}
			if (vida == 0) {
				incx = 0;
				incy = 0;
				posx = -100;
				posy = -100;
				objx = -100;
				objy = -100;
				estado = DESTRUIDO;
				
				if (Configuraciones.soundEnabled) {
					Assets.nav.play(Configuraciones.soundLevel);
					Assets.na.play(Configuraciones.soundLevel);
				}
			} else {
				if (Configuraciones.soundEnabled) {
					Assets.nav.play(Configuraciones.soundLevel);
				}
			}
		}
	}
}
