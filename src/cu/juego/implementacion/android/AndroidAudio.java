package cu.juego.implementacion.android;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import cu.juego.implementacion.Audio;
import cu.juego.implementacion.Musica;
import cu.juego.implementacion.Sonido;

public class AndroidAudio implements Audio{
AssetManager assets;
SoundPool soundPool;

public AndroidAudio(Activity activity){
	activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
	this.assets = activity.getAssets();
	this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
}

@Override
public Musica nuevaMusica(String nombreArchivo) {
	// TODO Auto-generated method stub
	try{
	AssetFileDescriptor assetsDescriptor = assets.openFd(nombreArchivo);
	
	return new AndroidMusica(assetsDescriptor);
	}catch (Exception e) {
		throw new RuntimeException("No se a podido cargar el archivo '" + nombreArchivo + "'");
	}
}

@Override
public Sonido nuevoSonido(String filename) {
	// TODO Auto-generated method stub
	try{
		AssetFileDescriptor assetsDescriptor = assets.openFd(filename);
		int soundId = soundPool.load(assetsDescriptor, 0);
		return new AndroidSonido(soundPool, soundId);
		}catch (Exception e) {
			throw new RuntimeException("No se a podido cargar el archivo '" + filename + "'");
		}
}

}
