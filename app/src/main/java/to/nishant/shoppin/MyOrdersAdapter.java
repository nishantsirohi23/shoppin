package to.nishant.shoppin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import to.nishant.shoppin.R;


import java.util.ArrayList;

public class MyOrdersAdapter extends RecyclerView.Adapter<MyOrdersAdapter.MyViewHolder> {
    Context context;
    ArrayList<MyOrdersClass> list;


    public MyOrdersAdapter(Context context, ArrayList<MyOrdersClass> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.orderslist_item,parent,false);
        return new MyViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MyOrdersClass product = list.get(position);
        holder.ordername.setText(product.getProductname());
        holder.orderprice.setText(product.getProductprice());
        Glide.with(context.getApplicationContext()).load(product.getProductimageurl()).into(holder.orderimage);
        holder.ordercard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context.getApplicationContext(), "clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context.getApplicationContext(),Trackorder.class);
                intent.putExtra("ordername",product.getProductname());
                intent.putExtra("orderprice",product.getProductprice());
                intent.putExtra("orderimageurl",product.getProductimageurl());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class MyViewHolder extends  RecyclerView.ViewHolder{
        ImageView orderimage;
        TextView ordername,orderprice;
        CardView ordercard;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            orderimage = itemView.findViewById(R.id.orderlistimage);
            ordername = itemView.findViewById(R.id.orderlistname);
            orderprice = itemView.findViewById(R.id.orderlistprice);
            ordercard = itemView.findViewById(R.id.ordercard);



        }
    }

}

