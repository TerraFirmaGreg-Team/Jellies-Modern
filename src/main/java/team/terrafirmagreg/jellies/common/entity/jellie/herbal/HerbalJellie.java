package team.terrafirmagreg.jellies.common.entity.jellie.herbal;

import net.dries007.tfc.common.entities.livestock.TFCAnimal;
import net.dries007.tfc.util.calendar.Calendars;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import team.terrafirmagreg.jellies.Jellies;
import team.terrafirmagreg.jellies.common.data.JelliesItems;
import team.terrafirmagreg.jellies.common.data.JelliesTags;
import team.terrafirmagreg.jellies.common.entity.JellieBase;
import team.terrafirmagreg.jellies.config.JelliesConfig;

public class HerbalJellie extends JellieBase {
    public HerbalJellie(EntityType<? extends TFCAnimal> animal, Level level) {
        super(animal, level);
    }

    // region Config Bypass
    public float getAdultFamiliarityCap() {
        return (float) JelliesConfig.INSTANCE.entities.herbalJellie.familiarityCap;
    }

    public int getDaysToAdulthood() {
        return JelliesConfig.INSTANCE.entities.herbalJellie.adulthoodDays;
    }

    public int getUsesToElderly() {
        return JelliesConfig.INSTANCE.entities.herbalJellie.uses;
    }

    public boolean eatsRottenFood() {
        return JelliesConfig.INSTANCE.entities.herbalJellie.eatsRottenFood;
    }

    public boolean isReadyForAnimalProduct() {
        return getFamiliarity() > JelliesConfig.INSTANCE.entities.herbalJellie.produceFamiliarity && hasProduct() && this.level().dimension() == this.getDimension() && isHungry();
    }

    public long getProductsCooldown() {
        return Math.max(0, JelliesConfig.INSTANCE.entities.herbalJellie.produceTicks + getProducedTick() - Calendars.get(level()).getTicks());
    }

    public int getChildCount() {
        return JelliesConfig.INSTANCE.entities.herbalJellie.childCount;
    }

    public long getGestationDays() {
        return JelliesConfig.INSTANCE.entities.herbalJellie.gestationDays;
    }
    // endregion

    public static boolean spawnRules(EntityType<? extends JellieBase> type, LevelAccessor level, MobSpawnType spawn, BlockPos pos, RandomSource rand) {
        return level.getBiome(pos).is(JelliesTags.Biomes.JellieHabitat) && level.getBiome(pos).is(JelliesTags.Biomes.HerbalJellieHabitat) && checkMobSpawnRules(type, level, spawn, pos, rand);
    }

    public String getVariantName() {
        return "herbal";
    }

    public ResourceLocation getTextureLocation() {
        return Jellies.id("textures/entity/jellie/herbal.png");
    }

    public ResourceKey<Level> getDimension() {
        return Level.OVERWORLD;
    }

    public Item getProduct() {
        return JelliesItems.HERBAL_SLIME_BALL.asItem();
    }
}
