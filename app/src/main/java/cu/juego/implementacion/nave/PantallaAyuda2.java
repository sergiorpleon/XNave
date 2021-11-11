package cu.juego.implementacion.nave;

import java.util.List;


import cu.juego.implementacion.Graficos;
import cu.juego.implementacion.Juego;
import cu.juego.implementacion.Pantalla;
import cu.juego.implementacion.Input.TouchEvent;

public class PantallaAyuda2 extends Pantalla {
	public PantallaAyuda2(Juego juego) {
		super(juego);
		Control.autor =true;
	}

	@Override
	public void update(float deltaTime) {
		Graficos g = juego.getGraficos();
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

			int len = touchEvents.size();
			for (int i = 0; i < len; i++) {
				TouchEvent event = touchEvents.get(i);
				if (event.type == TouchEvent.TOUCH_UP) {
					//if (event.x > 256 && event.y > 416) {
					if (event.x > 236 && event.y < 80) {
						juego.setScreen(new PantallaAyuda3(juego));
						if (Configuraciones.soundEnabled) {
							Assets.pulsar.play(Configuraciones.soundLevel);
						}
					}

					if (event.x > 20 && event.x < 196) {
						if (event.y > 20 && event.y < 60) {
							Control.autor = !Control.autor;
						}
					}
				}

			}
		}
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub
		Graficos g = juego.getGraficos();
		g.drawPixmap(Assets.fondo, 0, 0);
		// g.drawPixmap(Assets.ayuda2, 64, 100);
		g.drawPixmap(Assets.ayuda2, 9, 80);
		// x =256 y = 416
		if (Control.autor) {
			g.drawPixmap(Assets.logotren, 20, 20);
		} else {
			g.drawPixmap(Assets.autor, 20, 20);

		}
		g.drawPixmap(Assets.botones1, 240, 10, 0, 0, 64, 64);
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
