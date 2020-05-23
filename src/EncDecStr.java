
import java.nio.charset.StandardCharsets;
import java.util.Random;
/**
 * The encryption/decryption class for string and char
 * Currently only support ASCII
 *
 * More charset will be supported soon,
 * and enc/dec files will be supported soon.
 *
 * @author tyraellee
 * */
public class EncDecStr {
    private static int[] transform(String message){
        byte[] arr = message.getBytes(StandardCharsets.US_ASCII);
        int[] out = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            out[i] = arr[i];
        }
        return out;
    }

    private static String transform(int[] arr){
        byte[] string = new byte[arr.length];
        for (int i = 0; i < arr.length; i++) {
            string[i] = (byte)arr[i];
        }
        return new String(string,StandardCharsets.US_ASCII);
    }

    public static int[][] enc(long x, long z, int n, String s){
        int[] message = transform(s);
        int[][]pv = new int[message.length][2];
        for (int i = 0; i < message.length; i++) {
            long[] enc = EncDec.enc(x,z,n,message[i]);
            pv[i][0] = (int)enc[0];
            pv[i][1] = (int)enc[1];
        }
        return pv;
    }

    public static void dec(int[][]pv, long y, int n){
        int[] message = new int[pv.length];
        for (int i = 0; i < pv.length; i++) {
            message[i] = (int)EncDec.dec(pv[i][0],pv[i][1],y,n);
        }
        String out = transform(message);
        System.out.println(out);
    }

    public static long keyGen(int n){
        long key_out = 0;
        int[] key = new int[n];
        for (int i = 0; i < n; i++) {
            Random ket_gen = new Random();
            key[i] = ket_gen.nextInt(10);
        }
        int index = n-1;
        for (int bit : key){
            key_out += (bit * Math.pow(10,index));
            index--;
        }
        return key_out;
    }

    public static long  keyGenZ(long x, long y, int n){
        String tmp = (x * y)+"";
        long z = Long.parseLong(tmp.substring(tmp.length()-n));
        return Long.parseLong(tmp.substring(tmp.length()-n));
    }

    public static void main(String[] args) {
        String message = "Test enc and dec.";
        long x,z,y;
        x = keyGen(4);
        y = keyGen(4);
        z = keyGenZ(x,y,4);

        dec(enc(x,z,4,message), y, 4);
    }
}
