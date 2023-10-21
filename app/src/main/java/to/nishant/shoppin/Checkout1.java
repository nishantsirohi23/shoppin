package to.nishant.shoppin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import to.nishant.shoppin.R;
import com.google.android.material.card.MaterialCardView;

public class Checkout1 extends AppCompatActivity {
    CardView btngotopayment;
    MaterialCardView addresscard;
    CardView cardaddnewaddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_checkout1);
        btngotopayment = findViewById(R.id.btngotoppayment);
        addresscard = findViewById(R.id.cardselectaddress);
        cardaddnewaddress = findViewById(R.id.cardaddnewaddress);
        cardaddnewaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(Checkout1.this,addnewaddress.class);
               startActivity(intent);
            }
        });
        addresscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addresscard.setChecked(true);
                addresscard.setChecked(!addresscard.isChecked());
                addresscard.toggle();

            }
        });
        btngotopayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (addresscard.isChecked() == true) {
                    Intent intent = new Intent(Checkout1.this,Checkout2.class);
                    startActivity(intent);

                }
                else {
                    Toast.makeText(getApplicationContext(), "Please select a Delivery address", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}