package com.amadeus.skiplist;

import java.util.Comparator;

public class SkipList<K, V> {
    //常量
    private static final int MAX_LEVEL = 32;
    private static final double P = 0.25;
    //字段
    private int size;
    private Node<K, V> first;
    /**
     * 有效层高
     */
    private int level;

    private Comparator comparator;
    //构造方法
    public SkipList(Comparator comparator) {
        this.comparator = comparator;
    }
    public SkipList() {
        this(null);
    }

    //方法

    public V get(K key) {
        keyNullCheck(key);

        Node<K, V> node = first;
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;

            while (node.nexts[i] != null &&
                    (cmp = compare(node.nexts[i].key, key)) < 0) {

                node = node.nexts[i];
            }
            if (cmp == 0) {
                return node.nexts[i].value;
            }
        }

        return null;
    }


    public V add(K key, V value) {
        keyNullCheck(key);

        Node<K, V>[] prevs = new Node[level];
        Node<K, V> node = first;
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;

            while (node.nexts[i] != null &&
                    (cmp = compare(node.nexts[i].key, key)) < 0) {

                node = node.nexts[i];
            }

            if (cmp == 0) {
                V oldValue = node.nexts[i].value;
                node.nexts[i].value = value;
                return oldValue;
            }
            prevs[i] = node;
        }

        Node<K, V> newNode = new Node<>(key, value);
        //newNode.nexts.length 是 新节点的高度
        for (int i = 0; i < newNode.nexts.length; i++) {
            if (i >= level) {
                first.nexts[i] = newNode;
            } else {
                newNode.nexts[i] = prevs[i].nexts[i];
                prevs[i].nexts[i] = newNode;
            }
        }

        size++;

        //更新高度
        level = Math.max(newNode.nexts.length, level);
        return null;
    }



    public boolean isEmpty() {
        return size == 0;
    }



    private void keyNullCheck(K key) {
        if (key == null) {
            throw new NullPointerException("key != null !!");
        }
    }

    private int compare(K k1, K k2) {
//        if (comparator == null) {
//            return ((Comparable)k1).compareTo(k2);
//        }
//        return comparator.compare(k1, k2);
        return comparator != null ?
                comparator.compare(k1, k2) :
                ((Comparable)k1).compareTo(k2);
    }



















    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V>[] nexts;

//        public Node(K key, V value, Node<K, V> nodes) {
//            this.key = key;
//            this.value = value;
//            int level = randomLevel();
//            this.nexts = new Node[level];
//        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            int level = randomLevel();
            this.nexts = new Node[level];
        }

        public Node(K key, V value, int level) {
            this.key = key;
            this.value = value;
            this.nexts = new Node[level];
        }

        private int randomLevel() {
            int level = 1;
            while (Math.random() < P && level < MAX_LEVEL) {
                level++;
            }
            return level;
        }
    }







}
