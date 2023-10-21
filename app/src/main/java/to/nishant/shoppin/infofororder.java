package to.nishant.shoppin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import to.nishant.shoppin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class infofororder extends AppCompatActivity {
    String costumerid,infoname,infodel,infoadd;
    DatabaseReference databaseReference;
    EditText textname,textadd,textdel;
    Button btn_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infofororder);
        textname = findViewById(R.id.infoname);
        textadd = findViewById(R.id.infoadd);
        textdel = findViewById(R.id.infodel);
        btn_confirm =  findViewById(R.id.btn_confirm);
        costumerid = getIntent().getStringExtra("userid");
        databaseReference = FirebaseDatabase.getInstance().getReference("Orders").child(costumerid);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoname = textname.getText().toString();
                infoadd = textadd.getText().toString();
                infodel = textdel.getText().toString();

                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("cart").child(costumerid);
                reference1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                            DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("MyOrders").child(costumerid);
                            List<String> cartlistitems =new ArrayList<String>();
                            cartlistitems.add(dataSnapshot.getKey());
                            for(String values:cartlistitems){
                                HashMap<String, Object> hashMap = new HashMap<>();
                                hashMap.put("costumername",infoname);
                                hashMap.put("costumeradd",infoadd);
                                databaseReference1.child(values).updateChildren(hashMap);
                            }



                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

            }
        });


    }
}