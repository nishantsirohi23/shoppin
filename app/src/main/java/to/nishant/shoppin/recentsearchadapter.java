package to.nishant.shoppin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import to.nishant.shoppin.R;


import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class recentsearchadapter extends RecyclerView.Adapter<recentsearchadapter.MyViewHolder>{
    Context context;
    ArrayList<recentsearchclass> list;
    int countProducts = 0;
    int totalprice = 0;


    public recentsearchadapter(Context context, ArrayList<recentsearchclass> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recent_search,parent,false);
        return new MyViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        recentsearchclass cart = list.get(position);
        Glide.with(context.getApplicationContext()).load(cart.getProductimageurl()).into(holder.cartimageview);
        holder.cartimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProductViewFragment(cart.getProductname(),cart.getProductprice(),cart.getProductdesc(),cart.getProductimageurl(),cart.getCartitem(),cart.getSellerid(),cart.getProductdiscount(),cart.getProducttype(),cart.getProductcompany())).addToBackStack(null).commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class MyViewHolder extends  RecyclerView.ViewHolder{
        CircleImageView cartimageview;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cartimageview = itemView.findViewById(R.id.recentsearchimage);

        }
    }

}

