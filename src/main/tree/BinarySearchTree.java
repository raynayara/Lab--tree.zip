package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinarySearchTree {

    class Node {
        int key;
        Node left, right;

        public Node(int item) {
            key = item;
            left = right = null;
        }
    }

    Node root;

    public BinarySearchTree() {
        root = null;
    }

    public boolean buscaElemento(int valor) {
        Node current = root;
        while (current != null) {
            if (valor == current.key) {
                return true;
            } else if (valor < current.key) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }

    public int minimo() {
        if (root == null) {
            throw new IllegalStateException("Árvore está vazia");
        }
        Node current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.key;
    }

    public int maximo() {
        if (root == null) {
            throw new IllegalStateException("Árvore está vazia");
        }
        Node current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.key;
    }

    public void insereElemento(int valor) {
        if (root == null) {
            root = new Node(valor);
            return;
        }
        Node current = root;
        while (true) {
            if (valor < current.key) {
                if (current.left == null) {
                    current.left = new Node(valor);
                    return;
                }
                current = current.left;
            } else if (valor > current.key) {
                if (current.right == null) {
                    current.right = new Node(valor);
                    return;
                }
                current = current.right;
            } else {
                // Se o valor já existe, não faz nada
                return;
            }
        }
    }

    public void remove(int valor) {
        Node parent = null;
        Node current = root;
        while (current != null && current.key != valor) {
            parent = current;
            if (valor < current.key) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        if (current == null) {
            // O valor não está presente na árvore
            return;
        }
        if (current.left == null) {
            if (parent == null) {
                root = current.right;
            } else if (current == parent.left) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else if (current.right == null) {
            if (parent == null) {
                root = current.left;
            } else if (current == parent.left) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        } else {
            Node sucessor = getMinimoNode(current.right);
            int temp = sucessor.key;
            remove(sucessor.key);
            current.key = temp;
        }
    }

    private Node getMinimoNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    public int[] preOrdem() {
        List<Integer> result = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        if (root != null) {
            stack.push(root);
            while (!stack.isEmpty()) {
                Node node = stack.pop();
                result.add(node.key);
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] emOrdem() {
        List<Integer> result = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Node current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            result.add(current.key);
            current = current.right;
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] posOrdem() {
        List<Integer> result = new ArrayList<>();
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        if (root != null) {
            stack1.push(root);
            while (!stack1.isEmpty()) {
                Node node = stack1.pop();
                stack2.push(node);
                if (node.left != null) {
                    stack1.push(node.left);
                }
                if (node.right != null) {
                    stack1.push(node.right);
                }
            }
            while (!stack2.isEmpty()) {
                result.add(stack2.pop().key);
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
