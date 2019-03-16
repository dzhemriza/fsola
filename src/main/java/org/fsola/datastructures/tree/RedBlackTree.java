/*
 * org.fsola
 *
 * File Name: RedBlackTree.java
 *
 * Copyright 2015 Dzhem Riza
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
 * Red-Black tree implementation.
 * <p>
 * Used Resources: README file resources section line 39, line 61, line 62, line 63, line 66.
 *
 * @param <K, V>
 * @param <V>
 */
public class RedBlackTree<K extends Comparable<K>, V> {

    //
    // Standard Red-Black Tree Implementation
    //
    // Properties
    //
    // 1. A node is either red or black.
    // 2. The root is black. (This rule is sometimes omitted. Since the root can
    // always be changed from red to black, but not necessarily vice versa, this
    // rule has little effect on analysis.)
    // 3. All leaves (NIL) are black. (All leaves are same color as the root.)
    // 4. Every red node must have two black child nodes.
    // 5. Every path from a given node to any of its descendant leaves contains
    // the same number of black nodes.
    //
    //
    // Rotation
    //
    //      |                        |
    //     [A]        right         [B]
    //    /   \      -------->     /   \
    //   [B]  [z]     left        [x]  [A]
    //  /   \        <--------        /   \
    // [x]  [y]                      [y]  [z]
    //
    //
    // Legend: [A] -> Red Node, (A) -> Black Node, {A} -> Undeterminated
    //
    // Insertion
    //
    // Case 1: Root is always black
    //
    //    |            |
    //   [A]    ->    (A)
    //  /   \        /   \
    //
    // Case 2: If parent is black no violation in properties
    //
    //    (A)
    //   /   \   -> B is the new node and parent is black - no violation
    //       [B]
    //
    // Case 3: Parent and uncle are red
    //
    //      (G)                      [G]
    //     /   \    flip colors     /   \
    //   [P]   [U]  ---------->   (P)   (U)
    //  /                        /
    // [N]                      [N]
    //
    // N: New node
    // P: Parent node of N
    // G: Grand parent of N
    // U: Uncle of N
    //
    // Continue processing on [G]
    //
    // Case 4: Parent is red uncle is black N is right child of left child parent
    //
    //      (G)                              (G)
    //     /   \     left rotate on P       /   \
    //   [P]   (U)   --------------->     [N]   (U)
    //  /   \                            /   \
    //      [N]                        [P]
    //
    // N is P now
    //
    // Case 5: Parent is red uncle is black
    //
    //       (G)                              [P]
    //      /   \     right rotate on G      /   \
    //    [P]    (U)  ----------------->   [N]   (G)
    //   /                                          \
    // [N]                                          (U)
    //
    //       [P]                             (P)
    //      /   \        change colors      /   \
    //    [N]    (G)     ------------>    [N]   [G]
    //              \                              \
    //              (U)                            (U)
    //
    //
    // Removal
    //
    // Case 1: If x has parent go to case 2
    //
    // Case 2: If sibling of N is red rotate and change colors
    //
    //      (P)                                       [S]
    //     /   \       rotate based on parent        /   \
    //   (N)   [S]     --------------------->      (P)  (Sr)
    //        /   \                               /   \
    //      (Sl)  (Sr)                          (N)  (Sl)
    //
    //
    //      [S]                                         (S)
    //     /   \       Change colors to S and P        /   \
    //   (P)  (Sr)     ------------------------>     [P]  (Sr)
    //        /   \                                 /   \
    //      (N)  (Sl)                             (N)  (Sl)
    //
    // Case 3: If P and S are black make S red and start from case 1 of P
    //
    //      (P)                                       (P)
    //     /   \       Change S color                /   \
    //   (N)   (S)     --------------------->      (N)   [S]
    //        /   \                                     /   \
    //      (Sl)  (Sr)                               (Sl)  (Sr)
    //
    // Case 4: P is red and S is black
    //
    //      [P]                                       (P)
    //     /   \       Change P and S color          /   \
    //   (N)   (S)     --------------------->      (N)   [S]
    //        /   \                                     /   \
    //      (Sl)  (Sr)                               (Sl)  (Sr)
    //
    // Case 5: S is black Sl is red Sr is black
    //
    //      (S)                                   (Sl)
    //     /   \       Change P and S color          \
    //   [Sl]  (Sr)    --------------------->        [S]
    //                                                  \
    //                                                  (Sr)
    //
    // Case 6: Sr is red and N is the left child of P
    //
    //      {P}                                          {S}
    //     /   \       rotate and change color          /   \
    //   (N)   (S)     ---------------------->        (P)   (Sr)
    //            \                                   /
    //           [Sr]                               (N)
    //

