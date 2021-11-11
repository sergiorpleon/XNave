package cu.juego.implementacion;

import java.security.KeyStore.Builder;
import java.util.List;

public interface Input{
	public static class KeyEvent {
		public static final int KEY_DOWN = 0;
		public static final int KEY_UP = 1;

		public int type;
		public int keyCode;
		public char keyChar;

		public String toString() {
			StringBuilder builder = new StringBuilder();
			if (type == KEY_DOWN) {
				builder.append("tecla pulsada, ");
			} else {
				builder.append("tecla levantada, ");
			}
			builder.append(keyCode);
			builder.append(", ");
			builder.append(keyChar);
			return builder.toString();
		}

	}

	public static class TouchEvent {
		public static final int TOUCH_DOWN = 0;
		public static final int TOUCH_UP = 1;
		public static final int TOUCH_DRAGGED = 2;

		public int type;
		public int x, y;
		public int pointer;

		public String toString() {
			StringBuilder builder = new StringBuilder();
			if (type == TOUCH_DOWN) {
				builder.append("touch down, ");
			} else if (type == TOUCH_DRAGGED) {
				builder.append("touch dragged, ");
			} else {
				builder.append("touch up, ");
			}
			builder.append(pointer);
			builder.append(", ");
			builder.append(x);
			builder.append(", ");
			builder.append(y);
			return builder.toString();
		}

	}
	
	public static class BackEvent{
		public boolean press;
	}

	public boolean isKeyPressed(int keyCode);

	public boolean isTouchPressed(int pointer);

	public int getTouhX(int pointer);

	public int getTouhY(int pointer);

	public float getAcelX();

	public float getAcelY();

	public float getAcelZ();

	public List<KeyEvent> getkeyEvents();

	public List<TouchEvent> getTouchEvents();
}
