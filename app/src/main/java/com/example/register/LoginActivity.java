package com.example.register;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.cafe.MainActivity;
import com.example.cafe.R;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText login_email, login_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        login_email = findViewById( R.id.login_email );
        login_password = findViewById( R.id.login_password );

        Button join_button = findViewById(R.id.join_button);
        join_button.setOnClickListener(view -> {
            Intent joinintent = new Intent( LoginActivity.this, RegisterActivity.class );
            startActivity( joinintent );
        });


        Button login_button = findViewById(R.id.login_button);
        login_button.setOnClickListener(view -> {
            String UserEmail = login_email.getText().toString();
            String UserPwd = login_password.getText().toString();

            Response.Listener<String> responseListener = response -> {
                try {
                    JSONObject jsonObject = new JSONObject( response );
                    boolean success = jsonObject.getBoolean( "success" );

                    if(success) {//로그인 성공시

                        String UserEmail1 = jsonObject.getString( "UserEmail" );
                        String UserPwd1 = jsonObject.getString( "UserPwd" );
                        String UserName = jsonObject.getString( "UserName" );

                        Toast.makeText( getApplicationContext(), String.format("%s님 환영합니다.", UserName), Toast.LENGTH_SHORT ).show();
                        Intent intent = new Intent( LoginActivity.this,  MainActivity.class );

                        intent.putExtra( "UserEmail", UserEmail1);
                        intent.putExtra( "UserPwd", UserPwd1);
                        intent.putExtra( "UserName", UserName );

                        startActivity( intent );

                    } else {//로그인 실패시
                        Toast.makeText( getApplicationContext(), "로그인에 실패하셨습니다.", Toast.LENGTH_SHORT ).show();
                        return;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            };
            LoginRequest loginRequest = new LoginRequest( UserEmail, UserPwd, responseListener );
            RequestQueue queue = Volley.newRequestQueue( LoginActivity.this );
            queue.add( loginRequest );

        });
    }
}
