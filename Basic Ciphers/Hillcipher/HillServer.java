import java.net.*;
import java.io.*;

class HillServer {

    static int modInv(int a){
        for(int i=1;i<26;i++) if((a*i)%26==1) return i;
        return -1;
    }

    static String encrypt(String t,int[][] k){
        t=t.toUpperCase();
        if(t.length()%2!=0) t+="X";
        StringBuilder r=new StringBuilder();

        for(int i=0;i<t.length();i+=2){
            int x=t.charAt(i)-'A';
            int y=t.charAt(i+1)-'A';
            r.append((char)(((k[0][0]*x+k[0][1]*y)%26)+'A'));
            r.append((char)(((k[1][0]*x+k[1][1]*y)%26)+'A'));
        }
        return r.toString();
    }

    static String decrypt(String t,int[][] k){
        int det=(k[0][0]*k[1][1]-k[0][1]*k[1][0])%26;
        if(det<0) det+=26;
        int detInv=modInv(det);
        
        int[][] inv={{k[1][1]*detInv%26,-k[0][1]*detInv%26},{-k[1][0]*detInv%26,k[0][0]*detInv%26}};
        for(int i=0;i<2;i++)for(int j=0;j<2;j++)if(inv[i][j]<0)inv[i][j]+=26;
        
        StringBuilder r=new StringBuilder();
        for(int i=0;i<t.length();i+=2){
            int x=t.charAt(i)-'A';
            int y=t.charAt(i+1)-'A';
            r.append((char)(((inv[0][0]*x+inv[0][1]*y)%26)+'A'));
            r.append((char)(((inv[1][0]*x+inv[1][1]*y)%26)+'A'));
        }
        return r.toString().replaceAll("X+$", "");
    }

    public static void main(String[] args)throws Exception{
        ServerSocket ss=new ServerSocket(5006);
        Socket s=ss.accept();

        DataInputStream in=new DataInputStream(s.getInputStream());

        int[][] key={{3,3},{2,5}};
        String message=in.readUTF();
        String encrypted=encrypt(message,key);
        String decrypted=decrypt(encrypted,key);
        
        System.out.println("Encrypted message: "+encrypted);
        System.out.println("Decrypted message: "+decrypted);
        
        ss.close();
    }
}
