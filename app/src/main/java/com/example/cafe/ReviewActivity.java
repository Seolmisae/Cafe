package com.example.cafe;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.method.ScrollingMovementMethod;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;

public class ReviewActivity extends AppCompatActivity {
    String name;
    String loginID;
    String loginSort;
    float rate;
    EditText mEditTextreview, mEditTexttitle;
    TextView mTextViewResult;
    private AlertDialog dialog;
    String temp="";


    private final int GET_GALLERY_IMAGE = 200;
    private ImageView reviewImageview;

    private static final String IP_ADDRESS = "서버 Ip 주소";
    private static final String TAG = "phpreviewup";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        Intent intent = getIntent();
    /*    name = intent.getExtras().getString("name");
        loginID = intent.getExtras().getString("loginID");
        loginSort = intent.getExtras().getString("loginSort");*/


        mTextViewResult = findViewById(R.id.textView_result);
        mTextViewResult.setMovementMethod(new ScrollingMovementMethod());

        TextView reviewText= findViewById(R.id.reviewText);
        EditText titleEdit= findViewById(R.id.titleEdit);
        EditText reviewEdit= findViewById(R.id.reviewEdit);
        Button cancelButton= findViewById(R.id.cancelButton);
        Button okButton= findViewById(R.id.okButton);
        RatingBar reviewRating= findViewById(R.id.reviewRating);

        mEditTexttitle = findViewById(R.id.titleEdit);
        mEditTextreview = findViewById(R.id.reviewEdit);


        reviewImageview = findViewById(R.id.reviewImageview);
        //이미지 버튼 클릭 시
        reviewImageview.setOnClickListener(v -> {

            Intent intent1 = new Intent(Intent.ACTION_PICK);
            //intent.setType("image/*");
            intent1. setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(intent1, GET_GALLERY_IMAGE);
        });

        //취소버튼 클릭 시
        cancelButton.setOnClickListener(view -> {
            // Intent intent = new Intent( MainActivity.this,InformationActivity.class );
            startActivity( intent );
        });

        reviewRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rate = rating;
            }
        });

        //확인 버튼 클릭 시
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReviewActivity.InsertData task = new InsertData();
                task.execute("http://" + IP_ADDRESS + "/ImageUpload.php", name, Float.toString(rate), loginID, mEditTexttitle.getText().toString(), mEditTextreview.getText().toString(), temp);

                AlertDialog.Builder builder = new AlertDialog.Builder(ReviewActivity.this);
                dialog = builder.setMessage("리뷰작성이 완료되었습니다.")
                        .setNegativeButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })

                        .create();
                dialog.show();


                return;
            }
        });
    }

    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(ReviewActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            mTextViewResult.setText(result);
            Log.d(TAG, "POST response  - " + result);
        }


        @Override
        protected String doInBackground(String... params) {

            String name = params[1];
            String rate = params[2];
            String userID = params[3];
            String title = params[4];
            String review = params[5];
            String image = params[6];


            String serverURL = params[0];
            String postParameters = "name=" + name + "&rate=" + rate + "&userID=" + userID + "&title=" + title + "&review=" + review + "&image=" + image;


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes(StandardCharsets.UTF_8));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);

                return "Error: " + e.getMessage();
            }

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri selectedImageUri = data.getData();
            reviewImageview.setImageURI(selectedImageUri);

            Bitmap selPhoto = null;
            try {
                selPhoto = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            BitMapToString(selPhoto);
        }

    }
    public void BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);    //bitmap compress
        byte [] arr=baos.toByteArray();
        String image= Base64.encodeToString(arr, Base64.DEFAULT);

        try{
            temp= URLEncoder.encode(image,"utf-8");
        }catch (Exception e){
            Log.e("exception",e.toString());
        }
    }

}