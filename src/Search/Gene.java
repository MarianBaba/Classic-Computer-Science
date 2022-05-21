package Search;

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

}
