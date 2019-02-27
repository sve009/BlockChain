/**
 * A Wrapper for byte[] with some helping functions 
 * to determine validity.
 */
public class Hash {
  //fields------------------------------------

  private byte[] data;

  //constructor-------------------------------

  public Hash(byte[] data) {
    this.data = data;
  }

  //Methods-----------------------------------

  public byte[] getData() {
    return this.data;
  }

  public boolean isValid() {
    String str = this.toString();

    for (int i = 0; i < 3; i++) {
      if (str.charAt(i) != 0)  {
        return false;
      }
    }

    return true;
  }

  public String toString() {
    String retString = new String();
    for (byte b: this.data) {
      retString += String.format("%x", Byte.toUnsignedInt(b));
    }

    return retString;
  }

  public boolean equals(Object other) {
    String str = this.toString();
    return (str.equals(other.toString()));
  }
}
