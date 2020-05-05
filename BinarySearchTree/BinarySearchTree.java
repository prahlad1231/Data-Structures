package datastructures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class BinarySearchTree <T extends Comparable<T>> {

    private int totalNodes = 0;
    private Node root = null;

    private class Node {
        T data;
        Node left, right;

        public Node(T data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public int size() {
        return totalNodes;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    public boolean contains(T element) {
        return contains(root, element);
    }

    private boolean contains(Node node, T element) {
        if (node == null) return false;
        int comp = element.compareTo(node.data);
        if (comp < 0) return contains(node.left, element);
        else if (comp > 0) return contains(node.right, element);
        else return true;
    }

    public boolean add(T element) {
        // no duplicates allowed in the binary tree
        if (contains(element)) return false;
        root = add(root, element);
        totalNodes++;
        return true;
    }

    private Node add(Node node, T element) {
        if (node == null) {
            node = new Node(element, null, null);
        } else {
            if (element.compareTo(node.data) < 0) {
                node.left = add(node.left, element);
            } else {
                node.right = add(node.right, element);
            }
        }
        return node;
    }

    public boolean remove(T element) {
        if (!contains(element)) return false;
        root = remove(root, element);
        totalNodes--;
        return true;
    }

    private Node remove(Node node, T element) {
        if (node == null) return null;
        int comp = element.compareTo(node.data);
        if (comp < 0) {
            node.left = remove(node.left, element);
        } else if (comp > 0) {
            node.right = remove(node.right, element);
        } else { // if node to be deleted is found
            /*
                4 cases:
                    1. node with no children (leaf node)
                    2. node with left child only
                    3. node with right child only
                    4. node with both children
             */
            // case 1 or case 3
            if (node.left == null) {
                Node rightChild = node.right;
                node.data = null; // removing the data in the node
                node = null; // for garbage collection
                return rightChild;
            } else if (node.right == null) { // case 2
                Node leftChild = node.left;
                node.data = null;
                node = null;
                return leftChild;
            } else { // case 4
                Node tmp = minNode(node.right);
                node.data = tmp.data;
                // remove the leftmost node from the right sub-tree
                node.right = remove(node.right, node.data);
            }
        }
        return node;
    }

    private Node minNode(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // tree traversal
    enum TraversalMethod {
        PREORDER, // dfs technique
        INORDER,
        POSTORDER,
        BFS, // also known as level order tree traversal
    }

    public Iterator<T> iterator(TraversalMethod traversalMethod) {
        switch (traversalMethod) {
            case PREORDER:
                return preorder();
            case INORDER:
                return inorder();
            case POSTORDER:
                return postorder();
            case BFS:
                return bfs();
        }
        return null;
    }

    private Iterator<T> preorder() {
        final int expectedTotalNodes = totalNodes;
        final Stack<Node> stack = new Stack<>();
        stack.push(root);
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (expectedTotalNodes != totalNodes) throw new ConcurrentModificationException();
                return root != null && !stack.isEmpty();
            }

            @Override
            public T next() {
                if (expectedTotalNodes != totalNodes) throw new ConcurrentModificationException();
                Node node = stack.pop();
                if (node.right != null) stack.push(node.right);
                if (node.left != null) stack.push(node.left);
                return node.data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private Iterator<T> inorder() {
        final int expectedTotalNodes = totalNodes;
        final Stack<Node> stack = new Stack<>();
        stack.push(root);
        return new Iterator<T>() {
            Node tmp = root;
            @Override
            public boolean hasNext() {
                if (expectedTotalNodes != totalNodes) throw new ConcurrentModificationException();
                return root != null && !stack.isEmpty();
            }

            @Override
            public T next() {
                if (expectedTotalNodes != totalNodes) throw new ConcurrentModificationException();
                // add all elements of the left part to the stack
                while (tmp != null && tmp.left != null) {
                    stack.push(tmp.left);
                    tmp = tmp.left;
                }
                Node node = stack.pop();
                if (node.right != null) {
                    stack.push(node.right);
                    tmp = node.right;
                }
                return node.data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private Iterator<T> postorder() {
        final int expectedTotalNodes = totalNodes;
        final Stack<Node> tempStack = new Stack<>();
        final Stack<Node> finalStack = new Stack<>();
        tempStack.push(root);
        while (!tempStack.isEmpty()) {
            Node node = tempStack.pop();
            if (node != null) {
                finalStack.push(node);
                if (node.left != null) tempStack.push(node.left);
                if (node.right != null) tempStack.push(node.right);
            }
        }
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (expectedTotalNodes != totalNodes) throw new ConcurrentModificationException();
                return (root != null && !finalStack.isEmpty());
            }

            @Override
            public T next() {
                if (expectedTotalNodes != totalNodes) throw new ConcurrentModificationException();
//                System.out.println("To return: " + );
                return finalStack.pop().data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private Iterator<T> bfs() {
        final int expectedTotalNodes = totalNodes;
        Queue<Node> queue = new Queue<>();
        queue.offer(root);
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (expectedTotalNodes != totalNodes) throw new ConcurrentModificationException();
                return (root != null && !queue.isEmpty());
            }

            @Override
            public T next() {
                if (expectedTotalNodes != totalNodes) throw new ConcurrentModificationException();
                Node node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
                return node.data;
            }
            
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        System.out.println("Size: " + bst.size());
        System.out.println("Height: " + bst.height());
        bst.add(10);
        bst.add(20);
        bst.add(15);
        bst.add(30);
        bst.add(50);
        bst.add(5);
        bst.add(4);
        bst.add(8);
        System.out.println("Size: " + bst.size());
        System.out.println("Height: " + bst.height());
        System.out.println("PREORDER: ");
        Iterator itr = bst.iterator(TraversalMethod.PREORDER);
        while (itr.hasNext()) {
            System.out.print(itr.next() + ", ");
        }

        System.out.println("\nINORDER: ");
        Iterator itr2 = bst.iterator(TraversalMethod.INORDER);
        while (itr2.hasNext()) {
            System.out.print(itr2.next() + ", ");
        }

        System.out.println("\nPOSTORDER: ");
        Iterator itr3 = bst.iterator(TraversalMethod.POSTORDER);
        while (itr3.hasNext()) {
            System.out.print(itr3.next() + ", ");
        }

        System.out.println("\nBFS: ");
        Iterator itr4 = bst.iterator(TraversalMethod.BFS);
        while (itr4.hasNext()) {
            System.out.print(itr4.next() + ", ");
        }
    }
}























