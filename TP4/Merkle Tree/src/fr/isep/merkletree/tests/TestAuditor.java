package fr.isep.merkletree.tests;

import fr.isep.merkletree.Auditor;
import fr.isep.merkletree.Hash;
import fr.isep.merkletree.LogServer;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class TestAuditor {
    public void AuditorConstructor() {
        LogServer logServer = new LogServer("tests/testFile.txt");
        Auditor auditor = new Auditor(logServer);

        assertEquals(auditor.getLogServer().getTree().getHash(), logServer.getTree().getHash());
    }

    @Test()
    public void buildHashEndNull() {
        LogServer l = new LogServer("tests/testFile.txt");
        Auditor a = new Auditor(l);

        Hash hash  = a.buildNewHash("test", 0, 0, new LinkedList<>());
        assertEquals(new Hash("test"), hash);
    }
}
