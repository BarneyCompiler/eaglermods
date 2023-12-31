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
public class Material {
	public static final Material air = new MaterialTransparent(MapColor.airColor);
	public static final Material grass = new Material(MapColor.grassColor);
	public static final Material ground = new Material(MapColor.dirtColor);
	public static final Material wood = (new Material(MapColor.woodColor)).setBurning();
	public static final Material rock = (new Material(MapColor.stoneColor)).setRequiresTool();
	public static final Material iron = (new Material(MapColor.ironColor)).setRequiresTool();
	public static final Material anvil = (new Material(MapColor.ironColor)).setRequiresTool().setImmovableMobility();
	public static final Material water = (new MaterialLiquid(MapColor.waterColor)).setNoPushMobility();
	public static final Material lava = (new MaterialLiquid(MapColor.tntColor)).setNoPushMobility();
	public static final Material leaves = (new Material(MapColor.foliageColor)).setBurning().setTranslucent()
			.setNoPushMobility();
	public static final Material plants = (new MaterialLogic(MapColor.foliageColor)).setNoPushMobility();
	public static final Material vine = (new MaterialLogic(MapColor.foliageColor)).setBurning().setNoPushMobility()
			.setReplaceable();
	public static final Material sponge = new Material(MapColor.yellowColor);
	public static final Material cloth = (new Material(MapColor.clothColor)).setBurning();
	public static final Material fire = (new MaterialTransparent(MapColor.airColor)).setNoPushMobility();
	public static final Material sand = new Material(MapColor.sandColor);
	public static final Material circuits = (new MaterialLogic(MapColor.airColor)).setNoPushMobility();
	public static final Material carpet = (new MaterialLogic(MapColor.clothColor)).setBurning();
	public static final Material glass = (new Material(MapColor.airColor)).setTranslucent().setAdventureModeExempt();
	public static final Material redstoneLight = (new Material(MapColor.airColor)).setAdventureModeExempt();
	public static final Material tnt = (new Material(MapColor.tntColor)).setBurning().setTranslucent();
	public static final Material coral = (new Material(MapColor.foliageColor)).setNoPushMobility();
	public static final Material ice = (new Material(MapColor.iceColor)).setTranslucent().setAdventureModeExempt();
	public static final Material packedIce = (new Material(MapColor.iceColor)).setAdventureModeExempt();
	public static final Material snow = (new MaterialLogic(MapColor.snowColor)).setReplaceable().setTranslucent()
			.setRequiresTool().setNoPushMobility();
	/**+
	 * The material for crafted snow.
	 */
	public static final Material craftedSnow = (new Material(MapColor.snowColor)).setRequiresTool();
	public static final Material cactus = (new Material(MapColor.foliageColor)).setTranslucent().setNoPushMobility();
	public static final Material clay = new Material(MapColor.clayColor);
	public static final Material gourd = (new Material(MapColor.foliageColor)).setNoPushMobility();
	public static final Material dragonEgg = (new Material(MapColor.foliageColor)).setNoPushMobility();
	public static final Material portal = (new MaterialPortal(MapColor.airColor)).setImmovableMobility();
	public static final Material cake = (new Material(MapColor.airColor)).setNoPushMobility();
	public static final Material web = (new Material(MapColor.clothColor) {
		/**+
		 * Returns if this material is considered solid or not
		 */
		public boolean blocksMovement() {
			return false;
		}
	}).setRequiresTool().setNoPushMobility();
	/**+
	 * Pistons' material.
	 */
	public static final Material piston = (new Material(MapColor.stoneColor)).setImmovableMobility();
	public static final Material barrier = (new Material(MapColor.airColor)).setRequiresTool().setImmovableMobility();
	private boolean canBurn;
	private boolean replaceable;
	private boolean isTranslucent;
	private final MapColor materialMapColor;
	/**+
	 * Determines if the material can be harvested without a tool
	 * (or with the wrong tool)
	 */
	private boolean requiresNoTool = true;
	private int mobilityFlag;
	private boolean isAdventureModeExempt;

	public Material(MapColor color) {
		this.materialMapColor = color;
	}

