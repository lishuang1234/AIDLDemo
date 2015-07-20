package com.baidu_lishuang10.aidlclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.baidu_lishuang10.aidldemo.IPet;
import com.baidu_lishuang10.bean.Person;
import com.baidu_lishuang10.bean.Pet;

import java.util.List;

/**
 * Created by baidu_lishuang10 on 15/7/20.
 */
public class ComplexActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ComplexActivity";
    private EditText mPersonName;
    private ListView mListView;
    private IPet mIPet;
    private List<Pet> mPets;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex);
        findViewById(R.id.activity_complex_btn).setOnClickListener(this);
        mPersonName = (EditText) findViewById(R.id.activity_complex_et);
        mListView = (ListView) findViewById(R.id.activity_complex_lv);
        Intent intent = new Intent();
        intent.setAction("com.baidu_lishuang10.aidldemo.COMPLEX_SERVICE");
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);//绑定service
    }

    @Override
    public void onClick(View v) {
        String name = mPersonName.getText().toString();
        if (name.equals(""))
            return;
        Log.e(TAG, "person name is:" + name);
        try {
            mPets = mIPet.getPets(new Person(0, name, name));
            Log.e(TAG, "mPets size is:" + mPets.size());
            updateListView();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    private void updateListView() {
        mListView.setAdapter(null);
        if (mPets == null) {
            return;
        }
        ArrayAdapter<Pet> arrayAdapter = new ArrayAdapter< >(ComplexActivity.this, android.R.layout.simple_list_item_1, mPets);
        mListView.setAdapter(arrayAdapter);

    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIPet = IPet.Stub.asInterface(service);//获取IBinder代理对象，获取数据
            Log.e(TAG, "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIPet = null;
        }
    };

    @Override
    protected void onDestroy() {
        unbindService(serviceConnection);
        super.onDestroy();
    }
}
