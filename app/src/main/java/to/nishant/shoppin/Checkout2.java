package to.nishant.shoppin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import to.nishant.shoppin.R;
import com.google.android.material.card.MaterialCardView;

public class Checkout2 extends AppCompatActivity {

    RelativeLayout btngotoorderplaced;
    MaterialCardView cardcod,cardupi,cardcredit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_checkout2);
        btngotoorderplaced = findViewById(R.id.btngotoorderplced);

        cardcod = findViewById(R.id.cardcod);
        cardupi = findViewById(R.id.cardupi);
        cardcredit = findViewById(R.id.cardcredit);
        cardupi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardupi.setChecked(true);
                cardupi.setStrokeColor(getResources().getColor(R.color.black));
                cardupi.toggle();
                cardupi.setChecked(!cardupi.isChecked());
                cardcod.setChecked(false);
                cardcredit.setChecked(false);
            }
        });
        cardcredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardcredit.setChecked(true);
                cardcredit.toggle();
                cardcredit.setChecked(!cardcredit.isChecked());
                cardcod.setChecked(false);
                cardupi.setChecked(false);
            }
        });
        cardcod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardcod.setChecked(true);
                cardcod.setChecked(!cardcod.isChecked());
                cardcod.toggle();
                cardupi.setChecked(false);
                cardcredit.setChecked(false);


            }
        });
        btngotoorderplaced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cardcredit.isChecked() == true || cardcod.isChecked() == true || cardupi.isChecked() == true){

                    Intent intent = new Intent(getApplicationContext(),Checkout3.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please select a payment method", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}