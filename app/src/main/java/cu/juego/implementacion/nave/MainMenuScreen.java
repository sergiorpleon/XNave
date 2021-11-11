package cu.juego.implementacion.nave;

import java.util.List;

import cu.juego.implementacion.Graficos;
import cu.juego.implementacion.Juego;
import cu.juego.implementacion.Pantalla;
import cu.juego.implementacion.Input.TouchEvent;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.util.Log;

public class MainMenuScreen extends Pantalla {

	public MainMenuScreen(Juego juego) {
		super(juego);
	}

	@Override
	public void update(float dataTime) {
		Graficos g = juego.getGraficos();
		List<TouchEvent> touchEvents = juego.getInput().getTouchEvents();
		juego.getInput().getkeyEvents();

		if (Control.isback) {
			synchronized (this) {
				Control.isback = false;
			}

			 juego.close();
			 //if (Configuraciones.soundEnabled) {
			 //Assets.pulsar.play(1);
			 //}
		} else {

			int len = touchEvents.size();
			for (int i = 0; i < len; i++) {
				TouchEvent event = touchEvents.get(i);
				if (event.type == TouchEvent.TOUCH_UP) {
					if (inBounds(event, 256, 0, 64, 64)) {
						Configuraciones.soundEnabled = !Configuraciones.soundEnabled;
						if (Configuraciones.soundEnabled) {
							Assets.pulsar.play(Configuraciones.soundLevel);
						}
					}

					if (inBounds(event, 120, 120, 50, 240)) {
						
						juego.setScreen(new PantallaJuego(juego));
						if (Configuraciones.soundEnabled) {
							Assets.pulsar.play(Configuraciones.soundLevel);
						}
						return;
					}

					// if (inBounds(event, 64, 220 + 42, 192, 42)) {
					if (inBounds(event, 180, 120, 50, 240)) {
						juego.setScreen(new PantallaMaximasPuntuaciones(juego));
						if (Configuraciones.soundEnabled) {
							Assets.pulsar.play(Configuraciones.soundLevel);
						}
						return;
					}

					// if (inBounds(event, 64, 220 + 84, 192, 42)) {
					if (inBounds(event, 240, 120, 50, 240)) {
						juego.setScreen(new PantallaAyuda(juego));
						if (Configuraciones.soundEnabled) {
							Assets.pulsar.play(Configuraciones.soundLevel);
						}
						return;
					}
				}
			}
		}
	}

	public boolean inBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub
		Graficos g = juego.getGraficos();
		g.drawPixmap(Assets.fondo, 0, 0);
		
		g.drawPixmap(Assets.blanco, 184, 60, 0, 0, 60, 300);
		
		g.drawPixmap(Assets.blanco, 242, 120, 0, 0, 60, 300);
		
		g.drawPixmap(Assets.blanco1, 124, 120, 0, 0, 60, 240);
		g.drawPixmap(Assets.diamantegrande, 126, -10, 0, 0, 160, 160);
		g.drawPixmap(Assets.meteoritogrande, 190, 340, 0, 0, 160, 160);
		g.drawPixmap(Assets.meteoritogrande, -170, 80, 0, 0, 160, 160, 300, 300);
		g.drawPixmap(Assets.navegrande, 10, 250, 0, 0, 160, 160);
		
		//g.drawPixmap(Assets.enemigogrande, 10, 300, 0, 0, 160, 160);
		//g.drawPixmap(Assets.lineas, 0, 0);
		//g.drawPixmap(Assets.logo, 32, 20);
		//g.drawPixmap(Assets.menuprincipal, 64, 220);
		g.drawPixmap(Assets.menuprincipal, 120, 120, 0, 0, 50, 240);
		g.drawPixmap(Assets.menuprincipal, 180, 120, 50, 0, 50, 240);
		g.drawPixmap(Assets.menuprincipal, 240, 120, 100, 0, 50, 240);

		if (Configuraciones.soundEnabled) {
			g.drawPixmap(Assets.botones, 256, 0, 0, 0, 64, 64);
		} else {
			g.drawPixmap(Assets.botones, 256, 0, 64, 0, 64, 64);
		}
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		Configuraciones.saved(juego.getFileIO());

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}



}
