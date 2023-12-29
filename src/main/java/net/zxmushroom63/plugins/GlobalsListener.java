package net.zxmushroom63.plugins;

import java.util.Map;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;
import org.teavm.jso.JSFunctor;

class GlobalsListener {
    @JSFunctor
    public interface GlobalsHandler extends JSObject {
        void onGlobalsUpdate(String global);
    }

    @JSBody(params = { "handler" }, script = "window.PluginAPI.globals.onGlobalsUpdate = handler;")
    static native void provideCallback(GlobalsHandler handler);

    @JSBody(params = { "handler" }, script = "window.PluginAPI.globals.onRequire = handler;")
    static native void provideRequireCallback(GlobalsHandler handler);
}