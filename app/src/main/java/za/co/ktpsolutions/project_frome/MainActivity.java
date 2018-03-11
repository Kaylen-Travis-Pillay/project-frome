package za.co.ktpsolutions.project_frome;

/*
* Author: Kaylen Travis Pillay
* Licence:
*
*   MIT License
*   Copyright (c) 2018 Kaylen Travis Pillay
*
*   Permission is hereby granted, free of charge, to any person obtaining a copy
*   of this software and associated documentation files (the "Software"), to deal
*   in the Software without restriction, including without limitation the rights
*   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
*   copies of the Software, and to permit persons to whom the Software is
*   furnished to do so, subject to the following conditions:
*
*   The above copyright notice and this permission notice shall be included in all
*   copies or substantial portions of the Software.
*
*   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
*   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
*   FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
*   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
*   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
*   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
*   SOFTWARE.
* */

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.ldoublem.loadingviewlib.view.LVGearsTwo;

import java.lang.ref.WeakReference;

import es.dmoral.toasty.Toasty;

/*
    The MainActivity in the project performs the initial loading of the application.
    This gives the application the opportunity to load or perform any setup that you
    as the developer want. If you have no setup, the this activity can serve as a
    splash screen, or possibly removed altogether.

    The two major libraries used in this activity are:
        1. LoadingView (https://github.com/ldoublem/LoadingView)
        2. Toasty (https://github.com/GrenderG/Toasty)

    The docs (view link adjacent to library name) for these libraries gives you an
    insight into the use of them and ways in which you can customise the default
    usage within this project.

    DEFAULT:
    By default the project uses Google SignIn (https://goo.gl/thrZW9) to authenticate
    and sign up the user to the service. Therefore this activity checks if there is a
    user already signed in or if no user is logged in.
 */
public class MainActivity extends Activity {

    //Static variables
    private static final int LOAD_DURATION = 5000;
    private static final String TAG = "LoadingActivity-:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get reference to LVGearTwo view
        LVGearsTwo lp_load_view = findViewById(R.id.lp_load_view);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //Start LoadingAsyncThread task to perform loading
        new LoadingAsyncThread(this).execute();
    }

    /*
        * LoadingAsyncThread is a background async task that allows you to perform
        * any initial setup code before the next phase/intent of the application
        * starts (by default the next phase of the application is authentication)
        *
        * NOTE:
        *   The Params, Progress and Return type of LoadingAsyncTask has been set
        *   to Void by default. You may customise this to your needs. The default
        *   project will pause the thread for 5 seconds to mimic background code
        *   running, which essentially is your custom loading/setup code.
        */
    private static class LoadingAsyncThread extends AsyncTask<Void, Void, Void>{

        private WeakReference<MainActivity> activity_reference;

        LoadingAsyncThread(MainActivity context){
            activity_reference = new WeakReference<>(context);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // Perform some action before the background code runs.
            // By default the project will start the loading animation.

            MainActivity activity = activity_reference.get();
            if(activity!=null && !activity.isFinishing()){
                //Get a reference to the loading icon and start the animation.
                LVGearsTwo lp_load_view = activity.findViewById(R.id.lp_load_view);
                lp_load_view.startAnim();
            }

            //TODO: Implement your custom code here
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // Add your custom loading/setup code here. The doInBackground
            // method of LoadingAsyncThread should return something that
            // can be used by the onPostExecute to possible update your UI,
            // and/or move to the next phase or intent of the application.
            //
            // By default the project will mimic this background 'work' by
            // pausing this background thread for LOAD_DURATION milliseconds.

            try{
                Thread.sleep(LOAD_DURATION);
            } catch (InterruptedException e){
                Log.e(TAG, e.getMessage());
            }

            //TODO: Implement your custom code here

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            // Perform some action after the background task has completed
            // it's execution. By default the project stops the animation and
            // moves to the authentication phase/intent of the project.

            MainActivity activity = activity_reference.get();
            if(activity!=null && !activity.isFinishing()){
                //Get reference to loading icon and stop the animation
                LVGearsTwo lp_load_view = activity.findViewById(R.id.lp_load_view);
                lp_load_view.stopAnim();
                //Execute moveToNextPhase on the currently active activity
                activity.moveToNextPhase();
            }

            //TODO: Implement your custom code here
        }
    }

    private void moveToNextPhase(){
        // moveToNextPhase moves to application to the next phase of the project.
        // By default the project moves to the authentication phase of the project.
        // The toast that is displayed after loading gives attribution to the
        // original developer and can be removed

        if(isUserSignedIn()){
            //TODO: (Development) Move directly to the store activity of the project!
        }else{

            Toasty.info(this
                    , getText(R.string.developer_attribution)
                    ,Toast.LENGTH_SHORT
                    ,true)
                    .show();

            //TODO: (Development) Move to the authentication activity of the project
        }

        //TODO: Implement your custom here
    }

    private boolean isUserSignedIn(){
        // Check whether there is a signed in user.

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        return account != null;
    }
}
