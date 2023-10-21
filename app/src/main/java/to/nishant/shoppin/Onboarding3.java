package to.nishant.shoppin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import to.nishant.shoppin.R;


public class Onboarding3 extends AppCompatActivity {
    CardView next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#1fb08f"));
        }
        setContentView(R.layout.activity_onboarding3);
        next = findViewById(R.id.btnnext3);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Onboarding3.this,
                        SignupActivity.class);
                //Intent is used to switch from one activity to another.

                startActivity(i);
            }
        });
    }
}