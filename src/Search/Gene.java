package Search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Gene {

    // nucleotide -> codon -> amino acid -> protein

    // four possible values of a nucleotide
    public enum Nucleotide {
        A, C, G, T
    }

    // static nested class, doesn't need enclosing class to be instantiated
    // a codon is made of three nucleotides
    // we want to be able to search for nucleotides and compare codons
    public static class Codon implements Comparable<Codon> {
        public final Nucleotide first, second, third;

        // compares the first nucleotides, if they are equal, compare the second,
        // lastly if they still are equal compare the third
        private final Comparator<Codon> comparator = Comparator.comparing((Codon c) -> c.first)
                .thenComparing((Codon c) -> c.second)
                .thenComparing((Codon c) -> c.third);

        // constructor
        public Codon(String codonStr) {
            first = Nucleotide.valueOf(codonStr.substring(0, 1));
            second = Nucleotide.valueOf(codonStr.substring(1, 2));
            third = Nucleotide.valueOf(codonStr.substring(2, 3));
        }

        // method implementation for the interface comparator
        @Override
        public int compareTo(Codon other) {
            return comparator.compare(this, other);
        }
    }

    private ArrayList<Codon> codons = new ArrayList<>();

    public Gene(String geneStr) {
        for (int i = 0; i < geneStr.length() - 3; i += 3) {
            codons.add(new Codon(geneStr.substring(i, i + 3)));
        }
    }

    // let's implement a LINEAR SEARCH for a gene string: we want to find out if a
    // gene contains a codon,
    // so we simply search the array list of codons linearly: one element at the
    // time, until we reach the end
    // of the data structure or we find the element we are looking for

    public boolean linearContains(Codon key) {
        for (Codon codon : codons) {
            if (codon.compareTo(key) == 0) {
                return true;
            }
        }
        return false;
    }

    // now, let's implement the BINARY SEARCH for a gene string. It's complexity is
    // = O(n logn), the size of the
    // search space is always cut in half
    // we need to sort the data structure we want to search (could be a
    // disadvantage)

    public boolean binaryContains(Codon key) {
        ArrayList<Codon> sortedCodons = new ArrayList<Codon>(codons);
        Collections.sort(sortedCodons);
        int low = 0;
        int high = sortedCodons.size() - 1;
        while (low <= high) {
            int middle = (low + high) / 2;
            int comparison = codons.get(middle).compareTo(key);
            if (comparison < 0) {
                low = middle + 1;
            } else if (comparison > 0) {
                high = middle - 1;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String geneStr = "ACGTGGCTCTCTAACGTACGTACGTACGGGGTTTATATATACCCTAGGACTCCCTTT";
        Gene myGene = new Gene(geneStr);
        Codon acg = new Codon("ACG");
        Codon gat = new Codon("GAT");
        System.out.println(myGene.linearContains(acg)); // true
        System.out.println(myGene.linearContains(gat)); // false
        System.out.println(myGene.binaryContains(acg)); // true
        System.out.println(myGene.binaryContains(gat)); // false
    }

}
