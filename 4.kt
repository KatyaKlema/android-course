/*
 * Your task is to implement a palindrome test.
 *
 * A string is called a palindrome when it reads the same way left-to-right
 * and right-to-left.
 *
 * See http://en.wikipedia.org/wiki/Palindrome
 */
package palindrome

fun isPalindrome(s: String): Boolean {
    if(s.length == 0)
        return true
    var currentIdx = 0
    var n = s.length
    while(currentIdx < n){
        if(s[currentIdx] != s[n - 1 - currentIdx])
            return false
        currentIdx++
    }
    return true
}


