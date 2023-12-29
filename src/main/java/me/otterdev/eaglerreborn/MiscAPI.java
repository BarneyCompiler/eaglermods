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

public class MiscAPI {
  @JSBody(params = { "message" }, script = "return confirm(message);")
  public static native boolean confirm(String message);
  
  @JSBody(params = { }, script = "return navigator.hardwareConcurrency;")
  public static native int CPUCores();

}
