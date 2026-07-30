package team.terrafirmagreg.jellies.common.entity.jellie.lava;

import net.dries007.tfc.common.entities.livestock.TFCAnimal;
import net.dries007.tfc.common.entities.livestock.TFCAnimalProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import team.terrafirmagreg.jellies.Jellies;
import team.terrafirmagreg.jellies.common.data.JelliesEntities;
import team.terrafirmagreg.jellies.common.data.JelliesTags;
import team.terrafirmagreg.jellies.common.entity.JellieBase;
import team.terrafirmagreg.jellies.common.entity.jellie.plant.PlantJellie;

public class LavaJellie extends JellieBase {
    public boolean birthLatexJellie = false;

    public LavaJellie(EntityType<? extends TFCAnimal> animal, Level level) {
        super(animal, level);
    }

    public static boolean spawnRules(EntityType<? extends JellieBase> type, LevelAccessor level, MobSpawnType spawn, BlockPos pos, RandomSource rand) {
        return level.getBiome(pos).is(JelliesTags.Biomes.JellieHabitat) && level.getBiome(pos).is(JelliesTags.Biomes.LavaJellieHabitat) && checkMobSpawnRules(type, level, spawn, pos, rand);
    }

    public String getVariantName() {
        return "lava";
    }

    public ResourceLocation getTextureLocation() {
        return Jellies.id("textures/entity/jellie/lava.png");
    }

    public ResourceKey<Level> getDimension() {
        return Level.NETHER;
    }

    public float getAmbientalTemperature() {
        return 10F;
    }

    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob other) {
        if (other != this && other instanceof PlantJellie mate && !isFertilized() && !mate.isFertilized()) {
            this.onFertilized(mate);
            mate.birthLatexJellie = true;
            return null;
        } else if (other == this) {
            birthLatexJellie = false;
            final Entity baby = JelliesEntities.LATEX_JELLIE.get().create(level);

            if (baby instanceof TFCAnimalProperties properties && baby instanceof AgeableMob ageable) {
                setBabyTraits(properties);
                return ageable;
            }

            return null;
        }

        return super.getBreedOffspring(level, other);
    }
}
