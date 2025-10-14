package org.example;

import org.example.Lab1Exercise1;

public class Main {
    public static void main(String[] args) {
        Lab1Exercise1.MyList arrayList = Lab1Exercise1.MyList.getList(Lab1Exercise1.ListType.Array);
        arrayList.add(5);
        System.out.println(arrayList.get(0));

        Lab1Exercise1.MyList linkedList = Lab1Exercise1.MyList.getList(Lab1Exercise1.ListType.LinkedList);
        linkedList.add(7);
        System.out.println(linkedList.get(0));

        Lab1Exercise1.MyList syncList = Lab1Exercise1.MyList.getList(Lab1Exercise1.ListType.SyncList);
        syncList.add(9);
        System.out.println(syncList.get(0));
    }
}
