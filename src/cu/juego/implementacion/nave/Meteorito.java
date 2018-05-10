package cu.juego.implementacion.nave;

public class Meteorito {
public static final int dimInicial =1;
public static final int dimFinal =80;

public static final int DESTRUIDO = 4;
public static final int LEJOS = 3;
public static final int MEDIA = 2;
public static final int CERCA = 1;

int posx;
int posy;
int dimx;
int dimy;

public Meteorito(){
	posx = (int) (Math.random()*320);
	posy = (int) (Math.random()*480);
	dimy = dimx = (int) ((Math.random()*(dimInicial+90))-45);
}


public boolean estalla(){
	return(dimx>dimFinal);
}

}
