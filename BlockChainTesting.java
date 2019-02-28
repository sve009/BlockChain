import java.security.NoSuchAlgorithmException;

public class BlockChainTesting {
        public static void main(String[] args) {
                BlockChain blkChain = new BlockChain(500);
                System.out.println(blkChain);

                System.out.println("");

                Block blk1 = blkChain.mine(-150);
                System.out.println(blk1);
                blkChain.printBalances();

                System.out.println("");

                blkChain.append(blk1);
                System.out.println(blkChain);

                for (int i = 0; i < 3; i++) {
                        blkChain.append(blkChain.mine(-50));
                }

                System.out.println(blkChain);
                System.out.println("");

                System.out.println(blkChain.isValidBlockChain());
                System.out.println("");
                blkChain.printBalances();

                blkChain.removeLast();
                System.out.println(blkChain);
                System.out.println("");

                System.out.println("Size: " + blkChain.getSize());
                System.out.println("");

                System.out.println(blkChain.getHash());
                blkChain.printBalances();
        }
}

                
