package fr.isep.merkletree.tests;

import fr.isep.merkletree.Hash;
import fr.isep.merkletree.MerkleTree;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestMerkleTree {

    @Test
    public void testMerkleTreeDisplay() {
        String s1 = "1";
        String s2 = "1";

        MerkleTree merkleTree1 = new MerkleTree(s1, 1);
        MerkleTree merkleTree2 = new MerkleTree(s2, 2);

        merkleTree1.displayTree(0);
        merkleTree2.displayTree(0);
    }

    @Test
    public void testMerkleTreeConstructor() {
        String s1 = "tested";
        final int index = 0;

        MerkleTree merkleTree1 = new MerkleTree(s1, index);

        assertNull(merkleTree1.getLeft());
        assertNull(merkleTree1.getRight());
        assertEquals(merkleTree1.getStart(), index);
        assertEquals(merkleTree1.getEnd(), index);
        assertEquals(merkleTree1.getSize(), 1);
        assertEquals(merkleTree1.getPower(), 1);
        assertEquals(merkleTree1.getHash(), new Hash(s1));
    }
}
