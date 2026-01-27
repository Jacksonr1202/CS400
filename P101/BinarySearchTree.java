public class BinarySearchTree<T extends Comparable<T>> implements SortedCollection<T> {
    protected BinaryNode<T> root = null;
    private int size = 0;

    public void insert(T data)throws NullPointerException{
        if(data == null){
            throw new NullPointerException("Cannot insert null data into the tree");
        }
        if(size == 0){
            root = new BinaryNode<>(data);
            size++;
            return;
        }
        BinaryNode<T> parentNode = root;
        BinaryNode<T> childNode = null;
        boolean nodeInserted = false;
        while(!nodeInserted){
            if(data.compareTo(parentNode.getData()) <= 0){
                childNode = parentNode.getLeft();
                if(childNode == null){
                    BinaryNode<T> newNode = new BinaryNode<>(data);
                    newNode.setUp(parentNode);
                    parentNode.setLeft(newNode);
                    size++;
                    nodeInserted = true;
                }
                else{
                    parentNode = childNode;
                }
            }
            else if(data.compareTo(parentNode.getData()) > 0){
                childNode = parentNode.getRight();
                if(childNode == null){
                    BinaryNode<T> newNode = new BinaryNode<>(data);
                    newNode.setUp(parentNode);
                    parentNode.setRight(newNode);
                    size++;
                    nodeInserted = true;
                }
                else{
                    parentNode = childNode;
                }
            }
        }
    }
    public boolean contains(Comparable<T> find){
        BinaryNode<T> currentNode = root;
        while(currentNode != null){
            if(find.compareTo(currentNode.getData()) == 0){
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
    public int size(){
        return this.size;
    };
    public boolean isEmpty(){
        return this.size == 0;
    };
    public void clear(){
        this.root = null;
        this.size = 0;
    };
    public BinarySearchTree(){
        super();
    }
}