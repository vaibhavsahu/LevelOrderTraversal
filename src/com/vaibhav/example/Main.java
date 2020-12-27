package com.vaibhav.example;


import java.util.*;

class TreeNode {
      public static final Map<Integer, List<Integer>> levelMap = new LinkedHashMap<>();
      int val;
      TreeNode left;
      TreeNode right;
      int level;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
          this.level = 0;
      }

      public static void addLevelInfo(TreeNode root, int level){
          if(root != null){
              root.level = level;
              List<Integer> list = new ArrayList<>();
              if(levelMap.containsKey(level)){
                  list.addAll(levelMap.get(level));
              }
              list.add(root.val);
              levelMap.put(level, list);
          }

          if(root.left != null){
              addLevelInfo(root.left, level+1);
          }
          if(root.right != null){
              addLevelInfo(root.right, level+1);
          }
      }

      public static List<Integer> getLeftView(TreeNode root){
          List<Integer> list = new ArrayList<>();

          levelMap.entrySet()
                  .stream()
                  .forEach( e -> list.add(e.getValue().get(0)));

          return list;
      }

    public static List<Integer> getRightView(TreeNode root){
        List<Integer> list = new ArrayList<>();

        levelMap.entrySet()
                .stream()
                .forEach( e -> list.add(e.getValue().get(e.getValue().size()-1)));

        return list;
    }
  }

public class Main {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        TreeNode.addLevelInfo(root, 0);
        System.out.println(TreeNode.levelMap);

        System.out.println(TreeNode.getLeftView(root));
        System.out.println(TreeNode.getRightView(root));

    }
}
