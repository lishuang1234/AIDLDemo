// ICat.aidl
package com.baidu_lishuang10.aidldemo;

// Declare any non-default types here with import statements

interface ICat {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

            String getColor();
            double getWeight();
}
