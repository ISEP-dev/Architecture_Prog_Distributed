package fr.isep.merkletree.tests;

import fr.isep.merkletree.Hash;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestHash {
    @Test
    public void testHashLength() {
        String s1 = "1";
        String s2 = "1";

        Hash hash1 = new Hash(s1);
        Hash hash2 = new Hash(s2);
        System.out.println("hash1: " + hash1.toString() + " -> " + hash1.hashLength);
        System.out.println("hash2: " + hash2.toString() + " -> " + hash2.hashLength);

        assertEquals(hash1.hashLength, 32);
        assertEquals(hash2.hashLength, 32);
    }
}
