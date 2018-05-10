package cu.juego.implementacion.android;

import java.util.ArrayList;
import java.util.List;

import cu.juego.implementacion.*;
import cu.juego.implementacion.Input.TouchEvent;
import cu.juego.implementacion.Pool.PoolObjectFactory;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class SingleTouchHandler implements TouchHandler{
	boolean isTouch;
	int touchX;
	int touchY;
	Pool<TouchEvent> touchEventPool;
	List<TouchEvent> touchEventBuffer = new ArrayList<TouchEvent>();
	List<TouchEvent> touchEvents = new ArrayList<TouchEvent>();
	float scaleX;
	float scaleY;
	public SingleTouchHandler(View view, float scaleX, float scaleY) {
		PoolObjectFactory<TouchEvent> factory = new PoolObjectFactory<TouchEvent>() {

			@Override
			public TouchEvent createObject() {
				// TODO Auto-generated method stub
				return new TouchEvent();
			}

		};
		touchEventPool = new Pool<TouchEvent>(factory, 100);
		view.setOnTouchListener(this);
		
		this.scaleX = scaleX;
		this.scaleY = scaleY;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		synchronized (this) {
			TouchEvent touchEvent = touchEventPool.newObject();
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				touchEvent.type = TouchEvent.TOUCH_DOWN;
				isTouch = true;
				break;
			case MotionEvent.ACTION_MOVE:
				touchEvent.type = TouchEvent.TOUCH_DRAGGED;
				isTouch = true;
				break;
			case MotionEvent.ACTION_CANCEL:
			case MotionEvent.ACTION_UP:
				touchEvent.type = TouchEvent.TOUCH_UP;
				isTouch = false;
				break;
			default:
				break;
			}

			touchEvent.x = touchX = (int) (event.getX()*scaleX);
			touchEvent.y = touchY = (int) (event.getY()*scaleY);
			touchEventBuffer.add(touchEvent);
			return true;
		}
		
	}
	
	@Override
	public boolean isTouchDown(int pointer){
		synchronized (this) {
			if(pointer == 0){
				return isTouch;
			}else{
				return false;
			}
		}
	}

	@Override
	public int getTouchX(int pointer){
		synchronized (this) {
			return touchX;
		}
	}
	
	@Override
	public int getTouchY(int pointer){
		synchronized (this) {
			return touchY;
		}
	}
	
	@Override
	public List<TouchEvent> getTouchEvents(){
		synchronized (this) {
			int len = touchEvents.size();
			for (int i = 0; i < len; i++) {
				touchEventPool.free(touchEvents.get(i));
			}
			touchEvents.clear();
			touchEvents.addAll(touchEventBuffer);
			touchEventBuffer.clear();
			return touchEvents;
		}
	}
	
	

}
