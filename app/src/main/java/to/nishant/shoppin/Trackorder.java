package to.nishant.shoppin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import to.nishant.shoppin.R;


public class Trackorder extends AppCompatActivity {
    ImageView trackorderimage;
    TextView trackordername,trackorderprice;
    String ordername,orderprice,orderimageurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_trackorder);
        trackorderimage = findViewById(R.id.trackorderimage);
        trackordername = findViewById(R.id.trackordername);
        trackorderprice = findViewById(R.id.trackorderprice);

        orderimageurl = getIntent().getExtras().getString("orderimageurl");
        ordername = getIntent().getExtras().getString("ordername");
        orderprice = getIntent().getExtras().getString("orderprice");
        String upperStringname = ordername.substring(0, 1).toUpperCase() + ordername.substring(1).toLowerCase();

        Glide.with(getApplicationContext()).load(orderimageurl).into(trackorderimage);
        trackorderprice.setText(orderprice);
        trackordername.setText(upperStringname);

    }
}