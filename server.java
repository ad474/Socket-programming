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
                new EchoThread(socket,clientCount++).start();
            }
            
        }
        catch(IOException ex)
            { System.err.println(ex); }
    }
}
        
class EchoThread extends Thread {
    protected Socket socket;
    int sno;

    public EchoThread(Socket clientSocket,int c) {
        this.socket = clientSocket;
        this.sno=c;
    }

    public void run() {
        try{
            DataInputStream ip = new DataInputStream(socket.getInputStream());
            DataOutputStream op = new DataOutputStream(socket.getOutputStream()); 
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
            byte[] bs = new byte[count];
            ip.read(bs);
            for (byte b:bs) {
               char c = (char)b;
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
                    byte[] bs2 = new byte[count];
                    ip.read(bs2);
                    String temp="";
                    for (byte b:bs2) {
                        char c = (char)b;
                        temp+=c;
                    }
                    if(temp.equalsIgnoreCase("bye")){
                        socket.close();
                        System.out.println("Connection with client "+sno+" closed");
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