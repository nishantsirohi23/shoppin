package to.nishant.shoppin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import to.nishant.shoppin.R;

public class Checkout3 extends AppCompatActivity {

    CardView gotoorders,continueshopping;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_checkout3);
        gotoorders = findViewById(R.id.btngotoorders);
        continueshopping = findViewById(R.id.btncontinueshopping);
        gotoorders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Checkout3.this,Home.class);
                startActivity(intent);
            }
        });
        continueshopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Checkout3.this,Home.class);
                startActivity(intent);

            }
        });
    }
}