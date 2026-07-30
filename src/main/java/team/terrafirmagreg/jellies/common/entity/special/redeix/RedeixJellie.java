package team.terrafirmagreg.jellies.common.entity.special.redeix;

import net.dries007.tfc.common.entities.livestock.TFCAnimal;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import team.terrafirmagreg.jellies.Jellies;
import team.terrafirmagreg.jellies.common.entity.JellieBase;

public class RedeixJellie extends JellieBase {
    public RedeixJellie(EntityType<? extends TFCAnimal> animal, Level level) {
        super(animal, level);
    }

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
