package team.terrafirmagreg.jellies.common.data;

import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.util.entry.EntityEntry;
import com.tterrag.registrate.util.entry.ItemEntry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.common.ForgeSpawnEggItem;

import team.terrafirmagreg.jellies.Jellies;

public class JelliesItems {
    public static void init() {
    }

    private static <T extends Mob> ItemEntry<ForgeSpawnEggItem> registerSpawnEgg(EntityEntry<T> entity, int color1, int color2) {
        return Jellies.REGISTRATE.item("spawn_egg/" + entity.getId().getPath(),
                (p) -> new ForgeSpawnEggItem(entity, color1, color2, p))
                .setData(ProviderType.ITEM_MODEL, (ctx, prov) -> prov.withExistingParent(ctx.getName(), ResourceLocation.withDefaultNamespace("item/template_spawn_egg")))
                .register();
    }
}
