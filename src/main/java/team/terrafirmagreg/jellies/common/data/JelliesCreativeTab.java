package team.terrafirmagreg.jellies.common.data;

import com.tterrag.registrate.util.entry.RegistryEntry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;

import team.terrafirmagreg.jellies.Jellies;
import team.terrafirmagreg.jellies.JelliesRegistrate;

@SuppressWarnings({ "unused" })
public class JelliesCreativeTab {
    public static void init() {
    }

    public static RegistryEntry<CreativeModeTab> JELLIES = Jellies.REGISTRATE.defaultCreativeTab("jellies",
            builder -> builder.title(Component.translatable("jellies.creative_tab.jellies"))
                    .icon(() -> new ItemStack(Items.SLIME_BALL))
                    .displayItems(new RegistrateDisplayItemsGenerator("jellies", Jellies.REGISTRATE)))
            .register();

    public record RegistrateDisplayItemsGenerator(String name, JelliesRegistrate registrate) implements CreativeModeTab.DisplayItemsGenerator {

        @Override
        public void accept(CreativeModeTab.ItemDisplayParameters itemDisplayParameters,
                CreativeModeTab.Output output) {
            var tab = registrate.get(name, Registries.CREATIVE_MODE_TAB);
            for (var entry : registrate.getAll(Registries.BLOCK)) {
                Block block = entry.get();
                var stack = new ItemStack(block, 1);

                if (registrate.isInCreativeTab(entry, tab))
                    continue;
                if (entry.getId().getNamespace().equals("jellies") && !stack.isEmpty())
                    output.accept(block);
            }
            for (var entry : registrate.getAll(Registries.ITEM)) {
                if (registrate.isInCreativeTab(entry, tab))
                    continue;
                Item item = entry.get();
                var stack = new ItemStack(item, 1);
                if (item instanceof BlockItem)
                    continue;
                if (entry.getId().getNamespace().equals("jellies"))
                    output.accept(stack);
            }
        }
    }
}
