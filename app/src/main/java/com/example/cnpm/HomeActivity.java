package com.example.cnpm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.cnpm.model.APIClient;
import com.example.cnpm.model.Body;
import com.example.cnpm.model.ResultLogout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button btnLogout = (Button) findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }
    private void logout() {
                Intent intent = getIntent();
                String accesstoken = intent.getStringExtra("accesstoken");
                Retrofit retrofit = APIClient.getClient();
                HerokuService requestApi = retrofit.create(HerokuService.class);
                Call<ResultLogout> call = requestApi.logout(accesstoken);
                call.enqueue(new Callback<ResultLogout>() {
                    @Override
                    public void onResponse(Call<ResultLogout> call, Response<ResultLogout> response) {
                        try {
                            ResultLogout result = response.body();
                            if (result.getMessage().equals("OK")){
                                startActivity(new Intent(HomeActivity.this,MainActivity.class));
                                HomeActivity.this.finish();
                            }
                            else {
                                Toast.makeText(HomeActivity.this, "something were wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                        catch (Exception e){
                            Toast.makeText(HomeActivity.this, "something were wrong", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ResultLogout> call, Throwable t) {
                        Toast.makeText(HomeActivity.this, "Can not connect to server", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
