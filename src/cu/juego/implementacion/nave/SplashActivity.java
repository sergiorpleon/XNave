package cu.juego.implementacion.nave;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class SplashActivity extends Activity {

	// Set the duration of the splash screen
	private static final long SPLASH_SCREEN_DELAY = 3000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Set portrait orientation
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		// Hide title bar
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		// Set portrait orientation
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		// Hide title bar
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_splash);
		
		//int ww = (getWindowManager().getDefaultDisplay().getWidth()/2);
		//LinearLayout layoutlogo = (LinearLayout) findViewById(R.id.LayoutLogo1);
		//LayoutParams lp=new LayoutParams(ww, ww);
		//layoutlogo.setLayoutParams(lp);
		//layoutlogo.setGravity(Gravity.CENTER);
		

		

		TimerTask task = new TimerTask() {
			@Override
			public void run() {

				// Start the next activity
				Intent mainIntent = new Intent().setClass(SplashActivity.this,
						JuegoNave.class);
				startActivity(mainIntent);

				// Close the activity so the user won't able to go back this
				// activity pressing Back button
				finish();
			}
		};

		// Simulate a long loading process on application startup.
		Timer timer = new Timer();
		timer.schedule(task, SPLASH_SCREEN_DELAY);
	}

}
