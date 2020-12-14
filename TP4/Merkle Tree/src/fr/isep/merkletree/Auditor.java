package fr.isep.merkletree;

import java.util.LinkedList;

public class Auditor {
    LogServer logServer;
    Hash hashRoot;
    int size;

    public Auditor(LogServer logServer) {
        this.logServer = logServer;
        this.hashRoot = logServer.tree.hash;
        this.size = logServer.tree.size;
    }

    public boolean isMember(String event, int index) {
        LinkedList<Hash> auditPath = logServer.genPath(index);
        final Hash hash = buildNewHash(event, index, logServer.tree.end, auditPath);
        return hash.equals(hashRoot);
    }

    public Hash buildNewHash(String event, int index, int end, LinkedList<Hash> path) {
        if (end == 0) {
            return new Hash(event);
        }
        final int middle = Integer.highestOneBit(end);
        if (index < middle) {
            Hash right = path.removeLast();
            Hash left = buildNewHash(event, index, middle - 1, path);
            return new Hash(left, right);
        }
        Hash left = path.removeLast();
        Hash right = buildNewHash(event, index - middle, end - middle, path);
        return new Hash(left, right);
    }

    public LogServer getLogServer() {
        return logServer;
    }

    public Hash getHashRoot() {
        return hashRoot;
    }

    public int getSize() {
        return size;
    }
}