    public static enum Color {
        RED, BLACK
    }

    public static class TreeNode<K extends Comparable<K>, V> {
        private TreeNode<K, V> left;
        private TreeNode<K, V> right;
        private TreeNode<K, V> parent;
        private Color color;
        private K key;
        private V value;

        public TreeNode(TreeNode<K, V> left, TreeNode<K, V> right,
                        TreeNode<K, V> parent,
                        Color color,
                        K key, V value) {
            this.left = left;
            this.right = right;
            this.parent = parent;
            this.color = color;
            this.key = key;
            this.value = value;
        }

        public TreeNode<K, V> getLeft() {
            return left;
        }

        public void setLeft(TreeNode<K, V> left) {
            this.left = left;
        }

        public TreeNode<K, V> getRight() {
            return right;
        }

        public void setRight(TreeNode<K, V> right) {
            this.right = right;
        }

        public TreeNode<K, V> getParent() {
            return parent;
        }

        public void setParent(TreeNode<K, V> parent) {
            this.parent = parent;
        }

        public boolean isRoot() {
            return (parent == null);
        }

        public boolean isLeaf() {
            return (left == null & right == null);
        }

        public Color getColor() {
            return this.color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public boolean isRed() {
            return this.color == Color.RED;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            sb.append("TreeNode(color: ");
            sb.append(this.color);
            sb.append(", key: ");
            sb.append(key);
            sb.append(", value: ");
            sb.append(value);
            sb.append(")");

            return sb.toString();
        }
    }

    private TreeNode<K, V> root;

    /**
     * Add new element in the tree.
     *
     * @param key
     * @param value
     */
    public void insert(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("key could not be null.");
        }

        // Standard binary search tree insertion

        TreeNode<K, V> newNode = new TreeNode<>(null, null, null, Color.RED, key, value);
        // RBT: All new nodes are colored RED

        // Case 1: Tree is empty
        if (isEmpty()) {
            root = newNode;
        } else {
            // Case 2: Find a place for inserting this element
            TreeNode<K, V> p = root;

            while (p != null) {
                if (key.compareTo(p.getKey()) < 0) {
                    // On left side
                    if (p.getLeft() == null) {
                        // Parent is p and this node is inserted in the left side
                        // of p
                        p.setLeft(newNode);
                        newNode.setParent(p);
                        break;
                    } else {
                        p = p.getLeft();
                    }
                } else if (0 < key.compareTo(p.getKey())) {
                    // On right side
                    if (p.getRight() == null) {
                        p.setRight(newNode);
                        newNode.setParent(p);
                        break;
                    } else {
                        p = p.getRight();
                    }
                } else {
                    throw new IllegalStateException("Having duplicate keys is not supported.");
                }
            }
        }

        // RBT: Balance Red-Black Tree
        rbFixUp(newNode);
    }

    /**
     * Delete item from tree
     *
     * @param key
     * @return
     */
    public V delete(K key) {
        if (isEmpty()) {
            return null;
        }

        V result = null;

        TreeNode<K, V> n = find(root, key);
        if (n != null) {
            // save the value of the node
            result = n.getValue();
            // delete the node
            delete(n);
        }

        return result;
    }

    /**
     * Replace two nodes in tree.
     *
     * @param oldNode
     * @param newNode
     */
    private void replaceNode(TreeNode<K, V> oldNode, TreeNode<K, V> newNode) {
        if (oldNode.isRoot()) {
            root = newNode;
            setColor(root, Color.BLACK);
        } else {
            if (oldNode.getParent().getLeft() == oldNode) {
                oldNode.getParent().setLeft(newNode);
            } else {
                oldNode.getParent().setRight(newNode);
            }
        }

        if (newNode != null) {
            newNode.setParent(oldNode.getParent());
        }
    }

