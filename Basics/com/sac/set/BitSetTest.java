package com.sac.set;

import java.util.BitSet;

/**
 * For the specific problem you mentioned: when you called bits2.set(7), you set
 * the seventh bit to true.
 * 
 * @author ssachdev
 *
 */
public class BitSetTest {

	public static void main(String[] args) {

		// Creates a new bit set. All bits are initially false
		BitSet bitset = new BitSet();

		// Default size of the BitSet is 64. Size of a Long
		System.out.println("Default size of the BitSet :" + bitset.size());

		// At this point the BitSet is Empty
		System.out.println("At this point the BitSet is Empty :" + bitset.isEmpty());

		// set(int Index) : This method sets the bit at the specified index to true i.e
		// adds a value.
		bitset.set(0);
		bitset.set(7);
		bitset.set(10);

		System.out.println("GET---" + bitset.get(7));
		System.out.println(bitset);

		// Check how many bits are set to True
		System.out.println("No Of Bits set to True :" + bitset.cardinality());

		// Now revert the 7th Bit and set it to false
		bitset.clear(7);

		// After revert of 7th bit - Check how many bits are set to True
		System.out.println("After revert of 7th bit - No Of Bits set to True :" + bitset.cardinality());

		// Now Set bit 7 to True again
		bitset.flip(7);

		// After fliping the 7th bit again number of true bit will be 3
		System.out.println("After fliping the 7th bit again number of true bit will be 4 :" + bitset.cardinality());

	}
}