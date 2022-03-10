import java.util.ArrayList;


public class MyBST<K extends Comparable<K>,V>{
    MyBSTNode<K,V> root = null;
    int size = 0;

    public int size(){
        return size;
    }

    public V insert(K key, V value){
        if(key == null){
          throw new NullPointerException();
        }
        MyBSTNode<K,V> curr = root;
        if(curr == null){
          curr = new MyBSTNode(key, value, null);
          root = curr;
          return null;
        }
        while(curr.getKey().compareTo(key) != 0){
          if(curr.getKey().compareTo(key) > 0){ // search the left subtree
            if(curr.getLeft() == null){
              break;
            }
            else{
              curr = curr.getLeft();
            }
          }
          else{ // search the right subtree
            if(curr.getRight() == null){
              break;
            }
            else{
              curr = curr.getRight();
            }
          }
        }

        if(curr.getKey().compareTo(key) == 0){
          V oldVal = curr.getValue();
          curr.setValue(value);
          return oldVal;
        }
        else if(curr.getKey().compareTo(key) > 0){

          MyBSTNode<K,V> newNode = new MyBSTNode(key, value, curr);
          curr.setLeft(newNode);
          size++;
        }
        else{
          MyBSTNode<K,V> newNode = new MyBSTNode(key, value, curr);
          curr.setRight(newNode);
          size++;
        }
        return null;
    }

    public V search(K key){
      if(key == null){
        return null;
      }
      MyBSTNode<K,V> curr = root;
      while(curr.getKey() != key){
        if(curr.getKey().compareTo(key) > 0){ // search the left subtree
          if(curr.getLeft() == null){
            break;
          }
          else{
            curr = curr.getLeft();
          }
        }
        else{ // search the right subtree
          if(curr.getRight() == null){
            break;
          }
          else{
            curr = curr.getRight();
          }
        }
      }
      if(curr.getKey() == key){
        return curr.getValue();
      }
      return null;
    }

    public V remove(K key){
      MyBSTNode<K,V> curr = root;
      //find the key
      while(curr.getKey() != key){
        if(curr.getKey().compareTo(key) > 0){ // search the left subtree
          if(curr.getLeft() == null){
            break;
          }
          else{
            curr = curr.getLeft();
          }
        }
        else{ // search the right subtree
          if(curr.getRight() == null){
            break;
          }
          else{
            curr = curr.getRight();
          }
        }
      }
      if(curr.getKey() != key){ // key doesnt exist
        return null;
      }

      //key exists
      if(curr.getLeft() != null && curr.getRight() != null){ // 2 children
        K newKey = curr.successor().getKey();
        V newValue = curr.successor().getValue();
        curr.setKey(newKey);
        curr.setValue(newValue);
        if(curr.successor().getParent().getLeft().getKey().compareTo(
                                            curr.successor().getKey()) == 0){
          curr.successor().getParent().setLeft(null);
        }
        else{
          curr.successor().getParent().setRight(null);
        }
        curr.successor().setParent(null);
      }
      else if(curr.getLeft() != null && curr.getRight() == null){ //only left
        curr.getLeft().setParent(curr.getParent());
        if(curr.getParent().getKey().compareTo(curr.getKey()) > 0){
          curr.getParent().setLeft(curr.getLeft());
        }
        else if(curr.getParent().getKey().compareTo(curr.getKey()) < 0){
          curr.getParent().setRight(curr.getLeft());
        }
      }
      else if(curr.getLeft() == null && curr.getRight() != null){ //only right
        curr.getRight().setParent(curr.getParent());
        if(curr.getParent().getKey().compareTo(curr.getKey()) > 0){
          curr.getParent().setLeft(curr.getRight());
        }
        else if(curr.getParent().getKey().compareTo(curr.getKey()) < 0){
          curr.getParent().setRight(curr.getRight());
        }
      }
      else{ // no children
        if(curr.getParent().getKey().compareTo(curr.getKey()) > 0){
          curr.getParent().setLeft(null);
        }
        else if(curr.getParent().getKey().compareTo(curr.getKey()) < 0){
          curr.getParent().setRight(null);
        }
        curr.setParent(null);
      }
      size--;
      return curr.getValue();
    }

    public ArrayList<MyBSTNode<K, V>> inorder(){
        ArrayList<MyBSTNode<K, V>> ansList = new ArrayList<MyBSTNode<K, V>>();
        MyBSTNode<K,V> curr = root;
        MyBSTNode<K,V> succ;
        if(curr == null){
          return ansList;
        }
        while(curr.getLeft() != null){
          curr = curr.getLeft();
        }
        succ = curr.successor();
        while(succ != null && succ.getKey().compareTo(curr.getKey()) > 0){
          ansList.add(curr);
          curr = curr.successor();
          succ = succ.successor();
        }
        ansList.add(curr);
        return ansList;
    }

