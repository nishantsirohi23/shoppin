package to.nishant.shoppin;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import to.nishant.shoppin.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;


public class SellerDashboardFragment extends Fragment {
    CardView profilebtn,ordersbtn,payments,uploadbtn,productsbtn,signoutbtn;
    FirebaseAuth firebaseAuth;
    CircleImageView profilephoto;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_seller_dashboard, container, false);
        profilebtn = view.findViewById(R.id.profilebtn);
        ordersbtn = view.findViewById(R.id.ordersbtn);
        payments = view.findViewById(R.id.paymentsbtn);
        productsbtn =view. findViewById(R.id.Products);
        uploadbtn = view.findViewById(R.id.uploadbtn);
        signoutbtn = view.findViewById(R.id.signoutbtn);
        profilephoto = view.findViewById(R.id.profilephoto);
        profilephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),SignupActivity.class);
                startActivity(intent);
            }
        });
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Profile", Toast.LENGTH_SHORT).show();
            }
        });
        ordersbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Orders", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(),SellerOrders.class);
                startActivity(intent);
            }
        });
        payments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Profile", Toast.LENGTH_SHORT).show();
            }
        });
        productsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Products", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(),SellerProducts.class);
                startActivity(intent);
            }
        });
        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Upload", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(),Wishlist.class);
                intent.putExtra("sellerid",currentUser.getUid());
                startActivity(intent);
            }
        });
        signoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Sign Out", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();

            }
        });



        return view;
    }
}