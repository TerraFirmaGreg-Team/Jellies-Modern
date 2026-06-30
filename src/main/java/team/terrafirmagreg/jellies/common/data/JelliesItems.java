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

    public static final ItemEntry<Item> PLANT_SLIME_BALL = Jellies.REGISTRATE.item("jellie/slime_ball/plant", Item::new)
            .defaultModel()
            .tag(JelliesTags.Items.SLIME_BALL)
            .register();

    public static final ItemEntry<Item> GLOWBERRY_SLIME_BALL = Jellies.REGISTRATE.item("jellie/slime_ball/glowberry", Item::new)
            .defaultModel()
            .tag(JelliesTags.Items.SLIME_BALL)
            .register();

    public static final ItemEntry<Item> LATEX_SLIME_BALL = Jellies.REGISTRATE.item("jellie/slime_ball/latex", Item::new)
            .defaultModel()
            .tag(JelliesTags.Items.SLIME_BALL)
            .register();

    public static final ItemEntry<ForgeSpawnEggItem> JELLIE_SPAWN_EGG = registerSpawnEgg(JelliesEntities.JELLIE_BASE, 0x6ce3f5, 0x1ca9eb);

    private static <T extends Mob> ItemEntry<ForgeSpawnEggItem> registerSpawnEgg(EntityEntry<T> entity, int color1, int color2) {
        return Jellies.REGISTRATE.item("spawn_egg/" + entity.getId().getPath(),
                (p) -> new ForgeSpawnEggItem(entity, color1, color2, p))
                .setData(ProviderType.ITEM_MODEL, (ctx, prov) -> prov.withExistingParent(ctx.getName(), ResourceLocation.withDefaultNamespace("item/template_spawn_egg")))
                .register();
    }
}
