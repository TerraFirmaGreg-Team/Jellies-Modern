package team.terrafirmagreg.jellies.common.entity.jellie.base;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;

import lombok.Getter;

import team.terrafirmagreg.jellies.Jellies;
import team.terrafirmagreg.jellies.common.data.JelliesItems;
import team.terrafirmagreg.jellies.common.data.JelliesTags;

@MethodsReturnNonnullByDefault
public enum JellieBaseVariant implements StringRepresentable {
    // BATCH 1
    PLANT(Jellies.id("textures/entity/jellie/plant.png"), Level.NETHER, JelliesTags.Biomes.PlantJellieHabitat, JelliesItems.PLANT_SLIME_BALL.asItem()),
    GLOWBERRY(Jellies.id("textures/entity/jellie/glowberry.png"), Level.NETHER, JelliesTags.Biomes.GlowberryJellieHabitat, JelliesItems.GLOWBERRY_SLIME_BALL.asItem()),
    SPRING(Jellies.id("textures/entity/jellie/spring.png"), Level.NETHER, JelliesTags.Biomes.SpringJellieHabitat, null),
    ICE(Jellies.id("textures/entity/jellie/ice.png"), Level.NETHER, JelliesTags.Biomes.IceJellieHabitat, null),
    LAVA(Jellies.id("textures/entity/jellie/lava.png"), Level.NETHER, JelliesTags.Biomes.LavaJellieHabitat, null),
    LATEX(Jellies.id("textures/entity/jellie/latex.png"), Level.NETHER, null, JelliesItems.LATEX_SLIME_BALL.asItem()),

    // BATCH 2
    CERTUS(Jellies.id("textures/entity/jellie/certus.png"), Level.OVERWORLD, null, null),
    PHOSPHORUM(Jellies.id("textures/entity/jellie/phosphorum.png"), Level.OVERWORLD, null, null),
    PENTETIC(Jellies.id("textures/entity/jellie/pentetic.png"), Level.OVERWORLD, null, null),
    HERBAL(Jellies.id("textures/entity/jellie/herbal.png"), Level.OVERWORLD, null, null),
    BIOTITE(Jellies.id("textures/entity/jellie/biotite.png"), Level.NETHER, null, null);

    private static final Map<String, JellieBaseVariant> variantNameMap = new HashMap<>();

    static {
        for (JellieBaseVariant variant : values()) {
            variantNameMap.put(variant.getSerializedName(), variant);
        }
    }

    private final String name;
    @Getter
    private final ResourceLocation texture;
    @Getter
    private final ResourceKey<Level> dimension;
    @Getter
    private final TagKey<Biome> biome;
    @Getter
    private final Item item;

    JellieBaseVariant(ResourceLocation texture, @Nullable ResourceKey<Level> dimension, @Nullable TagKey<Biome> biome, @Nullable Item item) {
        this.name = this.name().toLowerCase(Locale.ROOT);
        this.texture = texture;
        this.dimension = dimension;
        this.biome = biome;
        this.item = item;
    }

    // region Getters
    public @NotNull String getSerializedName() {
        return this.name;
    }

    public static JellieBaseVariant getByName(String name) {
        JellieBaseVariant variant = variantNameMap.get(name);
        if (variant != null) {
            return variant;
        }

        return SPRING;
    }

    public static JellieBaseVariant getByHabitat(ResourceKey<Level> dimension, Holder<Biome> biome) {
        for (Map.Entry<String, JellieBaseVariant> entry : variantNameMap.entrySet()) {
            JellieBaseVariant variant = entry.getValue();

            if (variant.getBiome() != null &&
                    dimension.equals(variant.getDimension()) &&
                    biome.is(variant.getBiome())) {
                return variant;
            }
        }

        return SPRING;
    }
    // endregion
}
