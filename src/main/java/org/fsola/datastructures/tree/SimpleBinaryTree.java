/*
 * org.fsola
 *
 * File Name: SimpleBinaryTree.java
 *
 * Copyright 2014 Dzhem Riza
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fsola.datastructures.tree;

/**
 * Simple implementation of binary search tree (without balancing).
 * <p>
 * Used Resources: README file resources section line 1
 *
 * @param <T, V>
 * @param <V>
 */
public class SimpleBinaryTree<T extends Comparable<T>, V> {

    public static class TreeNode<T extends Comparable<T>, V> {
        private TreeNode<T, V> left;
        private TreeNode<T, V> right;
        private TreeNode<T, V> parent;
        private T key;
        private V value;

        public TreeNode(TreeNode<T, V> left, TreeNode<T, V> right,
                        TreeNode<T, V> parent,
                        T key, V value) {
            this.left = left;
            this.right = right;
            this.parent = parent;
            this.key = key;
            this.value = value;
        }

        public TreeNode<T, V> getLeft() {
            return left;
        }

        public TreeNode<T, V> getRight() {
            return right;
        }

        public TreeNode<T, V> getParent() {
            return parent;
        }

        public boolean isRoot() {
            return (parent == null);
        }

        public boolean isLeaf() {
            return (left == null & right == null);
        }

        public T getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    /**
     * Root node
     */
    private TreeNode<T, V> root;

    /**
     * Add new item in sub tree
     *
     * @param node
     * @param key
     * @param value
     * @return
     */
    private TreeNode<T, V> insert(TreeNode<T, V> node, T key, V value) {
        TreeNode<T, V> i = node;

        while (i != null) {
            if (key.compareTo(i.key) < 0) {
                // Iterate on left site
                if (i.left == null) {
                    i.left = new TreeNode<>(null, null, i, key, value);
                    return i.left;
                }
                i = i.left;
            } else if (0 < key.compareTo(i.key)) {
                // Iterate on right site
                if (i.right == null) {
                    i.right = new TreeNode<>(null, null, i, key, value);
                    return i.right;
                }
                i = i.right;
            } else {
                throw new IllegalStateException(
                        "Adding keys with duplicate keys is forbidden!");
            }
        }

        // Node is null
        return new TreeNode<>(null, null, null, key, value);
    }

    /**
     * Add new item in tree
     *
     * @param key
     * @param value
     */
    public void insert(T key, V value) {
        TreeNode<T, V> newNode = insert(root, key, value);

        if (root == null) {
            root = newNode;
        }
    }

    /**
     * Search in tree for a specific key
     *
     * @param key
     * @return
     */
    public TreeNode<T, V> search(T key) {
        TreeNode<T, V> result = null;

        if (null != root) {
            TreeNode<T, V> i = root;

            while (i != null) {
                if (key.compareTo(i.key) < 0) {
                    i = i.left;
                } else if (0 < key.compareTo(i.key)) {
                    i = i.right;
                } else {
                    return i;
                }
            }
        }

        return result;
    }

    /**
     * Simple interface for traversal of the tree.
     *
     * @param <T>
     * @param <V>
     */
    private static interface TreeNodeVisitor<T extends Comparable<T>, V> {

        /**
         * Visit tree node method.
         *
         * @param node
         */
        public void visit(TreeNode<T, V> node);
    }

    /**
     * Method updates parent node with new node.
     *
     * @param node
     * @param nodeForReplacement
     */
    private void unlinkSimpleNode(TreeNode<T, V> node,
                                  TreeNode<T, V> nodeForReplacement) {
        if (node.isRoot()) {
            // This is a root element replace main reference only
            root = nodeForReplacement;
            if (root != null) {
                root.parent = null;
            }
        } else {
            if (node.parent.left == node) {
                node.parent.left = nodeForReplacement;
                if (null != nodeForReplacement) {
                    nodeForReplacement.parent = node.parent;
                }
            } else if (node.parent.right == node) {
                node.parent.right = nodeForReplacement;
                if (nodeForReplacement != null) {
                    nodeForReplacement.parent = node.parent;
                }
            } else {
                throw new IllegalStateException(
                        "Node with key: " + node.key +
                                " parent reference contains invalid data.");
            }
        }

        node.parent = null;
        node.key = null;
        node.value = null;
        node.left = null;
        node.right = null;
    }

