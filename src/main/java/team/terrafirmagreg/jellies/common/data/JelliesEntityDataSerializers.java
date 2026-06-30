package team.terrafirmagreg.jellies.common.data;

import java.util.function.Supplier;

import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import team.terrafirmagreg.jellies.Jellies;
import team.terrafirmagreg.jellies.common.entity.jellie.base.JellieBaseVariant;

public class JelliesEntityDataSerializers {
    public static final DeferredRegister<EntityDataSerializer<?>> ENTITY_DATA_SERIALIZERS = DeferredRegister
            .create(ForgeRegistries.Keys.ENTITY_DATA_SERIALIZERS, Jellies.MOD_ID);

    public static final RegistryObject<EntityDataSerializer<JellieBaseVariant>> JELLIE_VARIANT = register("jellie_variant",
            () -> EntityDataSerializer.simple(
                    (buf, variant) -> buf.writeUtf(variant.getSerializedName()),
                    buf -> JellieBaseVariant.getByName(buf.readUtf())));

    private static <T extends EntityDataSerializer<?>> RegistryObject<T> register(String name, Supplier<T> dataSerializer) {
        return ENTITY_DATA_SERIALIZERS.register(name, dataSerializer);
    }
}
