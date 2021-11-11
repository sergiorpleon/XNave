package cu.juego.implementacion.nave;

import cu.juego.implementacion.Graficos;
import cu.juego.implementacion.Juego;
import cu.juego.implementacion.Pantalla;
import cu.juego.implementacion.Graficos.PixmapFormat;

public class LoadingScreen extends Pantalla {

	public LoadingScreen(Juego juego) {
		super(juego);
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		Graficos g = juego.getGraficos();
		Assets.fondo = g.newPixmap("fondo.png", PixmapFormat.RGB565);
		Assets.fondo1 = g.newPixmap("fondo1.png", PixmapFormat.RGB565);
		Assets.menuprincipal = g.newPixmap("menuprincipal.png",
				PixmapFormat.ARGB4444);
		Assets.botones = g.newPixmap("botones.png", PixmapFormat.ARGB4444);
		Assets.botones1 = g.newPixmap("botones1.png", PixmapFormat.ARGB4444);
		Assets.ayuda1 = g.newPixmap("ayuda1.png", PixmapFormat.ARGB4444);
		Assets.ayuda2 = g.newPixmap("ayuda2.png", PixmapFormat.ARGB4444);
		Assets.ayuda3 = g.newPixmap("ayuda3.png", PixmapFormat.ARGB4444);
		Assets.numeros = g.newPixmap("numeros.png", PixmapFormat.ARGB4444);
		Assets.preparado = g.newPixmap("preparado.png", PixmapFormat.ARGB4444);
		Assets.menupausa = g.newPixmap("menupausa.png", PixmapFormat.ARGB4444);
		Assets.finjuego = g.newPixmap("finjuego.png", PixmapFormat.ARGB4444);
		// Assets.trenarriba = g.newPixmap("trenarriba.png",
		// PixmapFormat.ARGB4444);
		// Assets.trenabajo = g.newPixmap("trenabajo.png",
		// PixmapFormat.ARGB4444);
		// Assets.trenderecha = g.newPixmap("trenderecha.png",
		// PixmapFormat.ARGB4444);
		// Assets.trenizquierda = g.newPixmap("trenizquierda.png",
		// PixmapFormat.ARGB4444);
		// Assets.tripulacion = g.newPixmap("tripulacion.png",
		// PixmapFormat.ARGB4444);
		// Assets.tripulacionvertical = g.newPixmap("tripulacionvertical.png",
		// PixmapFormat.ARGB4444);
		// Assets.tripulacionhorizontal =
		// g.newPixmap("tripulacionhorizontal.png", PixmapFormat.ARGB4444);
		// Assets.bagon1 = g.newPixmap("bagon1.png", PixmapFormat.ARGB4444);
		// Assets.bagon2 = g.newPixmap("bagon2.png", PixmapFormat.ARGB4444);
		// Assets.bagon3 = g.newPixmap("bagon3.png", PixmapFormat.ARGB4444);

		Assets.logotren = g.newPixmap("logotren.png", PixmapFormat.ARGB4444);
		Assets.autor = g.newPixmap("autor.png", PixmapFormat.ARGB4444);
		// Assets.lineas = g.newPixmap("lineas.png", PixmapFormat.ARGB4444);
		// Assets.lineas2 = g.newPixmap("lineas2.png", PixmapFormat.ARGB4444);
		Assets.nave = g.newPixmap("nave.png", PixmapFormat.ARGB4444);
		Assets.meteorito = g.newPixmap("meteorito.png", PixmapFormat.ARGB4444);
		Assets.bola = g.newPixmap("bola.png", PixmapFormat.ARGB4444);
		Assets.diamante = g.newPixmap("diamante.png", PixmapFormat.ARGB4444);
		Assets.diamantegrande = g.newPixmap("diamantegrande.png",
				PixmapFormat.ARGB4444);
		Assets.enemigogrande = g.newPixmap("enemigogrande.png",
				PixmapFormat.ARGB4444);
		Assets.navegrande = g
				.newPixmap("navegrande.png", PixmapFormat.ARGB4444);
		Assets.meteoritogrande = g.newPixmap("meteoritogrande.png",
				PixmapFormat.ARGB4444);
		// Assets.metNEGRO = g.newPixmap("metNEGRO.png", PixmapFormat.ARGB4444);
		// Assets.metROJO = g.newPixmap("metROJO.png", PixmapFormat.ARGB4444);
		// Assets.metNARANJA = g.newPixmap("metNARANJA.png",
		// PixmapFormat.ARGB4444);
		Assets.enemigo = g.newPixmap("enemigo.png", PixmapFormat.ARGB4444);
		Assets.enemigo1 = g.newPixmap("enemigo1.png", PixmapFormat.ARGB4444);
		Assets.blanco = g.newPixmap("blanco.png", PixmapFormat.ARGB4444);
		Assets.blanco1 = g.newPixmap("blanco1.png", PixmapFormat.ARGB4444);
		Assets.niebla = g.newPixmap("niebla.png", PixmapFormat.ARGB4444);
		
		Assets.award = g.newPixmap("award.png", PixmapFormat.ARGB4444);

		Assets.na = juego.getAudio().nuevoSonido("nav.ogg");
		Assets.nav = juego.getAudio().nuevoSonido("nave.ogg");
		Assets.diaman = juego.getAudio().nuevoSonido("diamante.ogg");
		Assets.pulsar = juego.getAudio().nuevoSonido("pulsar.ogg");
		Assets.ataque = juego.getAudio().nuevoSonido("ataque.ogg");
		Assets.derrota = juego.getAudio().nuevoSonido("derrota.ogg");


		//Assets.musicafondo = juego.getAudio().nuevaMusica("musica.mp3");

		//Configuraciones.load(juego.getFileIO());
		Configuraciones.cargar();
		
		juego.setScreen(new MainMenuScreen(juego));
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
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
