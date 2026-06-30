package team.terrafirmagreg.jellies.client;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import team.terrafirmagreg.jellies.common.CommonInit;

public class ClientInit extends CommonInit {
    public ClientInit() {
        super();
    }

    @SubscribeEvent
    public void clientSetup(FMLClientSetupEvent evt) {
        evt.enqueueWork(() -> {

        });
    }
}