    /**
     * Method deletes one simple tree node.<p/>Simple node is such node that has
     * only one child (left or right) or has no children.
     *
     * @param node
     * @return
     */
    private boolean deleteSimpleNode(TreeNode<T, V> node) {
        boolean result = true;

        if (node.isLeaf()) {
            unlinkSimpleNode(node, null);
        } else {
            if (node.left == null || node.right == null) {
                // We have only left or right children
                if (node.left != null) {
                    unlinkSimpleNode(node, node.left);
                } else {
                    unlinkSimpleNode(node, node.right);
                }
            } else {
                // We have left and right children
                result = false;
            }
        }

        return result;
    }

    /**
     * Finds min element by given node.
     *
     * @param node
     * @return
     */
    private TreeNode<T, V> findMin(TreeNode<T, V> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * Deletes tree node
     *
     * @param node
     */
    private void deleteTreeNode(TreeNode<T, V> node) {

        if (!deleteSimpleNode(node)) {
            // We have left and right child's
            TreeNode<T, V> replace = findMin(node.right);
            node.key = replace.key;
            node.value = replace.value;
            // Minimum node is always simple
            deleteSimpleNode(replace);
        }
    }

    /**
     * Delete tree node by given key
     *
     * @param key
     */
    public V deleteKey(T key) {
        V result = null;
        TreeNode<T, V> node = search(key);

        if (null != node) {
            result = node.value;
            deleteTreeNode(node);
        }

        return result;
    }

    /**
     * Print tree recursive to std out.
     *
     * @param node
     * @param deep
     * @param prefix
     */
    private void print(TreeNode<T, V> node, int deep, String prefix) {
        if (node == null) {
            return;
        }

        for (int i = 0; i < deep; ++i) {
            System.out.print("  "); // 4 spaces for each level
        }
        System.out.print(prefix);
        System.out.print(node.key);
        System.out.println();

        print(node.left, deep + 1, "L:");
        print(node.right, deep + 1, "R:");
    }

    /**
     * Prints tree structure to std out.
     */
    public void print() {
        if (root == null) {
            System.out.println("Tree is empty!");
            return;
        }

        print(root, 0, "ROOT:");
    }

    /**
     * Inorder traversal of the tree.
     *
     * @param node
     */
    private void inorder(TreeNode<T, V> node, TreeNodeVisitor<T, V> visitor) {
        if (node == null) {
            return;
        }

        inorder(node.getLeft(), visitor);
        visitor.visit(node);
        inorder(node.getRight(), visitor);
    }

    /**
     * Find k-th smallest element in the BST. Using inorder traversal.
     *
     * @param k
     * @return
     */
    public TreeNode<T, V> findKthSmallestElement(int k) {

        class KthSmallest implements TreeNodeVisitor<T, V> {
            private int c = 0;
            private TreeNode<T, V> node = null;
            private int k = 0;

            public KthSmallest(int k) {
                this.k = k;
            }

            @Override
            public void visit(TreeNode<T, V> node) {
                this.c++;
                if (this.c == this.k) {
                    this.node = node;
                }
            }

            public TreeNode<T, V> getNode() {
                return this.node;
            }
        }

        KthSmallest visitor = new KthSmallest(k);

        inorder(root, visitor);

        return visitor.getNode();
    }

    /**
     * Return tree root node
     *
     * @return
     */
    public TreeNode<T, V> getRoot() {
        return root;
    }
}
