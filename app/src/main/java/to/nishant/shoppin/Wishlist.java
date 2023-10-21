package to.nishant.shoppin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import to.nishant.shoppin.R;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class Wishlist extends AppCompatActivity {
    Button getimage,btnuplaodimage;
    private StorageReference reference = FirebaseStorage.getInstance().getReference();
    DatabaseReference databaseReference;
    ImageView imageView;
    Uri imageUri;
    EditText productname,productdesc,productprice,productdiscount,producttype,productclass,productcompany;
    String sellerid;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);
        getimage = findViewById(R.id.getimage);
        btnuplaodimage = findViewById(R.id.uploadimage);
        imageView = findViewById(R.id.productimageupload);
        productdesc = findViewById(R.id.productdescription);
        productname = findViewById(R.id.productname);
        productprice = findViewById(R.id.productprice);
        productdiscount = findViewById(R.id.productdiscount);
        producttype = findViewById(R.id.producttype);
        productclass = findViewById(R.id.productclass);
        productcompany = findViewById(R.id.productcompany);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        sellerid = firebaseUser.getUid();
        getimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent , 2);

            }
        });
        btnuplaodimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Wishlist.this,"clicked",Toast.LENGTH_SHORT).show();
                imageView.setImageURI(imageUri);
                uploadToFirebase(imageUri);

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode ==2 && resultCode == RESULT_OK && data != null){
            Toast.makeText(Wishlist.this,"choose",Toast.LENGTH_SHORT).show();
            imageUri = data.getData();
            imageView.setImageURI(imageUri);



        }
    }
    private void uploadToFirebase(Uri uri){

        final StorageReference fileRef = reference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Toast.makeText(getApplicationContext(),"uploaded success",Toast.LENGTH_SHORT).show();
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                        String key = databaseReference.child("Products").push().getKey();
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("productname",productname.getText().toString());
                        hashMap.put("productdesc",productdesc.getText().toString());
                        hashMap.put("productdiscount",productdiscount.getText().toString());
                        hashMap.put("productprice",productprice.getText().toString());
                        hashMap.put("productclass",productclass.getText().toString());
                        hashMap.put("producttype",producttype.getText().toString());
                        hashMap.put("productcompany",productcompany.getText().toString());
                        hashMap.put("productimageurl",uri.toString());
                        hashMap.put("sellerid",sellerid);
                        hashMap.put("productid",key.substring(1));
                        databaseReference.child("Products").child(key).setValue(hashMap);
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Wishlist.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getFileExtension(Uri mUri){

        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));

    }
}