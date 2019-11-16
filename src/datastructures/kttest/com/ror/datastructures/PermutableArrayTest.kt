package com.ror.datastructures

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class PermutableArrayTest {
    @Test
    fun single_element_array_index_test() {
        val pa = permutableArrayOf(1)
        assertEquals(1, pa.get(0))
    }

    @org.junit.jupiter.api.Test
    fun single_element_array_permutation_test() {
        val pa = permutableArrayOf(1)
        assertThrows<NoSuchElementException> { pa.nextPermutation() }
    }

    @Test
    fun three_elements_array_premutations() {
        val pa = permutableArrayOf(1, 2, 3)
        val actualPermutations = HashSet<List<Int>>()

        while (true) {
            actualPermutations.add((0..2).map { index -> pa.get(index) })
            try {
                pa.nextPermutation()
            } catch (e: NoSuchElementException) {
                // Expected permutations exhaustion
                break
            }
        }

        val expectedPermutations = setOf(
                listOf(1, 2, 3),
                listOf(1, 3, 2),
                listOf(2, 1, 3),
                listOf(2, 3, 1),
                listOf(3, 1, 2),
                listOf(3, 2, 1)
        )

        assertEquals(expectedPermutations, actualPermutations)
    }

    @Test
    fun four_elements_array_permutations() {
        val pa = permutableArrayOf(1, 2, 3, 4)
        val permutations = HashSet<List<Int>>();
        while (true) {
            permutations.add((0..3).map { pa.get(it) })
            try {
                pa.nextPermutation()
            } catch (e: NoSuchElementException) {
                // expected exception
                break
            }
        }

        assertEquals(24, permutations.size)
    }


    private fun permutableArrayOf(vararg elements: Int) =
            PermutableArray(Array(elements.size) { index -> elements[index] })

}