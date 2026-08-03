package team.terrafirmagreg.jellies.common.data;

import java.util.function.Supplier;

import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.util.entry.EntityEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.tterrag.registrate.util.nullness.NonNullSupplier;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import team.terrafirmagreg.jellies.Jellies;
import team.terrafirmagreg.jellies.common.entity.*;
import team.terrafirmagreg.jellies.common.entity.jellie.biotite.BiotiteJellie;
import team.terrafirmagreg.jellies.common.entity.jellie.certus.CertusJellie;
import team.terrafirmagreg.jellies.common.entity.jellie.glowberry.GlowberryJellie;
import team.terrafirmagreg.jellies.common.entity.jellie.herbal.HerbalJellie;
import team.terrafirmagreg.jellies.common.entity.jellie.ice.IceJellie;
import team.terrafirmagreg.jellies.common.entity.jellie.latex.LatexJellie;
import team.terrafirmagreg.jellies.common.entity.jellie.lava.LavaJellie;
import team.terrafirmagreg.jellies.common.entity.jellie.pentetic.PenteticJellie;
import team.terrafirmagreg.jellies.common.entity.jellie.phosphorum.PhosphorumJellie;
import team.terrafirmagreg.jellies.common.entity.jellie.plant.PlantJellie;
import team.terrafirmagreg.jellies.common.entity.jellie.rock.*;
import team.terrafirmagreg.jellies.common.entity.jellie.spring.SpringJellie;
import team.terrafirmagreg.jellies.common.entity.special.pyritie.*;
import team.terrafirmagreg.jellies.common.entity.special.redeix.*;
import team.terrafirmagreg.jellies.common.entity.unique.teto.*;

