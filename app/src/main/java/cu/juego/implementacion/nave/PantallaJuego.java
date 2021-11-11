package cu.juego.implementacion.nave;

import java.util.List;

import android.R.color;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.util.Log;

import cu.juego.implementacion.Graficos;
import cu.juego.implementacion.Juego;
import cu.juego.implementacion.Pantalla;
import cu.juego.implementacion.Pixmap;
import cu.juego.implementacion.Input.TouchEvent;

public class PantallaJuego extends Pantalla {
	MediaPlayer mp;

	enum EstadoJuego {
		Preparado, Ejecutandose, Pausado, FinJuego
	}

	EstadoJuego estado = EstadoJuego.Preparado;
	Mundo mundo;
	int antiguaPuntuacion = 0;
	String puntuacion = "0";

	public PantallaJuego(Juego juego) {
		super(juego);
		mundo = new Mundo();

		mp = MediaPlayer.create(juego.getContext(), R.raw.musica);
		mp.setLooping(true);
		mp.setVolume(Configuraciones.musicLevel, Configuraciones.musicLevel);
	}

	public void updateReady(List<TouchEvent> touchEvents) {
		if (touchEvents.size() > 0) {
			estado = EstadoJuego.Ejecutandose;
		}
	}

	public void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
		// Graficos g =juego.getGraficos();
		// List<TouchEvent> touchEvents = juego.getInput().getTouchEvents();
		// juego.getInput().getkeyEvents();
		// mundo.nave.newposx = mundo.nave.xnave;
		// mundo.nave.newposy = mundo.nave.ynave;
		boolean disparar = false;
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			//if (event.x > 1 && event.x < 319 && event.y > 1 && event.y < 479) {
				if (event.type == TouchEvent.TOUCH_DOWN) {
					// disparar = true;
					mundo.nave.newposx = event.x;
					mundo.nave.newposy = event.y;

				} else if (event.type == TouchEvent.TOUCH_UP) {
					// detiene el movimiento hacia posicion
					// mundo.regresarUna();
					mundo.nave.newposx = mundo.nave.xnave;
					mundo.nave.newposy = mundo.nave.ynave;

					mundo.xdisparo = event.x;
					mundo.ydisparo = event.y;

					// if (event.x < 64 && event.y > 416) {
				} else if (event.type == TouchEvent.TOUCH_DRAGGED) {
					mundo.nave.newposx = event.x;
					mundo.nave.newposy = event.y;
					if (event.x < 1 || event.x > 319 || event.y < 1
							|| event.y > 479) {

						mundo.nave.newposx = mundo.nave.xnave;
						mundo.nave.newposy = mundo.nave.ynave;

					}
				}
			//} else {

