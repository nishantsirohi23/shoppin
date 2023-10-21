package to.nishant.shoppin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import to.nishant.shoppin.R;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SellerProducts extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    SellerProductsAdapter adapter;
    ArrayList<SellerProductsClass> list;
    FirebaseAuth myAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_products);
        databaseReference = FirebaseDatabase.getInstance().getReference("Products");
        recyclerView = findViewById(R.id.sellerproducts_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SellerProducts.this);
        list = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(SellerProducts.this,2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        myAuth = FirebaseAuth.getInstance();
        FirebaseUser sellerproduct = myAuth.getCurrentUser();
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String sellerid = dataSnapshot.child("sellerid").getValue().toString();
                    if (sellerid.equals(sellerproduct.getUid())){
                        SellerProductsClass product = dataSnapshot.getValue(SellerProductsClass.class);
                        list.add(product);
                        }

                }

                adapter = new SellerProductsAdapter(SellerProducts.this,list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }
}