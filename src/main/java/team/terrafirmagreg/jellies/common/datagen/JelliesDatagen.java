package team.terrafirmagreg.jellies.common.datagen;

import com.tterrag.registrate.providers.ProviderType;

import team.terrafirmagreg.jellies.Jellies;

public class JelliesDatagen {
    public static void init() {
        Jellies.REGISTRATE.addDataGenerator(ProviderType.LANG, JelliesLangHandler::init);
    }
}
