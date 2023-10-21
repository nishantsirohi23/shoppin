package to.nishant.shoppin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import to.nishant.shoppin.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.HashMap;

public class SignupActivity extends AppCompatActivity {
    EditText signupname,signuppass,signupemail;
    String name,pass,email;
    FirebaseAuth mAuth;
    CardView signupbtn;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_signup);
        signupemail = findViewById(R.id.signupemailtext);
        signupname = findViewById(R.id.signupnametext);
        signuppass = findViewById(R.id.signupasstext);
        mAuth = FirebaseAuth.getInstance();
        name = signupname.getText().toString();
        signupbtn = findViewById(R.id.signupbtn);
        pass = signuppass.getText().toString();
        email = signupemail.getText().toString();


        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_SHORT).show();
                name = signupname.getText().toString();
                pass = signuppass.getText().toString();
                email = signupemail.getText().toString();
                closeKeyboard();
                createUser();
            }
        });
    }
    private void createUser() {
        if (email.isEmpty()) {
            signupemail.setError("This Field cannnot be empty");
        } else if (pass.isEmpty()) {
            signuppass.setError("This Field is empty");

        }
        else if (name.isEmpty()) {
            signupname.setError("This Field is empty");

        }
        else {
            mAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                String userUid = user.getUid();
                                LayoutInflater inflater = (LayoutInflater)
                                        getSystemService(LAYOUT_INFLATER_SERVICE);
                                View popupView = inflater.inflate(R.layout.activity_sigunorloginpopup, null);

                                // create the popup window
                                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                                boolean focusable = true; // lets taps outside the popup also dismiss it
                                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                                // show the popup window
                                // which view you pass in doesn't matter, it is only used for the window tolken
                                popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
                                popupWindow.setBackgroundDrawable(ContextCompat.getDrawable(SignupActivity.this,R.drawable.registration_card_back));
                                // dismiss the popup window when touched
                                popupWindow.getContentView().findViewById(R.id.cardbtngotohome).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(SignupActivity.this,Home.class);
                                        startActivity(intent);
                                    }
                                });

                                FirebaseInstanceId.getInstance().getInstanceId()
                                        .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                                if (!task.isSuccessful()) {
                                                    Log.w("tokenfailed", "getInstanceId failed", task.getException());
                                                    return;
                                                }

                                                // Get new Instance ID token
                                                String token = task.getResult().getToken();
                                                reference = FirebaseDatabase.getInstance().getReference("users").child(userUid);
                                                HashMap<String, Object> hashMap = new HashMap<>();
                                                hashMap.put("name",name);
                                                hashMap.put("email",email);
                                                hashMap.put("pass",pass);
                                                hashMap.put("userid",userUid);
                                                hashMap.put("tokenid",token);
                                                reference.setValue(hashMap);
                                            }
                                        });



                            }
                            else {
                                // If sign in fails, display a message to the user.

                            }
                        }
                    });
        }

    }

    private void closeKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null){
            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

}