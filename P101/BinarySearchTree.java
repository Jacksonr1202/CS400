public class BinarySearchTree<T extends Comparable<T>> implements SortedCollection<T> {
    protected BinaryNode<T> root = null;
      /**
     * Performs the naive binary search tree insert algorithm to recursively
     * insert the provided newNode (which has already been initialized with a
     * data value) into the provided tree/subtree. When the provided subtree
     * is null, this method does nothing. 
     */
    protected void insertHelper(BinaryNode<T> newNode, BinaryNode<T> subtree) {
        if(subtree == null){
            return;
        }
        if(newNode.getData().compareTo(subtree.getData()) <= 0){
            if(subtree.getLeft() == null){
                newNode.setUp(subtree);
                subtree.setLeft(newNode);
            }
            else{
                insertHelper(newNode, subtree.getLeft());
            }
        }
        else{
            if(subtree.getRight() == null){
                newNode.setUp(subtree);
                subtree.setRight(newNode);
            }
            else{
                insertHelper(newNode, subtree.getRight());
            }
        }
    }
    @Override
    public void insert(T data)throws NullPointerException{
        if(data == null){
            throw new NullPointerException("Cannot insert null data into the tree");
        }
        if(root == null){
            root = new BinaryNode<>(data);
            return;
        }
        BinaryNode<T> newNode = new BinaryNode<>(data);
        insertHelper(newNode, root);
    }
    @Override
    public boolean contains(Comparable<T> find){
        BinaryNode<T> currentNode = root;
        while(currentNode != null){
            if(find.equals(currentNode.getData())){
                return true;
            }
            else if(find.compareTo(currentNode.getData()) < 0){
                currentNode = currentNode.getLeft();
            }
            else{
                currentNode = currentNode.getRight();
            }
        }
        return false;
    }
    
    @Override
    public int size(){
        return sizeHelper(root);
    }

    private int sizeHelper(BinaryNode<T> node){
        if(node == null){
            return 0;
        }
        return 1 + sizeHelper(node.getLeft()) + sizeHelper(node.getRight());
    }
    @Override
    public boolean isEmpty(){
        return root == null;
    }
    @Override
    public void clear(){
        this.root = null;
    }

    public BinarySearchTree(){
        super();
    }

    public boolean test1(){
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(3);
        tree.insert(7);
        tree.insert(12);
        tree.insert(18);
        tree.insert(5);
        boolean expectedContains = tree.contains(3) && tree.contains(7) && tree.contains(10) && tree.contains(18);
        BinaryNode<Integer> leftChild = tree.root != null ? tree.root.getLeft() : null;
        BinaryNode<Integer> rightChild = tree.root != null ? tree.root.getRight() : null;
        boolean expectedLeftLeaf = leftChild != null && leftChild.getLeft() != null && leftChild.getLeft().getData().equals(3);
        boolean expectedRightLeaf = rightChild != null && rightChild.getRight() != null && rightChild.getRight().getData().equals(18);
        BinaryNode<Integer> duplicateParent = leftChild != null ? leftChild.getLeft() : null;
        BinaryNode<Integer> duplicateNode = duplicateParent != null ? duplicateParent.getRight() : null;
        boolean expectedDuplicatePlacement = duplicateNode != null && duplicateNode.getData().equals(5) && duplicateNode.getUp() == duplicateParent;
        boolean expectedSize = tree.size() == 8;
        return expectedContains && expectedLeftLeaf && expectedRightLeaf && expectedDuplicatePlacement && expectedSize;
    }

    public boolean test2(){
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.insert("mango");
        tree.insert("apple");
        tree.insert("peach");
        tree.insert("banana");
        tree.insert("pear");
        boolean expectedContains = tree.contains("mango") && tree.contains("apple") && tree.contains("pear");
        boolean expectedMissing = !tree.contains("orange");
        boolean expectedSize = tree.size() == 5;
        boolean expectedRoot = tree.root.getData().equals("mango");
        return expectedContains && expectedMissing && expectedSize && expectedRoot;
    }

    public boolean test3(){
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(2);
        tree.insert(1);
        tree.insert(3);
        tree.insert(4);
        boolean sizeBeforeClear = tree.size() == 4;
        tree.clear();
        boolean emptyAfterClear = tree.isEmpty() && tree.size() == 0;
        tree.insert(6);
        tree.insert(5);
        boolean sizeAfterRebuild = tree.size() == 2 && tree.contains(6) && tree.contains(5);
        return sizeBeforeClear && emptyAfterClear && sizeAfterRebuild;
    }

    public static void main(String[] args){
        BinarySearchTree<Integer> harness = new BinarySearchTree<>();
        boolean[] results = {harness.test1(), harness.test2(), harness.test3()};
        for(int i = 0; i < results.length; i++){
            System.out.println("test" + (i + 1) + ": " + (results[i] ? "pass" : "FAIL"));
        }
    }
}