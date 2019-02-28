import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Driver class that demonstrates
 * and lets users use the BlockChain
 * class for fun.
 * Just a groovy repl.
 */
public class BlockChainDriver {

  //main--------------------------------------------------

  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.err, true);
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int initial;
    String input = new String();

    if (args.length != 1) {
      pen.println("Incorrect number of parameters");
      System.exit(1);
    }

    initial = Integer.parseInt(args[0]);
    BlockChain block = new BlockChain(initial);
    pen.println("Initial block:");
    pen.println(block);

    while (!input.contentEquals("quit")) {
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
      } else if (input.contentEquals("mine")) {
        int amount;
        pen.println("Amount transferred?");
        amount = Integer.parseInt(reader.readLine());
        pen.println("amount = " + amount
            + " nonce = " + block.mine(amount).getNonce());
      } else if (input.contentEquals("append")) {
        int amount;
        long nonce;
        pen.println("Amount transferred?");
        amount = Integer.parseInt(reader.readLine());
        pen.println("Nonce?");
        nonce = Long.parseLong(reader.readLine());
        block.append(new Block(block.getSize(), amount, block.getHash(), nonce));
      } else if (input.contentEquals("remove")) {
        block.removeLast();
      } else if (input.contentEquals("check")) {
        if (block.isValidBlockChain()) {
          pen.println("Chain is valid!");
        } else {
          pen.println("Chain is invalid!");
        }
      } else if (input.contentEquals("report")) {
        block.printBalances();
      } else if (input.contentEquals("quit")) {
        System.exit(1);
      }
    } // while loop
  }// main
}// class

