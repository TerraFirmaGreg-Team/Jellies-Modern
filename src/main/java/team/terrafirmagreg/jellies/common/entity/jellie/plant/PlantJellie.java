package team.terrafirmagreg.jellies.common.entity.jellie.plant;

import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.plant.Plant;
import net.dries007.tfc.common.entities.livestock.TFCAnimal;
import net.dries007.tfc.common.entities.livestock.TFCAnimalProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;

import earth.terrarium.adastra.api.planets.Planet;
import su.terrafirmagreg.core.common.entity.moonrabbit.MoonRabbit;

import team.terrafirmagreg.jellies.Jellies;
import team.terrafirmagreg.jellies.common.data.JelliesEntities;
import team.terrafirmagreg.jellies.common.data.JelliesItems;
import team.terrafirmagreg.jellies.common.data.JelliesTags;
import team.terrafirmagreg.jellies.common.entity.JellieBase;
import team.terrafirmagreg.jellies.common.entity.jellie.lava.LavaJellie;
import team.terrafirmagreg.jellies.common.entity.special.pyritie.PyritieJellie;

public class PlantJellie extends JellieBase {
    public boolean birthLatexJellie = false;

    public PlantJellie(EntityType<? extends TFCAnimal> animal, Level level) {
        super(animal, level);
    }

    public static boolean spawnRules(EntityType<? extends JellieBase> type, LevelAccessor level, MobSpawnType spawn, BlockPos pos, RandomSource rand) {
        return level.getBiome(pos).is(JelliesTags.Biomes.JellieHabitat) && level.getBiome(pos).is(JelliesTags.Biomes.PlantJellieHabitat) && checkMobSpawnRules(type, level, spawn, pos, rand);
    }

    public String getVariantName() {
        return "plant";
    }

    public ResourceLocation getTextureLocation() {
        return Jellies.id("textures/entity/jellie/plant.png");
    }

    public ResourceKey<Level> getDimension() {
        return Level.NETHER;
    }

    public Item getProduct() {
        return JelliesItems.PLANT_SLIME_BALL.asItem();
    }

    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob other) {
        if (other != this && other instanceof LavaJellie mate && !isFertilized() && !mate.isFertilized() && getUUID().compareTo(mate.getUUID()) < 0) {
            this.onFertilized(mate);
            mate.birthLatexJellie = true;
            return null;
        } else if (other == this && birthLatexJellie) {
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

    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        final ItemStack held = player.getItemInHand(hand);

        if (held.is(TFCBlocks.PLANTS.get(Plant.LILAC).get().asItem()) && this.level().dimension() == Planet.MOON) {
            boolean hasMoonRabbit = this.level().getEntitiesOfClass(MoonRabbit.class, this.getBoundingBox().inflate(2.0D), rabbit -> rabbit.getMoonVariant().id() == 5).stream().findAny().isPresent();

            if (hasMoonRabbit) {
                final PyritieJellie pyJellie = this.convertTo(JelliesEntities.PYRITIE_JELLIE.get(), false);
                if (pyJellie != null && this.level() instanceof ServerLevelAccessor server) {
                    pyJellie.finalizeSpawn(server, this.level().getCurrentDifficultyAt(blockPosition()), MobSpawnType.CONVERSION, null, null);
                }

                held.shrink(1);
                return InteractionResult.SUCCESS;
            }
        }

        return super.mobInteract(player, hand);
    }
}
