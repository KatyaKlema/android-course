/*
 * Your task is to implement the sum() function so that it computes the sum of
 * all elements in the given array a.
 */
package sum

fun sum(a: IntArray): Int {
    var sum = 0
    for (num in a)
    	sum += num
    return sum
}

