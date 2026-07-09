package team.terrafirmagreg.jellies.common.entity.jellie.glowberry;

import net.dries007.tfc.common.entities.livestock.TFCAnimal;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;
import team.terrafirmagreg.jellies.Jellies;
import team.terrafirmagreg.jellies.common.data.JelliesItems;
import team.terrafirmagreg.jellies.common.data.JelliesTags;
import team.terrafirmagreg.jellies.common.entity.JellieBase;

public class GlowberryJellie extends JellieBase {
    public GlowberryJellie(EntityType<? extends TFCAnimal> animal, Level level) {
        super(animal, level);
    }

    public static boolean spawnRules(EntityType<? extends JellieBase> type, LevelAccessor level, MobSpawnType spawn, BlockPos pos, RandomSource rand) {
        return level.getBiome(pos).is(JelliesTags.Biomes.JellieHabitat) && level.getBiome(pos).is(JelliesTags.Biomes.GlowberryJellieHabitat) && checkMobSpawnRules(type, level, spawn, pos, rand);
    }

    public String getVariantName() {
        return "glowberry";
    }

    public ResourceLocation getTextureLocation() {
        return Jellies.id("textures/entity/jellie/glowberry.png");
    }

    public ResourceKey<Level> getDimension() {
        return Level.NETHER;
    }

    public Item getProduct() {
        return JelliesItems.GLOWBERRY_SLIME_BALL.asItem();
    }
}
