package net.minecraft.block.material;

import net.zxmushroom63.plugins.BaseData;
import net.zxmushroom63.plugins.PluginData;

/**+
 * This portion of EaglercraftX contains deobfuscated Minecraft 1.8 source code.
 * 
 * Minecraft 1.8.8 bytecode is (c) 2015 Mojang AB. "Do not distribute!"
 * Mod Coder Pack v9.18 deobfuscation configs are (c) Copyright by the MCP Team
 * 
 * EaglercraftX 1.8 patch files are (c) 2022-2023 LAX1DUDE. All Rights Reserved.
 * 
 * WITH THE EXCEPTION OF PATCH FILES, MINIFIED JAVASCRIPT, AND ALL FILES
 * NORMALLY FOUND IN AN UNMODIFIED MINECRAFT RESOURCE PACK, YOU ARE NOT ALLOWED
 * TO SHARE, DISTRIBUTE, OR REPURPOSE ANY FILE USED BY OR PRODUCED BY THE
 * SOFTWARE IN THIS REPOSITORY WITHOUT PRIOR PERMISSION FROM THE PROJECT AUTHOR.
 * 
 * NOT FOR COMMERCIAL OR MALICIOUS USE
 * 
 * (please read the 'LICENSE' file this repo's root directory for more info) 
 * 
 */
public class MapColor {
	/**+
	 * Holds all the 16 colors used on maps, very similar of a
	 * pallete system.
	 */
	public static final MapColor[] mapColorArray = new MapColor[64];
	public static final MapColor airColor = new MapColor(0, 0);
	public static final MapColor grassColor = new MapColor(1, 8368696);
	public static final MapColor sandColor = new MapColor(2, 16247203);
	public static final MapColor clothColor = new MapColor(3, 13092807);
	public static final MapColor tntColor = new MapColor(4, 16711680);
	public static final MapColor iceColor = new MapColor(5, 10526975);
	public static final MapColor ironColor = new MapColor(6, 10987431);
	public static final MapColor foliageColor = new MapColor(7, 31744);
	public static final MapColor snowColor = new MapColor(8, 16777215);
	public static final MapColor clayColor = new MapColor(9, 10791096);
	public static final MapColor dirtColor = new MapColor(10, 9923917);
	public static final MapColor stoneColor = new MapColor(11, 7368816);
	public static final MapColor waterColor = new MapColor(12, 4210943);
	public static final MapColor woodColor = new MapColor(13, 9402184);
	public static final MapColor quartzColor = new MapColor(14, 16776437);
	public static final MapColor adobeColor = new MapColor(15, 14188339);
	public static final MapColor magentaColor = new MapColor(16, 11685080);
	public static final MapColor lightBlueColor = new MapColor(17, 6724056);
	public static final MapColor yellowColor = new MapColor(18, 15066419);
	public static final MapColor limeColor = new MapColor(19, 8375321);
	public static final MapColor pinkColor = new MapColor(20, 15892389);
	public static final MapColor grayColor = new MapColor(21, 5000268);
	public static final MapColor silverColor = new MapColor(22, 10066329);
	public static final MapColor cyanColor = new MapColor(23, 5013401);
	public static final MapColor purpleColor = new MapColor(24, 8339378);
	public static final MapColor blueColor = new MapColor(25, 3361970);
	public static final MapColor brownColor = new MapColor(26, 6704179);
	public static final MapColor greenColor = new MapColor(27, 6717235);
	public static final MapColor redColor = new MapColor(28, 10040115);
	public static final MapColor blackColor = new MapColor(29, 1644825);
	public static final MapColor goldColor = new MapColor(30, 16445005);
	public static final MapColor diamondColor = new MapColor(31, 6085589);
	public static final MapColor lapisColor = new MapColor(32, 4882687);
	public static final MapColor emeraldColor = new MapColor(33, '\ud93a');
	public static final MapColor obsidianColor = new MapColor(34, 8476209);
	public static final MapColor netherrackColor = new MapColor(35, 7340544);
	public int colorValue;
	public int colorIndex;

	private MapColor(int index, int color) {
		if (index >= 0 && index <= 63) {
			this.colorIndex = index;
			this.colorValue = color;
			mapColorArray[index] = this;
		} else {
			throw new IndexOutOfBoundsException("Map colour ID must be between 0 and 63 (inclusive)");
		}
	}

	public void loadPluginData(BaseData data) {
		colorIndex = data.getInt("colorIndex");
		colorValue = data.getInt("colorValue");
	}

	public PluginData makePluginData() {
		PluginData data = new PluginData();
		data.set("colorIndex", colorIndex);
		data.set("colorValue", colorValue);

		int[] rgb = new int[3];
		int rr;
		int gg;
		int bb;
		int dec = colorValue;
		rr = (int) Math.floor(dec / 65536);
		dec -= rr * 65536;
		gg = (int) Math.floor(dec / 256);
		dec -= gg * 256;
		bb = dec;
		rr = Math.min(rr, 255);
		rgb[0] = rr;
		rgb[1] = gg;
		rgb[2] = bb;
		data.set("rgb", rgb);

		data.setCallbackVoid("reload", () -> {
			loadPluginData(data);
		});

		return data;
	}

	public int func_151643_b(int parInt1) {
		short short1 = 220;
		if (parInt1 == 3) {
			short1 = 135;
		}

		if (parInt1 == 2) {
			short1 = 255;
		}

		if (parInt1 == 1) {
			short1 = 220;
		}

		if (parInt1 == 0) {
			short1 = 180;
		}

		int i = (this.colorValue >> 16 & 255) * short1 / 255;
		int j = (this.colorValue >> 8 & 255) * short1 / 255;
		int k = (this.colorValue & 255) * short1 / 255;
		return -16777216 | i << 16 | j << 8 | k;
	}
}