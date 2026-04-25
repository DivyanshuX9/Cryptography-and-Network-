import java.net.*;
import java.io.*;
import java.util.Scanner;

class VernamClient {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter message: ");
        String message = sc.nextLine();
        System.out.print("Enter key: ");
        String key = sc.nextLine();
        
        Socket s = new Socket("localhost", 5004);
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        
        out.writeUTF(message);
        out.writeUTF(key);
        
        s.close();
        sc.close();
    }
}