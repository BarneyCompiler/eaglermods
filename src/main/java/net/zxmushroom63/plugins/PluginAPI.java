package net.zxmushroom63.plugins;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;

import net.lax1dude.eaglercraft.v1_8.log4j.LogManager;
import net.lax1dude.eaglercraft.v1_8.log4j.Logger;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.ChatComponentText;
import net.minecraft.block.material.Material;

import java.lang.Class;
import java.lang.String;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PluginAPI {
    private Minecraft mc;
    public ArrayList<String> requiredList;
    public static final Logger logger = LogManager.getLogger();
    public static String version = "a4.3";

    @JSBody(params = { "version" }, script = "var PluginAPI = {};\r\n" + //
            "PluginAPI.events = {};\r\n" + //
            "PluginAPI.events.types = [];\r\n" + //
            "PluginAPI.events.listeners = {};\r\n" + //
            "PluginAPI.globals = {};\r\n" + //
            "PluginAPI.version = version;\r\n" + //
            "PluginAPI.addEventListener = function addEventListener(name, callback) {\r\n" + //
            "  if (PluginAPI.events.types.includes(name)) {\r\n" + //
            "    if (!Array.isArray(PluginAPI.events.listeners[name])) {\r\n" + //
            "      PluginAPI.events.listeners[name] = [];\r\n" + //
            "    }\r\n" + //
            "    PluginAPI.events.listeners[name].push(callback);\r\n" + //
            "    console.log(\"Added new event listener.\");\r\n" + //
            "  } else {\r\n" + //
            "    throw new Error(\"This event does not exist!\");\r\n" + //
            "  }\r\n" + //
            "};\r\n" + //
            "PluginAPI.events.newEvent = function newEvent(name) {\r\n" + //
            "  PluginAPI.events.types.push(name);\r\n" + //
            "};\r\n" + //
            "PluginAPI.events.callEvent = function callEvent(name, data) {\r\n" + //
            "  if (\r\n" + //
            "    !PluginAPI.events.types.includes(name) ||\r\n" + //
            "    !Array.isArray(PluginAPI.events.listeners[name])\r\n" + //
            "  ) {\r\n" + //
            "    return;\r\n" + //
            "  }\r\n" + //
            "  PluginAPI.events.listeners[name].forEach((func) => {\r\n" + //
            "    func(data);\r\n" + //
            "  });\r\n" + //
            "\r\n" + //
            "  PluginAPI.globals._initUpdate();\r\n" + //
            "};\r\n" + //
            "PluginAPI.updateComponent = function updateComponent(component) {\r\n" + //
            "  if (\r\n" + //
            "    typeof component !== \"string\" ||\r\n" + //
            "    PluginAPI[component] === null ||\r\n" + //
            "    PluginAPI[component] === undefined\r\n" + //
            "  ) {\r\n" + //
            "    return;\r\n" + //
            "  }\r\n" + //
            "  if (!PluginAPI.globals || !PluginAPI.globals.onGlobalsUpdate) {\r\n" + //
            "    return;\r\n" + //
            "  }\r\n" + //
            "  if (!PluginAPI.globals.toUpdate) {\r\n" + //
            "    PluginAPI.globals.toUpdate = [];\r\n" + //
            "  }\r\n" + //
            "  if (PluginAPI.globals.toUpdate.indexOf(component) === -1) {\r\n" + //
            "    PluginAPI.globals.toUpdate.push(component);\r\n" + //
            "  }\r\n" + //
            "};\r\n" + //
            "PluginAPI.require = function require(component) {\r\n" + //
            "  if (\r\n" + //
            "    typeof component !== \"string\"\r\n" + //
            "  ) {\r\n" + //
            "    return;\r\n" + //
            "  }\r\n" + //
            "  if (!PluginAPI.globals || !PluginAPI.globals.onRequire) {\r\n" + //
            "    return;\r\n" + //
            "  }\r\n" + //
            "  PluginAPI.globals.onRequire(component);\r\n" + //
            "}\r\n" + //
            "PluginAPI.globals._initUpdate = function _initUpdate() {\r\n" + //
            "  if (!PluginAPI.globals.toUpdate) {\r\n" + //
            "    PluginAPI.globals.toUpdate = [];\r\n" + //
            "  }\r\n" + //
            "  PluginAPI.globals.toUpdate.forEach((id) => {\r\n" + //
            "    PluginAPI.globals.onGlobalsUpdate(id);\r\n" + //
            "  });\r\n" + //
            "  PluginAPI.globals.toUpdate = [];\r\n" + //
            "};\r\n" + //
            "window.PluginAPI = PluginAPI;")
    private static native void init(String version);

    @JSBody(params = { "name" }, script = "PluginAPI.events.newEvent(name);")
    private static native void newEvent(String name);

    @JSBody(params = { "name", "data" }, script = "PluginAPI.events.callEvent(name, data); return data;")
    public static native BaseData callEvent(String name, BaseData data);

    @JSBody(params = { "name", "data" }, script = "PluginAPI[name]=data;")
    public static native void setGlobal(String name, BaseData data);

    @JSBody(params = { "name" }, script = "return PluginAPI[name] || {};")
    public static native BaseData getGlobal(String name);

    @JSBody(params = {}, script = "return PluginAPI;")
    public static native BaseData getPluginAPI();

    @JSBody(params = { "data" }, script = "console.log(data);")
    public static native void logJSObj(JSObject data);

    public void onGlobalUpdated(String global) {
        //logger.info("Global update request: "+global);
        BaseData data = getGlobal(global);
        //logJSObj(data);
        if (data == null) {
            return;
        }
        switch (global) {
            case "player":
                mc.thePlayer.loadPluginData(data);
        }
    }

    public void onRequire(String global) {
        if(!requiredList.contains(global)){
            logger.info("Required global: "+global);
            requiredList.add(global);
        }
    }

    public PluginAPI(Minecraft mcIn) {
        this.mc = mcIn;
        requiredList = new ArrayList<String>();

        init(version);
        newEvent("sendchatmessage");
        newEvent("key");
        newEvent("update");
        newEvent("postmotionupdate");
        newEvent("premotionupdate");

        globalsFunctor(this);
        globalsRequireFunctor(this);

        getPluginAPI().setCallbackVoidWithDataArg("displayToChat", (BaseData params)->{mc.ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(params.getString("msg")));});
        
        setGlobal("enchantments", Enchantment.makePluginDataStatic());
        setGlobal("blocks", Blocks.makePluginData());
        setGlobal("items", Items.makePluginData());
        setGlobal("materials", Material.makePluginDataStatic());
        setGlobal("constructors", PluginConstructors.makeContructorsPluginData());
        PluginGUI.loadFont();
    }

    static void globalsFunctor(PluginAPI pluginAPI) {
        GlobalsListener.provideCallback((String name)->{
            pluginAPI.onGlobalUpdated(name);
        });
    }

    static void globalsRequireFunctor(PluginAPI pluginAPI) {
        GlobalsListener.provideRequireCallback((String name)->{
            pluginAPI.onRequire(name);
        });
    }

    public void onUpdate() {
        if (requiredList.contains("player")) {
            PluginAPI.setGlobal("player", mc.thePlayer.makePluginData());
        }
		PluginAPI.callEvent("update", new PluginData());
    }
}