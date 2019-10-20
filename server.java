import java.net.*; 
import java.io.*; 
public class server{
    public static void main(String[] a){
        int clientCount=1;
        try{
            ServerSocket serversocket = new ServerSocket(8000);
            System.out.println("Server Started");
            while(true){
                Socket socket = serversocket.accept(); //listen for connection request
                new EchoThread(socket).start();
            }
            
        }
        catch(IOException ex)
            { System.err.println(ex); }
    }
}
        
class EchoThread extends Thread {
    protected Socket socket;

    public EchoThread(Socket clientSocket) {
        this.socket = clientSocket;
    }

    public void run() {
        try{
            DataInputStream ip = new DataInputStream(socket.getInputStream()); //create data input & output streams – input from client
            DataOutputStream op = new DataOutputStream(socket.getOutputStream()); //create data input & output streams – output to client 
            String hello="Hello";
            byte[] hellob=new byte[hello.length()];
            for (int i = 0; i < hello.length(); i++) {
                hellob[i]=(byte)hello.charAt(i);
            }
            op.write(hellob);
            int count=ip.available();
            if(count==0){
                while(count==0){
                    count=ip.available();
                }
            }
            //System.out.println("Count="+count);
            byte[] bs = new byte[count];
         
            // read data into buffer
            ip.read(bs);

            // for each byte in the buffer
            for (byte b:bs) {

               // convert byte into character
               char c = (char)b;

               // print the character
               System.out.print(c);
            }
                System.out.println("");
                
                while(true){
                    try{
                        count=ip.available();
            if(count==0){
                while(count==0){
                    count=ip.available();
                }
            }
            //System.out.println("Count="+count);
            byte[] bs2 = new byte[count];
         
            // read data into buffer
            ip.read(bs2);

            // for each byte in the buffer
            String temp="";
            for (byte b:bs2) {

               // convert byte into character
               char c = (char)b;

               // print the character
               //System.out.print(c);
               temp+=c;
            }
            if(temp.equalsIgnoreCase("bye")){
                socket.close();
                        System.out.println("Socket is closed");
            return;
            }
            
                    }
                    catch (IOException e) {
                e.printStackTrace();
                return;
            }
                }
        }
        catch(IOException ex)
            { System.err.println(ex); }
        
    }
}

