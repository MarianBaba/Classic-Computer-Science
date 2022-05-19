package simplest;

import java.util.BitSet;

public class Compression {

    // compressing data is really important when we work with big amounts of
    // information, also it is important to store data
    // in the right format, otherwise we could be wasting a lot of space in memory

    // let's build an utility function that transforms string of nucleotides (the
    // molecules that make up genes) in binary strings,
    // which take up less memory storage

    // there are four nucleotides: A, C, G, T, so we only need 2 bits per nucleotide
    // (remember that a Unicode char takes 16 bits)

    private BitSet bitSet;
    private int length;

    public Compression(String gene) {
        compress(gene);
    }

    // compression method
    private void compress(String gene) {
        length = gene.length();

        bitSet = new BitSet(length * 2); // if we have 3 chars in the string, we'll need 3*2 = 6 bits to represent it

        final String upperGene = gene.toUpperCase();

        for (int i = 0; i < length; i++) {

            final int firstLocation = 2 * i;
            final int secondLocation = 2 * i + 1;

            switch (upperGene.charAt(i)) {
                case 'A': // 00 = A
                    bitSet.set(firstLocation, false);
                    bitSet.set(secondLocation, false);
                    break;
                case 'C': // 01 = C
                    bitSet.set(firstLocation, false);
                    bitSet.set(secondLocation, true);
                    break;
                case 'G': // 10 = G
                    bitSet.set(firstLocation, true);
                    bitSet.set(secondLocation, false);
                    break;
                case 'T': // 11 = T
                    bitSet.set(firstLocation, true);
                    bitSet.set(secondLocation, true);
                    break;
                default:
                    throw new IllegalArgumentException("The string provided is invalid");
            }
        }
    }

    // decompression method
    public String decompress() {
        if (bitSet == null) {
            return "";
        } else {
            StringBuilder builder = new StringBuilder(length);

            for (int i = 0; i < (length * 2); i += 2) {
                final int firstBit = (bitSet.get(i) ? 1 : 0);
                final int secondBit = (bitSet.get(i + 1) ? 1 : 0);
                final int lastBits = firstBit << 1 | secondBit;
                switch (lastBits) {
                    case 0b00: // 00 is 'A'
                        builder.append('A');
                        break;
                    case 0b01: // 01 is 'C'
                        builder.append('C');
                        break;
                    case 0b10: // 10 is 'G'
                        builder.append('G');
                        break;
                    case 0b11: // 11 is 'T'
                        builder.append('T');
                        break;
                }
            }
            return builder.toString();
        }
    }

    public static void main(String... strings) {
        final String original = "TAGGGATTAACCGTTATATATATATAGCCATGGATCGATTATATAGGGATTAACCGTTATATATATATAGCCATGGATCGATTATA";

        Compression compressed = new Compression(original);
        final String decompressed = compressed.decompress();

        System.out.println(decompressed);
        System.out.println("original is the same as decompressed: " + original.equalsIgnoreCase(decompressed));
    }

}
