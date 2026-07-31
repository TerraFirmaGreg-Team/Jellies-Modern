package team.terrafirmagreg.jellies.config;

import org.jetbrains.annotations.ApiStatus;

import dev.toma.configuration.Configuration;
import dev.toma.configuration.config.Config;
import dev.toma.configuration.config.Configurable;
import dev.toma.configuration.config.UpdateRestrictions;
import dev.toma.configuration.config.format.ConfigFormats;

import team.terrafirmagreg.jellies.Jellies;

@Config(id = Jellies.MOD_ID)
public class JelliesConfig {
    public static JelliesConfig INSTANCE;
    private static final Object LOCK = new Object();

    @ApiStatus.Internal
    public static dev.toma.configuration.config.ConfigHolder<JelliesConfig> INTERNAL_INSTANCE;

    public static void init() {
        synchronized (LOCK) {
            if (INSTANCE == null || INTERNAL_INSTANCE == null) {
                INTERNAL_INSTANCE = Configuration.registerConfig(JelliesConfig.class, ConfigFormats.YAML);
                INSTANCE = INTERNAL_INSTANCE.getConfigInstance();
            }
        }
    }

    public static JelliesConfig getInstance() {
        init();
        return INSTANCE;
    }

    @Configurable
    @Configurable.Comment({ "Config options for jellie stats." })
    public EntityConfigs entities = new EntityConfigs();

    public static class EntityConfigs {
        @Configurable
        public JellieEntityConfig biotiteJellie = new JellieEntityConfig();
        @Configurable
        public JellieEntityConfig certusJellie = new JellieEntityConfig();
        @Configurable
        public JellieEntityConfig glowberryJellie = new JellieEntityConfig();
        @Configurable
        public JellieEntityConfig herbalJellie = new JellieEntityConfig();
        @Configurable
        public JellieEntityConfig iceJellie = new JellieEntityConfig();
        @Configurable
        public JellieEntityConfig latexJellie = new JellieEntityConfig();
        @Configurable
        public JellieEntityConfig lavaJellie = new JellieEntityConfig();
        @Configurable
        public JellieEntityConfig penteticJellie = new JellieEntityConfig();
        @Configurable
        public JellieEntityConfig phosphorumJellie = new JellieEntityConfig();
        @Configurable
        public JellieEntityConfig plantJellie = new JellieEntityConfig();
        @Configurable
        public JellieEntityConfig rockJellie = new JellieEntityConfig();
        @Configurable
        public JellieEntityConfig springJellie = new JellieEntityConfig();
        @Configurable
        public JellieEntityConfig pyritieJellie = new JellieEntityConfig();
        @Configurable
        public JellieEntityConfig redeixJellie = new JellieEntityConfig();

        public static class JellieEntityConfig {
            @Configurable
            @Configurable.Comment({ "", "Default: 1" })
            @Configurable.DecimalRange(min = 0, max = 1)
            @Configurable.UpdateRestriction(UpdateRestrictions.GAME_RESTART)
            public double familiarityCap = 1;

            @Configurable
            @Configurable.Comment({ "", "Default: 16" })
            @Configurable.Range(min = 1)
            @Configurable.UpdateRestriction(UpdateRestrictions.GAME_RESTART)
            public int adulthoodDays = 16;

            @Configurable
            @Configurable.Comment({ "", "Default: 120" })
            @Configurable.Range(min = 1)
            @Configurable.UpdateRestriction(UpdateRestrictions.GAME_RESTART)
            public int uses = 120;

            @Configurable
            @Configurable.Comment({ "", "Default: false" })
            @Configurable.UpdateRestriction(UpdateRestrictions.GAME_RESTART)
            public boolean eatsRottenFood = false;

            @Configurable
            @Configurable.Comment({ "", "Default: 23500" })
            @Configurable.Range(min = 20)
            @Configurable.UpdateRestriction(UpdateRestrictions.GAME_RESTART)
            public int produceTicks = 23500;

            @Configurable
            @Configurable.Comment({ "", "Default: 0.15" })
            @Configurable.DecimalRange(min = 0, max = 1)
            @Configurable.UpdateRestriction(UpdateRestrictions.GAME_RESTART)
            public double produceFamiliarity = 0.15;

            @Configurable
            @Configurable.Comment({ "", "Default: 1" })
            @Configurable.Range(min = 0)
            @Configurable.UpdateRestriction(UpdateRestrictions.GAME_RESTART)
            public int childCount = 1;

            @Configurable
            @Configurable.Comment({ "", "Default: 32" })
            @Configurable.Range(min = 1)
            @Configurable.UpdateRestriction(UpdateRestrictions.GAME_RESTART)
            public long gestationDays = 32;
        }
    }
}
