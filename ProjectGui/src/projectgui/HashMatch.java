package projectgui;

import java.util.Random;
import java.math.BigInteger;

/**
 * <h1>Class for Hash-Match</h1>
 * This class match two string using robin-karp algorithm
 * @author Mursalin
 * @version 1.0.0
 */
public class HashMatch {

    private String pat;
    private long patHash;
    private int patlen;
    private long prime;
    private int R; //radix
    private long RM; //R^(patlen-1) % prime 

    /**
     * Finds if there is a match between two given string
     * @param txt String One
     * @param pat String Two
     * @return String 1 for match otherwise 0
     */
    public String RabinKarp(String txt, String pat) {
        
        String temp;
        if(txt.length()<pat.length()){
            temp=txt;
            txt=pat;
            pat=temp;
        }
        
        
        this.pat = pat;
        R = 256;
        patlen = pat.length();
        prime = longRandomPrime();
        RM = 1;                                //precompute R^(M-1) % Q for use in removing leading digit
        for (int i = 1; i <= patlen - 1; i++) {
            RM = (R * RM) % prime;
        }
        patHash = hash(pat, patlen);
        int pos = search(txt);
        if (pos == -1) {
            return "0";
        } else {
            //System.out.println("Pattern found at position : " + pos);
            return "1";
        }

    }

    private long hash(String key, int M) {
        long h = 0;
        for (int j = 0; j < M; j++) {
            h = (R * h + key.charAt(j)) % prime;
        }
        return h;
    }

    private boolean check(String txt, int i) {
        for (int j = 0; j < patlen; j++) {
            if (pat.charAt(j) != txt.charAt(i + j)) {
                return false;
            }
        }
        return true;
    }

    //Funtion to check for exact match
    
    private int search(String txt) {
        int N = txt.length();
        if (N < patlen) {
            return N;
        }
        long txtHash = hash(txt, patlen);

        if ((patHash == txtHash) && check(txt, 0)) {
            return 0;
        }

        //check for hash match. if hash match then check for exact match
        for (int i = patlen; i < N; i++) {

            // Remove leading digit, add trailing digit, check for match. 
            txtHash = (txtHash + prime - RM * txt.charAt(i - patlen) % prime) % prime;
            txtHash = (txtHash * R + txt.charAt(i)) % prime;
            // match
            int offset = i - patlen + 1;
            if ((patHash == txtHash) && check(txt, offset)) {
                return offset;
            }

        }
        return -1;
    }

    private static long longRandomPrime() {

        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();

    }

}
