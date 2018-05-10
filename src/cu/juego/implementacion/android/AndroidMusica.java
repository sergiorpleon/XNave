package cu.juego.implementacion.android;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

import cu.juego.implementacion.Musica;

public class AndroidMusica implements Musica, OnCompletionListener{
	MediaPlayer mediaPlayer;
	boolean isPrepared = false;
	
	
	public AndroidMusica(AssetFileDescriptor assetFileDescriptor){
		mediaPlayer = new MediaPlayer();
		try {
			mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
			mediaPlayer.prepare();
			isPrepared = true;
			mediaPlayer.setOnCompletionListener(this);
		} catch (Exception e) {
			throw new RuntimeException("No se pudo cargar el sonido");
		}
	}
	
	
	@Override
	public void play() {
		// TODO Auto-generated method stub
		if(mediaPlayer.isPlaying()){
			return;
		}
		try {
			synchronized (this) {
				if(isPrepared){
					mediaPlayer.prepare();
					mediaPlayer.start();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		mediaPlayer.stop();
		synchronized (this ) {
			isPrepared= false;
		}
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		if(mediaPlayer.isPlaying()){
			mediaPlayer.pause();
		}
	}

	@Override
	public void setLooping(boolean looping) {
		// TODO Auto-generated method stub
		mediaPlayer.setLooping(looping);
	}

	@Override
	public void setVolume(float volume) {
		// TODO Auto-generated method stub
		mediaPlayer.setVolume(volume, volume);
	}

	@Override
	public boolean isPlay() {
		// TODO Auto-generated method stub
		return mediaPlayer.isPlaying();
	}

	@Override
	public boolean isStop() {
		// TODO Auto-generated method stub
		return !isPrepared;
	}

	@Override
	public boolean isLooping() {
		// TODO Auto-generated method stub
		return mediaPlayer.isLooping();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		if(mediaPlayer.isPlaying()){
			mediaPlayer.stop();
			mediaPlayer.release();
		}
	}


	@Override
	public void onCompletion(MediaPlayer arg0) {
		// TODO Auto-generated method stub
		synchronized (this) {
			isPrepared=false;
		}
	}

}
