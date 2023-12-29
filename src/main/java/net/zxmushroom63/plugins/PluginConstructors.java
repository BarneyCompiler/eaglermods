package net.zxmushroom63.plugins;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;

import net.lax1dude.eaglercraft.v1_8.log4j.LogManager;
import net.lax1dude.eaglercraft.v1_8.log4j.Logger;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3i;
import net.minecraft.util.Vec3;
import net.minecraft.block.material.Material;

import java.lang.Class;
import java.lang.String;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PluginConstructors {
    public static PluginData makeContructorsPluginData() {
        PluginData constructorsGlobal = new PluginData();
        constructorsGlobal.setCallbackObjectWithDataArg("ItemStack", (BaseData data)->{
            return ItemStack.fromPluginData(data).makePluginData();
        });
        constructorsGlobal.setCallbackObjectWithDataArg("AxisAlignedBB", (BaseData data)->{
            return AxisAlignedBB.fromPluginData(data).makePluginData();
        });
        constructorsGlobal.setCallbackObjectWithDataArg("BlockPos", (BaseData data)->{
            return BlockPos.fromPluginData(data).makePluginData();
        });
        constructorsGlobal.setCallbackObjectWithDataArg("Vec3i", (BaseData data)->{
            return Vec3i.fromPluginData(data).makePluginData();
        });
        constructorsGlobal.setCallbackObjectWithDataArg("Vec3", (BaseData data)->{
            return Vec3.fromPluginData(data).makePluginData();
        });
        return constructorsGlobal;
    }
}