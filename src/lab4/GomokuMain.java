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
             GomokuClient client2=new GomokuClient(4007);
             GomokuGameState gamestate2 = new GomokuGameState(client2);
             GomokuGUI gui2 = new GomokuGUI(gamestate2, client2);
         } else{
             int port = Integer.parseInt(args[0]);
             GomokuClient client=new GomokuClient(port);
             GomokuGameState gamestate=new GomokuGameState(client);
             GomokuGUI gu1=new GomokuGUI(gamestate,client);
         }
     }
 }
