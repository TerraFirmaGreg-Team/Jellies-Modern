package team.terrafirmagreg.jellies.common.data;

import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.util.entry.EntityEntry;
import com.tterrag.registrate.util.entry.ItemEntry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;

import team.terrafirmagreg.jellies.Jellies;

@SuppressWarnings("unused")
public class JelliesItems {
    public static void init() {
    }

    public static final ItemEntry<Item> JELLIE_FOOD = Jellies.REGISTRATE.item("jellie/food/nutritional_mash", Item::new)
            .defaultModel()
            .tag(JelliesTags.Items.JELLIE_FOOD)
            .lang("Nutritional Mash")
            .register();

    public static final ItemEntry<Item> PLANT_SLIME_BALL = Jellies.REGISTRATE.item("jellie/slime_ball/plant", Item::new)
            .defaultModel()
            .tag(JelliesTags.Items.SLIME_BALL)
            .lang("Plant Slime Ball")
            .register();

    public static final ItemEntry<Item> GLOWBERRY_SLIME_BALL = Jellies.REGISTRATE.item("jellie/slime_ball/glowberry", Item::new)
            .defaultModel()
            .tag(JelliesTags.Items.SLIME_BALL)
            .lang("Glow Berry Slime Ball")
            .register();

    public static final ItemEntry<Item> MILK_SLIME_BALL = Jellies.REGISTRATE.item("jellie/slime_ball/milk", Item::new)
            .defaultModel()
            .tag(JelliesTags.Items.SLIME_BALL)
            .lang("Milk Slime Ball")
            .register();

    public static final ItemEntry<Item> PHOSPHORUM_SLIME_BALL = Jellies.REGISTRATE.item("jellie/slime_ball/phosphorum", Item::new)
            .defaultModel()
            .tag(JelliesTags.Items.SLIME_BALL)
            .lang("Phosphorum Slime Ball")
            .register();

    public static final ItemEntry<Item> HERBAL_SLIME_BALL = Jellies.REGISTRATE.item("jellie/slime_ball/herbal", Item::new)
            .defaultModel()
            .tag(JelliesTags.Items.SLIME_BALL)
            .lang("Herbal Slime Ball")
            .register();

    public static final ItemEntry<Item> BIOTITE_SLIME_BALL = Jellies.REGISTRATE.item("jellie/slime_ball/biotite", Item::new)
            .defaultModel()
            .tag(JelliesTags.Items.SLIME_BALL)
            .lang("Biotite Slime Ball")
            .register();

    public static final ItemEntry<Item> SPRING_SLIME_BALL = Jellies.REGISTRATE.item("jellie/slime_ball/spring", Item::new)
            .defaultModel()
            .tag(JelliesTags.Items.SLIME_BALL)
            .lang("Spring Slime Ball")
            .register();

    // Basic
    public static final ItemEntry<ForgeSpawnEggItem> BIOTITE_JELLIE_SPAWN_EGG = registerSpawnEgg("Biotite", JelliesEntities.BIOTITE_JELLIE, 0xB8B8B8, 0x3C4239);
    public static final ItemEntry<ForgeSpawnEggItem> CERTUS_JELLIE_SPAWN_EGG = registerSpawnEgg("Certus", JelliesEntities.CERTUS_JELLIE, 0xB5D8FC, 0x6689AD);
    public static final ItemEntry<ForgeSpawnEggItem> GLOWBERRY_JELLIE_SPAWN_EGG = registerSpawnEgg("Glowberry", JelliesEntities.GLOWBERRY_JELLIE, 0xFFE8A9, 0xAD8925);
    public static final ItemEntry<ForgeSpawnEggItem> HERBAL_JELLIE_SPAWN_EGG = registerSpawnEgg("Herbal", JelliesEntities.HERBAL_JELLIE, 0xB0BBA0, 0x536733);
    public static final ItemEntry<ForgeSpawnEggItem> ICE_JELLIE_SPAWN_EGG = registerSpawnEgg("Ice", JelliesEntities.ICE_JELLIE, 0xD3E2FF, 0x678AFF);
    public static final ItemEntry<ForgeSpawnEggItem> MILK_JELLIE_SPAWN_EGG = registerSpawnEgg("Milk", JelliesEntities.MILK_JELLIE, 0xFFFBE6, 0xB3AD98);
    public static final ItemEntry<ForgeSpawnEggItem> LAVA_JELLIE_SPAWN_EGG = registerSpawnEgg("Lava", JelliesEntities.LAVA_JELLIE, 0xDA7D25, 0xC13C03);
    public static final ItemEntry<ForgeSpawnEggItem> PENTETIC_JELLIE_SPAWN_EGG = registerSpawnEgg("Pentetic", JelliesEntities.PENTETIC_JELLIE, 0xF0EDF2, 0xA094AC);
    public static final ItemEntry<ForgeSpawnEggItem> PHOSPHORUM_JELLIE_SPAWN_EGG = registerSpawnEgg("Phosphorum", JelliesEntities.PHOSPHORUM_JELLIE, 0x5D2A25, 0x281111);
    public static final ItemEntry<ForgeSpawnEggItem> PLANT_JELLIE_SPAWN_EGG = registerSpawnEgg("Plant", JelliesEntities.PLANT_JELLIE, 0x7AAA4B, 0x17430C);
    public static final ItemEntry<ForgeSpawnEggItem> ROCK_JELLIE_SPAWN_EGG = registerSpawnEgg("Rock", JelliesEntities.ROCK_JELLIE, 0x7C7C7C, 0x363636);
    public static final ItemEntry<ForgeSpawnEggItem> SPRING_JELLIE_SPAWN_EGG = registerSpawnEgg("Spring", JelliesEntities.SPRING_JELLIE, 0x6ce3f5, 0x1ca9eb);

    // Special
    public static final ItemEntry<ForgeSpawnEggItem> PYRITIE_JELLIE_SPAWN_EGG = registerSpawnEgg("Pyritie", JelliesEntities.PYRITIE_JELLIE, 0xFFB2E1, 0xFF7092);
    public static final ItemEntry<ForgeSpawnEggItem> REDEIX_JELLIE_SPAWN_EGG = registerSpawnEgg("Eevee", JelliesEntities.REDEIX_JELLIE, 0xBE9C71, 0x6E4D30);

    // Unique
    public static final ItemEntry<ForgeSpawnEggItem> TETO_JELLIE_SPAWN_EGG = registerSpawnEgg("Teto", JelliesEntities.TETO_JELLIE, 0xEB7185, 0xA5163D);

    private static <T extends Mob> ItemEntry<ForgeSpawnEggItem> registerSpawnEgg(String name, EntityEntry<T> entity, int color1, int color2) {
        return Jellies.REGISTRATE.item("spawn_egg/" + entity.getId().getPath(),
                (p) -> new ForgeSpawnEggItem(entity, color1, color2, p))
                .setData(ProviderType.ITEM_MODEL, (ctx, prov) -> prov.withExistingParent(ctx.getName(), ResourceLocation.withDefaultNamespace("item/template_spawn_egg")))
                .lang(name + " Jellie Spawn Egg")
                .register();
    }
}
