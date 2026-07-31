package team.terrafirmagreg.jellies.common.entity.special.redeix;

import net.dries007.tfc.common.entities.livestock.TFCAnimal;
import net.dries007.tfc.util.calendar.Calendars;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import team.terrafirmagreg.jellies.Jellies;
import team.terrafirmagreg.jellies.common.entity.JellieBase;
import team.terrafirmagreg.jellies.config.JelliesConfig;

public class RedeixJellie extends JellieBase {
    public RedeixJellie(EntityType<? extends TFCAnimal> animal, Level level) {
        super(animal, level);
    }

    // region Config Bypass
    public float getAdultFamiliarityCap() {
        return (float) JelliesConfig.INSTANCE.entities.redeixJellie.familiarityCap;
    }

    public int getDaysToAdulthood() {
        return JelliesConfig.INSTANCE.entities.redeixJellie.adulthoodDays;
    }

    public int getUsesToElderly() {
        return JelliesConfig.INSTANCE.entities.redeixJellie.uses;
    }

    public boolean eatsRottenFood() {
        return JelliesConfig.INSTANCE.entities.redeixJellie.eatsRottenFood;
    }

    public boolean isReadyForAnimalProduct() {
        return getFamiliarity() > JelliesConfig.INSTANCE.entities.redeixJellie.produceFamiliarity && hasProduct() && this.level().dimension() == this.getDimension() && isHungry();
    }

    public long getProductsCooldown() {
        return Math.max(0, JelliesConfig.INSTANCE.entities.redeixJellie.produceTicks + getProducedTick() - Calendars.get(level()).getTicks());
    }

    public int getChildCount() {
        return JelliesConfig.INSTANCE.entities.redeixJellie.childCount;
    }

    public long getGestationDays() {
        return JelliesConfig.INSTANCE.entities.redeixJellie.gestationDays;
    }
    // endregion

    public String getVariantName() {
        return "redeix";
    }

    public ResourceLocation getTextureLocation() {
        return Jellies.id("textures/entity/jellie_special/redeix.png");
    }

    public static boolean spawnRules(EntityType<? extends JellieBase> type, LevelAccessor level, MobSpawnType spawn, BlockPos pos, RandomSource rand) {
        return false;
    }

    // TODO: SPECIAL OBTAIN
}
