package cu.juego.implementacion.nave;

public class Diamante {
	public static final int DETRAS = 0;
	public static final int ACTIVO = 1;
	public static final int INACTIVO = 2;

	public int posx;
	public int posy;

	public int estado;

	public static final int TIEMPOVIDA = 100;
	public int vida;

	public Diamante() {
		posx = -50;
		posy = -50;
		estado = INACTIVO;
	}

	public void update() {
		
		vida++;
		if (vida > TIEMPOVIDA) {
			estado = INACTIVO;
		}
	}

	public boolean tomado(int objx, int objy) {
		
			if (Math.sqrt(Math.pow((posx - objx), 2)
					+ Math.pow((posy - objy), 2)) < (10) && estado == ACTIVO) {
				estado = INACTIVO;
				return true;
			}else {
				return false;
			}
		

	}
}
