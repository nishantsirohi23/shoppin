package to.nishant.shoppin;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import to.nishant.shoppin.R;

import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import de.hdodenhof.circleimageview.CircleImageView;

public class OnboardFragment extends Fragment {
    LinearLayout sneakers;

    CardView cardcompanypuma , cardcompanynike , cardcompanyvero , cardcompanytommy ,cardcompanylevis , cardcompanyverscae;
    CardView cardmenshirt,cardmenjacket,cardmen3,cardmen4;
    CardView cardwomen1,cardwomen2,cardwomen3,cardwomen4;
    CardView cardelectronics1,cardelectronics2,cardelectronics3,cardelectronics4;
    ImageButton btnsearch;
    TextView titlemainshop;
    CircleImageView profilephotomain;
    SliderView sliderView;
    CardView mens,women,elec,beauty;
    DrawerLayout mDrawerLayout;
    SNavigationDrawer sNavigationDrawer;
    Class fragmentClass;
    public static Fragment fragment;
    int[] images = {R.drawable.onlinesale,
            R.drawable.megasale,
            R.drawable.supersale,
            R.drawable.slideimage3

    };



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_onboard, container, false);
        mens = view.findViewById(R.id.cardmenswear);
        women = view.findViewById(R.id.cardwomenwear);
        elec = view.findViewById(R.id.cardelec);
        beauty = view.findViewById(R.id.cardbeauty);
        mens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MenFragment()).addToBackStack(null).commit();
            }
        });
        women.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MenFragment()).addToBackStack(null).commit();
            }
        });
        elec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MenFragment()).addToBackStack(null).commit();
            }
        });
        beauty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MenFragment()).addToBackStack(null).commit();
            }
        });
        titlemainshop = view.findViewById(R.id.titlemainshop);
        TextPaint paint = titlemainshop.getPaint();

        float widthmen = paint.measureText("Electronics");
        float width = paint.measureText("ShopHere");

        Shader textShader = new LinearGradient(0, 0, width, titlemainshop.getTextSize(),
                new int[]{
                        Color.parseColor("#F97C3C"),
                        Color.parseColor("#FDB54E"),
                        Color.parseColor("#64B678"),
                        Color.parseColor("#478AEA"),
                        Color.parseColor("#8446CC"),
                }, null, Shader.TileMode.CLAMP);
        titlemainshop.getPaint().setShader(textShader);

        profilephotomain = view.findViewById(R.id.profilephotomain);
        profilephotomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Checkout1.class);
                startActivity(intent);
            }
        });

        btnsearch = view.findViewById(R.id.btnsearch);
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SlidingRootNavBuilder(getActivity())
                        .withMenuLayout(R.layout.menu_left_layout)
                        .inject();
                //FragmentSearch fragment = new FragmentSearch();
                //getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            }
        });
        cardwomen1 = view.findViewById(R.id.cardwomen1);
        cardwomen2 = view.findViewById(R.id.cardwomen2);
        cardwomen3 = view.findViewById(R.id.cardwomen3);
        cardwomen4 = view.findViewById(R.id.cardwomen4);
        cardelectronics1 = view.findViewById(R.id.cardlaptop);
        cardelectronics2 = view.findViewById(R.id.cardwearables);
        cardelectronics3 = view.findViewById(R.id.cardheadphones);
        cardelectronics4 = view.findViewById(R.id.cardtablet);
        cardmen3 = view.findViewById(R.id.cardmen3);
        cardmen4 = view.findViewById(R.id.cardmen4);

        cardcompanypuma = view.findViewById(R.id.cardcompanypuma);
        cardcompanynike = view.findViewById(R.id.cardcompanynike);
        cardcompanyvero = view.findViewById(R.id.cardcompanyveromoda);
        cardcompanytommy = view.findViewById(R.id.cardcompanytommy);
        cardcompanylevis = view.findViewById(R.id.cardcompanylevis);
        cardcompanyverscae = view.findViewById(R.id.cardcompanyversace);
        cardmenshirt = view.findViewById(R.id.cardmenshirt);
        cardmenjacket = view.findViewById(R.id.cardmenjacket);
        cardmenshirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MainProductFragment("Men Shirt","shirt", "menswear","none")).addToBackStack(null).commit();
            }
        });
        cardmenjacket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MainProductFragment("Men Jakets","jacket", "menswear","none")).addToBackStack(null).commit();
            }
        });
        cardcompanyverscae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MainProductFragment("Versace","none", "none","versace")).addToBackStack(null).commit();

            }
        });
        cardcompanypuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MainProductFragment("Puma","none", "none","puma")).addToBackStack(null).commit();
            }
        });
        cardcompanyvero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MainProductFragment("Vero Moda","none", "none","vero moda")).addToBackStack(null).commit();
            }
        });
        cardcompanynike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MainProductFragment("Nike","none", "none","nike")).addToBackStack(null).commit();
            }
        });
        cardcompanytommy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MainProductFragment("Tommy Hilfiger","none", "none","tommyhilfiger")).addToBackStack(null).commit();
            }
        });
        cardcompanylevis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MainProductFragment("Levi's","none", "none","levis")).addToBackStack(null).commit();
            }
        });

        cardwomen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MainProductFragment("Men Shirt","shirt", "menswear","none")).addToBackStack(null).commit();
            }
        });
        cardwomen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MainProductFragment("Men Shirt","shirt", "menswear","none")).addToBackStack(null).commit();
            }
        });
        cardwomen3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MainProductFragment("Men Shirt","shirt", "menswear","none")).addToBackStack(null).commit();
            }
        });
        cardwomen4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MainProductFragment("Men Shirt","shirt", "menswear","none")).addToBackStack(null).commit();
            }
        });
        cardmen3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MainProductFragment("Men Shirt","shirt", "menswear","none")).addToBackStack(null).commit();
            }
        });
        cardmen4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MainProductFragment("Men Shirt","shirt", "menswear","none")).addToBackStack(null).commit();
            }
        });
        sliderView = view.findViewById(R.id.image_slider);
        SliderAdapter sliderAdapter = new SliderAdapter(images);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();





        return view;
    }
}