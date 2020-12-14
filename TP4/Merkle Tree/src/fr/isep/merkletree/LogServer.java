package fr.isep.merkletree;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class LogServer {
    MerkleTree tree;

    public LogServer(MerkleTree tree) {
        this.tree = tree;
    }

    public LogServer(String file) {
        Queue<MerkleTree> merkleTreeQueue = new LinkedList<>();
        Scanner input;
        try {
            input = new Scanner(new FileReader(file));
            int index = 0;
            while (input.hasNextLine()) {
                String line = input.nextLine();
                MerkleTree merkle = new MerkleTree(line, index);
                merkleTreeQueue.add(merkle);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found");
        }
        buildTree(merkleTreeQueue);
    }

    public void buildTree(Queue<MerkleTree> merkleTrees) {
        Queue<MerkleTree>[] merkleQueues = new LinkedList[2];
        merkleQueues[0] = merkleTrees;
        merkleQueues[1] = new LinkedList<>();
        if (merkleQueues[0].size() == 0) {
            System.out.println("Empty queue");
        } else {
            int a = 0, b = 1;
            while (merkleQueues[a].size() != 1) {
                MerkleTree left, right;
                while (merkleQueues[b].size() > 0) {
                    left = merkleQueues[a].poll();
                    right = merkleQueues[a].poll();
                    if (right == null)
                        merkleQueues[b].add(left);
                    else
                        merkleQueues[b].add(new MerkleTree(left, right));
                }
                a = (a + 1) % 2;
                b = (b + 1) % 2;
            }
            tree = merkleQueues[a].poll();
        }
    }

    public Hash currentRootHash() {
        return tree.hash;
    }

    public void append(String log) {
        tree = appendOne(log, tree, tree.size);
    }

    public void append(LinkedList<String> linkedList) {
        tree = appendAll(linkedList, tree, tree.size);
    }

    private MerkleTree appendOne(String log, MerkleTree currentTree, int index) {
        if (currentTree == null)
            return new MerkleTree(log, 0);
        else if (currentTree.size == currentTree.power) {
            MerkleTree newMerkleTree = new MerkleTree(log, index);
            return new MerkleTree(currentTree, newMerkleTree);
        } else {
            return new MerkleTree(currentTree.left, appendOne(log, currentTree.right, index));
        }
    }

    private MerkleTree appendAll(LinkedList<String> linkedList, MerkleTree currentTree, int index) {
        if (linkedList.isEmpty()) return currentTree;
        else if (currentTree == null) {
            currentTree = new MerkleTree(linkedList.poll(), 0);
            return appendAll(linkedList, currentTree, index + 1);
        } else {
            int nbElements = currentTree.power - currentTree.size;
            MerkleTree result;

            if (nbElements == 0) {
                result = new MerkleTree(currentTree, new MerkleTree(linkedList.poll(), index));
                return appendAll(linkedList, result, index + 1);
            } else if (linkedList.size() > nbElements) {
                LinkedList<String> completeTreeElements = new LinkedList<>();

                for (int i = 0; i < nbElements; i++) completeTreeElements.add(linkedList.poll());

                result = new MerkleTree(currentTree.left, appendAll(completeTreeElements, currentTree.right, index));
                result = new MerkleTree(result, new MerkleTree(linkedList.poll(), index + nbElements));
                return new MerkleTree(result.left, appendAll(linkedList, result.right, index + nbElements + 1));
            } else {
                if (linkedList.size() == 1) return appendOne(linkedList.poll(), currentTree, index);
                else return new MerkleTree(currentTree.left, appendAll(linkedList, currentTree.right, index));
            }
        }
    }

    public LinkedList<Hash> genPath(int index) {
        MerkleTree currentTree = tree;
        LinkedList<Hash> hashList = new LinkedList<>();
        return makePath(index, currentTree, hashList);
    }

    LinkedList<Hash> makePath(int index, MerkleTree currentTree, LinkedList<Hash> hashList) {
        if (currentTree.start == currentTree.end && currentTree.end == index) {
            return hashList;
        } else if (currentTree.left != null && currentTree.left.end >= index) {
            hashList.addFirst(currentTree.right.hash);
            return makePath(index, currentTree.left, hashList);
        } else if (currentTree.right != null && currentTree.right.end >= index) {
            hashList.addFirst(currentTree.left.hash);
            return makePath(index, currentTree.right, hashList);
        } else {
            return hashList;
        }
    }

    public LinkedList<Hash> genProof(int index) {
        MerkleTree currentTree = tree;
        LinkedList<Hash> hashList = new LinkedList<>();
        return makeProof(index, currentTree, hashList);
    }

    private LinkedList<Hash> makeProof(int index, MerkleTree currentTree, LinkedList<Hash> hashList) {
        if (currentTree.end < index || currentTree.start > index) {
            System.out.println("Index not possible");
            return hashList;
        } else {
            if (currentTree.end == index) {
                hashList.addFirst(currentTree.hash);
                return hashList;
            } else if (currentTree.left != null && currentTree.left.end < index) {
                hashList.addFirst(currentTree.left.hash);
                return makeProof(index, currentTree.right, hashList);
            } else if (currentTree.left != null && currentTree.left.end >= index) {
                hashList.addFirst(currentTree.right.hash);
                hashList = makeProof(index, currentTree.left, hashList);
                return hashList;
            } else {
                return null;
            }
        }
    }
}
