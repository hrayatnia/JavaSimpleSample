import java.net.*;
import java.io.*;

public class SocketClient {

   public static void main(String [] args) {
      String serverName = args[0];//first argument in terminal is server name
      								//(localhost or server ip or domain name)
      int port = Integer.parseInt(args[1]);//server port that we want to connect to.
      try {
         System.out.println("Connecting to " + serverName + " on port " + port);//print status or 
         																		//we can log that
         Socket client = new Socket(serverName, port);//create socket client object

         System.out.println("Just connected to " + client.getRemoteSocketAddress());//connect to server
         OutputStream outToServer = client.getOutputStream();
         DataOutputStream out = new DataOutputStream(outToServer);

         out.writeUTF("Message from :" + client.getLocalSocketAddress());
         InputStream inFromServer = client.getInputStream();
         DataInputStream in = new DataInputStream(inFromServer);

         System.out.println("Server result :" + in.readUTF());
         client.close();
      }catch(IOException e) {
         e.printStackTrace();
      }
   }
}
