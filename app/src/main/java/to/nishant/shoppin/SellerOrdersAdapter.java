package to.nishant.shoppin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import to.nishant.shoppin.R;


import java.util.ArrayList;

public class SellerOrdersAdapter extends RecyclerView.Adapter<SellerOrdersAdapter.MyViewHolder>{
    Context context;
    ArrayList<SellerOrdersClass> list;


    public SellerOrdersAdapter(Context context, ArrayList<SellerOrdersClass> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.sellerordersitem_list,parent,false);
        return new MyViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SellerOrdersClass orders = list.get(position);
        holder.ordername.setText(orders.getProductname());
        holder.orderprice.setText(orders.getProductprice());
        Glide.with(context.getApplicationContext()).load(orders.getProductimageurl()).into(holder.orderimage);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class MyViewHolder extends  RecyclerView.ViewHolder{
        ImageView orderimage;
        TextView ordername,orderprice;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            orderimage = itemView.findViewById(R.id.sellerorderimage);
            ordername = itemView.findViewById(R.id.sellerordername);
            orderprice = itemView.findViewById(R.id.sellerorderprice);
        }
    }

}
