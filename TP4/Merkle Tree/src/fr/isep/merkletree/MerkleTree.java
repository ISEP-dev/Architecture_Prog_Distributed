package fr.isep.merkletree;

public class MerkleTree {
    Hash hash;
    MerkleTree left, right;
    int start, end, size, power;

    public MerkleTree(String leaf, int index) {
        this.left = null;
        this.right = null;
        this.start = index;
        this.end = index;
        this.size = 1;
        this.power = 1;
        this.hash = new Hash(leaf);
    }

    public MerkleTree(MerkleTree left, MerkleTree right) {
        if (left.end != right.start - 1) {
            System.out.println("Merkle Trees are not contiguous");
            System.out.println("Trees not contiguous, left end at " + left.end + "; right starts at " + right.start );
            System.exit(1);
            return;
        }
        this.left = left;
        this.right = right;
        this.start = left.start;
        this.end = right.end;
        this.size = left.size + right.size;
        if (this.size < Math.max(left.power, right.power))
            this.power = Math.max(left.power, right.power);
        else
            this.power = 2 * Math.max(left.power, right.power);
        this.hash = new Hash(left.hash, right.hash);
    }

    public void displayTree(final int offset) {
        for (int i = 0; i < offset; i++) {
            System.out.println("  ");
        }
        System.out.println("[" + start + "," + end + "]: " + hash.toString());
        if (left != null) left.displayTree(offset + 1);
        if (right != null) right.displayTree(offset + 1);
    }

    public Hash getHash() {
        return hash;
    }

    public MerkleTree getLeft() {
        return left;
    }

    public MerkleTree getRight() {
        return right;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getSize() {
        return size;
    }

    public int getPower() {
        return power;
    }
}