    /**
     * Delete a node in RB tree
     *
     * @param n
     */
    private void delete(TreeNode<K, V> n) {
        if (n.getLeft() != null && n.getRight() != null) {
            // Find max on the left site
            TreeNode<K, V> predecessor = findMax(n.getLeft());

            n.key = predecessor.getKey();
            n.value = predecessor.getValue();

            n = predecessor;
        }

        // N need to has at least one child or no children
        if (n.getLeft() != null && n.getRight() != null) {
            throw new IllegalStateException("N has two non null children!");
        }

        TreeNode<K, V> child = n.getLeft() != null ? n.getLeft() : n.getRight();

        if (child != null) {
            replaceNode(n, child);

            if (n.getColor() == Color.BLACK) {
                // Fix RB properties
                rbFixDelete(child);
            }
            // Mark n for disposal
            n.setParent(null);
            n.setLeft(null);
            n.setRight(null);
        } else if (n.getParent() == null) {
            root = null;
            // n is subject of disposal in this case
        } else {
            // no children
            if (n.getColor() == Color.BLACK) {
                rbFixDelete(n);
            }

            if (n.getParent() != null) {
                if (n.getParent().getLeft() == n) {
                    n.getParent().setLeft(null);
                } else {
                    n.getParent().setRight(null);
                }
                n.setParent(null);
            }
        }
    }

    /**
     * @param n
     * @return Node color
     */
    private Color nodeColor(TreeNode<K, V> n) {
        if (n == null) {
            return Color.BLACK;
        } else {
            return n.getColor();
        }
    }

    /**
     * @param n
     * @return Left node of n
     */
    private TreeNode<K, V> left(TreeNode<K, V> n) {
        if (n == null) {
            return null;
        } else {
            return n.getLeft();
        }
    }

    /**
     * @param n
     * @return Right node of n
     */
    private TreeNode<K, V> right(TreeNode<K, V> n) {
        if (n == null) {
            return null;
        } else {
            return n.getRight();
        }
    }

    /**
     * @param n
     * @return Parent node of n
     */
    private TreeNode<K, V> parent(TreeNode<K, V> n) {
        if (n == null) {
            return null;
        } else {
            return n.getParent();
        }
    }

    /**
     * Set color of a node n
     *
     * @param n
     * @param color
     */
    private void setColor(TreeNode<K, V> n, Color color) {
        if (n != null) {
            n.setColor(color);
        }
    }

