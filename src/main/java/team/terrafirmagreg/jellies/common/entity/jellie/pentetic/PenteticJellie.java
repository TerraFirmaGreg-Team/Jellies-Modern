package team.terrafirmagreg.jellies.common.entity.jellie.pentetic;

import com.gregtechceu.gtceu.api.capability.GTCapabilityHelper;
import com.gregtechceu.gtceu.api.capability.IMedicalConditionTracker;
import com.gregtechceu.gtceu.api.data.medicalcondition.MedicalCondition;
import com.gregtechceu.gtceu.common.data.GTMedicalConditions;

import net.dries007.tfc.common.entities.livestock.TFCAnimal;
import net.dries007.tfc.util.calendar.Calendars;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import earth.terrarium.adastra.api.planets.Planet;
import su.terrafirmagreg.core.common.data.tfgt.TFGMedicalConditions;

import team.terrafirmagreg.jellies.Jellies;
import team.terrafirmagreg.jellies.common.data.JelliesTags;
import team.terrafirmagreg.jellies.common.entity.JellieBase;
import team.terrafirmagreg.jellies.config.JelliesConfig;

public class PenteticJellie extends JellieBase {
    public PenteticJellie(EntityType<? extends TFCAnimal> animal, Level level) {
        super(animal, level);
    }

    // region Config Bypass
    public float getAdultFamiliarityCap() {
        return (float) JelliesConfig.INSTANCE.entities.penteticJellie.familiarityCap;
    }

    public int getDaysToAdulthood() {
        return JelliesConfig.INSTANCE.entities.penteticJellie.adulthoodDays;
    }

    public int getUsesToElderly() {
        return JelliesConfig.INSTANCE.entities.penteticJellie.uses;
    }

    public boolean eatsRottenFood() {
        return JelliesConfig.INSTANCE.entities.penteticJellie.eatsRottenFood;
    }

    public boolean isReadyForAnimalProduct() {
        return getFamiliarity() > JelliesConfig.INSTANCE.entities.penteticJellie.produceFamiliarity && hasProduct() && this.level().dimension() == this.getDimension() && isHungry();
    }

    public long getProductsCooldown() {
        return Math.max(0, JelliesConfig.INSTANCE.entities.penteticJellie.produceTicks + getProducedTick() - Calendars.get(level()).getTicks());
    }

    public int getChildCount() {
        return JelliesConfig.INSTANCE.entities.penteticJellie.childCount;
    }

    public long getGestationDays() {
        return JelliesConfig.INSTANCE.entities.penteticJellie.gestationDays;
    }
    // endregion

    public static boolean spawnRules(EntityType<? extends JellieBase> type, LevelAccessor level, MobSpawnType spawn, BlockPos pos, RandomSource rand) {
        return level.getBiome(pos).is(JelliesTags.Biomes.JellieHabitat) && level.getBiome(pos).is(JelliesTags.Biomes.PenteticJellieHabitat) && checkMobSpawnRules(type, level, spawn, pos, rand);
    }

    public String getVariantName() {
        return "pentetic";
    }

    public ResourceLocation getTextureLocation() {
        return Jellies.id("textures/entity/jellie/pentetic.png");
    }

    public ResourceKey<Level> getDimension() {
        return Planet.MARS;
    }

    @Override
    public void tick() {
        this.tickRadAbsorption();
        super.tick();
    }

    private void tickRadAbsorption() {
        if (this.level().isClientSide || tickCount % 60 != 0) {
            return;
        }

        for (Player player : this.level().getEntitiesOfClass(Player.class, getBoundingBox().inflate(2.0D))) {
            IMedicalConditionTracker tracker = GTCapabilityHelper.getMedicalConditionTracker(player);
            if (tracker == null) {
                continue;
            }

            for (var entry : tracker.getMedicalConditions().object2FloatEntrySet()) {
                MedicalCondition condition = entry.getKey();
                if (condition != GTMedicalConditions.CARCINOGEN && condition != TFGMedicalConditions.RADIOACTIVE) {
                    continue;
                }

                if (1F > 0.05F * entry.getFloatValue()) {
                    tracker.removeMedicalCondition(condition);
                } else {
                    tracker.heal(condition, 1);
                }
            }
        }
    }
}
