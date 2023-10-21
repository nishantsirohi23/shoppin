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

public class SellerOrders extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    SellerOrdersAdapter adapter;
    ArrayList<SellerOrdersClass> list;
    FirebaseAuth myAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_orders);
        recyclerView = findViewById(R.id.sellerorders_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SellerOrders.this);
        list = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(SellerOrders.this,2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        myAuth = FirebaseAuth.getInstance();
        FirebaseUser sellerorder = myAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("sellerOrders");
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String sellerid = dataSnapshot.child("sellerid").getValue().toString();
                    if (sellerid.equals(sellerorder.getUid())){
                        SellerOrdersClass product = dataSnapshot.getValue(SellerOrdersClass.class);
                        list.add(product);


                    }
                }
                adapter = new SellerOrdersAdapter(SellerOrders.this,list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
    }
}