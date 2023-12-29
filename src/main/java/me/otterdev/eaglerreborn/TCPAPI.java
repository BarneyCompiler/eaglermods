package me.otterdev.eaglerreborn;

import java.util.LinkedList;
import java.util.List; 

import net.lax1dude.eaglercraft.v1_8.internal.teavm.TeaVMUtils;
import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;
import org.teavm.jso.browser.TimerHandler;
import org.teavm.jso.browser.Window;
import org.teavm.jso.dom.events.Event;
import org.teavm.jso.dom.events.EventListener;
import org.teavm.jso.dom.events.KeyboardEvent;
import org.teavm.jso.dom.events.MouseEvent;
import org.teavm.jso.dom.events.WheelEvent;
import org.teavm.jso.dom.html.HTMLCanvasElement;
import org.teavm.jso.dom.html.HTMLElement;
import org.teavm.jso.webgl.WebGLFramebuffer;
import org.teavm.jso.webgl.WebGLRenderbuffer;

import net.lax1dude.eaglercraft.v1_8.EagUtils;

public class TCPAPI {

  @JSBody(params = { }, script = "if(window.chrome && chrome.app && chrome.app.runtime){return true;}else{return false;}")
  public static native boolean chromeAppTCP(); // your chromebook is probably too new to run chrome apps

  @JSBody(params = { }, script = "if(navigator.userAgent.indexOf('Electron') >= 0){return true;}else{return false;}")
  public static native boolean electronAppTCP(); // this is for possible future Windows, Mac, Linux support for Eagler
}