	public static PluginData makePluginDataStatic() {
		PluginData data = new PluginData();
		data.set("air", air.makePluginData());
		data.set("grass", grass.makePluginData());
		data.set("ground", ground.makePluginData());
		data.set("wood", wood.makePluginData());
		data.set("rock", rock.makePluginData());
		data.set("iron", iron.makePluginData());
		data.set("anvil", anvil.makePluginData());
		data.set("water", water.makePluginData());
		data.set("lava", lava.makePluginData());
		data.set("leaves", leaves.makePluginData());
		data.set("plants", plants.makePluginData());
		data.set("vine", vine.makePluginData());
		data.set("sponge", sponge.makePluginData());
		data.set("cloth", cloth.makePluginData());
		data.set("fire", fire.makePluginData());
		data.set("sand", sand.makePluginData());
		data.set("circuits", circuits.makePluginData());
		data.set("carpet", carpet.makePluginData());
		data.set("glass", glass.makePluginData());
		data.set("redstoneLight", redstoneLight.makePluginData());
		data.set("tnt", tnt.makePluginData());
		data.set("coral", coral.makePluginData());
		data.set("ice", ice.makePluginData());
		data.set("packedIce", packedIce.makePluginData());
		data.set("snow", snow.makePluginData());
		data.set("craftedSnow", craftedSnow.makePluginData());
		data.set("cactus", cactus.makePluginData());
		data.set("clay", clay.makePluginData());
		data.set("gourd", gourd.makePluginData());
		data.set("dragonEgg", dragonEgg.makePluginData());
		data.set("portal", portal.makePluginData());
		data.set("cake", cake.makePluginData());
		data.set("web", web.makePluginData());
		data.set("piston", piston.makePluginData());
		data.set("barrier", barrier.makePluginData());
		return data;
	}

	public void loadPluginData(BaseData data) {
		canBurn = data.getBoolean("canBurn");
		replaceable = data.getBoolean("replaceable");
		requiresNoTool = data.getBoolean("requiresNoTool");
		isTranslucent = data.getBoolean("isTranslucent");
		isAdventureModeExempt = data.getBoolean("isAdventureModeExempt");
		materialMapColor.loadPluginData(data.getBaseData("materialMapColor"));

		mobilityFlag = data.getInt("mobilityFlag");
	}

	public PluginData makePluginData() {
		PluginData data = new PluginData();
		data.set("canBurn", canBurn);
		data.set("replaceable", replaceable);
		data.set("isTranslucent", isTranslucent);
		data.set("requiresNoTool", requiresNoTool);
		data.set("mobilityFlag", mobilityFlag);
		data.set("isAdventureModeExempt", isAdventureModeExempt);
		data.set("materialMapColor", materialMapColor.makePluginData());

		data.setCallbackVoid("reload", () -> {
			loadPluginData(data);
		});

		data.setCallbackBoolean("isLiquid", () -> {
			/**+
			 * Returns if blocks of these materials are liquids.
			 */
			return isLiquid();
		});
		data.setCallbackBoolean("isSolid", () -> {
			/**+
			 * Returns true if the block is a considered solid. This is true
			 * by default.
			 */
			return isSolid();
		});
		data.setCallbackBoolean("isReplaceable", () -> {
			/**+
			 * Returns whether the material can be replaced by other blocks
			 * when placed - eg snow, vines and tall grass.
			 */
			return isReplaceable();
		});
		data.setCallbackBoolean("isToolNotRequired", () -> {
			/**+
			 * Returns true if the material can be harvested without a tool
			 * (or with the wrong tool)
			 */
			return isToolNotRequired();
		});
		data.setCallbackBoolean("isOpaque", () -> {
			/**+
			 * Indicate if the material is opaque
			 */
			return isOpaque();
		});
		data.setCallbackBoolean("getCanBurn", () -> {
			/**+
			 * Returns if the block can burn or not.
			 */
			return getCanBurn();
		});
		data.setCallbackBoolean("blocksLight", () -> {
			/**+
			 * Will prevent grass from growing on dirt underneath and kill
			 * any grass below it if it returns true
			 */
			return blocksLight();
		});
		data.setCallbackBoolean("blocksMovement", () -> {
			/**+
			 * Returns if this material is considered solid or not
			 */
			return blocksMovement();
		});
		data.setCallbackObject("setTranslucent", () -> {
			/**+
			 * Marks the material as translucent
			 */
			return setTranslucent().makePluginData();
		});
		data.setCallbackObject("setRequiresTool", () -> {
			/**+
			 * Makes blocks with this material require the correct tool to
			 * be harvested.
			 */
			return setRequiresTool().makePluginData();
		});
		data.setCallbackObject("setBurning", () -> {
			/**+
			 * Set the canBurn bool to True and return the current object.
			 */
			return setBurning().makePluginData();
		});
		data.setCallbackObject("setReplaceable", () -> {
			/**+
			 * Sets {@link #replaceable} to true.
			 */
			return setReplaceable().makePluginData();
		});
		data.setCallbackObject("setNoPushMobility", () -> {
			/**+
			 * This type of material can't be pushed, but pistons can move
			 * over it.
			 */
			return setNoPushMobility().makePluginData();
		});
		data.setCallbackInt("getMaterialMobility", () -> {
			/**+
			 * Returns the mobility information of the material, 0 = free, 1
			 * = can't push but can move over, 2 = total immobility and stop
			 * pistons.
			 */
			return getMaterialMobility();
		});
		data.setCallbackObject("setImmovableMobility", () -> {
			/**+
			 * This type of material can't be pushed, and pistons are
			 * blocked to move.
			 */
			return setImmovableMobility().makePluginData();
		});
		data.setCallbackObject("setAdventureModeExempt", () -> {
			/**+
			 * @see #isAdventureModeExempt()
			 */
			return setAdventureModeExempt().makePluginData();
		});
		return data;
	}

