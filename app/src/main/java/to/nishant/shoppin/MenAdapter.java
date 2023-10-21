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

public class MenAdapter extends RecyclerView.Adapter<MenAdapter.MyViewHolder> {
    Context context;
    ArrayList<MenClass> list;



    public MenAdapter(Context context, ArrayList<MenClass> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.menlist_item,parent,false);
        return new MyViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MenClass product = list.get(position);
        holder.name.setText(product.getName());
        Glide.with(context.getApplicationContext())
                .load(product.getImageurl())
                .skipMemoryCache(false)
                .thumbnail( 0.6f )
                .into(holder.imageView);

        holder.categclasscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MainProductFragment(product.getName(), product.getType(), product.getSearchclass(),"none")).addToBackStack(null).commit();
            }
        });






    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class MyViewHolder extends  RecyclerView.ViewHolder{
        CardView categclasscard;
        TextView name;
        CircleImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            categclasscard = itemView.findViewById(R.id.mencategcard);
            name = itemView.findViewById(R.id.mencategname);
            imageView = itemView.findViewById(R.id.mencategimage);


        }
    }

}

