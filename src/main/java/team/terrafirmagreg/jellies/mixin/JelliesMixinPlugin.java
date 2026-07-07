package team.terrafirmagreg.jellies.mixin;

import java.util.List;
import java.util.Set;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import net.minecraftforge.fml.loading.LoadingModList;

public class JelliesMixinPlugin implements IMixinConfigPlugin {
    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        boolean isTfgLoaded = isModLoaded("tfg");

        if (mixinClassName.equals("team.terrafirmagreg.jellies.mixin.client.tfc.entities.EntityTooltipOverwriteMixin")
                || mixinClassName.equals("team.terrafirmagreg.jellies.mixin.common.registrate.AbstractRegistrateAccessor"))
            return !isTfgLoaded;
        if (mixinClassName.equals("team.terrafirmagreg.jellies.mixin.client.tfc.entities.EntityTooltipInjectMixin"))
            return isTfgLoaded;

        return true;
    }

    // spotless:off
    @Override public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}
    @Override public void onLoad(String mixinPackage) {}
    @Override public String getRefMapperConfig() {return null;}
    @Override public List<String> getMixins() {return null;}
    @Override public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
    @Override public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
    // spotless:on

    private static boolean isModLoaded(String modid) {
        return LoadingModList.get().getModFileById(modid) != null;
    }
}
