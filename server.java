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
            }
            
        }
        catch(IOException ex)
            { System.err.println(ex); }
    }
}