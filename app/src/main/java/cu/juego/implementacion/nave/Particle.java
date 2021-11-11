package cu.juego.implementacion.nave;

public class Particle {
	public double angle;
	public int posx;
	public int posy;
	
	public int incx;
	public int incy;
	
	public static final int INC = 5;
	
	public Particle(boolean pantalla){
		angle = (int) (Math.random()*(Math.PI*2));
		int zona;
		if(pantalla){
		    zona = 1;
		}else{
			zona = 1;
		}
		int posX = (int) (Math.pow(Math.random(), 2)*zona);
		int posY = (int) (Math.pow(Math.random(), 2)*zona);
		//posx = posX-zona/2+160;
		//posy = posY-zona/2+240;
		posx = 160;
		posy = 240;

		//incx = (int) (Math.cos(angle)*(INC));
		//incy = (int) (Math.sin(angle)*(INC));
		//incremento entre 6 y 11
		incx = (int) (Math.random()*(INC)+INC)+1;
		incy = (int) (Math.random()*(INC)+INC)+1;
		if(Math.sqrt(Math.pow(incx, 2)+Math.pow(incy, 2))<2){
			posx = 400;
			posy = 400;
		}
		incx=incx*((Math.random()<0.5)?-1:1);
		incy=incy*((Math.random()<0.5)?-1:1);
		if(posx<400 && posy<400 || incx == 0 || incy == 0){
			posx+=Math.random()*50 * incx;
			posy+=Math.random()*50 * incy;
		}
	}
	
	public void update(){
		posx += incx;
		posy += incy;
	}
}
