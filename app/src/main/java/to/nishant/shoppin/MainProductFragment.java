package to.nishant.shoppin;

import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

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


public class  MainProductFragment extends Fragment {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    ProductsAdapter adapter;
    ArrayList<ProductClass> list;
    TextView titlemainbar;
    String name,searchclass,type,company;

    public MainProductFragment() {

    }

    public MainProductFragment(String productname ,String producttype,String productclass,String productcompany) {
        this.name=productname;
        this.type = producttype;
        this.searchclass = productclass;
        this.company = productcompany;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragmentmainproduct, container, false);
        titlemainbar = view.findViewById(R.id.producttypetitle);
        Shader textShader=new LinearGradient(0, 50, 0, 20,
                new int[]{R.color.startcolor,R.color.endtext},
                new float[]{0, 1}, Shader.TileMode.CLAMP);
        titlemainbar.getPaint().setShader(textShader);
        databaseReference = FirebaseDatabase.getInstance().getReference("Products");
        recyclerView = view.findViewById(R.id.products_recycler_view);
        recyclerView.setHasFixedSize(true);
        list = new ArrayList<>();
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        RecyclerViewMargin decoration = new RecyclerViewMargin(100, 2);
        recyclerView.addItemDecoration(decoration);

        databaseReference.orderByValue().addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ProductClass product = dataSnapshot.getValue(ProductClass.class);
                    String dataproductclass = dataSnapshot.child("productclass").getValue().toString();
                    String dataproductcompany = dataSnapshot.child("productcompany").getValue().toString();
                    String dataproducttype = dataSnapshot.child("producttype").getValue().toString();

                    if (searchclass.equals("none")){
                        if (dataproducttype.equals(type)){
                            list.add(product);
                            titlemainbar.setText(name);
                        }
                    }
                    if (type.equals("none")){
                        if (dataproductclass.equals(searchclass) && dataproductcompany.equals(company)){
                            list.add(product);
                            titlemainbar.setText(name);
                        }

                    }
                    if (company.equals("none")){
                        if (dataproducttype.equals(type) && dataproductclass.equals(searchclass)){
                            list.add(product);
                            titlemainbar.setText(name);
                        }
                    }
                    if (company.equals("none") && searchclass.equals("none")){
                        if (dataproducttype.equals(type)){
                            list.add(product);
                            titlemainbar.setText(name);
                        }
                    }
                    if (type.equals("none") && searchclass.equals("none")){
                        if (dataproductcompany.equals(company)){
                            list.add(product);
                            titlemainbar.setText(name);
                        }
                    }
                    if (dataproductclass.equals(searchclass) && dataproductcompany.equals(company) && dataproducttype.equals(type)){
                        list.add(product);
                        name.toUpperCase();
                        titlemainbar.setText(name);
                    }


                }
                adapter = new ProductsAdapter(getContext(),list);
                recyclerView.setAdapter(adapter);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        return view;
    }
}