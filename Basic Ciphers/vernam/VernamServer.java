import java.net.*;
import java.io.*;

class VernamServer {
    static String encrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            char k = key.charAt(i % key.length());
            result.append((char)(c ^ k));
        }
        return result.toString();
    }
    
    static String decrypt(String text, String key) {
        return encrypt(text, key); // XOR is its own inverse
    }
    
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5004);
        Socket s = ss.accept();
        
        DataInputStream in = new DataInputStream(s.getInputStream());
        
        String message = in.readUTF();
        String key = in.readUTF();
        
        String encrypted = encrypt(message, key);
        String decrypted = decrypt(encrypted, key);
        
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
        
        ss.close();
    }
}