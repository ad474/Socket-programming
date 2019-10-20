import java.net.*; 
import java.io.*; 
import java.util.Scanner;
public class client {
    public static void main ( String[] a) {
        double radius = 10.0; 
        try
            {
                
            Socket clients = new Socket("localhost",8000);
            DataInputStream ip = new DataInputStream(clients.getInputStream()); //create an input stream to receive data from the server 
            DataOutputStream op = new DataOutputStream(clients.getOutputStream()); //create an output stream to send data to the server 
            String hi="Hi";
            byte[] hib=new byte[hi.length()];
            for (int i = 0; i < hi.length(); i++) {
                hib[i]=(byte)hi.charAt(i);
            }
            op.write(hib);
            //op.flush();
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
                
                
            Scanner input=new Scanner(System.in);
            String s=input.next();
            while(!s.equalsIgnoreCase("bye")){
                s=input.next();
            }
            
            
            byte[] byeb=new byte[s.length()];
            for (int i = 0; i < s.length(); i++) {
                byeb[i]=(byte)s.charAt(i);
            }
            op.write(byeb);
            
//            while(true){
//                
//                String s=input.next();
//                if(s.equalsIgnoreCase("bye")){
//                    clients.close(); //close the socket
//                    System.out.println("Socket closed");
//                    break;
//                }
//            
//            }
            
        } 
        catch(IOException ex)
            {
            System.err.println(ex);
        } 
    }
}