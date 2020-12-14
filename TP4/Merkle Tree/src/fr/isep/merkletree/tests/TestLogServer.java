package fr.isep.merkletree.tests;

import fr.isep.merkletree.Hash;
import fr.isep.merkletree.LogServer;
import fr.isep.merkletree.MerkleTree;
import org.junit.Test;

import java.util.Collections;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

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
        MerkleTree merkleTreeLeft = new MerkleTree("test1", 4);
        MerkleTree merkleTreeRight = new MerkleTree("test2", 5);
        MerkleTree serverMerkleTree = new MerkleTree(merkleTreeLeft, merkleTreeRight);

        logServer.getTree().displayTree(4);
        serverMerkleTree.displayTree(5);
    }

    @Test
    public void testGenPath() {
        LogServer logServer = new LogServer("tests/testFile.txt");

        MerkleTree merkleTreeLeft = new MerkleTree("test1", 4);
        MerkleTree merkleTreeRight = new MerkleTree("test2", 5);
        MerkleTree merkleTreeExpected = new MerkleTree(merkleTreeLeft, merkleTreeRight);

        LinkedList<Hash> path = logServer.genPath(0);
        LinkedList<Hash> expectedPath = new LinkedList<>(Collections.singletonList(merkleTreeExpected.getRight().getHash()));

        assertEquals(expectedPath.toArray().length, path.toArray().length);
    }

    @Test
    public void testAppendOne() {
        LogServer logServer = new LogServer("tests/testFile.txt");
        logServer.append("test3");

        MerkleTree mLeft = new MerkleTree("test1", 4);
        MerkleTree mRight = new MerkleTree("test2", 5);
        MerkleTree merkleTreeMerge = new MerkleTree(mLeft, mRight);

        MerkleTree mRight2 = new MerkleTree("test3", 6);
        MerkleTree mAppend = new MerkleTree(merkleTreeMerge, mRight2);

        assertEquals(mAppend.getHash(), logServer.getTree().getHash());
    }
}
