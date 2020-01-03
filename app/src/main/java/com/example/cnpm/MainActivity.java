package com.example.cnpm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cnpm.model.APIClient;
import com.example.cnpm.model.ResultLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {
    EditText editEmail,editPass;
    Button btnLogin,btnReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editEmail = (EditText)findViewById(R.id.edt_email);
        editPass = (EditText)findViewById(R.id.edt_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnReg = (Button) findViewById(R.id.btn_register);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
                MainActivity.this.finish();
            }
        });
    }
    private void Login(){
        String mail = editEmail.getText().toString().trim();
        String pass = editPass.getText().toString().trim();
        User user =new User(mail,pass);
        if (mail.isEmpty() && pass.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please enter enough information", Toast.LENGTH_SHORT).show();
        }
        else {
            Retrofit retrofit = APIClient.getClient();
            HerokuService requestApi = retrofit.create(HerokuService.class);
            Call<ResultLogin> call = requestApi.login(user);
            call.enqueue(new Callback<ResultLogin>() {
                @Override
                public void onResponse(Call<ResultLogin> call, Response<ResultLogin> response) {
                    try {
                        ResultLogin result = response.body();
                        if (result.getMessage().equals("OK")){
                            Toast.makeText(MainActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                            intent.putExtra("accesstoken",result.getBody().getAccesstoken());
                            startActivity(intent);
                            MainActivity.this.finish();
                        }
                        else if (result.getMessage().equals("account is logging in another place")){
                            Toast.makeText(MainActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Mail or Passwork is wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (Exception e){
                        Toast.makeText(MainActivity.this, "something is wrong", Toast.LENGTH_SHORT).show();

                    }
                    //Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onFailure(Call<ResultLogin> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Can not connect to server", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}