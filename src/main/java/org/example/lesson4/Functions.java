package org.example.lesson4;



public class Functions {
    public static boolean isPolindrome(String word){
        if(word.length()<2){
            return true;
        }
        if(word.charAt(0) != word.charAt(word.length() - 1)){
            return false;
        }
        return isPolindrome(word.substring(1,word.length() - 1));
    }
}
