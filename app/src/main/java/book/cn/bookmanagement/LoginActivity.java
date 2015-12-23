package book.cn.bookmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.squareup.okhttp.OkHttpClient;

import book.cn.bookmanagement.Models.RequestResultInfo;
import book.cn.bookmanagement.Utils.ServiceGenerator;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    private OkHttpClient _client = new OkHttpClient();

    @Bind(R.id.userNameEditText)
    EditText _userNameEditText;

    @Bind(R.id.passwordEditText)
    EditText _passwordEditText;

//    @Bind(R.id.loginButton)
//    Button _loginButton;
//    @Bind(R.id.registerButton)
//    Button _registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.loginButton)
    public void login(View v) {
        String un = _userNameEditText.getText().toString();
        String pwd = _passwordEditText.getText().toString();
        ServiceGenerator.BookManagementService bookManagementService
                = ServiceGenerator.createService(ServiceGenerator.BookManagementService.class);

        Call<RequestResultInfo> loginCall
                = bookManagementService.login(un, pwd);
        loginCall.enqueue(new Callback<RequestResultInfo>() {
            @Override
            public void onResponse(Response<RequestResultInfo> response, Retrofit retrofit) {
                Log.d(TAG, response.raw().toString());
                if (response.isSuccess()) {
                    Log.d(TAG, response.raw().toString());
                } else {
                    Log.d(TAG, response.raw().toString());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "error login");
            }
        });
    }

    @OnClick(R.id.registerButton)
    public void register(View v) {
        Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(i);
    }
}
