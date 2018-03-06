package za.co.ktpsolutions.project_frome;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.ldoublem.loadingviewlib.view.LVGearsTwo;

public class MainActivity extends Activity {

    //Static variables
    private static final int LOAD_DURATION = 5000;

    //Private variables
    private LVGearsTwo lp_load_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get reference to LVCircularJump view
        lp_load_view = findViewById(R.id.lp_load_view);
        //Start load animation
        lp_load_view.startAnim();

        //Handler for loading thread
        Handler handler = new Handler();
        LoadingThread loading_thread = new LoadingThread();
        handler.postDelayed(loading_thread, LOAD_DURATION);
    }

    private class LoadingThread implements Runnable{

        @Override
        public void run() {
            //TODO: Start intent for login/register screen
            //Temporary code for moving to next activity
            Toast.makeText(MainActivity.this,
                    getText(R.string.next_activity_toast),
                    Toast.LENGTH_SHORT)
                    .show();

            //Stop the load animation
            lp_load_view.stopAnim();
        }
    }
}
