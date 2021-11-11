package cu.juego.implementacion.nave;

import java.util.ArrayList;
import java.util.List;

public class Asteroiedes {
	public int tamanno;
	public List<Meteorito> met = new ArrayList<Meteorito>();

	public Diamante d1;
	public Diamante d2;

	public Asteroiedes() {
		d1 = new Diamante();
		d2 = new Diamante();
		tamanno = 15;
		Meteorito m;
		for (int i = 0; i < tamanno; i++) {
			m = new Meteorito();
			met.add(m);
		}
	}

	public void updateMeteorito() {
		d1.update();
		d2.update();
		Meteorito m;
		for (int i = 0; i < met.size(); i++) {
			m = met.get(i);
			m.dimx += 2;
			m.dimy += 2;

			if (m.dimx > 20 && m.dimx < 40) {
				if (Math.random() * 100 < 50) {
					// cuando creo un meteorito si hay un diamante noactivo lo
					// vuelvo activo

					if (d1.estado == Diamante.INACTIVO) {
						d1.posx = m.posx;
						d1.posy = m.posy;
						d1.estado = Diamante.DETRAS;
						d1.vida = 0;
					} else if (d2.estado == Diamante.INACTIVO) {
						d2.posx = m.posx;
						d2.posy = m.posy;
						d2.estado = Diamante.DETRAS;
						d2.vida = 0;
					}

				}
			}

			// cuando el meteorito desaprece por lo grande que es
			if (m.estalla()) {
				// cuando el meteorito pasa el diamante pasa tambien
				if (d1.posx == m.posx && d1.posy == m.posy) {
					d1.estado = Diamante.INACTIVO;
				}
				if (d2.posx == m.posx && d2.posy == m.posy) {
					d2.estado = Diamante.INACTIVO;
				}

				m.posx = (int) (Math.random() * 320);
				m.posy = (int) (Math.random() * 480);

				m.dimy = m.dimx = (int) (Math.random() * (m.dimInicial + 10));
			}
		}
	}

	public int disparo(int xdisparo, int ydisparo, LisBolas lbolas, int xnave,
			int ynave) {
		// TODO Auto-generated method stub
		// boolean retorno = false;
		int retorno = 0;
		Meteorito m;
		for (int i = 0; i < met.size(); i++) {
			m = met.get(i);

			if (Math.sqrt(Math.pow((xdisparo - m.posx), 2)
					+ Math.pow((ydisparo - m.posy), 2)) < (m.dimx / 2)) {

				// adicionar las bolas
				lbolas.adicionar(m.posx, m.posy, xnave, ynave);

				if (d1.posx == m.posx && d1.posy == m.posy) {
					d1.estado = Diamante.ACTIVO;
				}
				if (d2.posx == m.posx && d2.posy == m.posy) {
					d2.estado = Diamante.ACTIVO;
				}
				m.posx = (int) (Math.random() * 320);
				m.posy = (int) (Math.random() * 480);
				m.dimy = m.dimx = (int) (Math.random() * (m.dimInicial + 10));
				retorno += 1;

			}
		}
		return retorno;
	}

	public int disparo1(int xdisparo, int ydisparo, LisBolas lbolas, int xnave,
			int ynave) {
		// TODO Auto-generated method stub
		// boolean retorno = false;
		int retorno = 0;
		Meteorito m;
		for (int i = 0; i < met.size(); i++) {
			m = met.get(i);

			if (Math.sqrt(Math.pow((xdisparo - m.posx), 2)
					+ Math.pow((ydisparo - m.posy), 2)) < (50)) {

				// adicionar las bolas
				if (Configuraciones.soundEnabled) {
					if(Configuraciones.soundEnabled) {
						Assets.ataque.play(Configuraciones.soundLevel);
					}
				}

				lbolas.adicionar(m.posx, m.posy, xnave, ynave);

				if (d1.posx == m.posx && d1.posy == m.posy) {
					d1.estado = Diamante.ACTIVO;
				}
				if (d2.posx == m.posx && d2.posy == m.posy) {
					d2.estado = Diamante.ACTIVO;
				}
				m.posx = (int) (Math.random() * 320);
				m.posy = (int) (Math.random() * 480);
				m.dimy = m.dimx = (int) (Math.random() * (m.dimInicial + 10));
				retorno += 1;

			}
		}
		return retorno;
	}

	public boolean comprobarChoque(int xnave, int ynave) {
		Meteorito m;
		boolean retorno = false;
		for (int i = 0; i < met.size(); i++) {
			m = met.get(i);
			if (m.dimx > 60) {
				if (Math.sqrt(Math.pow((xnave - m.posx), 2)
						+ Math.pow((ynave - m.posy), 2)) < ((m.dimx / 2) - 3)) {
					retorno = true;
				}
			}
		}
		return retorno;
	}

	public boolean diamanteTomado(int xnave, int ynave) {
		// Meteorito m;
		boolean retorno = false;
		if (d1.estado != Diamante.INACTIVO)
			if (Math.sqrt(Math.pow((xnave - d1.posx), 2)
					+ Math.pow((ynave - d1.posy), 2)) < (20)) {
				retorno = true;
			}
		if (d2.estado != Diamante.INACTIVO)
			if (Math.sqrt(Math.pow((xnave - d2.posx), 2)
					+ Math.pow((ynave - d2.posy), 2)) < (20)) {
				retorno = true;
			}

		return retorno;
	}

}
