package com.smartconnect.unfyd_crm_app.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.smartconnect.unfyd_crm_app.Adapter.CustomList;
import com.smartconnect.unfyd_crm_app.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class DetailsActivity extends BaseActivity {
    TextView dateText;
    Calendar myCalendar;
    ImageView image,file,folder,enter,backicon;
    Bitmap thumbnail;
    File pic;
    EditText comments;
    ListView commentslist;
    CustomList adapter;
    private static final int FILE_SELECT_CODE = 0;

    ArrayList<String> list = new ArrayList<String>();

    protected static final int CAMERA_PIC_REQUEST = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        myCalendar = Calendar.getInstance();
        dateText = findViewById(R.id.date);
        image = findViewById(R.id.imageset);
        file = findViewById(R.id.fileset);
        folder = findViewById(R.id.pdfset);
        comments=findViewById(R.id.comments);
        enter=findViewById(R.id.enter);
        backicon=findViewById(R.id.backicon);
        backicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DetailsActivity.this,FirstActivity.class);
                startActivity(i);
            }
        });

        commentslist = findViewById(R.id.chatLayout);
        adapter = new CustomList(this,list);
        commentslist.setAdapter(adapter);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg) {
                list.add(comments.getText().toString());
                adapter.notifyDataSetChanged();
            }

        });

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        dateText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(DetailsActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        image.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , 0);

            }
        });
        file.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent fileintent = new Intent(Intent.ACTION_GET_CONTENT);
                fileintent.setType("file/*");
                startActivityForResult(fileintent,1);

            }
        });
        folder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent folderintent = new Intent(Intent.ACTION_GET_CONTENT);
                folderintent.setType("file/*");
                startActivityForResult(folderintent,2);
            }
        });



    }
    @Override
    public void onBackPressed()
    {

            finish();
    }
    private void updateLabel() {
        String myFormat = "dd MMM YYYY"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dateText.setText(sdf.format(myCalendar.getTime()));
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case 0:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    image.setImageURI(selectedImage);
                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                    Uri fileimage = imageReturnedIntent.getData();
                    file.setImageURI(fileimage);
                }
            case 2:
                if(resultCode==RESULT_OK){
                    Uri pdfimage = imageReturnedIntent.getData();
                    folder.setImageURI(pdfimage);
                }
                break;
        }
    }
}