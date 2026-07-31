package team.terrafirmagreg.jellies.common;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import team.terrafirmagreg.jellies.Jellies;
import team.terrafirmagreg.jellies.common.data.*;
import team.terrafirmagreg.jellies.config.JelliesConfig;

@SuppressWarnings({ "removal" })
public class CommonInit {
    public CommonInit() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.register(this);
        JelliesConfig.init();
        Jellies.REGISTRATE.registerEventListeners(eventBus);

        JelliesItems.init();
        JelliesCreativeTab.init();
        JelliesEntities.init();
        JelliesSounds.SOUNDS.register(eventBus);
    }

    public static void init() {
    }

    @SubscribeEvent
    public void modConstruct(FMLConstructModEvent event) {
        event.enqueueWork(CommonInit::init);
    }

    @SubscribeEvent
    public void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {

        });
    }
}
