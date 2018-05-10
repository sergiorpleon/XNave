package cu.juego.implementacion.android;

import java.util.List;

import junit.runner.Version;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.View;

import cu.juego.implementacion.Input;
import cu.juego.implementacion.TouchHandler;

public class AndroidInput implements Input{

	AccelerometerHandler accelHandler;
	KeyboardHandler keyHandler;
	TouchHandler touchHandler;
	
	public AndroidInput(Context context, View view, float scaleX, float scaleY){
		accelHandler = new AccelerometerHandler(context);
		keyHandler = new KeyboardHandler(view);
		if (Integer.parseInt(VERSION.SDK)<5) {
			touchHandler = new SingleTouchHandler(view, scaleX, scaleY);
		}else{
			touchHandler = new MultiTouchHandler(view, scaleX, scaleY);
		}
	}
	
	
	
	
	@Override
	public boolean isKeyPressed(int keyCode) {
		// TODO Auto-generated method stub
		return keyHandler.isKeyPressed(keyCode);
	}

	@Override
	public boolean isTouchPressed(int pointer) {
		// TODO Auto-generated method stub
		return touchHandler.isTouchDown(pointer);
	}

	@Override
	public int getTouhX(int pointer) {
		// TODO Auto-generated method stub
		return touchHandler.getTouchX(pointer);
	}

	@Override
	public int getTouhY(int pointer) {
		// TODO Auto-generated method stub
		return touchHandler.getTouchY(pointer);
	}

	@Override
	public float getAcelX() {
		// TODO Auto-generated method stub
		return accelHandler.getAccelX();
	}

	@Override
	public float getAcelY() {
		// TODO Auto-generated method stub
		return accelHandler.getAccelY();
	}

	@Override
	public float getAcelZ() {
		// TODO Auto-generated method stub
		return accelHandler.getAccelZ();
	}

	@Override
	public List<KeyEvent> getkeyEvents() {
		// TODO Auto-generated method stub
		return keyHandler.getKeyEvents();
	}

	@Override
	public List<TouchEvent> getTouchEvents() {
		// TODO Auto-generated method stub
		return touchHandler.getTouchEvents();
	}
	
}
