import java.io.*;
import java.net.*;
import java.util.Scanner;

class RailFenceClient {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter message: ");
        String message = sc.nextLine();
        System.out.print("Enter number of rails: ");
        int rails = sc.nextInt();
        
        Socket s = new Socket("localhost", 5002);
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        
        out.writeUTF(message);
        out.writeInt(rails);
        
        s.close();
        sc.close();
    }
}