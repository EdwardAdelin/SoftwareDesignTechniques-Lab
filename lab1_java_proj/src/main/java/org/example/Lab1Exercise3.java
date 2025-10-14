package org.example;

public class Lab1Exercise3 {
// russian puppet style Decorator pattern
    public static void main(String[] args) {
        // Create a logged ArrayList
        MyList loggedList = new LoggingDecorator(MyList.getList(ListType.Array));
        loggedList.add(5);  // Output: 5 was added to the list
        loggedList.add(10); // Output: 10 was added to the list

        // Test retrieval
        System.out.println("Element at index 0: " + loggedList.get(0));
        System.out.println("Element at index 1: " + loggedList.get(1));
    }

    // Enum for list types
    enum ListType { Array, LinkedList, SyncList }

    // MyList interface with factory method
    interface MyList {
        void add(int element);
        int get(int index);

        static MyList getList(ListType type) {
            switch (type) {
                case Array: return new MyArrayList();
                case LinkedList: return new MyLinkedList();
                case SyncList: return new MySynchronizedList();
                default: throw new IllegalArgumentException("Unknown list type");
            }
        }
    }

    // Array-backed list
    static class MyArrayList implements MyList {
        private java.util.List<Integer> list = new java.util.ArrayList<>();
        public void add(int element) { list.add(element); }
        public int get(int index) { return list.get(index); }
    }

    // Linked-list-backed
    static class MyLinkedList implements MyList {
        private java.util.List<Integer> list = new java.util.LinkedList<>();
        public void add(int element) { list.add(element); }
        public int get(int index) { return list.get(index); }
    }

    // Synchronized list
    static class MySynchronizedList implements MyList {
        private java.util.List<Integer> list = new java.util.ArrayList<>();
        public synchronized void add(int element) { list.add(element); }
        public synchronized int get(int index) { return list.get(index); }
    }

    // basically, a wrapper around a list that logs every element added, without
    // changing the original list classes
    // Logging decorator
    static class LoggingDecorator implements MyList {
        private final MyList wrappedList;

        public LoggingDecorator(MyList list) {
            this.wrappedList = list;
        }

        @Override
        public void add(int element) {
            wrappedList.add(element);
            System.out.println(element + " was added to the list");
        }

        @Override
        public int get(int index) {
            return wrappedList.get(index);
        }
    }
}

