// IPet.aidl
package com.baidu_lishuang10.aidldemo;

import com.baidu_lishuang10.bean.Pet;
import com.baidu_lishuang10.bean.Person;

interface IPet {
List<Pet> getPets(in Person owner);
}
