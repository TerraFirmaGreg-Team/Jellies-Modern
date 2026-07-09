package team.terrafirmagreg.jellies.common.entity.special.pyritie;

import net.dries007.tfc.common.entities.livestock.TFCAnimal;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import earth.terrarium.adastra.api.planets.Planet;

import team.terrafirmagreg.jellies.Jellies;
import team.terrafirmagreg.jellies.common.data.JelliesTags;
import team.terrafirmagreg.jellies.common.entity.JellieBase;

public class PyritieJellie extends JellieBase {
    public PyritieJellie(EntityType<? extends TFCAnimal> animal, Level level) {
        super(animal, level);
    }

    public static boolean spawnRules(EntityType<? extends JellieBase> type, LevelAccessor level, MobSpawnType spawn, BlockPos pos, RandomSource rand) {
        return level.getBiome(pos).is(JelliesTags.Biomes.JellieHabitat)
                && level.getBiome(pos).is(JelliesTags.Biomes.PyritieJellieHabitat)
                && checkMobSpawnRules(type, level, spawn, pos, rand);
    }

    public String getVariantName() {
        return "pyritie";
    }

    public ResourceLocation getTextureLocation() {
        return Jellies.id("textures/entity/jellie_special/pyritie.png");
    }

    public ResourceKey<Level> getDimension() {
        return Planet.MOON;
    }

    // TODO: SPECIAL OBTAIN
}
