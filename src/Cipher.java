import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Cipher {
    public static List<String> letters =
            Arrays.asList("a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z".split(","));

    public static String encrypt(String plain,int shift) {
        StringBuilder sb = new StringBuilder();

        plain = plain.toLowerCase(Locale.ROOT);
        for (int i = 0; i < plain.length(); i++) {
            String ch = plain.substring(i, i+1);
            int index = (letters.indexOf(ch) + shift) % 26;
            sb.append(letters.get(index));
        }

        return sb.toString();
    }

    public static String decrypt(String plain, int shift) {
        shift = 26 - shift;
        StringBuilder sb = new StringBuilder();

        plain = plain.toLowerCase(Locale.ROOT);
        for (int i = 0; i < plain.length(); i++) {
            String ch = plain.substring(i, i+1);
            int index = (letters.indexOf(ch) + shift) % 26;
            sb.append(letters.get(index));
        }

        return sb.toString();
    }
}
