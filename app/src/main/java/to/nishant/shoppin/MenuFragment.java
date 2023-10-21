package to.nishant.shoppin;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import to.nishant.shoppin.R;



public class MenuFragment extends Fragment {
    LinearLayout orders,wishlist;
    RelativeLayout account,buyagain,help;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_menu, container, false);
        orders = view.findViewById(R.id.btnorders);
        wishlist = view.findViewById(R.id.btnwishlist);
        account = view.findViewById(R.id.btnaccount);
        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderFragment fragment = new OrderFragment();
                FragmentManager manager = ( (AppCompatActivity) getContext() ).getSupportFragmentManager ();
                FragmentTransaction ft = manager.beginTransaction ();

                if (manager.findFragmentByTag ( "account" ) == null) { // No fragment in backStack with same tag..
                    ft.add ( R.id.fragment_container, fragment, "account" );
                    ft.addToBackStack ( "account" );
                    ft.commit ();
                }
                else {
                    ft.show ( manager.findFragmentByTag ( "account" ) ).commit ();
                }
            }
        });
        wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentWishlist fragment = new FragmentWishlist();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            }
        });
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileFragment fragment = new ProfileFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            }
        });
        return view;
    }
}