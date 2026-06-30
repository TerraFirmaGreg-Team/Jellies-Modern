package team.terrafirmagreg.jellies.common.data;

import com.tterrag.registrate.util.entry.EntityEntry;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import team.terrafirmagreg.jellies.Jellies;
import team.terrafirmagreg.jellies.common.entity.jellie.base.*;

@Mod.EventBusSubscriber(modid = Jellies.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@SuppressWarnings("unused")
public class JelliesEntities {
    public static void init() {
    }

    public static final EntityEntry<JellieBase> JELLIE_BASE = Jellies.REGISTRATE.entity("jellie", JellieBase::new, MobCategory.AMBIENT)
            .properties(p -> p.sized(1F, 1F).clientTrackingRange(8))
            .loot((prov, ctx) -> prov.add(ctx, new LootTable.Builder()))
            .tag(JelliesTags.Entities.Genderless)
            .attributes(JellieBase::createAttributes)
            .renderer(() -> JellieBaseRenderer::new)
            .spawnPlacement(SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, JellieBase::spawnRules)
            .register();

    @SubscribeEvent
    public static void onEntityLayerRegister(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(JellieBaseModel.LAYER_LOCATION, JellieBaseModel::createInnerBodyLayer);
        event.registerLayerDefinition(JellieBaseOuterLayer.LAYER_LOCATION, JellieBaseModel::createOuterBodyLayer);
        event.registerLayerDefinition(JellieBaseFaceLayer.LAYER_LOCATION, JellieBaseModel::createFaceLayer);
    }
}
