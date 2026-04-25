
import java.net.*;
import java.util.*;
import java.io.*;

public class Client {
    public static void main(String args[])throws Exception{
        Socket s = new Socket("localhost",1759);
        
        DataOutputStream out=new DataOutputStream(s.getOutputStream());
        
        Scanner i=new Scanner(System.in);
        System.out.print("Enter text: ");
        out.writeUTF(i.nextLine());

        System.out.print("Enter key: ");
        out.writeInt(i.nextInt());
        
        s.close();
        i.close();
    }
}
