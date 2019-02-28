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
    for (int i = 0; i < 3; i++) {
      if (this.data[i] != 0)  {
        return false;
      }
    }

    return true;
  }

  public String toString() {
    String retString = new String();
    for (int i = 0; i < this.data.length; i++) {
      byte b = this.data[i];
      int thing = Byte.toUnsignedInt(b);
      retString += String.format("%x", thing);
    }

    return retString;
  }

  public boolean equals(Hash other) {
    String str = this.toString();
    return (str.equals(other.toString()));
  }
}
