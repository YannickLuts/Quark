package vazkii.quark.content.world.config;

import net.minecraft.world.biome.Biome;
import vazkii.quark.base.module.config.Config;
import vazkii.quark.base.world.config.BiomeTypeConfig;
import vazkii.quark.base.world.config.ClusterSizeConfig;
import vazkii.quark.base.world.config.DimensionConfig;

public class BigStoneClusterConfig extends ClusterSizeConfig {

	@Config
	public boolean enabled = true;

	public BigStoneClusterConfig(Biome.Category... types) {
		this(DimensionConfig.overworld(false), 14, 9, 4, 20, 80, types);
	}

	public BigStoneClusterConfig(DimensionConfig dimensions, int clusterSize, int sizeVariation, int rarity, int minYLevel, int maxYLevel, Biome.Category... types) {
		super(rarity, clusterSize, clusterSize, sizeVariation, sizeVariation, false, types);
		this.dimensions = dimensions;
		biomes = new BiomeTypeConfig(false, types);
		
		this.minYLevel = minYLevel;
		this.maxYLevel = maxYLevel;
	}

}