@Mod.EventBusSubscriber(modid = Jellies.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@SuppressWarnings("unused")
public class JelliesEntities {
    public static void init() {
    }

    // Basic
    public static final EntityEntry<BiotiteJellie> BIOTITE_JELLIE = createJellie("biotite", "Biotite", BiotiteJellie::new, BiotiteJellie::createAttributes, BiotiteJellie::spawnRules,
            () -> JellieBaseRenderer::new);
    public static final EntityEntry<CertusJellie> CERTUS_JELLIE = createJellie("certus", "Certus", CertusJellie::new, CertusJellie::createAttributes, CertusJellie::spawnRules,
            () -> JellieBaseRenderer::new);
    public static final EntityEntry<GlowberryJellie> GLOWBERRY_JELLIE = createJellie("glowberry", "Glowberry", GlowberryJellie::new, GlowberryJellie::createAttributes, GlowberryJellie::spawnRules,
            () -> JellieBaseRenderer::new);
    public static final EntityEntry<HerbalJellie> HERBAL_JELLIE = createJellie("herbal", "Herbal", HerbalJellie::new, HerbalJellie::createAttributes, HerbalJellie::spawnRules,
            () -> JellieBaseRenderer::new);
    public static final EntityEntry<IceJellie> ICE_JELLIE = createJellie("ice", "Ice", IceJellie::new, IceJellie::createAttributes, IceJellie::spawnRules, () -> JellieBaseRenderer::new);
    public static final EntityEntry<LatexJellie> LATEX_JELLIE = createJellie("latex", "Latex", LatexJellie::new, LatexJellie::createAttributes, LatexJellie::spawnRules, () -> JellieBaseRenderer::new);
    public static final EntityEntry<LavaJellie> LAVA_JELLIE = createJellie("lava", "Lava", LavaJellie::new, LavaJellie::createAttributes, LavaJellie::spawnRules, () -> JellieBaseRenderer::new);
    public static final EntityEntry<PenteticJellie> PENTETIC_JELLIE = createJellie("pentetic", "Pentetic", PenteticJellie::new, PenteticJellie::createAttributes, PenteticJellie::spawnRules,
            () -> JellieBaseRenderer::new);
    public static final EntityEntry<PhosphorumJellie> PHOSPHORUM_JELLIE = createJellie("phosphorum", "Phosphorum", PhosphorumJellie::new, PhosphorumJellie::createAttributes,
            PhosphorumJellie::spawnRules,
            () -> JellieBaseRenderer::new);
    public static final EntityEntry<PlantJellie> PLANT_JELLIE = createJellie("plant", "Plant", PlantJellie::new, PlantJellie::createAttributes, PlantJellie::spawnRules, () -> JellieBaseRenderer::new);
    public static final EntityEntry<RockJellie> ROCK_JELLIE = createJellie("rock", "Rock", RockJellie::new, RockJellie::createAttributes, RockJellie::spawnRules, () -> RockJellieRenderer::new);
    public static final EntityEntry<SpringJellie> SPRING_JELLIE = createJellie("spring", "Spring", SpringJellie::new, SpringJellie::createAttributes, SpringJellie::spawnRules,
            () -> JellieBaseRenderer::new);

    // Special
    public static final EntityEntry<PyritieJellie> PYRITIE_JELLIE = createJellie("pyritie", "Pyritie", PyritieJellie::new, PyritieJellie::createAttributes, PyritieJellie::spawnRules,
            () -> PyritieJellieRenderer::new);
    public static final EntityEntry<RedeixJellie> REDEIX_JELLIE = createJellie("eevee", "Eevee", RedeixJellie::new, RedeixJellie::createAttributes, RedeixJellie::spawnRules,
            () -> RedeixJellieRenderer::new);

    // Unique
    public static final EntityEntry<TetoJellie> TETO_JELLIE = createJellie("teto", "Teto", TetoJellie::new, TetoJellie::createAttributes, TetoJellie::spawnRules,
            () -> TetoJellieRenderer::new);

    public static <T extends JellieBase> EntityEntry<T> createJellie(String id, String name, EntityType.EntityFactory<T> factory, Supplier<AttributeSupplier.Builder> attributes,
            SpawnPlacements.SpawnPredicate<T> spawnPredicate, NonNullSupplier<NonNullFunction<EntityRendererProvider.Context, EntityRenderer<? super T>>> renderer) {
        Jellies.REGISTRATE.addDataGenerator(ProviderType.LANG, prov -> {
            prov.add("entity.jellies." + id + ".female", name + " Jellie");
            prov.add("entity.jellies." + id + ".male", name + " Jellie");
        });

        return Jellies.REGISTRATE.entity(id, factory, MobCategory.AMBIENT)
                .properties(p -> p.sized(1F, 1F).clientTrackingRange(8))
                .loot((prov, ctx) -> prov.add(ctx, new LootTable.Builder()))
                .tag(JelliesTags.Entities.JELLIE, JelliesTags.Entities.GENDERLESS)
                .attributes(attributes)
                .renderer(renderer)
                .spawnPlacement(SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, spawnPredicate)
                .lang(name + " Jellie")
                .register();
    }

    @SubscribeEvent
    public static void onEntityLayerRegister(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(JellieBaseModel.LAYER_LOCATION, JellieBaseModel::createInnerBodyLayer);
        event.registerLayerDefinition(JellieBaseOuterLayer.LAYER_LOCATION, JellieBaseModel::createOuterBodyLayer);
        event.registerLayerDefinition(JellieBaseFaceLayer.LAYER_LOCATION, JellieBaseModel::createFaceLayer);

        event.registerLayerDefinition(RockJellieModel.LAYER_LOCATION, RockJellieModel::createInnerBodyLayer);
        event.registerLayerDefinition(RockJellieOuterLayer.LAYER_LOCATION, RockJellieModel::createOuterBodyLayer);
        event.registerLayerDefinition(RockJellieFaceLayer.LAYER_LOCATION, RockJellieModel::createFaceLayer);

        event.registerLayerDefinition(PyritieJellieModel.LAYER_LOCATION, PyritieJellieModel::createInnerBodyLayer);
        event.registerLayerDefinition(PyritieJellieOuterLayer.LAYER_LOCATION, PyritieJellieModel::createOuterBodyLayer);
        event.registerLayerDefinition(PyritieJellieFaceLayer.LAYER_LOCATION, PyritieJellieModel::createFaceLayer);

        event.registerLayerDefinition(RedeixJellieModel.LAYER_LOCATION, RedeixJellieModel::createInnerBodyLayer);
        event.registerLayerDefinition(RedeixJellieOuterLayer.LAYER_LOCATION, RedeixJellieModel::createOuterBodyLayer);
        event.registerLayerDefinition(RedeixJellieFaceLayer.LAYER_LOCATION, RedeixJellieModel::createFaceLayer);

        event.registerLayerDefinition(TetoJellieModel.LAYER_LOCATION, TetoJellieModel::createInnerBodyLayer);
        event.registerLayerDefinition(TetoJellieOuterLayer.LAYER_LOCATION, TetoJellieModel::createOuterBodyLayer);
        event.registerLayerDefinition(TetoJellieFaceLayer.LAYER_LOCATION, TetoJellieModel::createFaceLayer);
    }
}
