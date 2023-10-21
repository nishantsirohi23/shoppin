package to.nishant.shoppin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import to.nishant.shoppin.R;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class OrderFragment extends Fragment {
    DatabaseReference databaseReference;
    ArrayList<MyOrdersClass> list;
    RecyclerView recyclerView;
    FirebaseAuth firebaseAuth;
    LinearLayout nouser,yesuser,emptyorder;
    int countProducts = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_order, container, false);
        recyclerView = view.findViewById(R.id.myOrders_view);
        recyclerView.setHasFixedSize(true);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        nouser = view.findViewById(R.id.ordernouser);
        yesuser = view.findViewById(R.id.orderyesuser);
        emptyorder = view.findViewById(R.id.orderempty);
        if (firebaseUser == null){
            nouser.setVisibility(View.VISIBLE);
            yesuser.setVisibility(View.GONE);
        }
        else {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            list = new ArrayList<>();
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
            recyclerView.setLayoutManager(linearLayoutManager);
            databaseReference = FirebaseDatabase.getInstance().getReference("MyOrders").child(firebaseUser.getUid());
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        MyOrdersClass product = dataSnapshot.getValue(MyOrdersClass.class);
                        list.add(product);
                        countProducts = (int) snapshot.getChildrenCount();



                        if (snapshot.getChildrenCount() == 0){
                            recyclerView.setVisibility(View.GONE);
                            emptyorder.setVisibility(View.VISIBLE);
                            yesuser.setVisibility(View.GONE);
                        }

                        MyOrdersAdapter adapter;
                        adapter = new MyOrdersAdapter(getContext(),list);
                        recyclerView.setAdapter(adapter);
                    }
                    if (countProducts == 0){
                        recyclerView.setVisibility(View.GONE);
                        yesuser.setVisibility(View.GONE);
                        emptyorder.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }


        return view;
    }
}