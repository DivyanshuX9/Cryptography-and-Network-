import java.net.*;
import java.io.*;
import java.util.*;

class RowColServer {
    static String encrypt(String text, String key) {
        int cols = key.length();
        int rows = (int)Math.ceil((double)text.length() / cols);
        
        char[][] grid = new char[rows][cols];
        int k = 0;
        
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                grid[i][j] = (k < text.length()) ? text.charAt(k++) : 'X';
            }
        }
        
        Integer[] order = new Integer[cols];
        for(int i = 0; i < cols; i++) order[i] = i;
        
        Arrays.sort(order, (a, b) -> Character.compare(key.charAt(a), key.charAt(b)));
        
        StringBuilder result = new StringBuilder();
        for(int col : order) {
            for(int i = 0; i < rows; i++) {
                result.append(grid[i][col]);
            }
        }
        
        return result.toString();
    }
    
    static String decrypt(String text, String key) {
        int cols = key.length();
        int rows = text.length() / cols;
        
        Integer[] order = new Integer[cols];
        for(int i = 0; i < cols; i++) order[i] = i;
        
        Arrays.sort(order, (a, b) -> Character.compare(key.charAt(a), key.charAt(b)));
        
        char[][] grid = new char[rows][cols];
        int k = 0;
        
        for(int col : order) {
            for(int i = 0; i < rows; i++) {
                grid[i][col] = text.charAt(k++);
            }
        }
        
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                result.append(grid[i][j]);
            }
        }
        
        return result.toString().replaceAll("X+$", "");
    }
    
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5003);
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