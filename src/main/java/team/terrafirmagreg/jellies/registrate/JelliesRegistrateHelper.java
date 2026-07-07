package team.terrafirmagreg.jellies.registrate;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.util.nullness.NonNullSupplier;

import net.minecraftforge.fml.loading.LoadingModList;

public class JelliesRegistrateHelper {
    public static NonNullSupplier<Boolean> getDoDatagen(AbstractRegistrate<?> registrate) {
        if (LoadingModList.get().getModFileById("tfg") != null) {
            return ((com.gregtechceu.gtceu.core.mixins.registrate.AbstractRegistrateAccessor) registrate).getDoDatagen();
        } else {
            return ((team.terrafirmagreg.jellies.mixin.common.registrate.AbstractRegistrateAccessor) registrate).getDoDatagen();
        }
    }
}

