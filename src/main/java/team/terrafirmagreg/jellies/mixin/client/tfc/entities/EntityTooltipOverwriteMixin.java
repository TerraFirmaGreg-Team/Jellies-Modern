package team.terrafirmagreg.jellies.mixin.client.tfc.entities;

import static net.dries007.tfc.compat.jade.common.EntityTooltips.*;
import static net.dries007.tfc.compat.jade.common.EntityTooltips.FISH;
import static net.dries007.tfc.compat.jade.common.EntityTooltips.HOOK;
import static net.dries007.tfc.compat.jade.common.EntityTooltips.OCELOT;
import static net.dries007.tfc.compat.jade.common.EntityTooltips.PACK_PREDATOR;
import static net.dries007.tfc.compat.jade.common.EntityTooltips.PREDATOR;

import java.util.Locale;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;

import net.dries007.tfc.common.entities.ai.predator.PackPredator;
import net.dries007.tfc.common.entities.ai.prey.TFCOcelot;
import net.dries007.tfc.common.entities.aquatic.TFCSquid;
import net.dries007.tfc.common.entities.livestock.MammalProperties;
import net.dries007.tfc.common.entities.livestock.TFCAnimal;
import net.dries007.tfc.common.entities.livestock.TFCAnimalProperties;
import net.dries007.tfc.common.entities.livestock.horse.TFCChestedHorse;
import net.dries007.tfc.common.entities.livestock.horse.TFCHorse;
import net.dries007.tfc.common.entities.misc.TFCFishingHook;
import net.dries007.tfc.common.entities.predator.Predator;
import net.dries007.tfc.common.entities.prey.TFCFrog;
import net.dries007.tfc.common.entities.prey.TFCRabbit;
import net.dries007.tfc.common.entities.prey.WildAnimal;
import net.dries007.tfc.compat.jade.common.EntityTooltip;
import net.dries007.tfc.compat.jade.common.EntityTooltips;
import net.dries007.tfc.compat.jade.common.RegisterCallback;
import net.dries007.tfc.config.TFCConfig;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.calendar.Calendars;
import net.dries007.tfc.util.calendar.ICalendar;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.animal.WaterAnimal;

import team.terrafirmagreg.jellies.Jellies;
import team.terrafirmagreg.jellies.common.data.JelliesTags;
import team.terrafirmagreg.jellies.common.entity.jellie.base.JellieBase;

@Mixin(value = EntityTooltips.class, remap = false)
public class EntityTooltipOverwriteMixin {
    /**
     * @author Sakura
     * @reason Overwrite to hide gender in Jade for genderless animals
     */
    @Overwrite
    public static void register(RegisterCallback<EntityTooltip, Entity> registry) {
        registry.register("animal", JELLIE_ANIMAL, TFCAnimal.class);
        registry.register("horse", ANIMAL, TFCHorse.class);
        registry.register("chested_horse", ANIMAL, TFCChestedHorse.class);
        registry.register("rabbit", ANIMAL, TFCRabbit.class);
        registry.register("wild_animal", ANIMAL, WildAnimal.class);
        registry.register("frog", FROG, TFCFrog.class);
        registry.register("squid", SQUID, TFCSquid.class);
        registry.register("fish", FISH, WaterAnimal.class);
        registry.register("predator", PREDATOR, Predator.class);
        registry.register("pack_predator", PACK_PREDATOR, PackPredator.class);
        registry.register("ocelot", OCELOT, TFCOcelot.class);
        registry.register("rabbit", RABBIT, Rabbit.class);
        registry.register("fishing_hook", HOOK, TFCFishingHook.class);
        registry.register("jellie", JELLIE_BASE, JellieBase.class);
    }

    @Unique
    private static final EntityTooltip JELLIE_BASE = (level, entity, tooltip) -> {
        if (entity instanceof JellieBase jellie) {
            tooltip.accept(Component.translatable(
                    (Jellies.MOD_ID + ".tooltip.jellie.variant." + jellie.getVariant().getSerializedName())
                            .toLowerCase(Locale.ROOT)));
        }
    };

