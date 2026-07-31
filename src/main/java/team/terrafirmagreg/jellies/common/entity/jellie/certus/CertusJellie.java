package team.terrafirmagreg.jellies.common.entity.jellie.certus;

import net.dries007.tfc.common.entities.livestock.TFCAnimal;
import net.dries007.tfc.util.calendar.Calendars;
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
import team.terrafirmagreg.jellies.config.JelliesConfig;

public class CertusJellie extends JellieBase {
    public CertusJellie(EntityType<? extends TFCAnimal> animal, Level level) {
        super(animal, level);
    }

    // region Config Bypass
    public float getAdultFamiliarityCap() {
        return (float) JelliesConfig.INSTANCE.entities.certusJellie.familiarityCap;
    }

    public int getDaysToAdulthood() {
        return JelliesConfig.INSTANCE.entities.certusJellie.adulthoodDays;
    }

    public int getUsesToElderly() {
        return JelliesConfig.INSTANCE.entities.certusJellie.uses;
    }

    public boolean eatsRottenFood() {
        return JelliesConfig.INSTANCE.entities.certusJellie.eatsRottenFood;
    }

    public boolean isReadyForAnimalProduct() {
        return getFamiliarity() > JelliesConfig.INSTANCE.entities.certusJellie.produceFamiliarity && hasProduct() && this.level().dimension() == this.getDimension() && isHungry();
    }

    public long getProductsCooldown() {
        return Math.max(0, JelliesConfig.INSTANCE.entities.certusJellie.produceTicks + getProducedTick() - Calendars.get(level()).getTicks());
    }

    public int getChildCount() {
        return JelliesConfig.INSTANCE.entities.certusJellie.childCount;
    }

    public long getGestationDays() {
        return JelliesConfig.INSTANCE.entities.certusJellie.gestationDays;
    }
    // endregion

    public static boolean spawnRules(EntityType<? extends JellieBase> type, LevelAccessor level, MobSpawnType spawn, BlockPos pos, RandomSource rand) {
        return level.getBiome(pos).is(JelliesTags.Biomes.JellieHabitat) && level.getBiome(pos).is(JelliesTags.Biomes.CertusJellieHabitat) && checkMobSpawnRules(type, level, spawn, pos, rand);
    }

    public String getVariantName() {
        return "certus";
    }

    public ResourceLocation getTextureLocation() {
        return Jellies.id("textures/entity/jellie/certus.png");
    }

    public ResourceKey<Level> getDimension() {
        return Planet.MOON;
    }
}
