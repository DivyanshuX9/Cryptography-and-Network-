import java.net.*;
import java.io.*;

class Server{
    static String encrypt(String text, int key) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                c = (char) ((c - base + key) % 26 + base);
            }
            result.append(c);
        }
        return result.toString();
    }

    static String decrypt(String text, int key) {
        return encrypt(text, 26-key);
    }
    public static void main(String args[]) throws Exception{
        ServerSocket ss=new ServerSocket(1759);
        Socket s=ss.accept();

        DataInputStream in=new DataInputStream(s.getInputStream());

        String text=in.readUTF();
        int key=in.readInt();

        String encrypted=encrypt(text,key);
        String decrypted=decrypt(encrypted,key);

        System.out.println("Encrypted: "+encrypted);
        System.out.println("Decrypted: "+decrypted);
        
        ss.close();
    }
}