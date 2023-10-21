package to.nishant.shoppin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import to.nishant.shoppin.R;


import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SellerProductsAdapter extends RecyclerView.Adapter<SellerProductsAdapter.MyViewHolder>{
    Context context;
    ArrayList<SellerProductsClass> list;


    public SellerProductsAdapter(Context context, ArrayList<SellerProductsClass> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.sellerproductlist_item,parent,false);
        return new MyViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SellerProductsClass cart = list.get(position);

        holder.productname.setText(cart.getProductname());
        holder.productprice.setText(cart.getProductprice());
        holder.productdiscount.setText(cart.getProductdiscount());
        Glide.with(context.getApplicationContext()).load(cart.getProductimageurl()).into(holder.productimage);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView productname,productprice,productdiscount;
        CircleImageView productimage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            productimage = itemView.findViewById(R.id.sellerproductimage);
            productname = itemView.findViewById(R.id.sellerproductname);
            productprice = itemView.findViewById(R.id.sellerproductprice);
            productdiscount = itemView.findViewById(R.id.sellerproductdiscount);



        }
    }

}
