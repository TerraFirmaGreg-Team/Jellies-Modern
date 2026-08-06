package team.terrafirmagreg.jellies.common.entity.jellie.phosphorum;

import net.dries007.tfc.common.entities.livestock.TFCAnimal;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.calendar.Calendars;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import team.terrafirmagreg.jellies.Jellies;
import team.terrafirmagreg.jellies.common.data.JelliesItems;
import team.terrafirmagreg.jellies.common.entity.JellieBase;
import team.terrafirmagreg.jellies.config.JelliesConfig;

public class PhosphorumJellie extends JellieBase {
    public PhosphorumJellie(EntityType<? extends TFCAnimal> animal, Level level) {
        super(animal, level);
    }

    // region Config Bypass
    public float getAdultFamiliarityCap() {
        return (float) JelliesConfig.INSTANCE.entities.phosphorumJellie.familiarityCap;
    }

    public int getDaysToAdulthood() {
        return JelliesConfig.INSTANCE.entities.phosphorumJellie.adulthoodDays;
    }

    public int getUsesToElderly() {
        return JelliesConfig.INSTANCE.entities.phosphorumJellie.uses;
    }

    public boolean eatsRottenFood() {
        return JelliesConfig.INSTANCE.entities.phosphorumJellie.eatsRottenFood;
    }

    public boolean isReadyForAnimalProduct() {
        return getFamiliarity() > JelliesConfig.INSTANCE.entities.phosphorumJellie.produceFamiliarity && hasProduct() && this.level().dimension() == this.getDimension() && isHungry();
    }

    public long getProductsCooldown() {
        return Math.max(0, JelliesConfig.INSTANCE.entities.phosphorumJellie.produceTicks + getProducedTick() - Calendars.get(level()).getTicks());
    }

    public int getChildCount() {
        return JelliesConfig.INSTANCE.entities.phosphorumJellie.childCount;
    }

    public long getGestationDays() {
        return JelliesConfig.INSTANCE.entities.phosphorumJellie.gestationDays;
    }
    // endregion

    public String getVariantName() {
        return "phosphorum";
    }

    public ResourceLocation getTextureLocation() {
        return Jellies.id("textures/entity/jellie/phosphorum.png");
    }

    public ResourceKey<Level> getDimension() {
        return Level.OVERWORLD;
    }

    public Item getProduct() {
        return JelliesItems.PHOSPHORUM_SLIME_BALL.asItem();
    }

    public boolean isFood(ItemStack stack) {
        if (Helpers.isItem(stack, Items.ROTTEN_FLESH))
            return true;

        return super.isFood(stack);
    }
}
