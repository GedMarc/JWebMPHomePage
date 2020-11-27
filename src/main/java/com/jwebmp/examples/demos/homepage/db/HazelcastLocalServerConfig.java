package com.jwebmp.examples.demos.homepage.db;


import com.guicedee.guicedhazelcast.HazelcastProperties;
import com.guicedee.guicedhazelcast.services.IGuicedHazelcastServerConfig;
import com.hazelcast.config.Config;
import com.hazelcast.config.MetricsConfig;
import com.hazelcast.config.MetricsJmxConfig;
import com.hazelcast.config.MetricsManagementCenterConfig;

public class HazelcastLocalServerConfig
        implements IGuicedHazelcastServerConfig<HazelcastLocalServerConfig> {
    @Override
    public Config buildConfig(Config config) {

        config.getNetworkConfig()
                .setPublicAddress(HazelcastProperties.getAddress());
        config.getNetworkConfig()
                .setPortAutoIncrement(false);

        if (HazelcastProperties.isStartLocal()) {
            config.getNetworkConfig()
                    .getJoin()
                    .getMulticastConfig()
                    .setEnabled(false);
        }

        config.getManagementCenterConfig()
                .setScriptingEnabled(false);
        HazelcastProperties.setGroupName("jwebmp_homepage");
        config.setClusterName(HazelcastProperties.getGroupName());
        config.setInstanceName(HazelcastProperties.getInstanceName());

        config.setMetricsConfig(new MetricsConfig());
        config.getMetricsConfig()
                .setEnabled(true)
                .setJmxConfig(new MetricsJmxConfig().setEnabled(true))
                .setManagementCenterConfig(new MetricsManagementCenterConfig().setEnabled(true)
                        .setRetentionSeconds(5))
                .setCollectionFrequencySeconds(5);

        return config;
    }
}
