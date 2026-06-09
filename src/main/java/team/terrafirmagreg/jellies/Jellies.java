package team.terrafirmagreg.jellies;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

import team.terrafirmagreg.jellies.client.ClientInit;
import team.terrafirmagreg.jellies.common.CommonInit;

@Mod(Jellies.MOD_ID)
public final class Jellies {

    public static final String MOD_ID = "jellies";
    public static final String NAME = "Jellies";
    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);
    public static final JelliesRegistrate REGISTRATE = JelliesRegistrate.create(MOD_ID);

    public static ResourceLocation id(String name) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }

    public Jellies() {

        DistExecutor.unsafeRunForDist(() -> ClientInit::new, () -> CommonInit::new);
    }

}
