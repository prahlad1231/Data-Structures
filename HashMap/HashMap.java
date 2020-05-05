/*
	January 11, 2020
	Author: Er. Prahlad Panthi

	Program to demonstrate the working of hashing (double hashing and separate chaining combined)
*/

package datastructures;

import java.util.ArrayList;

class HashMap<K, V> {

    class MapNode<K, V> {

        K key;
        V value;
        MapNode<K, V> next;

        public MapNode(K key, V value) {
            this.key = key;
            this.value = value;
            next = null;
        }
    }

    ArrayList<MapNode<K, V>> map;

    int size; // number of pairs stored
    int sizeMap; // size of map

    final double DEFAULT_LOAD_FACTOR = 0.75;

    public HashMap() {
        sizeMap = 3;
        map = new ArrayList<>(sizeMap);

        for(int i = 0; i < sizeMap; ++i)
            map.add(null);

        System.out.println("HashMap created");
        System.out.println("Number of pairs in the map: " + size);
        System.out.println("Size of Map: " + sizeMap);
        System.out.println("Default load factor: " + DEFAULT_LOAD_FACTOR + "\n");
    }

    // hash function in order to insert a value
    private int getMapIndex(K key) {
        int hashcode = key.hashCode();
        return hashcode % sizeMap;
    }

    public void insert(K key, V value) {
        int mapIndex = getMapIndex(key);
        MapNode<K, V> head = map.get(mapIndex);

        // check if the key already exists
        while(head != null) {
            // if key is present, the value is updated
            if(head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        // if key is not present
        MapNode<K, V> newNode = new MapNode<K, V>(key, value);
        head = map.get(mapIndex);
        // inserting new node as new head in the linked list of MapNode
        newNode.next = head;

        map.set(mapIndex, newNode);
        System.out.println("Pair(" + key + ", " + value + ") inserted!\n");
        size++; // increasing the number of pairs that are stored in the list

        double loadFactor = (1.0 * size) / sizeMap;
        System.out.println("Current load factor: " + loadFactor + "\n");

        // rehash if load factor is > 0.75
        if(loadFactor > DEFAULT_LOAD_FACTOR) {
            System.out.println("WARNING: Load factor is greater than DEFAULT_LOAD_FACTOR");
            rehash();
            System.out.println("New size of map: " + sizeMap + "\n");
        }
        // if load factor is <= 0.75, no need to rehash
        System.out.println("Number of pairs in the map: " + size);
        System.out.println("Size of map: " + sizeMap);
    }

    private void rehash() {
        System.out.println("Rehashing.......\n");
        ArrayList<MapNode<K, V>> temp = map;
        map = new ArrayList<MapNode<K, V>> (2 * sizeMap);
        for(int i = 0; i < 2*sizeMap; ++i)
            map.add(null);

        // inserting all nodes from previous list to new list
        size = 0;
        sizeMap *= 2;

        for(int i = 0; i < temp.size(); ++i) {
            MapNode<K, V> head = temp.get(i);
            while(head != null) {
                K key = head.key;
                V value = head.value;

                insert(key, value);
                head = head.next;
            }
        }
        System.out.println("Rehashing completed!\n");
    }

    public void printMap() {
        ArrayList<MapNode<K, V>> temp = map;
        System.out.println("Current HashMap:");
        for(int i = 0; i < sizeMap; ++i) {
            MapNode<K, V> head = temp.get(i);

            while(head != null) {
                System.out.println("Key: " + head.key + ", Value: " + head.value);
                head = head.next;
            }
            System.out.println();
        }
    }
}