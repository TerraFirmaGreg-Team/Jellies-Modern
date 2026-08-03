package team.terrafirmagreg.jellies.common.datagen;

import com.tterrag.registrate.providers.RegistrateLangProvider;

public class JelliesLangHandler {
    public static void init(RegistrateLangProvider provider) {
        provider.add("config.screen.jellies", "Jellies Configuration");

        provider.add("config.jellies.option.entities", "Entities");
        provider.add("config.jellies.option.entities.comment.0", "Config options for jellie stats.");

        provider.add("config.jellies.option.biotiteJellie", "Biotite Jellie");
        provider.add("config.jellies.option.certusJellie", "Certus Jellie");
        provider.add("config.jellies.option.glowberryJellie", "Glowberry Jellie");
        provider.add("config.jellies.option.herbalJellie", "Herbal Jellie");
        provider.add("config.jellies.option.iceJellie", "Ice Jellie");
        provider.add("config.jellies.option.latexJellie", "Latex Jellie");
        provider.add("config.jellies.option.lavaJellie", "Lava Jellie");
        provider.add("config.jellies.option.penteticJellie", "Pentetic Jellie");
        provider.add("config.jellies.option.phosphorumJellie", "Phosphorum Jellie");
        provider.add("config.jellies.option.plantJellie", "Plant Jellie");
        provider.add("config.jellies.option.rockJellie", "Rock Jellie");
        provider.add("config.jellies.option.springJellie", "Spring Jellie");
        provider.add("config.jellies.option.pyritieJellie", "Pyritie Jellie");
        provider.add("config.jellies.option.redeixJellie", "Eevee Jellie");
        provider.add("config.jellies.option.tetoJellie", "Teto Jellie");

        provider.add("config.jellies.option.familiarityCap", "Familiarity Cap");
        provider.add("config.jellies.option.familiarityCap.comment.0", "Maximum familiarity that a wild jellie can reach.");
        provider.add("config.jellies.option.familiarityCap.comment.1", "Default: 1");
        provider.add("config.jellies.option.adulthoodDays", "Adulthood Days");
        provider.add("config.jellies.option.adulthoodDays.comment.0", "How many days it takes a jellie to reach adulthood.");
        provider.add("config.jellies.option.adulthoodDays.comment.1", "Default: 16");
        provider.add("config.jellies.option.uses", "Uses");
        provider.add("config.jellies.option.uses.comment.0", "How many uses a jellie has before becoming old.");
        provider.add("config.jellies.option.uses.comment.1", "Default: 120");
        provider.add("config.jellies.option.eatsRottenFood", "Eats Rotten Food");
        provider.add("config.jellies.option.eatsRottenFood.comment.0", "Does jellie eat rotten food.");
        provider.add("config.jellies.option.eatsRottenFood.comment.1", "Default: false");
        provider.add("config.jellies.option.produceTicks", "Produce Ticks");
        provider.add("config.jellies.option.produceTicks.comment.0", "How long it takes for a jellie to produce products.");
        provider.add("config.jellies.option.produceTicks.comment.1", "Default: 23500");
        provider.add("config.jellies.option.produceFamiliarity", "Produce Familiarity");
        provider.add("config.jellies.option.produceFamiliarity.comment.0", "Minimum familiarity needed for jellie to produce products.");
        provider.add("config.jellies.option.produceFamiliarity.comment.1", "Default: 0.15");
        provider.add("config.jellies.option.childCount", "Child Count");
        provider.add("config.jellies.option.childCount.comment.0", "How many children a jellie can have at once.");
        provider.add("config.jellies.option.childCount.comment.1", "Default: 1");
        provider.add("config.jellies.option.gestationDays", "Gestation Days");
        provider.add("config.jellies.option.gestationDays.comment.0", "How long it takes for a jellie to be born.");
        provider.add("config.jellies.option.gestationDays.comment.1", "Default: 32");

        provider.add("jellies.creative_tab.jellies", "Jellies");
    }
}
