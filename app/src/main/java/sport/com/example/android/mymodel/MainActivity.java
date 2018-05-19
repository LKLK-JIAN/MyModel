package sport.com.example.android.mymodel;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_start;
    private Button btn_stop;
    private TextView result;
    IAdditationService service1;
    AdditationConnect connect;
    int i;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_start=findViewById(R.id.btn_start);
        btn_stop=findViewById(R.id.btn_stop);
        result=findViewById(R.id.result);
        btn_start.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_start:
                connect=new AdditationConnect();
                bindService(new Intent(this,AdditationService.class),connect, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btn_stop:
                try {
                    result.setText(Integer.valueOf(service1.add(1,1)).toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    class AdditationConnect implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            service1= IAdditationService.Stub.asInterface((IBinder) service);
            Toast.makeText(MainActivity.this, "Service connected", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(MainActivity.this, "Service connected", Toast.LENGTH_LONG).show();
        }
    }

}
