package net.eazyhealth.id.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import net.eazyhealth.id.app.R;
import net.eazyhealth.id.app.custom.CustomAppCompatActivity;

public class SplashActivity extends CustomAppCompatActivity {

    private static final int DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        try {
//            PackageInfo info = getPackageManager().getPackageInfo("net.eazyhealth.id.app", PackageManager.GET_SIGNATURES);
//            for (android.content.pm.Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }

        new CountDownTimer(DELAY, 1000) {

            public void onTick(long millisUntilFinished) {}

            public void onFinish() {
//                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(i);
                finish();
            }
        }.start();

    }
}
