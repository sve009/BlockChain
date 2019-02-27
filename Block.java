import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Block {
        private int num;
        private int data;
        private Hash prevHash;
        private long nonce;
        private Hash hash;

        public Block(int num, int amount, Hash prevHash) {
               this.num = num;
               this.data = amount;
               this.prevHash = prevHash;

               //Generate hash
        }

        public Block(int num, int amount, Hash prevHash, long nonce) {
                this.num = num;
                this.data = amount;
                this.prevHash = prevHash;
                this.nonce = nonce;

                //Generate hash
        }

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
                //Implement
        }
}
