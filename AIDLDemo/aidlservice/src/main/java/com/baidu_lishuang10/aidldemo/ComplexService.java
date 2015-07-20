package com.baidu_lishuang10.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.baidu_lishuang10.bean.Person;
import com.baidu_lishuang10.bean.Pet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by baidu_lishuang10 on 15/7/15.
 */
public class ComplexService extends Service {
    private static final String TAG = "ComplexService";
    private static Map<Person, List<Pet>> pets = new HashMap<>();
    private PetBinder mBinder;

    static {
        ArrayList<Pet> petArrayList = new ArrayList<>();
        petArrayList.add(new Pet("cat", 3.2));
        petArrayList.add(new Pet("dog", 2.3));
        pets.put(new Person(0, "LS", "LS"), petArrayList);
        ArrayList<Pet> petArrayList2 = new ArrayList<>();
        petArrayList2.add(new Pet("cat2", 4.2));
        petArrayList2.add(new Pet("dog2", 3.3));
        pets.put(new Person(1, "LS2", "LS2"), petArrayList2);
    }

    @Override
    public void onCreate() {
        mBinder = new PetBinder();
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    //实现接口，提供数据
    class PetBinder extends IPet.Stub {
        @Override
        public List<Pet> getPets(Person owner) throws RemoteException {
            Log.e(TAG, "getPets(Person owner) : person name is : " + owner.getName());
            return pets.get(owner);
        }
    }

}
