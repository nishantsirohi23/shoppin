package to.nishant.shoppin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;

import to.nishant.shoppin.R;
import com.google.firebase.auth.FirebaseAuth;

import de.hdodenhof.circleimageview.CircleImageView;

public class DashboardSeller extends AppCompatActivity {
    CardView profilebtn,ordersbtn,payments,uploadbtn,productsbtn,signoutbtn;
    FirebaseAuth firebaseAuth;
    CircleImageView profilephoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_seller);


    }
}