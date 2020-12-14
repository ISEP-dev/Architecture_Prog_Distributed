package fr.isep.merkletree.tests;

import fr.isep.merkletree.LogServer;
import fr.isep.merkletree.MerkleTree;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class TestLogServer {
    @Test
    public void testLogServer() {
        MerkleTree merkleTree = new MerkleTree("testing", 1);

        LogServer logServer = new LogServer(merkleTree);

        logServer.getTree().displayTree(0);

        assertEquals(logServer.getTree(), merkleTree);
        assertEquals(logServer.currentRootHash(), merkleTree.getHash());
    }

    @Test
    public void testLogServerFile() {
        LogServer logServer = new LogServer("tests/testFile.txt");
        MerkleTree merkleTree = new MerkleTree("Warning", 7);
        LogServer logServerMerkle = new LogServer(merkleTree);

        logServer.getTree().displayTree(0);
        logServerMerkle.getTree().displayTree(0);
    }
}
