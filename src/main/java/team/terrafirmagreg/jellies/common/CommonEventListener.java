package team.terrafirmagreg.jellies.common;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import team.terrafirmagreg.jellies.Jellies;

@Mod.EventBusSubscriber(modid = Jellies.MOD_ID)
public class CommonEventListener {
    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event) {

    }

    @SubscribeEvent
    public static void onEntityLayerRegister(EntityRenderersEvent.RegisterLayerDefinitions event) {

    }
}