    static class MyBSTNode<K,V>{
        private static final String TEMPLATE = "Key: %s, Value: %s";
        private static final String NULL_STR = "null";

        private K key;
        private V value;
        private MyBSTNode<K,V> parent;
        private MyBSTNode<K,V> left = null;
        private MyBSTNode<K,V> right = null;

        /**
         * Creates a MyBSTNode<K,V> storing specified data
         * @param key the key the MyBSTNode<K,V> will
         * @param value the data the MyBSTNode<K,V> will store
         * @param parent the parent of this node
         */
        public MyBSTNode(K key, V value, MyBSTNode<K, V> parent){
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        /**
         * Return the key stored in the the MyBSTNode<K,V>
         * @return the key stored in the MyBSTNode<K,V>
         */
        public K getKey(){
            return key;
        }

        /**
         * Return data stored in the MyBSTNode<K,V>
         * @return the data stored in the MyBSTNode<K,V>
         */
        public V getValue(){
            return value;
        }

        /**
         * Return the parent
         * @return the parent
         */
        public MyBSTNode<K,V> getParent(){
            return parent;
        }

        /**
         * Return the left child
         * @return left child
         */
        public MyBSTNode<K,V> getLeft(){
            return left;
        }

        /**
         * Return the right child
         * @return right child
         */
        public MyBSTNode<K,V> getRight(){
            return right;
        }

        /**
         * Set the key stored in the MyBSTNode<K,V>
         * @param newKey the key to be stored
         */
        public void setKey(K newKey){
            this.key = newKey;
        }

        /**
         * Set the data stored in the MyBSTNode<K,V>
         * @param newValue the data to be stored
         */
        public void setValue(V newValue){
            this.value = newValue;
        }

        /**
         * Set the parent
         * @param newParent the parent
         */
        public void setParent(MyBSTNode<K,V> newParent){
            this.parent = newParent;
        }

        /**
         * Set the left child
         * @param newLeft the new left child
         */
        public void setLeft(MyBSTNode<K,V> newLeft){
            this.left = newLeft;
        }

        /**
         * Set the right child
         * @param newRight the new right child
         */
        public void setRight(MyBSTNode<K,V> newRight){
            this.right = newRight;
        }

        /**
         * TODO: add inline comments for this method to demonstrate your
         *   understanding of this method. The predecessor can be implemented
         *   in a similar way.
         *
         * This method returns the in order successor of current node object.
         * It can be served as a helper method when implementing inorder().
         * @return the successor of current node object
         */
        public MyBSTNode<K, V> successor(){
            if(this.getRight() != null){
                MyBSTNode<K,V> curr = this.getRight(); //start in the right
                //subtree
                while(curr.getLeft() != null){
                    curr = curr.getLeft();
                }
                //get the leftmost leaf of the right subtree
                return curr;
            }
            else{//if there is no greater child, the successor is one of the
              //ancestor nodes.
                MyBSTNode<K,V> parent = this.getParent();
                MyBSTNode<K,V> curr = this;
                while(parent != null && curr == parent.getRight()){
                  //keep looking at ancestors until you find one that is the
                  //left child of its parent
                    curr = parent;
                    parent = parent.getParent();
                }
                return parent;
            }
        }

        public MyBSTNode<K, V> predecessor(){
            if(this.getLeft() != null){
              //if there's a left subtree, get the rightmost node from it
                MyBSTNode<K,V> curr = this.getLeft();
                while(curr.getRight() != null){
                  curr = curr.getRight();
                }
                return curr;
            }
            //if there's no left subtree, return the first ancestor whose on
            //the left side
            else{
              MyBSTNode<K,V> parent = this.getParent();
              MyBSTNode<K,V> curr = this;
              while(parent != null && curr == parent.getLeft()){
                  curr = parent;
                  parent = parent.getParent();
              }
              return parent;
            }

        }

        /** This method compares if two node objects are equal.
         * @param obj The target object that the currect object compares to.
         * @return Boolean value indicates if two node objects are equal
         */
        public boolean equals(Object obj){
            if (!(obj instanceof MyBSTNode))
                return false;

            MyBSTNode<K,V> comp = (MyBSTNode<K,V>)obj;

            return( (this.getKey() == null ? comp.getKey() == null :
                this.getKey().equals(comp.getKey()))
                && (this.getValue() == null ? comp.getValue() == null :
                this.getValue().equals(comp.getValue())));
        }

        /**
         * This method gives a string representation of node object.
         * @return "Key:Value" that represents the node object
         */
        public String toString(){
            return String.format(
                    TEMPLATE,
                    this.getKey() == null ? NULL_STR : this.getKey(),
                    this.getValue() == null ? NULL_STR : this.getValue());
        }
    }

}
