package cu.juego.implementacion;

public interface Musica {

	public void play();
	
	public void stop();
	
	public void pause();
	
	public void setLooping(boolean looping);
	
	public void setVolume(float volume );
	
	public boolean isPlay();
	
	public boolean isStop();
	
	public boolean isLooping();
	
	public void dispose();
}
