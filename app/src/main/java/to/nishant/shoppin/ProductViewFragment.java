package to.nishant.shoppin;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import to.nishant.shoppin.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class ProductViewFragment extends Fragment {
    String name,price,imageurl,desc,product,seller,discount,company,type;
    TextView textproductprice,textproductname,textproductdiscount;
    ImageView imageproduct;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    ImageView btn_backview;
    RelativeLayout productsize2;
    LinearLayout choosesize;
    BottomSheetDialog sheetDialog;
    RelativeLayout dec,inc;
    TextView quantity;
    int value = 1;
    CardView btnaddtocart;
    public ProductViewFragment() {

    }

    public ProductViewFragment(String productname, String productprice,String productdes,String productimageurl ,String productid,String sellerid,String productdiscount,String producttype,String productcompany) {
        this.name=productname;
        this.price=productprice;
        this.imageurl=productimageurl;
        this.desc = productdes;
        this.product = productid;
        this.seller = sellerid;
        this.discount = productdiscount;
        this.type = producttype;
        this.company = productcompany;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_product_view, container, false);
        mAuth = FirebaseAuth.getInstance();
        btnaddtocart = view.findViewById(R.id.btnaddtocart);
        btn_backview = view.findViewById(R.id.btn_backview);
        dec = view.findViewById(R.id.decproduct);
        inc = view.findViewById(R.id.incproduct);
        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (value == 1){
                    Toast.makeText(getContext(), "Item number cannot be less than One", Toast.LENGTH_SHORT).show();
                }
                else
                    decrease();

            }
        });
        inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increase();

            }
        });
        quantity = view.findViewById(R.id.textquantity);
        choosesize = view.findViewById(R.id.choosesize);
        choosesize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sheetDialog = new BottomSheetDialog(getContext(),R.style.BottomSheetStyle);
                View view1 = LayoutInflater.from(getContext()).inflate(R.layout.bottomsheet_dialog,(LinearLayout)view.findViewById(R.id.sheet1));
                sheetDialog.setContentView(view1);
                sheetDialog.show();
            }
        });
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);


        btn_backview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity)getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MainProductFragment()).addToBackStack(null).commit();
            }
        });
        btnaddtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(getContext());
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        // yourMethod();
                        addtobag();
                    }
                }, 3000);

            }
        });
        textproductname = view.findViewById(R.id.view_productname);
        textproductprice = view.findViewById(R.id.view_productprice);
        imageproduct = view.findViewById(R.id.view_imageofproduct);
        textproductname.setText(name.toUpperCase());

        TextPaint paint = textproductname.getPaint();
        float width = paint.measureText("Tianjin, China");

        Shader textShader = new LinearGradient(0, 0, width, textproductname.getTextSize(),
                new int[]{
                        Color.parseColor("#F97C3C"),
                        Color.parseColor("#FDB54E"),
                        Color.parseColor("#64B678"),
                        Color.parseColor("#478AEA"),
                        Color.parseColor("#8446CC"),
                }, null, Shader.TileMode.CLAMP);
        textproductname.getPaint().setShader(textShader);
        textproductname.setText(name);
        textproductprice.setText(price);
        Glide.with(getContext()).load(imageurl).into(imageproduct);
        return view;
    }
    public void addtobag() {
        FirebaseUser user = mAuth.getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("cart").child(user.getUid());
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("cartitem", product);
        hashMap.put("productname", name);
        hashMap.put("productprice", price);
        hashMap.put("productimageurl", imageurl);
        hashMap.put("sellerid", seller);
        hashMap.put("productdiscount",discount);
        hashMap.put("producttype",type);
        hashMap.put("productcompany",company);
        hashMap.put("productdesc",desc);
        reference.child(product).setValue(hashMap);
        progressDialog.dismiss();
        //int width = WindowManager.LayoutParams.MATCH_PARENT;
        //View popupView = LayoutInflater.from(getActivity()).inflate(R.layout.activity_order_placed_card, null);
        //final PopupWindow popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        //popupWindow.showAsDropDown(popupView, 0, 0);

        //AppCompatActivity activity=(AppCompatActivity)getContext();
        //activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new CartFragment()).addToBackStack(null).commit();

    }
    public void decrease(){
        value--;
        quantity.setText(""+value);

    }
    public void increase(){
        value++;
        quantity.setText(""+value);
    }

}
