
public class BlockChain {
  public Node first;
  public Node last;
  public static int blockNum = -1;
  public Block block;
  
  public static class Node {
    public BlockChain value;
    public Node next;


    public Node(BlockChain value, Node next) {
      this.value = value;
      this.next = next;
    }

  }

  public BlockChain(int initial) {
    blockNum++;
    this.block = new Block(blockNum, initial, null);
    this.first = new Node(this, null);
    this.last = this.first;
  }

  public Block mine(int amount) {
    // Implement
  }

  public int getSize() {
    // Implement
  }

  public void append(Block blk) {
    // Implement
  }

  public boolean removeLast() {
    // Implement
  }

  public Hash getHash() {
    // Implement
  }

  public boolean isValidBlock() {
    // Implement
  }

  public void printBalances() {

  }

  public String toString() {

  }

}
