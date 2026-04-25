import java.net.*;
import java.io.*;

class PlayfairServer {
    static char[][] matrix = new char[5][5];
    
    static void createMatrix(String key) {
        boolean[] used = new boolean[26];
        used['J'-'A'] = true;
        int k = 0;
        
        for(char c : key.toUpperCase().toCharArray()) {
            if(c >= 'A' && c <= 'Z' && !used[c-'A']) {
                matrix[k/5][k%5] = c;
                used[c-'A'] = true;
                k++;
            }
        }
        
        for(char c = 'A'; c <= 'Z' && k < 25; c++) {
            if(!used[c-'A']) {
                matrix[k/5][k%5] = c;
                k++;
            }
        }
    }
    
    static int[] findPos(char c) {
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                if(matrix[i][j] == c) return new int[]{i, j};
            }
        }
        return new int[]{0, 0};
    }
    
    static String encrypt(String text, String key) {
        createMatrix(key);
        text = text.toUpperCase().replace("J", "I");
        StringBuilder processed = new StringBuilder();
        
        for(int i = 0; i < text.length(); i++) {
            char current = text.charAt(i);
            processed.append(current);
            
            if(i + 1 < text.length() && current == text.charAt(i + 1)) {
                processed.append('X');
            }
        }
        
        if(processed.length() % 2 != 0) processed.append('X');
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < processed.length(); i += 2) {
            char a = processed.charAt(i);
            char b = processed.charAt(i + 1);
            
            int[] posA = findPos(a), posB = findPos(b);
            
            if(posA[0] == posB[0]) {
                sb.append(matrix[posA[0]][(posA[1]+1)%5]);
                sb.append(matrix[posB[0]][(posB[1]+1)%5]);
            } else if(posA[1] == posB[1]) {
                sb.append(matrix[(posA[0]+1)%5][posA[1]]);
                sb.append(matrix[(posB[0]+1)%5][posB[1]]);
            } else {
                sb.append(matrix[posA[0]][posB[1]]);
                sb.append(matrix[posB[0]][posA[1]]);
            }
        }
        return sb.toString();
    }
    
    static String decrypt(String text, String key) {
        createMatrix(key);
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i);
            char b = text.charAt(i+1);
            
            int[] posA = findPos(a), posB = findPos(b);
            
            if(posA[0] == posB[0]) {
                sb.append(matrix[posA[0]][(posA[1]+4)%5]);
                sb.append(matrix[posB[0]][(posB[1]+4)%5]);
            } else if(posA[1] == posB[1]) {
                sb.append(matrix[(posA[0]+4)%5][posA[1]]);
                sb.append(matrix[(posB[0]+4)%5][posB[1]]);
            } else {
                sb.append(matrix[posA[0]][posB[1]]);
                sb.append(matrix[posB[0]][posA[1]]);
            }
        }
        
        String result = sb.toString();
        StringBuilder clean = new StringBuilder();
        
        for(int i = 0; i < result.length(); i++) {
            char c = result.charAt(i);
            if(c == 'X' && i > 0 && i < result.length()-1 && 
               result.charAt(i-1) == result.charAt(i+1)) {
                continue;
            }
            clean.append(c);
        }
        
        return clean.toString().replaceAll("X+$", "");
    }
    
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5001);
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