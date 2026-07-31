package team.terrafirmagreg.jellies.common.entity.jellie.latex;

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

public class LatexJellie extends JellieBase {
    public LatexJellie(EntityType<? extends TFCAnimal> animal, Level level) {
        super(animal, level);
    }

    // region Config Bypass
    public float getAdultFamiliarityCap() {
        return (float) JelliesConfig.INSTANCE.entities.latexJellie.familiarityCap;
    }

    public int getDaysToAdulthood() {
        return JelliesConfig.INSTANCE.entities.latexJellie.adulthoodDays;
    }

    public int getUsesToElderly() {
        return JelliesConfig.INSTANCE.entities.latexJellie.uses;
    }

    public boolean eatsRottenFood() {
        return JelliesConfig.INSTANCE.entities.latexJellie.eatsRottenFood;
    }

    public boolean isReadyForAnimalProduct() {
        return getFamiliarity() > JelliesConfig.INSTANCE.entities.latexJellie.produceFamiliarity && hasProduct() && this.level().dimension() == this.getDimension() && isHungry();
    }

    public long getProductsCooldown() {
        return Math.max(0, JelliesConfig.INSTANCE.entities.latexJellie.produceTicks + getProducedTick() - Calendars.get(level()).getTicks());
    }

    public int getChildCount() {
        return JelliesConfig.INSTANCE.entities.latexJellie.childCount;
    }

    public long getGestationDays() {
        return JelliesConfig.INSTANCE.entities.latexJellie.gestationDays;
    }
    // endregion

    public static boolean spawnRules(EntityType<? extends JellieBase> type, LevelAccessor level, MobSpawnType spawn, BlockPos pos, RandomSource rand) {
        return level.getBiome(pos).is(JelliesTags.Biomes.JellieHabitat) && level.getBiome(pos).is(JelliesTags.Biomes.LatexJellieHabitat) && checkMobSpawnRules(type, level, spawn, pos, rand);
    }

    public String getVariantName() {
        return "latex";
    }

    public ResourceLocation getTextureLocation() {
        return Jellies.id("textures/entity/jellie/latex.png");
    }

    public ResourceKey<Level> getDimension() {
        return Level.NETHER;
    }

    public Item getProduct() {
        return JelliesItems.LATEX_SLIME_BALL.asItem();
    }
}
