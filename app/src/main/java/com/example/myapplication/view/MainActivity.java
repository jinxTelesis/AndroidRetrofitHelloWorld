package com.example.myapplication.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.User;
import com.example.myapplication.service.PostAppService;
import com.example.myapplication.service.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText userEmail;
    private EditText passWord;
    private Button submitButton;
    private TextView resultTextView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        userEmail=(EditText)findViewById(R.id.et_email);
        passWord=(EditText)findViewById(R.id.et_password);
        submitButton=(Button)findViewById(R.id.btn_submit);
        resultTextView=(TextView)findViewById(R.id.tv_result);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postData();
            }
        });


    }

    private void postData(){

        User user = new User();
        user.setUserEmail(userEmail.getText().toString());
        user.setPassWord(passWord.getText().toString());

        Log.i("responsesetTest","*********************"+user.getId());

        PostAppService postAppService = RetrofitInstance.getService();
        Call<User> call=postAppService.getResult(user);

        userEmail.setText("");
        passWord.setText("");

        // api callback
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User returnUser=response.body();

                resultTextView.setText("Id is : "+ returnUser.getId());

                Log.i("responsetest","**********************" + returnUser.getId());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

    }
}
