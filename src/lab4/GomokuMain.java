package lab4;

import lab4.client.GomokuClient;
 import lab4.data.GomokuGameState;
 import lab4.gui.GomokuGUI;

 public class GomokuMain {
     public static void main(String[] args){
         int port = args.length == 1 ? Integer.parseInt(args[0]) : 4008;
         GomokuClient client=new GomokuClient(port);
         GomokuGameState gamestate = new GomokuGameState(client);
         GomokuGUI gui = new GomokuGUI(gamestate, client);
     }
 }
