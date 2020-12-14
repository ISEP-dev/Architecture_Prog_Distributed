package fr.isep.merkletree;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Hash {
    private byte[] hash;
    private MessageDigest digest;
    public int hashLength;

    public Hash() {
        try {
            digest = MessageDigest.getInstance("SHA-256");
            hashLength = digest.getDigestLength();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("No such algorithm");
        }
        hash = new byte[hashLength];
    }

    public Hash(String string) {
        this();
        final byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
        final int byteLength = bytes.length;
        final byte[] leaf = new byte[byteLength + 1];
        System.arraycopy(bytes, 0, leaf, 1, byteLength);
        leaf[0] = 0x00;
        this.hash = digest.digest(leaf);
    }

    public Hash(Hash h1, Hash h2) {
        this();
        final byte[] merge = new byte[1 + 2 * hashLength];
        merge[0] = 0x01;
        System.arraycopy(h1.hash, 0, merge, 1, hashLength);
        System.arraycopy(h2.hash, 0, merge, 1 + hashLength, hashLength);
        this.hash = digest.digest(merge);
    }

    @Override
    public String toString() {
        return Arrays.toString(hash);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Hash)) return false;
        final Hash otherHash = (Hash) obj;
        for (int i = 0; i < hash.length; i++) {
            if (hash[i] != otherHash.hash[i])
                return false;
        }
        return true;
    }
}
