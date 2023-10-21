package to.nishant.shoppin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class youmaylike extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    likeadapter adapter;
    ArrayList<ProductClass> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youmaylike);
        databaseReference = FirebaseDatabase.getInstance().getReference("Products");
        recyclerView = findViewById(R.id.like_recycler_view);
        recyclerView.setHasFixedSize(true);
        list = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        int count=0;
        while (count < 5){
            databaseReference.orderByValue().addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        ProductClass product = dataSnapshot.getValue(ProductClass.class);
                        list.add(product);



                    }
                    adapter = new likeadapter(getApplicationContext(),list);
                    recyclerView.setAdapter(adapter);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }

            });
            count++;
        }
    }
}