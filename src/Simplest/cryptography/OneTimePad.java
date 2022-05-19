package simplest.cryptography;

import java.util.Random;

public class OneTimePad {

    // function that return a sequence of *length* random bytes
    private static byte[] randomKey(int length) {
        byte[] dummy = new byte[length];
        Random random = new Random();
        random.nextBytes(dummy);
        return dummy;
    }

    // one time pad need keys that are: the same length as the string we want to
    // encrypt, completely random, secret

    // the XOR operation has a nice property:
    // A ^ B = C then C ^ B = A and C ^ A = B
    // so now we know how to encrypt and decrypt a string

    public static KeyPair encrypt(String original) {
        byte[] originalBytes = original.getBytes();
        byte[] dummyKey = randomKey(originalBytes.length);
        byte[] encryptedKey = new byte[originalBytes.length];

        for (int i = 0; i < originalBytes.length; i++) {
            encryptedKey[i] = (byte) (dummyKey[i] ^ originalBytes[i]);
        }

        return new KeyPair(dummyKey, encryptedKey);
    }

    public static String decrypt(KeyPair kp) {
        byte[] decrypted = new byte[kp.key1.length];
        for (int i = 0; i < kp.key2.length; i++) {
            decrypted[i] = (byte) (kp.key1[i] ^ kp.key2[i]);
        }
        return new String(decrypted);
    }

    public static void main(String... strings) {
        KeyPair kp = encrypt("Hello world!");
        System.out.println(decrypt(kp));
    }
}
