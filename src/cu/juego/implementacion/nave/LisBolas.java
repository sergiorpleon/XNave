package cu.juego.implementacion.nave;

import java.util.ArrayList;
import java.util.List;

public class LisBolas {
	public List<Bolas> bolas = new ArrayList<Bolas>();

	public LisBolas() {
		bolas = new ArrayList<Bolas>();
	}

	public void adicionar(int x, int y, int xnave, int ynave) {
		bolas.add(new Bolas(x, y, 1, 1, xnave, ynave));
		bolas.add(new Bolas(x, y, 1, -1, xnave, ynave));
		bolas.add(new Bolas(x, y, -1, 1, xnave, ynave));
		bolas.add(new Bolas(x, y, -1, -1, xnave, ynave));
	}

	public void updateLista() {
		Bolas b;
		for (int i = bolas.size() - 1; i >= 0; i--) {
			b = bolas.get(i);
			b.update();
			if (b.estado == Bolas.FUERA) {
				bolas.remove(i);
			}
		}
	}

	public boolean comprobarChoque(int xnave, int ynave) {
		Bolas b;
		boolean retorno = false;
		for (int i = 0; i < bolas.size(); i++) {
			b = bolas.get(i);
			if (Math.sqrt(Math.pow((xnave - b.posx), 2)
					+ Math.pow((ynave - b.posy), 2)) < (12)) {
				b.estado = Bolas.FUERA;
				retorno = true;
			}
		}
		return retorno;
	}

}
