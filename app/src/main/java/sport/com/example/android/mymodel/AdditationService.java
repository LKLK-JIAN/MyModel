package sport.com.example.android.mymodel;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by android on 2018/5/4.
 */

public class AdditationService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new IAdditationService.Stub() {

            @Override
            public int add(int i, int a) throws RemoteException {
                return  i+a;
            }
        };
    }
}
