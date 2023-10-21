package to.nishant.shoppin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import to.nishant.shoppin.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;


public class CartFragment extends Fragment{
    DatabaseReference databaseReference;
    RecyclerView recyclerView1;
    ArrayList<CartClass> list;
    CartAdapter adapter;
    FirebaseAuth firebaseAuth;
    Button send;
    private static String serverKey = "AAAABtZIeNk:APA91bFJ5W4PElXwapgSVw6zNp7mkX_YRTKj7oRPDMTNddKNVpHc19A7Gevcx6xkwP-Gzv_CaatufrVTwShiA2typtP0lEmNIxKqaqBQrG7uCvoTUry2DXR9_7t11fvs3FMmQCc_FeRD";
    CardView btn_placeOrder;
    ProgressDialog progressDialog;
    TextView priceofproduct,noofproduct;
    int countProducts = 0;
    int totalprice = 0;
    String sellerid;
    ScrollView user;
    LinearLayout nouser;
    LinearLayout cartempty;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.show();
        cartempty = view.findViewById(R.id.cartemptylayout);
        user = view.findViewById(R.id.cartyesuser);
        nouser = view.findViewById(R.id.cartnouser);
        progressDialog.setContentView(R.layout.progress_loading);
        progressDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser == null) {
            progressDialog.dismiss();
            user.setVisibility(View.GONE);
            nouser.setVisibility(View.VISIBLE);
        }
        else {
            priceofproduct = view.findViewById(R.id.priceofproducts);
            noofproduct = view.findViewById(R.id.noofproducts);
            btn_placeOrder = view.findViewById(R.id.btn_placeOrder);
            databaseReference = FirebaseDatabase.getInstance().getReference("cart").child(firebaseUser.getUid());
            recyclerView1 = view.findViewById(R.id.cart_recycler_view);
            recyclerView1.setHasFixedSize(true);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            list = new ArrayList<>();
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
            recyclerView1.setLayoutManager(linearLayoutManager);
            registerForContextMenu(recyclerView1);
            databaseReference.orderByKey().addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    list.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        CartClass product = dataSnapshot.getValue(CartClass.class);
                        list.add(product);
                        progressDialog.dismiss();
                        countProducts = (int) snapshot.getChildrenCount();
                        noofproduct.setText(Integer.toString(countProducts));
                        totalprice += Integer.parseInt(dataSnapshot.child("productprice").getValue().toString());



                    }
                    priceofproduct.setText(Integer.toString(totalprice));
                    if (snapshot.getChildrenCount() != 0){

                        progressDialog.dismiss();
                    }
                    if (snapshot.getChildrenCount() == 0){
                        progressDialog.dismiss();
                    }
                    if (countProducts == 0){
                        recyclerView1.setVisibility(View.GONE);
                        user.setVisibility(View.GONE);
                        cartempty.setVisibility(View.VISIBLE);

                    }
                    adapter = new CartAdapter(getContext(),list);
                    adapter.notifyDataSetChanged();
                    recyclerView1.setAdapter(adapter);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }


            });
            btn_placeOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("MyOrders").child(firebaseUser.getUid());
                    databaseReference.orderByKey().addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                                CartClass product = dataSnapshot.getValue(CartClass.class);
                                dataSnapshot.child("sellerid").getValue().toString();
                                HashMap<String, Object> hashMap = new HashMap<>();
                                hashMap.put("orderid", product.getCartitem());
                                hashMap.put("productname", product.getProductname());
                                hashMap.put("productprice", product.getProductprice());
                                hashMap.put("productimageurl", product.getProductimageurl());
                                hashMap.put("sellerid", product.getSellerid());
                                sellerid = product.getSellerid().toString();
                                Date currentTime = Calendar.getInstance().getTime();
                                hashMap.put("time",currentTime);
                                reference.child(product.getCartitem()).setValue(hashMap);




                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }



                    });

                    DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("sellerOrders");
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                CartClass product = dataSnapshot.getValue(CartClass.class);
                                dataSnapshot.child("sellerid").getValue().toString();
                                String key = databaseReference.child("sellerOrders").push().getKey();
                                HashMap<String, Object> hashMap = new HashMap<>();
                                hashMap.put("orderid",key.substring(1));
                                hashMap.put("productid", product.getCartitem());
                                hashMap.put("productname", product.getProductname());
                                hashMap.put("productprice", product.getProductprice());
                                hashMap.put("productimageurl", product.getProductimageurl());
                                hashMap.put("sellerid", product.getSellerid());
                                String sellerid = product.getSellerid();
                                Date currentTime = Calendar.getInstance().getTime();
                                hashMap.put("time", currentTime);
                                reference1.child(key).setValue(hashMap);
                                sendnotification(product.sellerid);

                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    Intent intent = new Intent(getContext(), Checkout1.class);
                    startActivity(intent);

                }
            });
        }



        return view;

    }
    public void sendnotification(String sellerid){
        FCMSend.SetServerKey(serverKey);
        String result = FCMSend.pushNotification(
                sellerid,
                "Hurray",
                "Hurry you got a new Order, Check Out"
        );
        System.out.println("RESULT " + result);
    }





}