package com.hulb.java.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *https://blog.csdn.net/weixin_42289193/article/details/81741756
 * 深度优先遍历
 *
 * https://www.javazhiyin.com/12976.html
 * 先序中序后序
 *
 *           1
 *          / \
 *         2   3
 *        / \ / \
 *      4  5 6  7
 *
 *
 * 广度 1 2 3 4 5 6 7
 * 前序 1 2 4 5 3 6 7
 * 中序 4 2 5 1 6 3 7
 * 后序 4 5 2 6 7 3 1
 *
 * @author hulb
 * @date 2019-08-14 16:23
 */
public class DeepFirstSort {

    public static void main(String[] args){

        /**
         * 构建一颗二叉树
         */
        TreeNode head=new TreeNode(1);
        TreeNode second=new TreeNode(2);
        TreeNode three=new TreeNode(3);
        TreeNode four=new TreeNode(4);
        TreeNode five=new TreeNode(5);
        TreeNode six=new TreeNode(6);
        TreeNode seven=new TreeNode(7);
        head.rightNode=three;
        head.leftNode=second;
        second.rightNode=five;
        second.leftNode=four;
        three.rightNode=seven;
        three.leftNode=six;

        // preOrder(head);
        System.out.println("前序 深度优先遍历结果：");
        preOrder2(head);

       // preOrder(head);
        System.out.println("\n中序 深度优先遍历结果：");
        inOrder2(head);

        System.out.println("\n后序 深度优先遍历结果：");
        postOrder2(head);

        System.out.print("\n广度优先遍历结果：");
        new DeepFirstSort().broadFirstSearch(head);

        System.out.print("\n深度优先遍历结果：");
        new DeepFirstSort().deepFirstSearch(head);


    }


    /**
     * 使用队列实现，先进先出。
     * 1.
     * @param treeNode
     */
    public void broadFirstSearch(TreeNode treeNode){
        if(treeNode == null){
            return;
        }
        Queue<TreeNode> treeNodeQueue = new LinkedList<>();
        treeNodeQueue.add(treeNode);
        while (!treeNodeQueue.isEmpty()){
            //将节点从队列中取出来
            TreeNode node = treeNodeQueue.poll();
            System.out.print(node.data + " ");

            if(node.leftNode!=null){
                treeNodeQueue.add(node.leftNode);
            }

            if(node.rightNode!=null){
                treeNodeQueue.add(node.rightNode);
            }
        }

    }

    /**
     * 使用栈实现，先进后出。
     *
     * 深度优先遍历分为先序遍历，中序遍历和后续遍历。
     *
     * 先序遍历：先访问根，在访问左子树，最后访问右子树，总结就是“根左右”；
     * 中序遍历：先访问左子树，再访问根，最后访问右子树，总结就是“左根右”；
     * 后序遍历：先访问左子树，再访问右子树，最后访问根，总结就是“左右根”；
     *
     * 通常采用递归的方式实现遍历，非递归方式需要结合栈（后进先出）的特点实现。
     * 1.
     * @param treeNode
     */
    public void deepFirstSearch(TreeNode treeNode){
        if(treeNode == null){
            return;
        }
        Stack<TreeNode> treeNodeStack = new Stack<>();
        treeNodeStack.add(treeNode);
        while (!treeNodeStack.isEmpty()){
            //将节点从队列中取出来
            TreeNode node = treeNodeStack.pop();
            System.out.print(node.data + " ");

            if(node.rightNode!=null){
                treeNodeStack.push(node.rightNode);
            }

            if(node.leftNode!=null){
                treeNodeStack.push(node.leftNode);
            }
        }


    }



    /**
     * 广度优先遍历是使用队列实现的
     * @param nodeHead
     */
    public void BroadFirstSearch(TreeNode nodeHead) {
        if(nodeHead==null) {
            return;
        }
        Queue<TreeNode> myQueue=new LinkedList<>();
        myQueue.add(nodeHead);
        while(!myQueue.isEmpty()) {
            TreeNode node=myQueue.poll();
            System.out.print(node.data+" ");
            if(null!=node.leftNode) {
                //深度优先遍历，我们在这里采用每一行从左到右遍历
                myQueue.add(node.leftNode);
            }
            if(null!=node.rightNode) {
                myQueue.add(node.rightNode);
            }

        }
    }

    /**
     * 递归法实现先序遍历，并打印
     * @param root
     */
    public static void preOrder(TreeNode root){
        if(root == null) {
            return;
        }
        System.out.print(root.data + " ");
        preOrder(root.leftNode);
        preOrder(root.rightNode);
    }


    /**
     * 递归法实现中序遍历，并打印
     * @param root
     */
    public static void inOrder(TreeNode root){
        if(root == null) return;
        //用递归的方法一直找到树的最左侧
        inOrder(root.leftNode);
        System.out.print(root.data + " ");
        inOrder(root.rightNode);
    }

    /**
     * 递归方法实现后序遍历，并打印
     * @param root
     */
    public static void postOrder(TreeNode root){
        if(root == null) {
            return;
        }
        postOrder(root.leftNode);
        postOrder(root.rightNode);
        System.out.print(root.data + " ");
    }

    /**
     * 非递归方法实现先序遍历，并打印
     * @param root
     */
    public static void preOrder2(TreeNode root){
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode currentRoot = null;
        stack.push(root);
        while (!stack.isEmpty()){
            currentRoot = stack.pop();
            System.out.print(currentRoot.data + " ");
            //栈是先入后出，需要先入栈右分支
            if (currentRoot.rightNode != null){
                stack.push(currentRoot.rightNode);
            }
            if (currentRoot.leftNode != null){
                stack.push(currentRoot.leftNode);
            }
        }
    }

    /**
     * 非递归方法实现中序遍历，并打印
     * 1，2，4 接连入栈。4先出栈，但1 比 2 先入栈 2 先出栈 。然后遍历2的右节点。
     * @param root
     */
    public static void inOrder2(TreeNode root){
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode currentRoot = root;
        while (currentRoot !=null || !stack.isEmpty()){
            //遍历到二叉树的最左侧
            while (currentRoot != null){
                stack.push(currentRoot);
                currentRoot = currentRoot.leftNode;
            }
            currentRoot = stack.pop();
            System.out.print(currentRoot.data + " ");
            currentRoot = currentRoot.rightNode;
        }
    }

    /**
     * 非递归方法实现后序遍历，并打印
     * @param root
     */
    public static void postOrder2(TreeNode root){
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode currentRoot = root;
        TreeNode rightRoot = null;
        while (currentRoot != null || !stack.isEmpty()){
            //递归找到最左边的节点
            while (currentRoot != null){
                stack.push(currentRoot);
                currentRoot = currentRoot.leftNode;
            }
            //如果找不到最左边的节点的话，就把该节点弹出来
            currentRoot = stack.pop();
            //当前节点没有右节点   或   上一个结点（已经输出的结点）是当前结点的右结点，
            // 则输出当前结点（当5输出后，5就是2的右结点，也是上一个结点，
            // 所以此时可以把2这个root输出了）
            while (currentRoot.rightNode == null || currentRoot.rightNode == rightRoot){
                System.out.print(currentRoot.data + " ");
                rightRoot = currentRoot;
                if (stack.isEmpty()){
                    return;
                }
                currentRoot = stack.pop();
            }
            //还有未遍历的右侧节点
            stack.push(currentRoot);
            currentRoot = currentRoot.rightNode;
        }
    }

}
