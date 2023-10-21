package to.nishant.shoppin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import to.nishant.shoppin.R;


public class Opening1 extends AppCompatActivity {
    CardView gotologin,gotosignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening1);
        gotologin = findViewById(R.id.gotosignup);
        gotosignup = findViewById(R.id.gotosignup);
        gotosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Opening1.this,SignupActivity.class);
                startActivity(intent);
            }
        });
        gotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Opening1.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}