	/**+
	 * Returns if blocks of these materials are liquids.
	 */
	public boolean isLiquid() {
		return false;
	}

	/**+
	 * Returns true if the block is a considered solid. This is true
	 * by default.
	 */
	public boolean isSolid() {
		return true;
	}

	/**+
	 * Will prevent grass from growing on dirt underneath and kill
	 * any grass below it if it returns true
	 */
	public boolean blocksLight() {
		return true;
	}

	/**+
	 * Returns if this material is considered solid or not
	 */
	public boolean blocksMovement() {
		return true;
	}

	/**+
	 * Marks the material as translucent
	 */
	private Material setTranslucent() {
		this.isTranslucent = true;
		return this;
	}

	/**+
	 * Makes blocks with this material require the correct tool to
	 * be harvested.
	 */
	protected Material setRequiresTool() {
		this.requiresNoTool = false;
		return this;
	}

	/**+
	 * Set the canBurn bool to True and return the current object.
	 */
	protected Material setBurning() {
		this.canBurn = true;
		return this;
	}

	/**+
	 * Returns if the block can burn or not.
	 */
	public boolean getCanBurn() {
		return this.canBurn;
	}

	/**+
	 * Sets {@link #replaceable} to true.
	 */
	public Material setReplaceable() {
		this.replaceable = true;
		return this;
	}

	/**+
	 * Returns whether the material can be replaced by other blocks
	 * when placed - eg snow, vines and tall grass.
	 */
	public boolean isReplaceable() {
		return this.replaceable;
	}

	/**+
	 * Indicate if the material is opaque
	 */
	public boolean isOpaque() {
		return this.isTranslucent ? false : this.blocksMovement();
	}

	/**+
	 * Returns true if the material can be harvested without a tool
	 * (or with the wrong tool)
	 */
	public boolean isToolNotRequired() {
		return this.requiresNoTool;
	}

	/**+
	 * Returns the mobility information of the material, 0 = free, 1
	 * = can't push but can move over, 2 = total immobility and stop
	 * pistons.
	 */
	public int getMaterialMobility() {
		return this.mobilityFlag;
	}

	/**+
	 * This type of material can't be pushed, but pistons can move
	 * over it.
	 */
	protected Material setNoPushMobility() {
		this.mobilityFlag = 1;
		return this;
	}

	/**+
	 * This type of material can't be pushed, and pistons are
	 * blocked to move.
	 */
	protected Material setImmovableMobility() {
		this.mobilityFlag = 2;
		return this;
	}

	/**+
	 * @see #isAdventureModeExempt()
	 */
	protected Material setAdventureModeExempt() {
		this.isAdventureModeExempt = true;
		return this;
	}

	/**+
	 * Retrieves the color index of the block. This is is the same
	 * color used by vanilla maps to represent this block.
	 */
	public MapColor getMaterialMapColor() {
		return this.materialMapColor;
	}
}