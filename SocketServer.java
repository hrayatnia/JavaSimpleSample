import java.net.*;//import network library (Socket Usage)
import java.io.*;// import I/O library (everything else)

public class SocketServer extends Thread {
   private ServerSocket serverSocket;
    /**
     * @author : Amirhesam Rayatnia <rayatnia@stu.nit.ac.ir>
     * @link : https://github.com/rayatnia/JavaSocketTutorial
     */
   public SocketServer(int port) throws IOException {
      serverSocket = new ServerSocket(port);//java socket object
      serverSocket.setSoTimeout(100000);//set timeout program will
       //break when longest request time was bigger than 10 seconds
   }

   public void run() {//Implement Run function from Thread Class
      while(true) {//run until timeout raise.
         try {
            System.out.println("Waiting for client on port " +
               serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();

            System.out.println("Just connected to " + server.getRemoteSocketAddress());
            DataInputStream in = new DataInputStream(server.getInputStream());

            System.out.println(in.readUTF());
            DataOutputStream out = new DataOutputStream(server.getOutputStream());
            out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress()
               + "\nGoodbye!");
            server.close();

         }catch(SocketTimeoutException s) {
            System.out.println("Socket timed out!");
            break;
         }catch(IOException e) {
            e.printStackTrace();
            break;
         }
      }
   }

   public static void main(String [] args) {
      int port = Integer.parseInt(args[0]);//get port in terminal from user
      try {
         Thread t = new SocketServer(port);//create Custom Socket Class That I Wrote
         t.start();//Start Thread in Socket
      }catch(IOException e)
         e.printStackTrace();
      }
   }
}