    /**
     * Fix up for RB properties while deleting a node
     *
     * @param n
     */
    private void rbFixDelete(TreeNode<K, V> n) {
        // FROM: java.util.TreeMap -> CLR
        while (n != root && getColor(n) == Color.BLACK) {
            if (n == left(parent(n))) {
                TreeNode<K, V> sib = right(parent(n));

                if (getColor(sib) == Color.RED) {
                    setColor(sib, Color.BLACK);
                    setColor(parent(n), Color.RED);
                    rotateLeft(parent(n));
                    sib = right(parent(n));
                }

                if (getColor(left(sib)) == Color.BLACK &&
                        getColor(right(sib)) == Color.BLACK) {
                    setColor(sib, Color.RED);
                    n = parent(n);
                } else {
                    if (getColor(right(sib)) == Color.BLACK) {
                        setColor(left(sib), Color.BLACK);
                        setColor(sib, Color.RED);
                        rotateRight(sib);
                        sib = right(parent(n));
                    }
                    setColor(sib, getColor(parent(n)));
                    setColor(parent(n), Color.BLACK);
                    setColor(right(sib), Color.BLACK);
                    rotateLeft(parent(n));
                    n = root;
                }
            } else { // Symmetric left -> right, right -> left
                TreeNode<K, V> sib = left(parent(n));

                if (getColor(sib) == Color.RED) {
                    setColor(sib, Color.BLACK);
                    setColor(parent(n), Color.RED);
                    rotateRight(parent(n));
                    sib = left(parent(n));
                }

                if (getColor(right(sib)) == Color.BLACK &&
                        getColor(left(sib)) == Color.BLACK) {
                    setColor(sib, Color.RED);
                    n = parent(n);
                } else {
                    if (getColor(left(sib)) == Color.BLACK) {
                        setColor(right(sib), Color.BLACK);
                        setColor(sib, Color.RED);
                        rotateLeft(sib);
                        sib = left(parent(n));
                    }
                    setColor(sib, getColor(parent(n)));
                    setColor(parent(n), Color.BLACK);
                    setColor(left(sib), Color.BLACK);
                    rotateRight(parent(n));
                    n = root;
                }
            }
        }

        setColor(n, Color.BLACK);

        // FROM: http://en.literateprograms.org/Special:DownloadCode/Red-black_tree_%28C%29
        // Note: Have issues with null pointers, but left here for educational reasons
//        while (true) {
//            // Case 1: If there is no parent break
//            if (parent(n) == null) {
//                break;
//            }
//
//            // Case 2: If sibling of N is red rotate and change colors
//            TreeNode<K, V> sibling = sibling(n);
//            if (isRed(sibling)) {
//                setColor(parent(n), Color.RED);
//                setColor(sibling, Color.BLACK);
//                if (left(parent(n)) == n) {
//                    rotateLeft(parent(n));
//                } else {
//                    rotateRight(parent(n));
//                }
//            }
//
//            // Case 3: If P and S are black make S red and start from case 1 of P
//            sibling = sibling(n);
//            if (getColor(parent(n)) == Color.BLACK &&
//                    !isRed(sibling) &&
//                    !isRed(left(sibling)) &&
//                    !isRed(right(sibling))) {
//                setColor(sibling, Color.RED);
//                n = parent(n);
//                // Start from case 1
//                continue;
//            }
//
//            // Case 4: P is red and S is black
//            sibling = sibling(n);
//            if (isRed(parent(n)) &&
//                    !isRed(sibling) &&
//                    !isRed(left(sibling)) &&
//                    !isRed(right(sibling))) {
//                setColor(sibling, Color.RED);
//                setColor(parent(n), Color.BLACK);
//                break;
//            }
//
//            // Case 5: S is black Sl is red Sr is black
//            sibling = sibling(n);
//            if (n == left(parent(n)) &&
//                    !isRed(sibling) &&
//                    isRed(left(sibling)) &&
//                    !isRed(right(sibling))) {
//                setColor(sibling, Color.RED);
//                setColor(left(sibling), Color.BLACK);
//                rotateRight(sibling);
//            } else if (n == right(parent(n)) &&
//                    !isRed(sibling) &&
//                    !isRed(left(sibling)) &&
//                    isRed(right(sibling))) {
//                setColor(sibling, Color.RED);
//                setColor(right(sibling), Color.BLACK);
//                rotateLeft(sibling);
//            }
//
//            // Case 6: Sr is red and N is the left child of P
//            sibling = sibling(n);
//            setColor(sibling, getColor(parent(n)));
//            setColor(parent(n), Color.BLACK);
//            if (n == left(parent(n))) {
//                setColor(right(sibling), Color.BLACK);
//                rotateLeft(parent(n));
//            } else {
//                setColor(left(sibling), Color.BLACK);
//                rotateRight(parent(n));
//            }
//        }
    }

    /**
     * Get sibling node
     *
     * @param x
     * @return
     */
    private TreeNode<K, V> sibling(TreeNode<K, V> x) {
        TreeNode<K, V> parent = x.getParent();

        if (parent.getLeft() == x) {
            return parent.getRight();
        } else {
            return parent.getLeft();
        }
    }

    /**
     * Get uncle node
     *
     * @param x
     * @return
     */
    private TreeNode<K, V> uncle(TreeNode<K, V> x) {
        TreeNode<K, V> gp = grandparent(x);

        if (gp.getLeft() == x.getParent()) {
            // Uncle is on right
            return gp.getRight();
        } else {
            // Uncle is on left
            return gp.getLeft();
        }
    }

    /**
     * Get grandparent node
     *
     * @param x
     * @return
     */
    private TreeNode<K, V> grandparent(TreeNode<K, V> x) {
        return x.getParent().getParent();
    }

    private boolean isRed(TreeNode<K, V> x) {
        if (x == null) {
            // All leaves are black
            return false;
        }

        return (x.getColor() == Color.RED);
    }

    private Color getColor(TreeNode<K, V> x) {
        if (x == null) {
            return Color.BLACK;
        } else {
            return x.getColor();
        }
    }

