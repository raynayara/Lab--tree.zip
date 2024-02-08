package tree;

import java.util.ArrayList;
import java.util.List;
import estrut.Tree;

public class BinarySearchTree implements Tree {
    class Node {
        int key;
        Node left, right;

        public Node(int item) {
            key = item;
            left = right = null;
        }
    }

    Node root;

    // Construtor público
    public BinarySearchTree() {
        root = null;
    }

    @Override
    public boolean buscaElemento(int valor) {
        return buscaElementoRec(root, valor);
    }

    private boolean buscaElementoRec(Node root, int valor) {
        if (root == null) {
            return false;
        }
        if (valor == root.key) {
            return true;
        }
        return valor < root.key ? buscaElementoRec(root.left, valor) : buscaElementoRec(root.right, valor);
    }

    @Override
    public int minimo() {
        if (root == null) {
            throw new IllegalStateException("Árvore está vazia");
        }
        return minimoRec(root);
    }

    private int minimoRec(Node root) {
        return root.left == null ? root.key : minimoRec(root.left);
    }

    @Override
    public int maximo() {
        if (root == null) {
            throw new IllegalStateException("Árvore está vazia");
        }
        return maximoRec(root);
    }

    private int maximoRec(Node root) {
        return root.right == null ? root.key : maximoRec(root.right);
    }

    @Override
    public void insereElemento(int valor) {
        root = insereElementoRec(root, valor);
    }

    private Node insereElementoRec(Node root, int valor) {
        if (root == null) {
            root = new Node(valor);
            return root;
        }
        if (valor < root.key) {
            root.left = insereElementoRec(root.left, valor);
        } else if (valor > root.key) {
            root.right = insereElementoRec(root.right, valor);
        }
        return root;
    }

    @Override
    public void remove(int valor) {
        root = removeRec(root, valor);
    }

    private Node removeRec(Node root, int valor) {
        if (root == null) {
            return root;
        }
        if (valor < root.key) {
            root.left = removeRec(root.left, valor);
        } else if (valor > root.key) {
            root.right = removeRec(root.right, valor);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            root.key = minimoRec(root.right);
            root.right = removeRec(root.right, root.key);
        }
        return root;
    }

    private void preOrdemRec(Node root, List<Integer> result) {
        if (root != null) {
            result.add(root.key);
            preOrdemRec(root.left, result);
            preOrdemRec(root.right, result);
        }
    }

    @Override
    public int[] preOrdem() {
        List<Integer> result = new ArrayList<>();
        preOrdemRec(root, result);
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private void emOrdemRec(Node root, List<Integer> result) {
        if (root != null) {
            emOrdemRec(root.left, result);
            result.add(root.key);
            emOrdemRec(root.right, result);
        }
    }

    @Override
    public int[] emOrdem() {
        List<Integer> result = new ArrayList<>();
        emOrdemRec(root, result);
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private void posOrdemRec(Node root, List<Integer> result) {
        if (root != null) {
            posOrdemRec(root.left, result);
            posOrdemRec(root.right, result);
            result.add(root.key);
        }
    }

    @Override
    public int[] posOrdem() {
        List<Integer> result = new ArrayList<>();
        posOrdemRec(root, result);
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
