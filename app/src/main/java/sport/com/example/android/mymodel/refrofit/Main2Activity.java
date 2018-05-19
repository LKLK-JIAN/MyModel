package sport.com.example.android.mymodel.refrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sport.com.example.android.mymodel.R;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_input;
    private TextView tv_result;
    private Button btn_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        et_input = findViewById(R.id.et_input);
        tv_result = findViewById(R.id.tv_result);
        btn_search=findViewById(R.id.btn_search);
        btn_search.setOnClickListener(this);

    }

    public void request(String request) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PostRequest_Interface  request_interface=retrofit.create(PostRequest_Interface.class);
        Call<Translation1> call = request_interface.getCall(request);
        call.enqueue(new Callback<Translation1>() {
            @Override
            public void onResponse(Call<Translation1> call, Response<Translation1> response) {
                String i=response.body().getTranslateResult().get(0).get(0).getTgt();
                tv_result.setText(i);
            }

            @Override
            public void onFailure(Call<Translation1> call, Throwable t) {
                Log.e("TAG", "onFailure:fair " );
            }
        });

    }

    @Override
    public void onClick(View v) {
        request(et_input.getText().toString());
    }
}