    /**
     * Ensure all Red-Black tree properties are valid.
     *
     * @param x
     */
    private void rbFixUp(TreeNode<K, V> x) {
        while (true) {
            // Case 1: Root is always black
            if (x.isRoot() && x.getColor() == Color.RED) {
                x.setColor(Color.BLACK);
            }

            if (x.isRoot()) {
                root = x;
                break;
            }

            // Case 2: If parent is black do nothing
            if (x.getParent().getColor() == Color.BLACK) {
                // All RB properties are valid
                break;
            }

            // Parent is RED

            // Case 3: Uncle is red - Flip colors
            TreeNode<K, V> uncle = uncle(x);
            if (isRed(uncle)) {
                // Uncle pointer exists

                x.getParent().setColor(Color.BLACK);
                uncle.setColor(Color.BLACK);

                x = grandparent(x);
                x.setColor(Color.RED);

                // Continue with balancing starting on grandparent
                continue;
            }

            // Uncle is BLACK

            // Case 4: Transient case: X is the right child of a left child parent
            // or left child of a right child parent
            boolean isLeftChild = (x == x.getParent().getLeft());
            boolean isRightChild = !isLeftChild;
            TreeNode<K, V> grandParent = grandparent(x);
            boolean isLeftChildParent = (x.getParent() == grandParent.getLeft());
            boolean isRightChildParent = !isLeftChildParent;

            if (isLeftChild && isRightChildParent) {
                rotateRight(x.getParent());
                x = x.getRight();
                continue;
            }

            if (isRightChild && isLeftChildParent) {
                rotateLeft(x.getParent());
                x = x.getLeft();
                continue;
            }

            // Case 5: Parent is RED and uncle is BLACK
            TreeNode<K, V> parent = x.getParent();

            if (isLeftChildParent) {
                rotateRight(grandParent);

                // Flip colors
                parent.getRight().setColor(Color.RED);
                parent.setColor(Color.BLACK);
            } else {
                rotateLeft(grandParent);

                // Flip colors
                parent.getLeft().setColor(Color.RED);
                parent.setColor(Color.BLACK);
            }

            x = parent;
        }
    }

    /**
     * Rotate the x node to the left.
     *
     * @param x
     */
    private void rotateLeft(TreeNode<K, V> x) {
        // Rotation
        //
        //      |                        |
        //     [A]                      [B]
        //    /   \                    /   \
        //   [B]  [z]     left        [x]  [A]
        //  /   \        <--------        /   \
        // [x]  [y]                      [y]  [z]

        // 1. x.right != null

        if (x == null) {
            return;
        }

        if (x.getRight() == null) {
            throw new IllegalStateException("rotateLeft: Node doesn't have right child.");
        }

        TreeNode<K, V> right = x.getRight();

        if (x.getParent() != null) {
            if (x.getParent().getLeft() == x) {
                x.getParent().setLeft(right);
            } else {
                x.getParent().setRight(right);
            }
        }

        right.setParent(x.getParent());
        if (right.getParent() == null) {
            root = right;
        }
        x.setParent(right);

        x.setRight(right.getLeft());
        right.setLeft(x);
    }

    /**
     * Rotate the x node to the right
     *
     * @param x
     */
    private void rotateRight(TreeNode<K, V> x) {
        // Rotation
        //
        //      |                        |
        //     [A]        right         [B]
        //    /   \      -------->     /   \
        //   [B]  [z]                 [x]  [A]
        //  /   \                         /   \
        // [x]  [y]                      [y]  [z]

        // 1. x.left != null

        if (x == null) {
            return;
        }

        if (x.getLeft() == null) {
            throw new IllegalStateException("rotateRight: Node doesn't have left child.");
        }

        TreeNode<K, V> left = x.getLeft();

        if (x.getParent() != null) {
            if (x.getParent().getLeft() == x) {
                x.getParent().setLeft(left);
            } else {
                x.getParent().setRight(left);
            }
        }

        left.setParent(x.getParent());
        if (left.getParent() == null) {
            root = left;
        }
        x.setParent(left);

        x.setLeft(left.getRight());
        left.setRight(x);
    }

    /**
     * Validates all Red-Black Tree properties.
     * <p>
     * 1. Root is black.
     * 2. Every single path from root to one of the leafs contains equal numbers
     * of black nodes.
     * 3. There is no 2 adjacent red nodes.
     *
     * @throws java.lang.IllegalStateException In case if one of
     *                                         the properties is violated
     */
    public void validateRbProperties() {
        if (isEmpty()) {
            // Tree is empty nothing to verify
            return;
        }

        // 1. Root is black
        if (root.getColor() != Color.BLACK) {
            throw new IllegalStateException("Root is RED");
        }

        if (!root.isRoot()) {
            throw new IllegalStateException("Root has parent!");
        }

        // Check colors (recursively check is there 2 adjacent red nodes)
        checkColors(root);

        // All simple paths from root to any leaf node contains equal
        // number of black nodes

        int expectedBlackNodesCount = 0;

        // Calculate count of left most simple path black nodes
        for (TreeNode<K, V> x = root; x != null; x = x.getLeft()) {
            if (x.getColor() == Color.BLACK) {
                expectedBlackNodesCount++;
            }
        }

        checkBlackNodes(root, 0, expectedBlackNodesCount);
    }

