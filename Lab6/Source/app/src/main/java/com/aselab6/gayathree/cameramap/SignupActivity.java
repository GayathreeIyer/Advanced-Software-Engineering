package com.aselab6.gayathree.cameramap;

/**
 * Created by Gayathree on 9/30/2016.
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;


public class SignupActivity extends AppCompatActivity {

    Button button;
    public int code=0;
    ImageView iv;
    Boolean status=false;
    Bitmap bit;
    ByteArrayOutputStream barray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        barray=new ByteArrayOutputStream();
        button=(Button)findViewById(R.id.signUp);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpAction(v);
            }
        });

        iv=(ImageView)findViewById(R.id.pic);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign__up, menu);
        return true;
    }

    public void signUpAction(View view)
    {
        //Object o=profilePic.getDrawable();
        //Log.d("value",bs.toByteArray());
        Intent intent=new Intent(this, CameraMapActivity.class);
        //bm.compress(Bitmap.CompressFormat.PNG, 50,bs);
        intent.putExtra("ImageObject",bit);
        startActivity(intent);
    }

    public void choosePic(View view)
    {
        CharSequence[] textArray= new CharSequence[2];
        textArray[0]="Camera";
        textArray[1]="Gallery";
        AlertDialog.Builder builder=new AlertDialog.Builder(this).setTitle("Select from").setItems(textArray, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==0) {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, code);
                }
                else if(which==1)
                {
                    status=true;
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    // Start the Intent
                    startActivityForResult(galleryIntent, 2);

                }

            }

        });
        builder.create().show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in A0ndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Object o=requestCode;
        Log.d("request",o.toString());
        o=resultCode;
        Log.d("result",o.toString());
        if (requestCode == 0 && resultCode == RESULT_OK) {

            Bitmap photo = (Bitmap) data.getExtras().get("data");
            bit=photo;
            iv.setImageBitmap(photo);
            Log.d("CameraDemo", "Your picture has been saved");
        }
        else
        {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

// Get the cursor
            Cursor cs = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
// Move to first row
            cs.moveToFirst();

            int columnIndex = cs.getColumnIndex(filePathColumn[0]);
            String imgDecodableString = cs.getString(columnIndex);
            cs.close();
            Log.d("String", imgDecodableString);
            bit= BitmapFactory
                    .decodeFile(imgDecodableString);
// Set the Image in ImageView after decoding the String
            // profilePic.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));
            iv.setImageBitmap(BitmapFactory
                    .decodeFile(imgDecodableString));


        }
    }
}
