package fr.cmm.tags;

/**
 * Created by Francois on 11/10/2018.
 */
public class Functions {

    public static String text(String s) {
        if (s.contains("\n")) {
            return s.replace("\n", "<br>");
        } else if (s.contains("&")) {
            return s.replace("&", "&amp;");
        } else {
            return s;
        }
    }
}
