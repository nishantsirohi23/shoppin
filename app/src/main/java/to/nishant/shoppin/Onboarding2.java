package to.nishant.shoppin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import to.nishant.shoppin.R;


public class Onboarding2 extends AppCompatActivity {
    TextView next,skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#ffbe98"));
        }
        setContentView(R.layout.activity_onboarding2);
        next = findViewById(R.id.btnnext2);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Onboarding2.this,
                        Onboarding3.class);
                //Intent is used to switch from one activity to another.

                startActivity(i);
            }
        });
    }
}