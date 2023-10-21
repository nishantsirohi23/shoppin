package to.nishant.shoppin;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import to.nishant.shoppin.R;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder> {
    Context context;
    ArrayList<ProductClass> list;
    FirebaseAuth firebaseAuth;



    public ProductsAdapter(Context context, ArrayList<ProductClass> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.productlist_item,parent,false);
        return new MyViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ProductClass product = list.get(position);
        holder.textproductprice.setText(product.getProductprice());
        DisplayMetrics displayMetrics = new DisplayMetrics();
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        holder.textproductname.setText(product.getProductname());
        holder.textproductdiscount.setText(product.getProductdiscount());
        Glide.with(context.getApplicationContext())
                .load(product.getProductimageurl())
                .skipMemoryCache(true)
                .thumbnail( 0.4f )
                .centerCrop()
                .diskCacheStrategy( DiskCacheStrategy.ALL )
                .into(holder.imageofproduct);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Recent Searches").child(firebaseUser.getUid());
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("orderid", product.getProductid());
                hashMap.put("productname", product.getProductname());
                hashMap.put("productprice", product.getProductprice());
                hashMap.put("productimageurl", product.getProductimageurl());
                hashMap.put("productdiscount",product.getProductdiscount());
                hashMap.put("producttype",product.getProducttype());
                hashMap.put("productcompany",product.getProductcompany());
                hashMap.put("productdesc",product.getProductdesc());
                hashMap.put("sellerid", product.getSellerid());
                Date currentTime = Calendar.getInstance().getTime();
                hashMap.put("time",currentTime);
                reference.child(product.getProductid()).setValue(hashMap);

                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProductViewFragment(product.getProductname(),product.getProductprice(),product.getProductdesc(),product.getProductimageurl(),product.getProductid(),product.getSellerid(),product.getProductdiscount(),product.getProducttype(),product.getProductcompany())).addToBackStack(null).commit();

            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class MyViewHolder extends  RecyclerView.ViewHolder{
        ImageView imageofproduct;
        TextView textproductname,textproductprice,textproductdiscount;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textproductdiscount = itemView.findViewById(R.id.textproductdiscount);
            textproductname = itemView.findViewById(R.id.textproductname);
            textproductprice = itemView.findViewById(R.id.textproductprice);
            imageofproduct = itemView.findViewById(R.id.imageofproduct);

        }
    }

}
