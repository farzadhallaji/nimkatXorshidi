package com.hallaji.farzad.nimkatxorshidi;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.ramotion.circlemenu.CircleMenuView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MenuActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ImageView imageViewBackground = findViewById(R.id.imageViewBackground);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        int saat = Integer.parseInt(sdf.format(new Date()).split(":")[0]);
        if(saat > 5 && saat < 19)
            imageViewBackground.setImageResource(R.drawable.sardaruz);
        else
            imageViewBackground.setImageResource(R.drawable.sardashab);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        final CircleMenuView menu = findViewById(R.id.circle_menu);
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {
                menu.open(true);
            }
        };
        handler.postDelayed(r, 100);

        final CountDownTimer countDownTimer = new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                //TODO: Do something every second
            }

            public void onFinish() {

                finish();
                Intent intent = new Intent(getApplication() , MainActivity.class);
                startActivity(intent);
                //YourActivity.finish();  outside the actvitiy

            }
        }.start();


        menu.setEventListener(new CircleMenuView.EventListener() {
            @Override
            public void onMenuOpenAnimationStart(@NonNull CircleMenuView view) {
                Log.d("D", "onMenuOpenAnimationStart");
            }

            @Override
            public void onMenuOpenAnimationEnd(@NonNull CircleMenuView view) {
                Log.d("D", "onMenuOpenAnimationEnd");
            }

            @Override
            public void onMenuCloseAnimationStart(@NonNull CircleMenuView view) {
                Log.d("D", "onMenuCloseAnimationStart");
            }

            @Override
            public void onMenuCloseAnimationEnd(@NonNull CircleMenuView view) {
                Log.d("D","onMenuCloseAnimationEnd");
            }

            @Override
            public void onButtonClickAnimationStart(@NonNull CircleMenuView view, int index) {
                Log.d("D", "onButtonClickAnimationStart| index: " + index);

            }

            @Override
            public void onButtonClickAnimationEnd(@NonNull CircleMenuView view, int index) {
                Log.d("D", "onButtonClickAnimationEnd| index: " + index);

                finish();
                switch (index){
//                    case  0 :
//                        Bundle mBundle = new Bundle();
//                        mBundle.putString("url", "http://www.havairan.com/");
//                        Intent intent0 = new Intent(getApplication() , WebPageActivity.class);
//                        intent0.putExtras(mBundle);
//                        startActivity(intent0);
//                        countDownTimer.cancel();
//                        break;
                    case  0 :
                        Intent intent0 = new Intent(getApplication() , CitiesActivity.class);
                        startActivity(intent0);
                        countDownTimer.cancel();
                        break;

                    case  1 :
                        Bundle mBundle1 = new Bundle();
                        mBundle1.putString("url", "http://techpark.ir/?login=true");
                        Intent intent1 = new Intent(getApplication() , WebPageActivity.class);
                        intent1.putExtras(mBundle1);
                        startActivity(intent1);
                        countDownTimer.cancel();
                        break;
                    case  2 :
                        Bundle mBundle2 = new Bundle();
                        mBundle2.putString("url", "https://www.google.com/maps/place/Pardis+Technology+Park/@35.7298978,51.8227088,17z/data=!3m1!4b1!4m5!3m4!1s0x3f91da3b00f3efc9:0x383d79f4cad589c8!8m2!3d35.7298935!4d51.8248975");
                        Intent intent2 = new Intent(getApplication() , WebPageActivity.class);
                        intent2.putExtras(mBundle2);
                        startActivity(intent2);
                        countDownTimer.cancel();
                        break;
                    case  3 :
                        Bundle mBundle3 = new Bundle();
                        mBundle3.putString("url", "http://www.online-traffic.ir/");
                        Intent intent3 = new Intent(getApplication() , WebPageActivity.class);
                        intent3.putExtras(mBundle3);
                        startActivity(intent3);
                        countDownTimer.cancel();
                        break;

                    case 4 :
                        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.sabaidea.aparat");
                        if (launchIntent != null) {
                            finish();
                            startActivity(launchIntent);//null pointer check in case package name was not found
                        }
                        startActivity(launchIntent);
                        countDownTimer.cancel();
                        break;

                    case 5 :
                        Bundle mBundle5 = new Bundle();
                        mBundle5.putString("ax", "metro");
                        Intent intent5 = new Intent(getApplication() , AxActivity.class);
                        intent5.putExtras(mBundle5);
                        startActivity(intent5);
                        countDownTimer.cancel();
                        break;

                    case 6 :
//                        Bundle mBundle6 = new Bundle();
//                        mBundle6.putString("ax", "brt");
//                        Intent intent6 = new Intent(getApplication() , Main5Activity.class);
//                        intent6.putExtras(mBundle6);
//                        startActivity(intent6);
//                        countDownTimer.cancel();
//                        break;

                        Intent intent6 = new Intent(getApplication() , AxActivity.class);
                        startActivity(intent6);
                        countDownTimer.cancel();
                        break;

                    case  7 :
                        Bundle mBundle7 = new Bundle();
                        mBundle7.putString("url", "http://www.aut.ac.ir/aut/fa/");
                        Intent intent7 = new Intent(getApplication() , WebPageActivity.class);
                        intent7.putExtras(mBundle7);
                        startActivity(intent7);
                        countDownTimer.cancel();
                        break;

                    case  8 :
                        Bundle mBundle8 = new Bundle();
                        mBundle8.putString("url", "http://edu.aut.ac.ir/iCMS/ed/");
                        Intent intent8 = new Intent(getApplication() , WebPageActivity.class);
                        intent8.putExtras(mBundle8);
                        startActivity(intent8);
                        countDownTimer.cancel();
                        break;

                }
            }

            @Override
            public boolean onButtonLongClick(@NonNull CircleMenuView view, int index) {
                Log.d("D", "onButtonLongClick| index: " + index);
                return true;
            }

            @Override
            public void onButtonLongClickAnimationStart(@NonNull CircleMenuView view, int index) {
                Log.d("D", "onButtonLongClickAnimationStart| index: " + index);
            }

            @Override
            public void onButtonLongClickAnimationEnd(@NonNull CircleMenuView view, int index) {
                Log.d("D", "onButtonLongClickAnimationEnd| index: " + index);
            }
        });

    }

}