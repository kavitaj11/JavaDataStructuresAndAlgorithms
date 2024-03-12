package com.designpatterns.singleton;

import java.io.Serializable;

public class SerializableSingleton implements Serializable {

        public static final SerializableSingleton INSTANCE = new SerializableSingleton();
        private SerializableSingleton() {    }
        protected Object readResolve() {
            return INSTANCE;
        }
}

