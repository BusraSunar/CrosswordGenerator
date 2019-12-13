import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tree {
    Node root;
    ArrayList<String > possible;

    Tree(){
        possible=new ArrayList<>();
        ArrayList<String> arrayList=new ArrayList<>();
        File file = new File("wordList.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc.hasNextLine())
            arrayList.add(sc.nextLine().toLowerCase());

        String arr[] = new String[arrayList.size()];
        for (int i=0;i<arr.length;i++){
            arr[i]=arrayList.get(i).toLowerCase();
        }
        this.root = this.insertLevelOrder(arr, this.root, 0);
    }
    // Tree Node
    static class Node {
        String data;
        Node left, right;
        Node(String data)
        {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // Function to insert nodes in level order
    public Node insertLevelOrder(String[] arr, Node root,
                                 int i)
    {
        // Base case for recursion
        if (i < arr.length) {
            Node temp = new Node(arr[i]);
            root = temp;

            // insert left child
            root.left = insertLevelOrder(arr, root.left,
                    2 * i + 1);

            // insert right child
            root.right = insertLevelOrder(arr, root.right,
                    2 * i + 2);
        }
        return root;
    }

    public int calculateTreeHeight(Node root){
        if(root == null){
            return 0;
        }else{
            int lsh = calculateTreeHeight(root.left);
            int rsh = calculateTreeHeight(root.right);
            return Math.max(lsh, rsh) + 1;
        }
    }
    public void possibleOutcomes(String aim){
        possible.removeAll(possible);
        int height = calculateTreeHeight(root);
        for(int i = 0; i < height; i++){
            levelOrderTraversal(root, i,aim);
        }
    }

    // Method for breadth first search
    public void levelOrderTraversal(Node node, int level,String aim){
        if(node == null){
            return;
        }
        if(level == 0) {
            Pattern pi = Pattern.compile(aim);
            Matcher ma = pi.matcher(node.data);
            if (ma.matches()) {
                possible.add(node.data);
            }
        }else{
            levelOrderTraversal(node.left, level-1,aim);
            levelOrderTraversal(node.right, level-1,aim);
        }
    }


    // Driver program to test above function
    public static void main(String args[])
    {

        size gui= new size();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(450,350);
        gui.setVisible(true);
        gui.setTitle("Main");


    }
}
