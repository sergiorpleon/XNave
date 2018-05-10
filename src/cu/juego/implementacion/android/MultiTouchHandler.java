package cu.juego.implementacion.android;

import java.util.ArrayList;
import java.util.List;

import android.view.MotionEvent;
import android.view.View;

import cu.juego.implementacion.Pool;
import cu.juego.implementacion.TouchHandler;
import cu.juego.implementacion.Input.TouchEvent;
import cu.juego.implementacion.Pool.PoolObjectFactory;

public class MultiTouchHandler implements TouchHandler{
	boolean[] isTouch = new boolean[20];
	int[] touchX = new int[20];;
	int[] touchY = new int[20];;
	Pool<TouchEvent> touchEventPool;
	List<TouchEvent> touchEventBuffer = new ArrayList<TouchEvent>();
	List<TouchEvent> touchEvents = new ArrayList<TouchEvent>();
	float scaleX;
	float scaleY;
	
	public MultiTouchHandler(View view, float scaleX, float scaleY) {
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
			int action = event.getAction() & MotionEvent.ACTION_MASK;
			int pointerIndex = (event.getAction() & MotionEvent.ACTION_MASK)>>MotionEvent.ACTION_POINTER_ID_SHIFT;
			int pointerId = event.getPointerId(pointerIndex);
			TouchEvent touchEvent;
			
			switch (action) {
			case MotionEvent.ACTION_DOWN:
			case MotionEvent.ACTION_POINTER_DOWN:
				touchEvent = touchEventPool.newObject();
				touchEvent.type = TouchEvent.TOUCH_DOWN;
				touchEvent.pointer = pointerId;
				touchEvent.x = touchX[pointerId] = (int)(event.getX(pointerIndex)*scaleX);
				touchEvent.y = touchX[pointerId] = (int)(event.getY(pointerIndex)*scaleY);
				
				isTouch[pointerId] = true;
				touchEventBuffer.add(touchEvent);
				break;

			case MotionEvent.ACTION_CANCEL:
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_POINTER_UP:
				touchEvent = touchEventPool.newObject();
				touchEvent.type = TouchEvent.TOUCH_UP;
				touchEvent.pointer = pointerId;
				touchEvent.x = touchX[pointerId] = (int)(event.getX(pointerIndex)*scaleX);
				touchEvent.y = touchX[pointerId] = (int)(event.getY(pointerIndex)*scaleY);
				
				isTouch[pointerId] = true;
				touchEventBuffer.add(touchEvent);
				break;
				
			case MotionEvent.ACTION_MOVE:
				int pointerCount = event.getPointerCount();
				for (int i = 0; i < pointerCount; i++) {
			    pointerIndex = i;
				pointerId = event.getPointerId(pointerIndex);	
				
				
				touchEvent = touchEventPool.newObject();
				touchEvent.type = TouchEvent.TOUCH_DRAGGED;
				touchEvent.pointer = pointerId;
				touchEvent.x = touchX[pointerId] = (int)(event.getX(pointerIndex)*scaleX);
				touchEvent.y = touchX[pointerId] = (int)(event.getY(pointerIndex)*scaleY);
				
				isTouch[pointerId] = true;
				touchEventBuffer.add(touchEvent);
				}
				break;
			default:
				break;
			}

			//touchEvent.x = touchX = (int) (event.getX()*scaleX);
			//touchEvent.y = touchY = (int) (event.getY()*scaleY);
			//touchEventBuffer.add(touchEvent);
			return true;
		}
		
	}
	
	@Override
	public boolean isTouchDown(int pointer){
		synchronized (this) {
			if(pointer < 0 || pointer > 20){
				return false;
			}else{
				return isTouch[pointer];
			}
		}
	}

	@Override
	public int getTouchX(int pointer){
		synchronized (this) {
			if(pointer < 0 || pointer > 20){
				return 0;
			}else{
				return touchX[pointer];
			}
		}
	}
	
	@Override
	public int getTouchY(int pointer){
		synchronized (this) {
			if(pointer < 0 || pointer > 20){
				return 0;
			}else{
				return touchY[pointer];
			}
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
