package com.datastructures.collectionsframework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class CollectionsFrequencyDemo {

    //Using Java.util. Collections.frequency() for Custom defined objects
        public static void main(String[] args) {
            Person a1 = new Person("Ram");
            Person a2 = new Person("Shayam");
            Person a3 = new Person("Rahim");
            Person a4 = new Person("Shayam");

            List<Person> list = new ArrayList<Person>();
            list.add(a1);
            list.add(a2);
            list.add(a3);
            list.add(a4);

            Person a = new Person("Shayam");
            int index = Collections.frequency(list, a);
            System.out.println("Frequency of Shayam:" + index);
        }
    }


    class Person {
        private String name;
        public Person(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
        @Override
        public boolean equals(Object o) {
            return name.equals(((Person)o).name);
        }
        @Override
        public int hashCode() {
            int hash = 13;
            hash = (31 * hash) + (null == name ? 0 : name.hashCode());
            return hash;
        }
    }

