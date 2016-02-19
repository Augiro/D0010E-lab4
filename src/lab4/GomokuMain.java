package lab4;

import lab4.client.GomokuClient;
 import lab4.data.GomokuGameState;
 import lab4.gui.GomokuGUI;

 public class GomokuMain {
     public static void main(String[] args){
         if(args.length==0){
             GomokuClient client=new GomokuClient(4008);
             GomokuGameState gamestate = new GomokuGameState(client);
             GomokuGUI gui = new GomokuGUI(gamestate, client);
         } else{
             int port = Integer.parseInt(args[0]);
             GomokuClient client=new GomokuClient(port);
             GomokuGameState gamestate=new GomokuGameState(client);
             GomokuGUI gu1=new GomokuGUI(gamestate,client);
         }
     }
 }