    @Unique
    private static final EntityTooltip JELLIE_ANIMAL = (level, entity, tooltip) -> {
        if (entity instanceof WildAnimal animal) {
            if (animal.displayMaleCharacteristics()) {
                tooltip.accept(Helpers.translateEnum(TFCAnimalProperties.Gender.MALE));
            } else if (animal.displayFemaleCharacteristics()) {
                tooltip.accept(Helpers.translateEnum(TFCAnimalProperties.Gender.FEMALE));
            }
            if (animal.isBaby()) {
                tooltip.accept(Component.translatable("tfc.jade.juvenile"));
            }
        }

        if (entity instanceof TFCAnimalProperties animal) {
            final MutableComponent line1 = Component.empty();
            boolean genderless = entity.getType().is(JelliesTags.Entities.Genderless);
            if (!genderless) {
                line1.append(Helpers.translateEnum(animal.getGender()));
            }

            if (animal.isFertilized()) {
                if (!genderless) {
                    line1.append(", ");
                }
                line1.append(Component.translatable("tfc.tooltip.fertilized"));
            }
            float familiarity = Math.max(0.0F, Math.min(1.0F, animal.getFamiliarity()));
            String familiarityPercent = String.format("%.2f", familiarity * 100.0F);

            final TFCAnimalProperties.Age age = animal.getAgeType();
            ChatFormatting familiarityStyle = ChatFormatting.GRAY;
            if (familiarity >= animal.getAdultFamiliarityCap() && age != TFCAnimalProperties.Age.CHILD) {
                familiarityStyle = ChatFormatting.RED;
            } else if (familiarity >= TFCConfig.SERVER.familiarityDecayLimit.get()) {
                familiarityStyle = ChatFormatting.WHITE;
            }
            if (!genderless || animal.isFertilized()) {
                line1.append(", ");
            }
            line1.append(Component.translatable("tfc.jade.familiarity", familiarityPercent).withStyle(familiarityStyle));
            tooltip.accept(line1);
            tooltip.accept(Component.translatable("tfc.jade.animal_size", animal.getGeneticSize()));
            if (animal.isReadyForAnimalProduct()) {
                tooltip.accept(animal.getProductReadyName().withStyle(ChatFormatting.GREEN));
            }
            if (animal.isReadyToMate()) {
                tooltip.accept(Component.translatable("tfc.jade.can_mate"));
            }

            final double usageRatio = animal.getUses() >= animal.getUsesToElderly() ? 0.99 : (float) animal.getUses() / animal.getUsesToElderly();
            switch (age) {
                case CHILD ->
                    tooltip.accept(Component.translatable("tfc.jade.adulthood_progress",
                            Calendars.get(level).getTimeDelta(ICalendar.TICKS_IN_DAY * (animal.getDaysToAdulthood()
                                    + animal.getBirthDay() - Calendars.get(level).getTotalDays()))));
                case ADULT ->
                    tooltip.accept(Component.translatable("tfc.jade.animal_wear",
                            String.format("%d%%", Math.min(100, Math.round(100f * usageRatio)))));
                case OLD -> tooltip.accept(Component.translatable("tfc.jade.old_animal"));
            }
        }

        if (entity instanceof MammalProperties mammal) {
            if (mammal.getPregnantTime() > 0) {
                tooltip.accept(Component.translatable("tfc.tooltip.animal.pregnant", entity.getName().getString()));

                final ICalendar calendar = Calendars.get(level);
                tooltip.accept(Component.translatable("tfc.jade.gestation_time_left",
                        calendar.getTimeDelta(ICalendar.TICKS_IN_DAY * (mammal.getGestationDays()
                                + mammal.getPregnantTime() - Calendars.get(level).getTotalDays()))));
            }
        }
    };
}
