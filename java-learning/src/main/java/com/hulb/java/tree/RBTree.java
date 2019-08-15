package com.hulb.java.tree;

/**
 * @author hulb
 * @date 2019-05-30 20:40
 * <p>
 * 红黑树(Red-Black Tree，简称R-B Tree)，它一种特殊的二叉查找树。
 * 红黑树是特殊的二叉查找树，意味着它满足二叉查找树的特征：任意一个节点所包含的键值，大于等于左孩子的键值，小于等于右孩子的键值。
 * 除了具备该特性之外，红黑树还包括许多额外的信息。
 * <p>
 * 红黑树的每个节点上都有存储位表示节点的颜色，颜色是红(Red)或黑(Black)。
 * 红黑树的特性:
 * (1) 每个节点或者是黑色，或者是红色。
 * (2) 根节点是黑色。
 * (3) 每个叶子节点是黑色。 [注意：这里叶子节点，是指为空的叶子节点！]
 * (4) 如果一个节点是红色的，则它的子节点必须是黑色的。
 * (5) 从一个节点到该节点的子孙节点的所有路径上包含相同数目的黑节点。
 * <p>
 * 关于它的特性，需要注意的是：
 * 第一，特性(3)中的叶子节点，是只为空(NIL或null)的节点。
 * 第二，特性(5)，确保没有一条路径会比其他路径长出俩倍。因而，红黑树是相对是接近平衡的二叉树。
 * <p>
 * <p>
 * <p>
 * 基本操作：
 * 是添加、删除和
 * 旋转：左旋 和 右旋
 */
public class RBTree<T extends Comparable<T>> {

    /**
     * 根节点
     */
    private RBTree<T> mRoot;

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    /**
     * RBTree是红黑树对应的类，
     * RBTNode是红黑树的节点类
     * @param <T>
     */
    public class RBTNode<T extends Comparable<T>> {

        /**
         * 颜色
         */
        boolean color;
        /**
         * 关键字（键值）
         */
        T key;
        /**
         * 左孩子
         */
        RBTNode<T> left;
        /**
         * 右孩子
         */
        RBTNode<T> right;
        /**
         * 父节点
         */
        RBTNode<T> parent;

        public RBTNode(T key, boolean color, RBTNode<T> parent, RBTNode<T> left, RBTNode<T> right) {
            this.key = key;
            this.color = color;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 对红黑树的节点(x)进行左旋转：对x左旋意味着将x变成一个左节点
     *
     * 左旋示意图(对节点x进行左旋)：
     *      px                              px
     *     /                               /
     *    x                               y
     *   /  \      --(左旋)-.           / \                #
     *  lx   y                          x  ry
     *     /   \                       /  \
     *    ly   ry                     lx  ly
     *
     *
     *
     */
    private void leftRotate(RBTNode<T> x){

        //设置x的右孩子为y
        RBTNode<T> y = x.right;

        //将y的左孩子 设为 x 的右孩子
        //如果y的左孩子
        x.right = y.left;
        if(y.left!=null){
            y.left.parent = x;
        }

    }


}
