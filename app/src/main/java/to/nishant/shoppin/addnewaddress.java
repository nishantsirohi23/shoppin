package to.nishant.shoppin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.EditText;

import to.nishant.shoppin.R;
import com.google.firebase.auth.FirebaseAuth;

public class addnewaddress extends AppCompatActivity {
    private EditText mTitle, mMessage,sendtoken;
    FirebaseAuth mAuth;
    private static String serverKey = "AAAABtZIeNk:APA91bFJ5W4PElXwapgSVw6zNp7mkX_YRTKj7oRPDMTNddKNVpHc19A7Gevcx6xkwP-Gzv_CaatufrVTwShiA2typtP0lEmNIxKqaqBQrG7uCvoTUry2DXR9_7t11fvs3FMmQCc_FeRD";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnewaddress);
        sendtoken = findViewById(R.id.sendtoken);

        FCMSend.SetServerKey(serverKey);







        mTitle = findViewById(R.id.mTitle);
        mMessage = findViewById(R.id.mMessage);




        findViewById(R.id.mSend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = mTitle.getText().toString().trim();
                String message = mMessage.getText().toString().trim();
                String toDeviceToken = sendtoken.getText().toString();
                if (!title.equals("") && !message.equals("")) {

                }
            }
        });

    }


}