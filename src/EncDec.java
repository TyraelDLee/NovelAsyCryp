import java.util.Random;

/**
 * The encryption/decryption class for numbers.
 *
 * @author tyraellee
 * */
public class EncDec {
    static long x,z,y;
    int n;
    //x and z are public keys, y is the private key. n is the index for x*y last n bit = z.

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

    public static long[] enc(long x, long z, int n, int t){
        Random ran_gen = new Random();
        int s = ran_gen.nextInt((int)Math.pow(10,n-1))+(int)Math.pow(10,n-2);
        if (s%10==0) s = s+1;
        String xs = (x*s)+"";
        long p = Long.parseLong(xs.substring(xs.length()-n));
        String zst = (z*s+t)+"";
        long v = Long.parseLong(zst.substring(zst.length()-n));
        System.out.println("p:"+p+", v:"+v);
        return new long[]{p,v};
    }

    public static long[] enc(long x, long z, int n, int t, int s){
        String xs = (x*s)+"";
        long p = Long.parseLong(xs.substring(xs.length()-n));
        long tmp = z*s+t;
        String zst = tmp+"";
        long v = Long.parseLong(zst.substring(zst.length()-n));
//        System.out.println("p:"+p+", v:"+v);
//        System.out.println("x*s="+xs+", z*s+t = "+tmp+", z = "+z);
        return new long[]{p,v};
    }

    public static long dec(long p, long v, long y, int n){
        System.out.println("p:"+p+", v:"+v);
        String yp = (y*p)+"";
        long u = yp.length()<=4?Long.parseLong(yp):Long.parseLong(yp.substring(yp.length()-n));
        long t = v-u;
//        System.out.println("u: "+u+", t: "+t);
        return t;
    }

    public static void pickKey(){
        x = keyGen(4);
        y = keyGen(4);
        z = keyGenZ(x,y,4);
    }

    public static void main(String[] args) {
        String message = "test encryption";
        Random random_gen = new Random(); // True random here.
        pickKey();
        long w = random_gen.nextInt();
        int t = 1_000_000;
        long[] sec = enc(x,z,5,12388);
        dec(sec[0], sec[1],y,5);
    }
}
