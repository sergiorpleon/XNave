package cu.juego.implementacion.android;

import cu.juego.implementacion.Audio;
import cu.juego.implementacion.FileIO;
import cu.juego.implementacion.Graficos;
import cu.juego.implementacion.Input;
import cu.juego.implementacion.Juego;
import cu.juego.implementacion.Pantalla;
import cu.juego.implementacion.nave.Configuraciones;
import cu.juego.implementacion.nave.Control;

import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public abstract class AndroidJuego extends Activity implements Juego {

	AndroidFastRenderView renderView;
	Graficos graficos;
	Audio audio;
	Input input;
	FileIO fileIO;
	Pantalla pantalla;
	WakeLock wakeLock;
	public Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
		int frameBufferWidth = isLandscape ? 480 : 320;
		int frameBufferHeigth = isLandscape ? 320 : 480;

		Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth,
				frameBufferHeigth, Config.RGB_565);

		float scaleX = (float) frameBufferWidth
				/ getWindowManager().getDefaultDisplay().getWidth();
		float scaleY = (float) frameBufferHeigth
				/ getWindowManager().getDefaultDisplay().getHeight();
		
		String nombrePrefer = "Navetron";
		SharedPreferences miPrefer = this.getSharedPreferences(nombrePrefer, MODE_PRIVATE);
		Configuraciones.miPrefer = miPrefer;
		
		renderView = new AndroidFastRenderView(this, frameBuffer);
		graficos = new AndroidGraficos(getAssets(), frameBuffer);
		fileIO = new AndroidFileIO(getAssets());
		audio = new AndroidAudio(this);
		input = new AndroidInput(this, renderView, scaleX, scaleY);

		pantalla = getStartScreen();
		setContentView(renderView);

		PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK,
				"GLGame");
	}

	@Override
	public void onResume() {
		super.onResume();
		wakeLock.acquire();
		pantalla.resume();
		renderView.resume();

	}

	@Override
	public void onPause() {
		super.onPause();
		wakeLock.release();
		pantalla.pause();
		renderView.pause();

		if (isFinishing()) {
			pantalla.dispose();
		}
	}

	@Override
	public Input getInput() {
		// TODO Auto-generated method stub
		return input;
	}

	@Override
	public FileIO getFileIO() {
		// TODO Auto-generated method stub
		return fileIO;
	}

	@Override
	public Graficos getGraficos() {
		// TODO Auto-generated method stub
		return graficos;
	}

	@Override
	public Audio getAudio() {
		// TODO Auto-generated method stub
		return audio;
	}

	@Override
	public void setScreen(Pantalla pantalla) {
		// TODO Auto-generated method stub
		if (pantalla == null) {
			throw new IllegalArgumentException("La pantalla no puede ser null");
		}
		this.pantalla.pause();
		this.pantalla.dispose();
		pantalla.resume();
		pantalla.update(0);
		this.pantalla = pantalla;

	}

	@Override
	public Pantalla getCurrentScreen() {
		// TODO Auto-generated method stub

		return pantalla;
	}

	@Override
	public Pantalla getStartScreen() {
		// TODO Auto-generated method stub
		return pantalla;
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		synchronized (this) {
			Control.isback = true;
		}

	}

	public void close() {
		// TODO Auto-generated method stub
		finish();
	}
	
	public void mostrarDialogo(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Confirmacion");
		builder.setMessage("Confirma la accion seleccionada?");
		builder.setPositiveButton("Aceptar",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Log.i("Confirmacion", "Confirmacion Aceptada.");
						dialog.cancel();
					}
				});
		builder.setNegativeButton("Cancelar",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Log.i("Confirmacion", "Confirmacion Cancelada.");
						dialog.cancel();
					}
				});
		builder.create();
		builder.show();
	}

	public AndroidFastRenderView getRenderView() {
		return renderView;
	}
	
	public Context getContext() {
		return context;
	}

}
