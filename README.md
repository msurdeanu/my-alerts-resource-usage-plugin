# MyAlerts Resource Usage Plugin

This repository is an example of plugin for [MyAlerts App](https://github.com/msurdeanu/my-alerts-app) that exposes some stats about resources used by the application.
Shows amount of memory free and used inside your JVM.

## List of extension points

### `StatisticsProvider`

```java
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
```

### `TranslationsProvider`

```java
@Extension
public static class ResourceUsageTranslationsProvider implements TranslationsProvider {

    @Override
    public Map<SupportedLanguage, ResourceBundle> getResourceBundles() {
        return Map.of(SupportedLanguage.ENGLISH, getBundle("translation", ENGLISH));
    }

}
```
