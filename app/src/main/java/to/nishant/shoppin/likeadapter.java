package to.nishant.shoppin;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import to.nishant.shoppin.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class likeadapter extends RecyclerView.Adapter<likeadapter.MyViewHolder> {
    Context context;
    ArrayList<ProductClass> list;




    public likeadapter(Context context, ArrayList<ProductClass> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.likelist_item,parent,false);
        return new MyViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ProductClass product = list.get(position);
        holder.likeprice.setText(product.getProductprice());
        holder.likename.setText(product.getProductname());
        holder.likedes.setText(product.getProductdesc());
        Glide.with(context.getApplicationContext())
                .load(product.getProductimageurl())
                .skipMemoryCache(false)
                .thumbnail( 0.4f )
                .into(holder.likeimage);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        CircleImageView likeimage;
        TextView likename,likeprice,likedes;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            likedes = itemView.findViewById(R.id.likedes);
            cardView = itemView.findViewById(R.id.cardgotoproduct);
            likename = itemView.findViewById(R.id.likename);
            likeprice = itemView.findViewById(R.id.likeprice);
            likeimage = itemView.findViewById(R.id.likeimage);
        }
    }

}

