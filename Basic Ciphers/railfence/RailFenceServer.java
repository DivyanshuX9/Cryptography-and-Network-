import java.net.*;
import java.io.*;

class RailFenceServer {
    static String encrypt(String text, int rails) {
        if(rails == 1) return text;
        
        StringBuilder[] fence = new StringBuilder[rails];
        for(int i = 0; i < rails; i++) fence[i] = new StringBuilder();
        
        int rail = 0, dir = 1;
        for(char c : text.toCharArray()) {
            fence[rail].append(c);
            rail += dir;
            if(rail == rails-1 || rail == 0) dir = -dir;
        }
        
        StringBuilder result = new StringBuilder();
        for(StringBuilder sb : fence) result.append(sb);
        return result.toString();
    }
    
    static String decrypt(String text, int rails) {
        if(rails == 1) return text;
        
        int[] railLens = new int[rails];
        int rail = 0, dir = 1;
        
        for(int i = 0; i < text.length(); i++) {
            railLens[rail]++;
            rail += dir;
            if(rail == rails-1 || rail == 0) dir = -dir;
        }
        
        StringBuilder[] fence = new StringBuilder[rails];
        for(int i = 0; i < rails; i++) fence[i] = new StringBuilder();
        
        int pos = 0;
        for(int i = 0; i < rails; i++) {
            fence[i].append(text.substring(pos, pos + railLens[i]));
            pos += railLens[i];
        }
        
        StringBuilder result = new StringBuilder();
        rail = 0; dir = 1;
        int[] indices = new int[rails];
        
        for(int i = 0; i < text.length(); i++) {
            result.append(fence[rail].charAt(indices[rail]++));
            rail += dir;
            if(rail == rails-1 || rail == 0) dir = -dir;
        }
        
        return result.toString();
    }
    
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5002);
        Socket s = ss.accept();
        
        DataInputStream in = new DataInputStream(s.getInputStream());
        
        String message = in.readUTF();
        int rails = in.readInt();
        
        String encrypted = encrypt(message, rails);
        String decrypted = decrypt(encrypted, rails);
        
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
        
        ss.close();
    }
}