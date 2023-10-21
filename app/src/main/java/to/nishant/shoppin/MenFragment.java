package to.nishant.shoppin;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import to.nishant.shoppin.R;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MenFragment extends Fragment {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    likeadapter adapter;
    ArrayList<ProductClass> list;
    String name;
    ViewPager viewPager;
    TextView item1,item2,item3,item4;
    String type = "all";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_men, container, false);
        item1 = view.findViewById(R.id.textView);
        item2 = view.findViewById(R.id.textView2);
        item3 = view.findViewById(R.id.textView3);
        item4 = view.findViewById(R.id.textView4);

        recyclerView = view.findViewById(R.id.categ_product_recycer);
        databaseReference = FirebaseDatabase.getInstance().getReference("Products");
        recyclerView.setHasFixedSize(true);
        list = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);


        databaseReference.orderByValue().addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String dataproducttype = dataSnapshot.child("producttype").getValue().toString();

                    ProductClass product = dataSnapshot.getValue(ProductClass.class);
                    list.add(product);


                }
                adapter = new likeadapter(getContext(),list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });
        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "tshirt";
                item1.setBackgroundColor(Color.parseColor("#ffc107"));
                item2.setBackgroundColor(Color.parseColor("#FFFFFF"));
                item3.setBackgroundColor(Color.parseColor("#FFFFFF"));
                item4.setBackgroundColor(Color.parseColor("#FFFFFF"));

                databaseReference.orderByValue().addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                            String dataproducttype = dataSnapshot.child("producttype").getValue().toString();
                            if (dataproducttype.equals(type)){
                                ProductClass product = dataSnapshot.getValue(ProductClass.class);
                                list.add(product);
                            }

                        }
                        adapter = new likeadapter(getContext(),list);
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }

                });

            }
        });
        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "jacket";
                item2.setBackgroundColor(Color.parseColor("#ffc107"));
                item1.setBackgroundColor(Color.parseColor("#FFFFFF"));
                item3.setBackgroundColor(Color.parseColor("#FFFFFF"));
                item4.setBackgroundColor(Color.parseColor("#FFFFFF"));
                list.clear();
                databaseReference.orderByValue().addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                            String dataproducttype = dataSnapshot.child("producttype").getValue().toString();
                            if (dataproducttype.equals(type)){
                                ProductClass product = dataSnapshot.getValue(ProductClass.class);
                                list.add(product);
                            }

                        }
                        adapter = new likeadapter(getContext(),list);
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }

                });

            }
        });
        item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "jeans";
                item3.setBackgroundColor(Color.parseColor("#ffc107"));
                item2.setBackgroundColor(Color.parseColor("#FFFFFF"));
                item1.setBackgroundColor(Color.parseColor("#FFFFFF"));
                item4.setBackgroundColor(Color.parseColor("#FFFFFF"));
                list.clear();
                databaseReference.orderByValue().addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                            String dataproducttype = dataSnapshot.child("producttype").getValue().toString();
                            if (dataproducttype.equals(type)){
                                ProductClass product = dataSnapshot.getValue(ProductClass.class);
                                list.add(product);
                            }

                        }
                        adapter = new likeadapter(getContext(),list);
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }

                });

            }
        });
        item4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "shirt";
                item4.setBackgroundColor(Color.parseColor("#ffc107"));
                item2.setBackgroundColor(Color.parseColor("#FFFFFF"));
                item3.setBackgroundColor(Color.parseColor("#FFFFFF"));
                item1.setBackgroundColor(Color.parseColor("#FFFFFF"));
                list.clear();
                databaseReference.orderByValue().addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                            String dataproducttype = dataSnapshot.child("producttype").getValue().toString();
                            if (dataproducttype.equals(type)){
                                ProductClass product = dataSnapshot.getValue(ProductClass.class);
                                list.add(product);
                            }

                        }
                        adapter = new likeadapter(getContext(),list);
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }

                });

            }
        });




        return view;
    }
}
