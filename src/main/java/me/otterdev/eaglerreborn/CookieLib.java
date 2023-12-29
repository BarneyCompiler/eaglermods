package me.otterdev.eaglerreborn;

import org.teavm.jso.JSBody;

public class CookieLib {
    @JSBody(params = { "cookie" }, script = "doesCookieExist(cookie);")
    public static native boolean doesCookieExist(String cookie);
    // this fails miserably if the value of the cookie is a string
    @JSBody(params = { "cookiename" }, script = "getCookie(cookiename);")
    public static native String getCookie(String cookiename);

    @JSBody(params = { "cname", "cvalue", "exdays" }, script = "setCookie(cname, cvalue, exdays);")
    public static native void setCookie(String cname, String cvalue, int exdays);
}