import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.PrintWriter;

public class BlockChain {

  public Node first;
  public Node last;
  public static int blockNum = -1;
  
  public static class Node {
    public Block value;
    public Node next;


    public Node(Block value, Node next) {
      this.value = value;
      this.next = next;
    }
  }

  public BlockChain(int initial) {
    blockNum++;
    this.first = new Node(new Block(BlockChain.blockNum, initial, null), null);
    this.last = this.first;
  }

  public Block mine(int amount) {
    // Implement
  }

  public int getSize() {
    return BlockChain.blockNum + 1;
  }

  public void append(Block blk) {
   Node node = new Node(blk, null);
   this.last.next = node;
   this.last = node; 
  }

  public boolean removeLast() {
    if (this.getSize() < 2) {
            return false;
    }

    Node temp = this.first;
    while (temp.next.next != null) {
            temp = temp.next;
    }

    temp.next = null;
    this.last = temp;

    return true;
  }

  public Hash getHash() {
    return this.last.value.getHash();
  }

  public boolean isValidBlockChain() {
    // Implement
  }

  public void printBalances() {

  }

  public String toString() {

  }

}
