package to.nishant.shoppin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import to.nishant.shoppin.R;


import java.util.ArrayList;

public class PlaceOrderAdapter extends RecyclerView.Adapter<PlaceOrderAdapter.MyViewHolder>{
    Context context;
    ArrayList<CartClass> list;


    public PlaceOrderAdapter(Context context, ArrayList<CartClass> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.placeorder_item,parent,false);
        return new MyViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CartClass cart = list.get(position);
        holder.placeordername.setText(cart.getProductname());
        holder.placeorderprice.setText(cart.getProductprice());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView placeordername,placeorderprice;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            placeordername = itemView.findViewById(R.id.placeordername);
            placeorderprice = itemView.findViewById(R.id.placeorderprice);

        }
    }

}

