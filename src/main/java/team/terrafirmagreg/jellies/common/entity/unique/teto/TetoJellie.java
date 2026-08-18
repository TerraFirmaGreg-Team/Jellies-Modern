package team.terrafirmagreg.jellies.common.entity.unique.teto;

import net.dries007.tfc.common.entities.livestock.TFCAnimal;
import net.dries007.tfc.common.items.Food;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.calendar.Calendars;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import earth.terrarium.adastra.api.planets.Planet;

import team.terrafirmagreg.jellies.Jellies;
import team.terrafirmagreg.jellies.common.entity.JellieBase;
import team.terrafirmagreg.jellies.config.JelliesConfig;

public class TetoJellie extends JellieBase {
    public TetoJellie(EntityType<? extends TFCAnimal> animal, Level level) {
        super(animal, level);
    }

    // region Config Bypass
    public float getAdultFamiliarityCap() {
        return (float) JelliesConfig.INSTANCE.entities.tetoJellie.familiarityCap;
    }

    public int getDaysToAdulthood() {
        return JelliesConfig.INSTANCE.entities.tetoJellie.adulthoodDays;
    }

    public int getUsesToElderly() {
        return JelliesConfig.INSTANCE.entities.tetoJellie.uses;
    }

    public boolean eatsRottenFood() {
        return JelliesConfig.INSTANCE.entities.tetoJellie.eatsRottenFood;
    }

    public boolean isReadyForAnimalProduct() {
        return getFamiliarity() > JelliesConfig.INSTANCE.entities.tetoJellie.produceFamiliarity && hasProduct() && this.level().dimension() == this.getDimension() && isHungry();
    }

    public long getProductsCooldown() {
        return Math.max(0, JelliesConfig.INSTANCE.entities.tetoJellie.produceTicks + getProducedTick() - Calendars.get(level()).getTicks());
    }

    public int getChildCount() {
        return JelliesConfig.INSTANCE.entities.tetoJellie.childCount;
    }

    public long getGestationDays() {
        return JelliesConfig.INSTANCE.entities.tetoJellie.gestationDays;
    }
    // endregion

    public static boolean spawnRules(EntityType<? extends JellieBase> type, LevelAccessor level, MobSpawnType spawn, BlockPos pos, RandomSource rand) {
        return false;
    }

    public String getVariantName() {
        return "teto";
    }

    public ResourceLocation getTextureLocation() {
        return Jellies.id("textures/entity/jellie_unique/teto.png");
    }

    public ResourceKey<Level> getDimension() {
        return Planet.MOON;
    }

    public boolean isFood(ItemStack stack) {
        if (isBread(stack))
            return true;

        return super.isFood(stack);
    }

    public static boolean isBread(ItemStack stack) {
        return Helpers.isItem(stack, TFCItems.FOOD.get(Food.BARLEY_BREAD).get()) ||
                Helpers.isItem(stack, TFCItems.FOOD.get(Food.MAIZE_BREAD).get()) ||
                Helpers.isItem(stack, TFCItems.FOOD.get(Food.OAT_BREAD).get()) ||
                Helpers.isItem(stack, TFCItems.FOOD.get(Food.RYE_BREAD).get()) ||
                Helpers.isItem(stack, TFCItems.FOOD.get(Food.RICE_BREAD).get()) ||
                Helpers.isItem(stack, TFCItems.FOOD.get(Food.WHEAT_BREAD).get());
    }
}
