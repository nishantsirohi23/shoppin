package to.nishant.shoppin;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import to.nishant.shoppin.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    Context context;
    ProgressDialog progressDialog;
    ArrayList<CartClass> list;
    int countProducts = 0;
    int totalprice = 0;


    public CartAdapter(Context context, ArrayList<CartClass> list) {
        this.context = context;
        this.list = list;

    }
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cartlist_item,parent,false);
        return new MyViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CartClass cart = list.get(position);
        holder.cartporductname.setText(cart.getProductname());
        holder.cartproductprice.setText(cart.getProductprice());
        Glide.with(context.getApplicationContext()).load(cart.getProductimageurl()).into(holder.cartimageview);
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(context);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_delete);
                progressDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        // yourMethod();
                        FirebaseAuth myAuth;
                        myAuth = FirebaseAuth.getInstance();
                        FirebaseUser user= myAuth.getCurrentUser();
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("cart").child(user.getUid());
                        reference.child(cart.getCartitem()).removeValue();
                        progressDialog.dismiss();

                    }
                }, 3000);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosition(holder.getPosition());
                return false;
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        CircleImageView cartimageview;
        TextView cartporductname,cartproductprice;
        LinearLayout btn_delete;
        LinearLayout cartfull,cartempty;
        RecyclerView recyclerView1;
        CardView carditemcard;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cartimageview = itemView.findViewById(R.id.cartlistimage);
            cartporductname = itemView.findViewById(R.id.cartlistname);
            cartproductprice = itemView.findViewById(R.id.cartlistprice);
            btn_delete = itemView.findViewById(R.id.btn_deletecart);
            cartempty = itemView.findViewById(R.id.cartemptylayout);
            recyclerView1 = itemView.findViewById(R.id.cart_recycler_view);
            carditemcard = itemView.findViewById(R.id.cartitemcard);

        }
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v,
                                        ContextMenu.ContextMenuInfo menuInfo) {
            //menuInfo is null

        }
    }


}
