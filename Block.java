import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.ByteBuffer;

/**
 * A Block class that keeps track of each individual
 * unit of the BlockChain.
 * Has two constructors, with the first finding a nonce
 * and the second requiring one to be found beforehand.
 * Also includes the static hashing method.
 */
public class Block {
  //fields-------------------------------------------------------------

  private int num;
  private int data;
  private Hash prevHash;
  private long nonce = -1;
  private Hash hash;

  //constructors-------------------------------------------------------

  public Block(int num, int amount, Hash prevHash) throws NoSuchAlgorithmException {
    this.num = num;
    this.data = amount;
    this.prevHash = prevHash;

    //Generate hash
    boolean nonceFound = false;
    long nonce = -1;

    while (this.nonce == -1) {
      nonce++;
      Hash hash = new Hash(Block.calculateHash(num, amount, prevHash, nonce));
      if (hash.isValid()) {
        this.nonce = nonce; 
        this.hash = hash;
      }
    }
  }


  public Block(int num, int amount, Hash prevHash, long nonce) throws NoSuchAlgorithmException {
    this.num = num;
    this.data = amount;
    this.prevHash = prevHash;
    this.nonce = nonce;

    //Generate hash
    this.hash = new Hash(Block.calculateHash(num, amount, prevHash, nonce));
  }

  //methods---------------------------------------------------------------

  public int getNum() {
    return this.num;
  }

  public int getAmount() {
    return this.data;
  }

  public long getNonce() {
    return this.nonce;
  }

  public Hash getPrevHash() {
    return this.prevHash;
  }

  public Hash getHash() {
    return this.hash;
  }

  public String toString() {
    return "Block " + this.getNum() + " (Amount: " + this.getAmount() 
      + ", Nonce: " + this.getNonce() + ", prevHash: " + this.getPrevHash()
      + ", hash: " + this.getHash() + ")";
  }

  public static byte[] calculateHash(int num, int data, Hash prevHash, long nonce) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("sha-256");

    md.update(ByteBuffer.allocate(4).putInt(num).array());
    md.update(ByteBuffer.allocate(4).putInt(data).array());
    if (prevHash != null) {
      md.update(prevHash.getData());
    }
    md.update(ByteBuffer.allocate(4).putLong(nonce).array());

    return md.digest();
  }
}
