/*
 * Your task is to implement the indexOfMax() function so that it returns
 * the index of the largest element in the array, or null if the array is empty.
 */
package maxindex

fun indexOfMax(a: IntArray): Int? {
    var ansIdx = 0
    if(a.size == 0)
    	return null
    var currentIdx = 0
    for (num in a){
    	if(a[ansIdx] <= num){
    		ansIdx = currentIdx
        }
    	currentIdx++
    }
    return ansIdx
}

