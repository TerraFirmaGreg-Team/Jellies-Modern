package team.terrafirmagreg.jellies.common.entity.jellie.certus;

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

public class CertusJellie extends JellieBase {
    public CertusJellie(EntityType<? extends TFCAnimal> animal, Level level) {
        super(animal, level);
    }

    public static boolean spawnRules(EntityType<? extends JellieBase> type, LevelAccessor level, MobSpawnType spawn, BlockPos pos, RandomSource rand) {
        return level.getBiome(pos).is(JelliesTags.Biomes.JellieHabitat) && level.getBiome(pos).is(JelliesTags.Biomes.CertusJellieHabitat) && checkMobSpawnRules(type, level, spawn, pos, rand);
    }

    public String getVariantName() {
        return "certus";
    }

    public ResourceLocation getTextureLocation() {
        return Jellies.id("textures/entity/jellie/certus.png");
    }

    // TODO: CHANGE TO MOON
    public ResourceKey<Level> getDimension() {
        return Level.NETHER;
    }

    // TODO: PRODUCT IS CERTUS BUDS
}
