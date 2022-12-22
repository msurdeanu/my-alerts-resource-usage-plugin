package org.myalerts.plugin.resourceusage;

import org.myalerts.domain.StatisticsGroup;
import org.myalerts.domain.StatisticsItem;
import org.myalerts.domain.SupportedLanguage;
import org.myalerts.provider.StatisticsProvider;
import org.myalerts.provider.TranslationsProvider;
import org.pf4j.Extension;
import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static java.lang.String.format;
import static java.util.Locale.ENGLISH;
import static java.util.ResourceBundle.getBundle;

/**
 * @author Mihai Surdeanu
 * @since 1.0.0
 */
public class ResourceUsagePlugin extends Plugin {

    private static final float ONE_MB = 1e6f;

    public ResourceUsagePlugin(final PluginWrapper wrapper) {
        super(wrapper);
    }

    @Extension(ordinal = 100)
    public static class ResourceUsageStatisticsProvider implements StatisticsProvider {

        @Override
        public StatisticsGroup getStatisticsGroup() {
            final var runtime = Runtime.getRuntime();
            return StatisticsGroup.builder()
                .root(StatisticsItem.builder()
                    .name("plugin.resource-usage.statistics.resource-usage.group")
                    .icon("vaadin:folder-o")
                    .build())
                .leafs(List.of(
                    StatisticsItem.builder()
                        .name("plugin.resource-usage.statistics.resource-usage.group.free-memory.name")
                        .icon("vaadin:file-text-o")
                        .value(format("%.4f MB", runtime.freeMemory() / ONE_MB))
                        .description("plugin.resource-usage.statistics.resource-usage.group.free-memory.description")
                        .build(),
                    StatisticsItem.builder()
                        .name("plugin.resource-usage.statistics.resource-usage.group.used-memory.name")
                        .icon("vaadin:file-text-o")
                        .value(format("%.4f MB", (runtime.totalMemory() - runtime.freeMemory()) / ONE_MB))
                        .description("plugin.resource-usage.statistics.resource-usage.group.used-memory.description")
                        .build()
                ))
                .build();
        }
    }

    @Extension
    public static class ResourceUsageTranslationsProvider implements TranslationsProvider {

        @Override
        public Map<SupportedLanguage, ResourceBundle> getResourceBundles() {
            return Map.of(SupportedLanguage.ENGLISH, getBundle("translation", ENGLISH));
        }

    }

}
