package to.nishant.shoppin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.RelativeLayout;

import to.nishant.shoppin.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.onesignal.OneSignal;

import java.util.ArrayList;

public class Home extends AppCompatActivity {


    private ArrayList<String> mTitles = new ArrayList<>();
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    int countProducts = 0;
    int totalprice = 0;
    private static final String ONESIGNAL_APP_ID = "96039d54-4610-4ff3-bf08-1a09d6c23b35";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home);
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);

        ChipNavigationBar chipNavigationBar = findViewById(R.id.bottom_navigation);
        chipNavigationBar.setItemSelected(R.id.nav_home,true);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            databaseReference = FirebaseDatabase.getInstance().getReference("cart").child(firebaseUser.getUid());
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        CartClass product = dataSnapshot.getValue(CartClass.class);
                        countProducts = (int) snapshot.getChildrenCount();



                    }
                    chipNavigationBar.showBadge(R.id.nav_cart,countProducts);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
        int displayWidth = getWindowManager().getDefaultDisplay().getHeight();
        RelativeLayout fragment = findViewById(R.id.fragment_container);
        fragment.getLayoutParams().height = displayWidth - 100;
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).addToBackStack(null).commit();
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment selectedFragment = null;

                switch (i) {
                    case R.id.nav_home:
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.nav_cart:
                        selectedFragment = new CartFragment();
                        break;
                    case R.id.nav_cat:
                        selectedFragment = new FragmentSearchCateg();
                        break;
                    case R.id.nav_menu:
                        selectedFragment = new MenuFragment();
                        break;

                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        selectedFragment).commit();

            }
        });
        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).addToBackStack(null).commit();
        }
    }



}

