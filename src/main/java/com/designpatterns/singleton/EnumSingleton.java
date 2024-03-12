package com.designpatterns.singleton;

/**
* Singleton pattern example using Java Enum
 *
 * Since enums are inherently serializable, we don't need to implement it with a serializable interface.
 *
 * If we use private constructor, an advanced user can change the private access modifier of the constructor
 * to anything they want at runtime using reflection. *
 * With Enum, this reflection problem is also not there.
 *
 * Therefore, it is 100% guaranteed that only one instance of the singleton is present within a JVM.
 * Thus, this method is recommended as the best method of making singletons in Java.
*/
public enum EnumSingleton {
    INSTANCE;
}


