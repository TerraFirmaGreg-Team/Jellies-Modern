package team.terrafirmagreg.jellies.common.entity.jellie.lava;

import net.dries007.tfc.common.entities.livestock.TFCAnimal;
import net.dries007.tfc.common.entities.livestock.TFCAnimalProperties;
import net.dries007.tfc.util.calendar.Calendars;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

import team.terrafirmagreg.jellies.Jellies;
import team.terrafirmagreg.jellies.common.data.JelliesEntities;
import team.terrafirmagreg.jellies.common.entity.JellieBase;
import team.terrafirmagreg.jellies.common.entity.jellie.plant.PlantJellie;
import team.terrafirmagreg.jellies.config.JelliesConfig;

public class LavaJellie extends JellieBase {
    public boolean birthLatexJellie = false;

    public LavaJellie(EntityType<? extends TFCAnimal> animal, Level level) {
        super(animal, level);
    }

    // region Config Bypass
    public float getAdultFamiliarityCap() {
        return (float) JelliesConfig.INSTANCE.entities.lavaJellie.familiarityCap;
    }

    public int getDaysToAdulthood() {
        return JelliesConfig.INSTANCE.entities.lavaJellie.adulthoodDays;
    }

    public int getUsesToElderly() {
        return JelliesConfig.INSTANCE.entities.lavaJellie.uses;
    }

    public boolean eatsRottenFood() {
        return JelliesConfig.INSTANCE.entities.lavaJellie.eatsRottenFood;
    }

    public boolean isReadyForAnimalProduct() {
        return getFamiliarity() > JelliesConfig.INSTANCE.entities.lavaJellie.produceFamiliarity && hasProduct() && this.level().dimension() == this.getDimension() && isHungry();
    }

    public long getProductsCooldown() {
        return Math.max(0, JelliesConfig.INSTANCE.entities.lavaJellie.produceTicks + getProducedTick() - Calendars.get(level()).getTicks());
    }

    public int getChildCount() {
        return JelliesConfig.INSTANCE.entities.lavaJellie.childCount;
    }

    public long getGestationDays() {
        return JelliesConfig.INSTANCE.entities.lavaJellie.gestationDays;
    }
    // endregion

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

    public boolean fireImmune() {
        return true;
    }
}
