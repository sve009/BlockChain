import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;

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

  public BlockChain(int initial) throws NoSuchAlgorithmException{
    blockNum++;
    this.first = new Node(new Block(BlockChain.blockNum, initial, null), null);
    this.last = this.first;
  }

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
    Node temp = this.first;
    int total = temp.value.getAmount();
    Hash prevHash = temp.value.getHash();
    Hash currentHash;

    if (!prevHash.isValid()) {
            return false;
    }

    while (temp.next != null) {
            temp = temp.next;
            total += temp.value.getAmount();
            currentHash = temp.value.getHash();

            if (total < 0 || !currentHash.equals(prevHash) || !currentHash.isValid()) {
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
            retString += temp.value.toString();
            retString += '\n';
            temp = temp.next;
    }

    return retString;
  }

}
