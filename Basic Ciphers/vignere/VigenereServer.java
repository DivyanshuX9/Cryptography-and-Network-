import java.net.*;
import java.io.*;

class VigenereServer {
    static String encrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        key = key.toUpperCase();
        
        for(int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if(Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                c = (char)((c - base + key.charAt(j % key.length()) - 'A') % 26 + base);
                j++;
            }
            result.append(c);
        }
        return result.toString();
    }
    
    static String decrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        key = key.toUpperCase();
        
        for(int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if(Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                c = (char)((c - base - (key.charAt(j % key.length()) - 'A') + 26) % 26 + base);
                j++;
            }
            result.append(c);
        }
        return result.toString();
    }
    
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5005);
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