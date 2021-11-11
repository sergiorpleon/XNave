package cu.juego.implementacion.android;

import android.app.Activity;
import android.media.SoundPool;

import cu.juego.implementacion.Sonido;

public class AndroidSonido implements Sonido{
int soundId;
SoundPool soundPool;

public AndroidSonido(SoundPool soundPool, int soundId){
	this.soundId = soundId;
	this.soundPool = soundPool;
}

@Override
public void play(float volume) {
	// TODO Auto-generated method stub
	soundPool.play(soundId, volume, volume, 0, 0, 1);
}

@Override
public void dispose() {
	// TODO Auto-generated method stub
	soundPool.unload(soundId);
}
}
