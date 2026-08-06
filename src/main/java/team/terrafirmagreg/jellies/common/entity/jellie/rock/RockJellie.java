package team.terrafirmagreg.jellies.common.entity.jellie.rock;

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

import team.terrafirmagreg.jellies.Jellies;
import team.terrafirmagreg.jellies.common.data.JelliesTags;
import team.terrafirmagreg.jellies.common.entity.JellieBase;
import team.terrafirmagreg.jellies.config.JelliesConfig;

public class RockJellie extends JellieBase {
    public RockJellie(EntityType<? extends TFCAnimal> animal, Level level) {
        super(animal, level);
    }

    // region Config Bypass
    public float getAdultFamiliarityCap() {
        return (float) JelliesConfig.INSTANCE.entities.rockJellie.familiarityCap;
    }

    public int getDaysToAdulthood() {
        return JelliesConfig.INSTANCE.entities.rockJellie.adulthoodDays;
    }

    public int getUsesToElderly() {
        return JelliesConfig.INSTANCE.entities.rockJellie.uses;
    }

    public boolean eatsRottenFood() {
        return JelliesConfig.INSTANCE.entities.rockJellie.eatsRottenFood;
    }

    public boolean isReadyForAnimalProduct() {
        return getFamiliarity() > JelliesConfig.INSTANCE.entities.rockJellie.produceFamiliarity && hasProduct() && this.level().dimension() == this.getDimension() && isHungry();
    }

    public long getProductsCooldown() {
        return Math.max(0, JelliesConfig.INSTANCE.entities.rockJellie.produceTicks + getProducedTick() - Calendars.get(level()).getTicks());
    }

    public int getChildCount() {
        return JelliesConfig.INSTANCE.entities.rockJellie.childCount;
    }

    public long getGestationDays() {
        return JelliesConfig.INSTANCE.entities.rockJellie.gestationDays;
    }
    // endregion

    public static boolean spawnRules(EntityType<? extends JellieBase> type, LevelAccessor level, MobSpawnType spawn, BlockPos pos, RandomSource rand) {
        if (pos.getY() >= level.getSeaLevel()) {
            return false;
        } else {
            int i = level.getMaxLocalRawBrightness(pos);
            int j = 4;
            if (rand.nextBoolean()) {
                return false;
            }

            return i > rand.nextInt(j) ? false : level.getBlockState(pos).isAir();
        }
    }

    public String getVariantName() {
        return "rock";
    }

    public ResourceLocation getTextureLocation() {
        return Jellies.id("textures/entity/jellie/rock.png");
    }

    public ResourceKey<Level> getDimension() {
        return Level.OVERWORLD;
    }

    // TODO: SPECIAL PRODUCT & INTERACT SYSTEM
}
