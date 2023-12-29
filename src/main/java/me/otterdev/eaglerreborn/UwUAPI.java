package me.otterdev.eaglerreborn;

import java.util.Random;

// OG code by Ran
// uwuifies any sentence/word!
// Usage:
// UwUAPI.uwuify("Your text here");
public class UwUAPI {
    @SuppressWarnings("unused")
    public static String uwuify(String stringToUwuify) {
        return stringToUwuify.toLowerCase().replaceAll("r|l","w").replaceAll("n([aeiou])", "ny$1").replaceAll("ove", "uve").replaceAll("uck", "uwq").replaceFirst("i", "i-i").replaceFirst("(?s)(.*)" + "i-i-i", "$1" + "i-i") + ((new java.util.Random().nextInt(10)) <= 2 ? " >_<" : "");
    }

}
