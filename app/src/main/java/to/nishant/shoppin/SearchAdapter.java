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

import to.nishant.shoppin.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {
    Context context;
    ArrayList<SearchClass> list;
    FirebaseAuth firebaseAuth;



    public SearchAdapter(Context context, ArrayList<SearchClass> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.searchlist_item,parent,false);
        return new MyViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SearchClass product = list.get(position);
        holder.nameofsearch.setText(product.getName());

        holder.searchcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MainProductFragment(product.getName(), product.getType(), product.getSearchclass(),product.getCompany())).addToBackStack(null).commit();
            }
        });






    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class MyViewHolder extends  RecyclerView.ViewHolder{
        CardView searchcard;
        TextView nameofsearch;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            searchcard = itemView.findViewById(R.id.searchcard);
            nameofsearch = itemView.findViewById(R.id.nameofsearch);

        }
    }

}
