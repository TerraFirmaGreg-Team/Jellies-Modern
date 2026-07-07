package team.terrafirmagreg.jellies;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraftforge.fml.loading.LoadingModList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.builders.Builder;
import com.tterrag.registrate.builders.NoConfigBuilder;
import com.tterrag.registrate.util.OneTimeEventReceiver;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.tterrag.registrate.util.nullness.NonNullSupplier;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@SuppressWarnings({ "removal" })
public class JelliesRegistrate extends AbstractRegistrate<JelliesRegistrate> {
    private final AtomicBoolean registered = new AtomicBoolean(false);

    protected JelliesRegistrate(String modId) {
        super(modId);
    }

    @NotNull
    public static JelliesRegistrate create(String modId) {
        return new JelliesRegistrate(modId);
    }

    public void registerRegistrate() {
        registerEventListeners(FMLJavaModLoadingContext.get().getModEventBus());
    }

    @Override
    public JelliesRegistrate registerEventListeners(IEventBus bus) {
        if (!registered.getAndSet(true)) {
            Consumer<RegisterEvent> onRegister = this::onRegister;
            Consumer<RegisterEvent> onRegisterLate = this::onRegisterLate;
            bus.addListener(EventPriority.LOW, onRegister);
            bus.addListener(EventPriority.LOWEST, onRegisterLate);
            bus.addListener(this::onBuildCreativeModeTabContents);
            OneTimeEventReceiver.addModListener(this, FMLCommonSetupEvent.class, $ -> {
                OneTimeEventReceiver.unregister(this, onRegister, RegisterEvent.class);
                OneTimeEventReceiver.unregister(this, onRegisterLate, RegisterEvent.class);
            });
            if (getDoDatagen(this).get()) {
                OneTimeEventReceiver.addModListener(this, GatherDataEvent.class, this::onData);
            }
        }
        return this;
    }

    protected <P> NoConfigBuilder<CreativeModeTab, CreativeModeTab, P> createCreativeModeTab(P parent, String name, Consumer<CreativeModeTab.Builder> config) {
        return this.generic(parent, name, Registries.CREATIVE_MODE_TAB, () -> {
            var builder = CreativeModeTab.builder()
                    .icon(() -> getAll(Registries.ITEM).stream().findFirst().map(ItemEntry::cast)
                            .map(ItemEntry::asStack).orElse(new ItemStack(Items.AIR)));
            config.accept(builder);
            return builder.build();
        });
    }

    private RegistryEntry<CreativeModeTab> currentTab;
    private static final Map<RegistryEntry<?>, RegistryEntry<CreativeModeTab>> TAB_LOOKUP = new IdentityHashMap<>();

    public RegistryEntry<CreativeModeTab> creativeModeTab() {
        return this.currentTab;
    }

    public void creativeModeTab(Supplier<RegistryEntry<CreativeModeTab>> currentTab) {
        this.currentTab = currentTab.get();
    }

    public void creativeModeTab(RegistryEntry<CreativeModeTab> currentTab) {
        this.currentTab = currentTab;
    }

    public boolean isInCreativeTab(RegistryEntry<?> entry, RegistryEntry<CreativeModeTab> tab) {
        return TAB_LOOKUP.get(entry) == tab;
    }

    public void setCreativeTab(RegistryEntry<?> entry, @Nullable RegistryEntry<CreativeModeTab> tab) {
        TAB_LOOKUP.put(entry, tab);
    }

    protected <R, T extends R> RegistryEntry<T> accept(String name, ResourceKey<? extends Registry<R>> type,
            Builder<R, T, ?, ?> builder, NonNullSupplier<? extends T> creator,
            NonNullFunction<RegistryObject<T>, ? extends RegistryEntry<T>> entryFactory) {
        RegistryEntry<T> entry = super.accept(name, type, builder, creator, entryFactory);

        if (this.currentTab != null) {
            TAB_LOOKUP.put(entry, this.currentTab);
        }

        return entry;
    }

    public <P> NoConfigBuilder<CreativeModeTab, CreativeModeTab, P> defaultCreativeTab(P parent, String name,
            Consumer<CreativeModeTab.Builder> config) {
        return createCreativeModeTab(parent, name, config);
    }

    public static NonNullSupplier<Boolean> getDoDatagen(AbstractRegistrate<?> registrate) {
        if (LoadingModList.get().getModFileById("tfg") != null) {
            return ((com.gregtechceu.gtceu.core.mixins.registrate.AbstractRegistrateAccessor) registrate).getDoDatagen();
        } else {
            return ((team.terrafirmagreg.jellies.mixin.common.registrate.AbstractRegistrateAccessor) registrate).getDoDatagen();
        }
    }
}
