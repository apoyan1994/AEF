package com.aef.edu.aef;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class AefMainActivity extends AppCompatActivity implements OnAnimationEndListener {
    AnimationHandler animationHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aef_main);

        if (null == savedInstanceState) {
            animationHandler = new AnimationHandler(this, (ImageView) findViewById(R.id.aef_description), findViewById(R.id.letters_container),
                    (TextView) findViewById(R.id.aef_latter_a),
                    (TextView) findViewById(R.id.aef_latter_e), (TextView) findViewById(R.id.aef_latter_f));

            findViewById(R.id.aef_description).post(new Runnable() {
                @Override
                public void run() {
                    animationHandler.startAnimation();
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 45) {
            finish();
        }
    }

    @Override
    public void onAnimationEnded() {
        startActivityForResult(new Intent(getApplicationContext(), AefContextActivity.class), 45);
    }
}
