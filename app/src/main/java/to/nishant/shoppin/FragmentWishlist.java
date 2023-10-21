package to.nishant.shoppin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import to.nishant.shoppin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class FragmentWishlist extends Fragment {
    RecyclerView recyclerView;
    DatabaseReference databaseReferencemen,databaseReferencewomen;
    MenAdapter adapter;
    ArrayList<MenClass> list;
    SliderView sliderView;
    TextView nameofclass;
    RelativeLayout menrelative;
    int[] images = {R.drawable.menslider1,
            R.drawable.menslider2,
            R.drawable.menslider3,
    };
    String name;
    public FragmentWishlist() {

    }

    public FragmentWishlist(String productname) {
        this.name=productname;


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_wishlist, container, false);
        menrelative = view.findViewById(R.id.menrelative);
        nameofclass = view.findViewById(R.id.text1men);
        sliderView = view.findViewById(R.id.men_image_slider);
        SliderAdapter sliderAdapter = new SliderAdapter(images);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();
        databaseReferencemen = FirebaseDatabase.getInstance().getReference("MenCateg");
        databaseReferencewomen = FirebaseDatabase.getInstance().getReference("WomenCateg");
        recyclerView = view.findViewById(R.id.recycler_class_categ);
        recyclerView.setHasFixedSize(true);
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));


        if (name == "men"){
            nameofclass.setText("Men's");
            final int sdk = android.os.Build.VERSION.SDK_INT;
            if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                menrelative.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.menmain) );
            } else {
                menrelative.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.menmain));
            }
            databaseReferencemen.orderByValue().addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        MenClass product = dataSnapshot.getValue(MenClass.class);
                        list.add(product);


                    }
                    adapter = new MenAdapter(getContext(),list);
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }

            });
        }
        if (name == "women"){
            nameofclass.setText("Women's");
            final int sdk = android.os.Build.VERSION.SDK_INT;
            if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                menrelative.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.womenmain) );
            } else {
                menrelative.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.womenmain));
            }
            databaseReferencewomen.orderByValue().addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        MenClass product = dataSnapshot.getValue(MenClass.class);
                        list.add(product);


                    }
                    adapter = new MenAdapter(getContext(),list);
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }

            });
        }



        return view;
    }
}