			//}
		}
		disparar = false;
		synchronized (this) {
			mundo.update(deltaTime);
			if (mundo.comprobarChoque()) {
				if (Configuraciones.soundEnabled) {
					Assets.derrota.play(Configuraciones.soundLevel);
				}
				estado = EstadoJuego.FinJuego;
			}
		}
		if (antiguaPuntuacion != mundo.puntuacion) {
			if (mundo.puntuacion - antiguaPuntuacion > 5) {
				if (Configuraciones.soundEnabled) {
					Assets.diaman.play(Configuraciones.soundLevel);
				}
			} else {
				if (Configuraciones.soundEnabled) {
					Assets.ataque.play(Configuraciones.soundLevel);
				}
			}
			antiguaPuntuacion = mundo.puntuacion;
			puntuacion = "" + antiguaPuntuacion;

		}

	}

	public void updatePause(List<TouchEvent> touchEvents) {
		// Graficos g =juego.getGraficos();
		// List<TouchEvent> touchEvents = juego.getInput().getTouchEvents();
		// juego.getInput().getkeyEvents();

		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {

				if (event.y > 160 && event.y <= 360) {
					if (event.x > 120 && event.x <= 168) {
						if (Configuraciones.soundEnabled) {
							Assets.pulsar.play(Configuraciones.soundLevel);
						}
						estado = EstadoJuego.Ejecutandose;
						mp.start();
						return;
					}

					if (event.x > 168 && event.x <= 216) {
						if (Configuraciones.soundEnabled) {
							Assets.pulsar.play(Configuraciones.soundLevel);
						}
						juego.setScreen(new MainMenuScreen(juego));
						return;
					}

				}
			}
		}
	}

	public void updateGameOver(List<TouchEvent> touchEvents) {
		// Graficos g =juego.getGraficos();
		// List<TouchEvent> touchEvents = juego.getInput().getTouchEvents();
		// juego.getInput().getkeyEvents();

		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (event.x >= 180 && event.x <= 252) {
					if (event.y >= 200 && event.y <= 264) {
						if (Configuraciones.soundEnabled) {
							Assets.pulsar.play(Configuraciones.soundLevel);
						}
						juego.setScreen(new MainMenuScreen(juego));
						return;
					}
				}
			}
		}
	}

	@Override
	public void update(float deltaTime) {
		// Graficos g =juego.getGraficos();
		List<TouchEvent> touchEvents = juego.getInput().getTouchEvents();
		juego.getInput().getkeyEvents();

		if (Control.isback) {
			synchronized (this) {
				Control.isback = false;
			}
			juego.setScreen(new MainMenuScreen(juego));
			if (Configuraciones.soundEnabled) {
				Assets.pulsar.play(Configuraciones.soundLevel);
			}
		} else {

			if (estado == EstadoJuego.Preparado) {
				updateReady(touchEvents);
			}
			if (estado == EstadoJuego.Ejecutandose) {
				updateRunning(touchEvents, deltaTime);
			}
			if (estado == EstadoJuego.Pausado) {
				updatePause(touchEvents);
			}
			if (estado == EstadoJuego.FinJuego) {
				updateGameOver(touchEvents);
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub
		Graficos g = juego.getGraficos();
		g.drawPixmap(Assets.fondo1, 0, 0);
		Particle p;
		for (int i = 0; i < mundo.lparticle.particulas.size(); i++) {
			// g.drawPixmap(headPixmap, nave.xnave-headPixmap.getWidth()/2,
			// nave.ynave-headPixmap.getHeight()/2);
			p = mundo.lparticle.particulas.get(i);
			g.drawPixel(p.posx, p.posy, Color.WHITE);
		}
		g.drawPixmap(Assets.niebla, 0, 0);
		
		
		drawWorld(mundo);
		if (estado == EstadoJuego.Preparado) {
			drawReadyUI();
		}
		if (estado == EstadoJuego.Ejecutandose) {
			drawRunningUI();
		}
		if (estado == EstadoJuego.Pausado) {
			drawPausedUI();
		}
		if (estado == EstadoJuego.FinJuego) {
			drawGameOverUI();
		}

		drawText(g, mundo.puntuacion + "", g.getWidth() - 42,
				g.getHeight() - 42);
	}

	private void drawWorld(Mundo mundo) {
		Graficos g = juego.getGraficos();
		Pixmap headPixmap = null;
		Nave nave = mundo.nave;
		// Locomotora jollyroger = mundo.locomotora;
		// Tripulacion head = jollyroger.partes.get(0);
		
		

		Meteorito met;
		if (mundo.asteroides.d1 != null) {
			if (mundo.asteroides.d1.estado != Diamante.INACTIVO)
				g.drawPixmap(Assets.diamante, mundo.asteroides.d1.posx - 5,
						mundo.asteroides.d1.posy - 5, 0, 0, 15, 15);
		}
		if (mundo.asteroides.d2 != null) {
			if (mundo.asteroides.d2.estado != Diamante.INACTIVO)
				g.drawPixmap(Assets.diamante, mundo.asteroides.d2.posx - 5,
						mundo.asteroides.d2.posy - 5, 0, 0, 15, 15);
		}
		for (int i = 0; i < mundo.asteroides.met.size(); i++) {
			// g.drawPixmap(headPixmap, nave.xnave-headPixmap.getWidth()/2,
			// nave.ynave-headPixmap.getHeight()/2);
			met = mundo.asteroides.met.get(i);
			if (met.dimx > 0) {
				g.drawPixmap(Assets.meteorito, met.posx - met.dimx / 2,
						met.posy - met.dimy / 2, 0, 0, 32, 32, met.dimx,
						met.dimy);
			}
		}

		Bolas b;
		for (int i = 0; i < mundo.lbolas.bolas.size(); i++) {
			// g.drawPixmap(headPixmap, nave.xnave-headPixmap.getWidth()/2,
			// nave.ynave-headPixmap.getHeight()/2);
			b = mundo.lbolas.bolas.get(i);
			if (b.estado != Bolas.FUERA) {
				g.drawPixmap(Assets.bola, b.posx - 15 / 2, b.posy - 15 / 2, 0,
						0, 15, 15, 15, 15);
			}
		}
		if (mundo.enemigo.estado == Enemigo.ESTATICO) {
			g.drawPixmap(Assets.enemigo, mundo.enemigo.posx - 32 / 2,
					mundo.enemigo.posy - 32 / 2, 0, 0, 32, 32, 32, 32);
		} else {
			g.drawPixmap(Assets.enemigo1, mundo.enemigo.posx - 32 / 2,
					mundo.enemigo.posy - 32 / 2, 0, 0, 32, 32, 32, 32);
		}
		headPixmap = Assets.nave;
		g.drawPixmap(headPixmap, nave.xnave - headPixmap.getWidth() / 2,
				nave.ynave - headPixmap.getHeight() / 2);

	}

	private void drawReadyUI() {
		Graficos g = juego.getGraficos();

		g.drawPixmap(Assets.preparado, 160 - Assets.preparado.getWidth() / 2,
				240 - Assets.preparado.getHeight() / 2);
		// g.drawLine(0, 416, 480, 416, Color.BLACK);
	}

	private void drawRunningUI() {
		Graficos g = juego.getGraficos();

		// g.drawPixmap(Assets.botones, 0, 0, 64, 128, 64, 64);
		// g.drawLine(0, 416, 480, 416, Color.BLACK);
		// g.drawPixmap(Assets.botones, 0, 416, 0, 64, 64, 64);
		// g.drawPixmap(Assets.botones,256,416, 0, 128,64, 64);
		// g.drawPixmap(Assets.botones, 256, 416, 64, 64, 64, 64);
	}

	private void drawPausedUI() {
		Graficos g = juego.getGraficos();

		g.drawPixmap(Assets.menupausa, 120, 160);
		// g.drawLine(0, 416, 480, 416, Color.BLACK);

	}

	private void drawGameOverUI() {
		Graficos g = juego.getGraficos();

		g.drawPixmap(Assets.finjuego, 160 - Assets.finjuego.getWidth() / 2,
				240 - Assets.finjuego.getHeight() / 2);
		g.drawPixmap(Assets.botones, 180, 200, 0, 128, 64, 64);
		// g.drawLine(0, 416, 480, 416, Color.BLACK);

	}

	private void drawText(Graficos g, String line, int x, int y) {

		int len = line.length();

		for (int i = 0; i < len; i++) {
			char character = line.charAt(i);
			if (character == ' ') {
				y -= 20;
				continue;
			}

			int srcY = 0;
			int srcWidth = 0;
			if (character == '.') {
				srcY = 210 - 200;
				srcWidth = 10;
			} else {
				srcY = 210 - ((character - '0') + 1) * 20;
				srcWidth = 20;
			}

			g.drawPixmap(Assets.numeros, x, y, 0, srcY, 32, srcWidth);
			y -= srcWidth;

		}

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		if (estado == EstadoJuego.Ejecutandose) {
			estado = EstadoJuego.Pausado;
		}

		if (mundo.finalJuego) {
			//Configuraciones.addScore(mundo.puntuacion);
			//Configuraciones.saved(juego.getFileIO());
			Configuraciones.adicionar(mundo.puntuacion);
			Configuraciones.guardar();
		}
		mp.pause();

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		if(Configuraciones.soundEnabled){
			mp.start();
		}

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		mp.stop();
	}

}
