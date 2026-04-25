import java.net.*;
import java.io.*;
import java.util.Scanner;

class HillClient {
    public static void main(String[] args)throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter message to encrypt: ");
        String message = sc.nextLine();
        
        Socket s=new Socket("localhost",5006);
        DataOutputStream out=new DataOutputStream(s.getOutputStream());

        out.writeUTF(message);
        s.close();
        sc.close();
    }
}

