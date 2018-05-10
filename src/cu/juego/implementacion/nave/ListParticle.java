package cu.juego.implementacion.nave;

import java.util.ArrayList;
import java.util.List;

public class ListParticle {
	public List<Particle> particulas = new ArrayList<Particle>();
	public int tamanno;

	public ListParticle() {
		particulas = new ArrayList<Particle>();
		tamanno = 150;
		Particle p;
		for (int i = 0; i < tamanno; i++) {
			p = new Particle(true);
			particulas.add(p);
		}
	}

	public void updateLista() {
		Particle p;
		for (int i = particulas.size() - 1; i >= 0; i--) {
			p = particulas.get(i);
			p.update();
			if ((p.posx > 320 || p.posx < 0) || (p.posy > 480 || p.posy < 0)) {
				// p.angle = (int) (Math.random()*(Math.PI*2));
				int zona = 1;
				// int posX = (int) (Math.pow(Math.random(), 2)*zona);
				// int posY = (int) (Math.pow(Math.random(), 2)*zona);
				// p.posx = posX-zona/2+160;
				// p.posy = posY-zona/2+240;
				p.posx = 160;
				p.posy = 240;
				// p.angle = Math.atan((posX-zona/2)/(posY-(zona+0.01)/2));

				// p.angle = Math.atan((posX-100/2)/(posY-100/2));

				// p.incx = (int) (Math.cos(p.angle)*(p.INC));
				// p.incy = (int) (Math.sin(p.angle)*(p.INC));
				// incremento entre 0 y 5
				p.incx = (int) (Math.random() * (p.INC));
				p.incy = (int) (Math.random() * (p.INC));
				//
				if (Math.sqrt(Math.pow(p.incx, 2) + Math.pow(p.incy, 2)) < 2
						|| p.incx == 0 || p.incy == 0) {
					p.posx = 500;
					p.posy = 500;
				}
				p.incx = p.incx * ((Math.random() < 0.5) ? -1 : 1);
				p.incy = p.incy * ((Math.random() < 0.5) ? -1 : 1);
				// tamano de paso numero entre 0 y 100
				p.posx += Math.random() * 20 * p.incx;
				p.posy += Math.random() * 20 * p.incy;

			}
		}
	}
}