    private void checkBlackNodes(TreeNode<K, V> x, int black, int expected) {
        if (x == null) {
            // End of one simple path
            if (black != expected) {
                throw new IllegalStateException("Find one simple path that contains: "
                        + black + " black nodes, expected: " + expected);
            }
            return;
        }

        if (x.getColor() == Color.BLACK) {
            black++;
        }

        checkBlackNodes(x.getLeft(), black, expected);
        checkBlackNodes(x.getRight(), black, expected);
    }

    private void checkColors(TreeNode<K, V> x) {
        if (x == null) {
            return;
        }

        if (x.isRed()) {
            if ((x.getLeft() != null) && (x.getLeft().getColor() == Color.RED)) {
                throw new IllegalStateException("Node with key: " +
                        x.getKey() + " has left RED child");
            } else if ((x.getRight() != null) && (x.getRight().getColor() == Color.RED)) {
                throw new IllegalStateException("Node with key: " +
                        x.getKey() + " has right RED child");
            }
        }

        checkColors(x.getLeft());
        checkColors(x.getRight());
    }

    /**
     * Print tree recursive to std out.
     *
     * @param node
     * @param deep
     * @param prefix
     */
    private void print(TreeNode<K, V> node, int deep, String prefix) {
        if (node == null) {
            return;
        }

        for (int i = 0; i < deep; ++i) {
            System.out.print("  "); // 4 spaces for each level
        }

        // [] -> Red node
        // () -> Black node

        if (node.getColor() == Color.RED) {
            System.out.print("[");
        } else {
            System.out.print("(");
        }
        System.out.print(prefix);
        System.out.print(node.key);
        if (node.getColor() == Color.RED) {
            System.out.print("]");
        } else {
            System.out.print(")");
        }
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
     * Retrieve element by key.
     *
     * @param key
     * @return {@code null} in case if {@code key} doesn't exists in tree.
     */
    public V find(K key) {
        if (isEmpty()) {
            return null;
        }
        if (key == null) {
            throw new IllegalArgumentException("key could not be null.");
        }

        V result = null;

        TreeNode<K, V> node = find(root, key);
        if (node != null) {
            result = node.getValue();
        }

        return result;
    }

    /**
     * Get min element in tree.
     *
     * @return
     */
    public V getMin() {
        if (isEmpty()) {
            return null;
        }
        TreeNode<K, V> min = findMin(root);
        return min.getValue();
    }

    /**
     * Get max element in tree.
     *
     * @return
     */
    public V getMax() {
        if (isEmpty()) {
            return null;
        }
        TreeNode<K, V> max = findMax(root);
        return max.getValue();
    }

    /**
     * Find min element in sub tree.
     *
     * @param node
     * @return
     */
    private TreeNode<K, V> findMin(TreeNode<K, V> node) {
        TreeNode<K, V> p = node;

        while (p.getLeft() != null) {
            p = p.getLeft();
        }

        return p;
    }

    /**
     * Find max element in sub tree.
     *
     * @param node
     * @return
     */
    private TreeNode<K, V> findMax(TreeNode<K, V> node) {
        TreeNode<K, V> p = node;

        while (p.getRight() != null) {
            p = p.getRight();
        }

        return p;
    }

    /**
     * Find element in binary search tree - standard algorithm.
     *
     * @param node
     * @param key
     * @return
     */
    private TreeNode<K, V> find(TreeNode<K, V> node, K key) {
        TreeNode<K, V> p = node;

        while (p != null) {
            if (key.equals(p.getKey())) {
                return p;
            }

            if (key.compareTo(p.getKey()) < 0) {
                // key is less than p.key the answer is on left
                p = p.getLeft();
            } else {
                // key is greater than p.key the answer is on right
                p = p.getRight();
            }
        }

        return null;
    }

    /**
     * @return {@code false} if tree doesn't contains any elements.
     */
    public boolean isEmpty() {
        return (root == null);
    }
}
