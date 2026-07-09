package team.terrafirmagreg.jellies.common.data;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.registries.ForgeRegistries;

import team.terrafirmagreg.jellies.Jellies;

public class JelliesTags {
    public static final class Items {
        public static final TagKey<Item> JELLIE_FOOD = createItemTag("jellie_food");
        public static final TagKey<Item> SLIME_BALL = createItemTag("slime_ball");

        private static TagKey<Item> createItemTag(String path) {
            return createItemTag(Jellies.id(path));
        }

        private static TagKey<Item> createItemTag(ResourceLocation resLoc) {
            return TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), resLoc);
        }
    }

    public static final class Blocks {

    }

    public static final class Fluids {

    }

    public static final class Entities {
        public static final TagKey<EntityType<?>> Genderless = createEntityTag("genderless");

        private static TagKey<EntityType<?>> createEntityTag(String path) {
            return createEntityTag(Jellies.id(path));
        }

        private static TagKey<EntityType<?>> createEntityTag(ResourceLocation resLoc) {
            return TagKey.create(ForgeRegistries.ENTITY_TYPES.getRegistryKey(), resLoc);
        }
    }

    public static final class Biomes {
        public static final TagKey<Biome> JellieHabitat = createBiomeTag("jellie_habitat");

        // Regular
        public static final TagKey<Biome> BiotiteJellieHabitat = createBiomeTag("nether/biotite_jellie_habitat");
        public static final TagKey<Biome> CertusJellieHabitat = createBiomeTag("moon/certus_jellie_habitat");
        public static final TagKey<Biome> GlowberryJellieHabitat = createBiomeTag("nether/glowberry_jellie_habitat");
        public static final TagKey<Biome> HerbalJellieHabitat = createBiomeTag("earth/herbal_jellie_habitat");
        public static final TagKey<Biome> IceJellieHabitat = createBiomeTag("nether/ice_jellie_habitat");
        public static final TagKey<Biome> LatexJellieHabitat = createBiomeTag("nether/latex_jellie_habitat");
        public static final TagKey<Biome> LavaJellieHabitat = createBiomeTag("nether/lava_jellie_habitat");
        public static final TagKey<Biome> PenteticJellieHabitat = createBiomeTag("mars/pentetic_jellie_habitat");
        public static final TagKey<Biome> PhosphorumJellieHabitat = createBiomeTag("earth/phosphorum_jellie_habitat");
        public static final TagKey<Biome> PlantJellieHabitat = createBiomeTag("nether/plant_jellie_habitat");
        public static final TagKey<Biome> RockJellieHabitat = createBiomeTag("overworld/rock_jellie_habitat");
        public static final TagKey<Biome> SpringJellieHabitat = createBiomeTag("nether/spring_jellie_habitat");

        // Unique

        // Special
        public static final TagKey<Biome> PyritieJellieHabitat = createBiomeTag("special/pyritie_jellie_habitat");

        private static TagKey<Biome> createBiomeTag(String path) {
            return createBiomeTag(Jellies.id(path));
        }

        private static TagKey<Biome> createBiomeTag(ResourceLocation resLoc) {
            return TagKey.create(ForgeRegistries.BIOMES.getRegistryKey(), resLoc);
        }
    }
}
