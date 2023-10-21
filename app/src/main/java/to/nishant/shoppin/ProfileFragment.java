package to.nishant.shoppin;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import to.nishant.shoppin.R;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ProfileFragment extends Fragment {
    FirebaseAuth firebaseAuth;
    LinearLayout user,nouser;
    CardView gotologin,gotosignup;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        user = view.findViewById(R.id.profileyesuser);
        nouser = view.findViewById(R.id.profilenouser);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser == null){
            user.setVisibility(View.GONE);
            nouser.setVisibility(View.VISIBLE);
            gotologin = view.findViewById(R.id.hey122344353545);
            gotosignup = view.findViewById(R.id.gotosignup);
            gotosignup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), SignupActivity.class);
                    startActivity(intent);
                }
            });
            gotologin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(),LoginActivity.class);
                    startActivity(intent);
                }
            });
        }
        return view;
    }
}