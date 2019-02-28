import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;

/**
 * The BlockChain class, contains a linked
 * data structure of blocks.
 * The Node class contains the block as well
 * as a reference to the next node.
 * The BlockChain itself keeps references to 
 * the first and the last nodes. 
 */
public class BlockChain {
  //fields------------------------------------------------------------

  public Node first;
  public Node last;
  public static int blockNum = -1;

  //Node--------------------------------------------------------------

  public static class Node {
    public Block value;
    public Node next;


    public Node(Block value, Node next) {
      this.value = value;
      this.next = next;
    }
  }

  //constructor------------------------------------------------------

  public BlockChain(int initial) throws NoSuchAlgorithmException{
    blockNum++;
    this.first = new Node(new Block(BlockChain.blockNum, initial, null), null);
    this.last = this.first;
  }

  //methods---------------------------------------------------------

  public Block mine(int amount) throws NoSuchAlgorithmException {
    long nonce = -1;
    boolean nonceFound = false;

    while (!nonceFound) {
      nonce++;

      Hash hash = new Hash(Block.calculateHash(BlockChain.blockNum + 1, amount, this.last.value.getHash(), nonce));

      if (hash.isValid()) {
        nonceFound = true;
      }
    }
    return new Block(BlockChain.blockNum + 1, amount, this.last.value.getHash(), nonce);
  }

  public int getSize() {
    return BlockChain.blockNum + 1;
  }

  public void append(Block blk) {
    Node node = new Node(blk, null);
    this.last.next = node;
    this.last = node; 
    BlockChain.blockNum++;
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

    BlockChain.blockNum--;

    return true;
  }

  public Hash getHash() {
    return this.last.value.getHash();
  }

  //Checks if hashes match up
  //if the hashes are valid, and
  //whether the total remains positive
  public boolean isValidBlockChain() {
    Node temp = this.first;
    int total = temp.value.getAmount();
    Hash prevHash;
    Hash currentHash = temp.value.getHash();

    if (!currentHash.isValid()) {
      return false;
    }

    while (temp.next != null) {
      temp = temp.next;
      total += temp.value.getAmount();
      prevHash = currentHash;
      currentHash = temp.value.getHash();

      if (total < 0 || !temp.value.getPrevHash().equals(prevHash) || !currentHash.isValid()) {
        return false;
      } 
    }

    return true;
  }

  public void printBalances() {
    PrintWriter pen = new PrintWriter(System.out, true);
    Node temp = this.first;
    int total = temp.value.getAmount();
    int initial = total;

    while (temp.next != null) {
      temp = temp.next;
      total += temp.value.getAmount();
    }

    pen.println("Alice: " + total + " Bob: " + (initial - total));
  }

  public String toString() {
    //Implement
    String retString = new String();
    PrintWriter pen = new PrintWriter(System.out, true);
    Node temp = this.first;

    while (temp != null) {
      retString += '\n';
      retString += temp.value.toString();
      temp = temp.next;
    }

    return retString;
  }

}
