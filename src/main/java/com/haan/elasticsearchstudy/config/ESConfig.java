package com.haan.elasticsearchstudy.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class ESConfig {

    @Value("${spring.data.elasticsearch.cluster-name}")
    private String clustername;
    @Value("${spring.data.elasticsearch.cluster-nodes}")
    private String[] clusternodes;

    private static final String HOST_PORT_SPLIT_SYMBOL = ":";
    private static final Logger LOGGER = LoggerFactory.getLogger(ESConfig.class);

    @Bean
    public Settings MySettings(){
        Settings settings = Settings.builder()
                .put("cluster.name",clustername)
                .build();
        return settings;
    }

    public TransportClient MyTransportClient(Settings settings) throws UnknownHostException {
        TransportClient client = new PreBuiltTransportClient(settings);
        for (String clusternode:clusternodes) {
            String[] split = clusternode.split(HOST_PORT_SPLIT_SYMBOL);
            String host = split[0];
            String port = split[1];
            LOGGER.info(host+":"+port);
            TransportAddress transportAddress = new TransportAddress(InetAddress.getByName(host), Integer.parseInt(port));
            client.addTransportAddress(transportAddress);
        }
        return client;
    }
}
