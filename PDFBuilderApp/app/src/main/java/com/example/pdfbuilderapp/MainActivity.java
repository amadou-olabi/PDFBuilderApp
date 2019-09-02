package com.example.pdfbuilderapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /*
     * Global Variables
     * */
    Switch aSwitch1;
    Switch aSwitch2;
    Switch aSwitch3;
    Switch aSwitch4;
    Switch aSwitch5;
    Switch aSwitch6;

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;

    Button imageButtonOK;
    ImageButton imageButtonCANCEL;

    EditText editTextDocDescription;

    Bitmap bitmap;
    ArrayList<Bitmap> bitmapArrayList;
    int theChosenIndex;
    int requestCodeSend;
    boolean imageChosenFlag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        listener();
    }

    private void init() {

        theChosenIndex = 0;
        imageChosenFlag = false;

        aSwitch1 = findViewById(R.id.switch1);
        aSwitch2 = findViewById(R.id.switch2);
        aSwitch3 = findViewById(R.id.switch3);
        aSwitch4 = findViewById(R.id.switch4);
        aSwitch5 = findViewById(R.id.switch5);
        aSwitch6 = findViewById(R.id.switch6);

        aSwitch2.setEnabled(false);
        aSwitch3.setEnabled(false);
        aSwitch4.setEnabled(false);
        aSwitch5.setEnabled(false);
        aSwitch6.setEnabled(false);

        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);

        imageButtonCANCEL = findViewById(R.id.imageButtonCancel);
        imageButtonOK = findViewById(R.id.buttonOK);

        editTextDocDescription = findViewById(R.id.editText_DocDescription);

        imageButtonOK.setEnabled(false);
        editTextDocDescription.setEnabled(false);
    }

    private void listener() {
        aSwitch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    theChosenIndex = 1;
                    pickImage(compoundButton.getRootView());
                    aSwitch2.setEnabled(true);
                } else {
                    theChosenIndex = 0;
                    aSwitch2.setEnabled(false);
                    imageButtonOK.setEnabled(false);
                    editTextDocDescription.setEnabled(false);
                    bitmapArrayList = null;
                    imageChosenFlag = false;
                }
            }
        });

        aSwitch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    theChosenIndex = 2;
                    pickImage(compoundButton.getRootView());
                    aSwitch3.setEnabled(true);
                } else {
                    theChosenIndex = 0;
                    aSwitch3.setEnabled(false);
                    if (!((bitmapArrayList == null) || bitmapArrayList.isEmpty())){
                        bitmapArrayList.remove(1);
                    }
                }
            }
        });

        aSwitch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    theChosenIndex = 3;
                    pickImage(compoundButton.getRootView());
                    aSwitch4.setEnabled(true);
                } else {
                    theChosenIndex = 0;
                    aSwitch4.setEnabled(false);
                    if (!((bitmapArrayList == null) || bitmapArrayList.isEmpty())){
                        bitmapArrayList.remove(2);
                    }
                }
            }
        });

        aSwitch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    theChosenIndex = 4;
                    pickImage(compoundButton.getRootView());
                    aSwitch5.setEnabled(true);
                } else {
                    theChosenIndex = 0;
                    aSwitch5.setEnabled(false);
                    if (!((bitmapArrayList == null) || bitmapArrayList.isEmpty())){
                        bitmapArrayList.remove(3);
                    }
                }
            }
        });

        aSwitch5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    theChosenIndex = 5;
                    pickImage(compoundButton.getRootView());
                    aSwitch6.setEnabled(true);
                } else {
                    theChosenIndex = 0;
                    aSwitch6.setEnabled(false);if (!((bitmapArrayList == null) || bitmapArrayList.isEmpty())){
                        bitmapArrayList.remove(4);
                    }
                }
            }
        });

        aSwitch6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    theChosenIndex = 6;
                    pickImage(compoundButton.getRootView());
                } else {
                    theChosenIndex = 0;
                    if (!((bitmapArrayList == null) || bitmapArrayList.isEmpty())){
                        bitmapArrayList.remove(5);
                    }
                }
            }
        });


        imageButtonCANCEL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                theChosenIndex = 0;
                aSwitch1.setChecked(false);
                aSwitch2.setChecked(false);
                aSwitch3.setChecked(false);
                aSwitch4.setChecked(false);
                aSwitch5.setChecked(false);
                aSwitch6.setChecked(false);
                imageView1.setImageResource(R.mipmap.ic_file_round);
                imageView2.setImageResource(R.mipmap.ic_file_round);
                imageView3.setImageResource(R.mipmap.ic_file_round);
                imageView4.setImageResource(R.mipmap.ic_file_round);
                imageView5.setImageResource(R.mipmap.ic_file_round);
                imageView6.setImageResource(R.mipmap.ic_file_round);

                imageButtonOK.setEnabled(false);

                editTextDocDescription.setText("");
                editTextDocDescription.setEnabled(false);

                Toast.makeText(MainActivity.this, getString(R.string.cancel_operation), Toast.LENGTH_SHORT).show();
            }
        });
        imageButtonOK.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, getString(R.string.compile_document), Toast.LENGTH_SHORT).show();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (!checkIfAlreadyHavePermission()) {
                        requestForSpecificPermission();
                    }
//                    PdfDocBuilder.createPDF(view.getContext(), editTextDocDescription.getText().toString());
//                    PdfDocBuilder.createPDFBitmap(view.getContext(),bitmap);
                    PdfDocBuilder.createPDFBitmapArray(view.getContext(),bitmapArrayList, editTextDocDescription.getText().toString());
//                    imageButtonOK.setVisibility(View.GONE);
//                    imageButtonOK.setVisibility(View.VISIBLE);
                } else Toast.makeText(MainActivity.this, "SDK is outdated !!!", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void pickImage(View v) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            try {
                Uri uri = (data != null ? data.getData() : null);
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    if(!imageChosenFlag){
                        bitmapArrayList = new ArrayList<>();
                        imageChosenFlag = true;
                    }
                    Bitmap bitmapResized = ResizeImageView.resizeBitmap(bitmap, 108, 108, true);
                    RoundedBitmapDrawable roundedBitmap = ResizeImageView.getRoundedImageDrawable(this, bitmapResized);
                    switch (theChosenIndex) {
                        case 1:
                            imageView1.setImageDrawable(roundedBitmap);
                            break;
                        case 2:
                            imageView2.setImageDrawable(roundedBitmap);
                            break;
                        case 3:
                            imageView3.setImageDrawable(roundedBitmap);
                            break;
                        case 4:
                            imageView4.setImageDrawable(roundedBitmap);
                            break;
                        case 5:
                            imageView5.setImageDrawable(roundedBitmap);
                            break;
                        case 6:
                            imageView6.setImageDrawable(roundedBitmap);
                            break;
                    }
                    bitmapArrayList.add(theChosenIndex-1,bitmap);
                    imageButtonOK.setEnabled(true);
                    editTextDocDescription.setEnabled(true);
                    theChosenIndex = 0;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean checkIfAlreadyHavePermission() {
        int result = this.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        requestCodeSend = result;
        return result == PackageManager.PERMISSION_GRANTED;
    }

    @TargetApi(Build.VERSION_CODES.Q)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void requestForSpecificPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.INTERNET,
                        Manifest.permission.ACCESS_MEDIA_LOCATION,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                101);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
