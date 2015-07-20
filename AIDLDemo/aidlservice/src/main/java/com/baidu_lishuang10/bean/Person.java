package com.baidu_lishuang10.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by baidu_lishuang10 on 15/7/15.
 */
public class Person implements Parcelable {


    public Person(int id, String name, String pass) {
        this.id = id;
        this.name = name;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    private int id;
    private String name;
    private String pass;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(pass);
    }

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {

        //从Parcelable读取数据
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source.readInt(), source.readString(), source.readString());
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];//这里的对象是无参构造的
        }
    };


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }

        if (getClass() != o.getClass()) {
            return false;
        }
        Person other = (Person) o;

        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }

        if (pass == null) {
            if (other.pass != null) {
                return false;
            }
        } else if (!pass.equals(other.pass)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (pass == null ? 0 : pass.hashCode());
        return result;
    }
}
