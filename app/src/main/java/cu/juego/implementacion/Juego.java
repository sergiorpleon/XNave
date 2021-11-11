package cu.juego.implementacion;

import android.content.Context;
import cu.juego.implementacion.android.AndroidFastRenderView;

public interface Juego {
public Input getInput();
public FileIO getFileIO();
public Graficos getGraficos();
public Audio getAudio();
public void setScreen(Pantalla pantalla);
public Pantalla getCurrentScreen();
public Pantalla getStartScreen();
public void close();
public void mostrarDialogo();
public AndroidFastRenderView getRenderView();

public Context getContext();
}
