/**
 * Name: Danny Lee
 * ID: A17209209
 * Email:dtl001@ucsd.edu
 * File description: this file contains the CustomTester class which tests
 the edge cases of the methods for the MyBST class.
 */

import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;

public class CustomTester {

    MyBST<Integer, Integer> testTree = new MyBST<Integer, Integer>();

    @Before
    public void setup(){

        //testTree is
        //      5
        //   3    7
        // 2  4  6  8
        testTree.insert(5, 100);
        testTree.insert(3, 200);
        testTree.insert(7, 300);
        testTree.insert(4, 400);
        testTree.insert(2, 500);
        testTree.insert(6, 600);
        testTree.insert(8, 700);
    }

    //test insert when key== null
    @Test
    public void testInsert1(){
      try{
        testTree.insert(null, 1);
        fail();
      } catch(NullPointerException e) {}
    }

// test insert when inserting a key already in the tree
    @Test
    public void testInsert2(){
      testTree.insert(5, 30);
      assertEquals((Integer)30, testTree.root.getValue());
    }

//test insert when key is lower than the lowest key in the tree
    @Test
    public void testInsert3(){
      testTree.insert(1, 4);
      assertEquals((Integer)4, testTree.root.getLeft().getLeft().getLeft().getValue());
    }

//test insert when key is greater than greatest key in the tree
    @Test
    public void testInsert4(){
      testTree.insert(99, 3);
      assertEquals((Integer)3, testTree.root.getRight().getRight().
        getRight().getValue());
    }

//test remove on the root
    @Test
    public void testRemove1(){
      testTree.remove(5);
      assertEquals((Integer)6, testTree.root.getKey());
      assertEquals((Integer)7, testTree.root.getRight().getKey());
    }

//test remove on a leaf
    @Test
    public void testRemove2(){
      testTree.remove(2);
      assertEquals(null, testTree.root.getLeft().getLeft());
    }

//test remove on a node with two children
    @Test
    public void testRemove3(){
      testTree.remove(3);
      assertEquals((Integer)4, testTree.root.getLeft().getKey());
      assertEquals(null, testTree.root.getLeft().getRight());
    }

//test search when the key does not exist in the tree
    @Test
    public void testSearch1(){
      assertEquals(null, testTree.search(999));
    }

//test search when key is null
    @Test
    public void testSearch2(){
      assertEquals(null, testTree.search(null));
    }

//test inorder when the tree is empty
    @Test
    public void testInorder(){
      MyBST<Integer, Integer> testTree2 = new MyBST<Integer, Integer>();
      ArrayList<MyBST.MyBSTNode<Integer, Integer>> ansList=testTree2.inorder();
      assertEquals(0, ansList.size());
    }


}
