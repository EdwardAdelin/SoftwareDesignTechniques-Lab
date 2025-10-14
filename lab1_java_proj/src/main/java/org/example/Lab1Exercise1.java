package org.example;

public class Lab1Exercise1 {
    //given part by prof
    public static void main(String[] args) {
        MyList arrayList = MyList.getList(ListType.Array);
        arrayList.add(5);
        System.out.println(arrayList.get(0));

        MyList linkedList = MyList.getList(ListType.LinkedList);
        linkedList.add(7);
        System.out.println(linkedList.get(0));

        MyList syncList = MyList.getList(ListType.SyncList);
        syncList.add(9);
        System.out.println(syncList.get(0));
    }
    // someone might want a list but not know exactly what kind of list
    public enum ListType { Array, LinkedList, SyncList }

    // specific interface for Factory Method design pattern
    public interface MyList {
        void add(int element);
        int get(int index);

        // 3 types of lists handeled by 3 types of cases
        static MyList getList(ListType type) {
            switch(type) {
                case Array: return new MyArrayList();
                case LinkedList: return new MyLinkedList();
                case SyncList: return new MySynchronizedList();
                default: throw new IllegalArgumentException();
            }
        }
    }

    // those might be the 3 different implementations of MyList, the concrete products
    static class MyArrayList implements MyList {
        private java.util.List<Integer> list = new java.util.ArrayList<>();
        public void add(int element) { list.add(element); }
        public int get(int index) { return list.get(index); }
    }

    static class MyLinkedList implements MyList {
        private java.util.List<Integer> list = new java.util.LinkedList<>();
        public void add(int element) { list.add(element); }
        public int get(int index) { return list.get(index); }
    }

    static class MySynchronizedList implements MyList {
        private java.util.List<Integer> list = new java.util.ArrayList<>();
        public synchronized void add(int element) { list.add(element); }
        public synchronized int get(int index) { return list.get(index); }
    }
}
