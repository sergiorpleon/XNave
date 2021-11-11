package cu.juego.implementacion.nave;

public class Bolas {
	public static final int BORDE = 1;
	public static final int DENTRO = 2;
	public static final int FUERA = 3;
	public static final int INICIAL = 4;
	
	int velocidad;

	public int estado;

	public int posx;
	public int posy;

	public int incx;
	public int incy;

	public int objx;
	public int objy;

	public Bolas(int x, int y, int incx, int incy, int xnave, int ynave) {
		velocidad = 5;
		
		posx = x + incx*velocidad;
		posy = y + incy*velocidad;

		
		
		estado = INICIAL;

		this.incx = incx*velocidad;
		this.incy = incy*velocidad;

		objx = xnave;
		objy = ynave;
	}

	public void update() {
		// if (estado == INICIAL) {
		posx += incx;
		posy += incy;
		// } else {

		// }
		updateEstado();
	}

	public void updateEstado() {
		switch (estado) {
		case INICIAL:
			if (posx < 5 || posx > 315 || posy < 5 || posy > 475) {
				estado = BORDE;
				posx += incx * (-1);
				posy += incy * (-1);
				if (Math.abs(objx - posx) > Math.abs(objy - posy)) {
					if (objx > posx) {
						incx = +velocidad;
					} else {
						incx = -velocidad;
					}
					incy = (int) ((objy - posy) * (velocidad / (Math.abs(objx - posx) + 0.1)));
				} else {
					if (objy > posy) {
						incy = +velocidad;
					} else {
						incy = -velocidad;
					}
					incx = (int) ((objx - posx) * (velocidad / (Math.abs(objy - posy) + 0.1)));
				}
			}
			break;
		case BORDE:
			// if (posx >= 0 && posx <= 320 && posy >= 0 && posy <= 480) {
			estado = DENTRO;

			// }
			break;
		case DENTRO:
			if (posx < 0 || posx > 320 || posy < 0 || posx > 480) {
				estado = FUERA;
			}
			break;
		case FUERA:

			break;

		default:
			break;
		}
	}

}
