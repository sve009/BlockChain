import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BlockChainDriver {

        public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.err, true);
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int initial;
    String input;
    if (args.length != 1) {
      pen.println("Incorrect number of parameters");
      System.exit(1);
    }
while (input != "quit") {
    initial = Integer.parseInt(args[0]);
    BlockChain block = new BlockChain(initial);
    pen.println(block);
    pen.println("Command?");
    input = reader.readLine();
    if (input.contentEquals("help")) {
      pen.println("Valid commands:\n" + "    mine: discovers the nonce for a given transaction\n"
          + "    append: appends a new block onto the end of the chain\n"
          + "    remove: removes the last block from the end of the chain\n"
          + "    check: checks that the block chain is valid\n"
          + "    report: reports the balances of Alice and Bob\n"
          + "    help: prints this list of commands\n" + "    quit: quits the program");
    }
    if (input.contentEquals("mine")) {
      int amount;
      pen.println("Amount transferred?");
      amount = Integer.parseInt(reader.readLine());
      pen.println(block.mine(amount));
    }


    if (input.contentEquals("append")) {
      int amount;
      int nonce;
      pen.println("Amount transferred?");
      amount = Integer.parseInt(reader.readLine());
      pen.println("Nonce?");
      nonce = Integer.parseInt(reader.readLine());
      Block blck = new Block(); // unsure what to do here
    }

    if (input.contentEquals("remove")) {
      block.removeLast();
    }
    if (input.contentEquals("check")) {
      if (block.isValidBlockChain()) {
        pen.println("Chain is valid!");
      } else {
        pen.println("Chain is invalid!");
      }
    }
    if (input.contentEquals("report")) {
      block.printBalances();
    }
    if (input.contentEquals("quit")) {
      System.exit(1);
    }
} // while loop
  }// main
}// class

