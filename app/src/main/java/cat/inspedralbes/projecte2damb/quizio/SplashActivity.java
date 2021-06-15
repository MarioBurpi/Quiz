package cat.inspedralbes.projecte2damb.quizio;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import cat.inspedralbes.projecte2damb.quizio.model.Question;
import cat.inspedralbes.projecte2damb.quizio.util.QuestionDownloader;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DURATION = 3000; // seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // Download a list of questions that would stay static all the game at QuestionDownloader.questionList
        QuestionDownloader.requestTrivia("", "", "");
        // Animate the logo
        ImageView splashScreen = findViewById(R.id.splashscreen);
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        splashScreen.startAnimation(fadeInAnimation);
        // Hide the top and bottom bars
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(intent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DURATION);
    }
}