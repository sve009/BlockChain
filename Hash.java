public class Hash {
        private byte[] data;

        public Hash(byte[] data) {
                this.data = data;
        }

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
