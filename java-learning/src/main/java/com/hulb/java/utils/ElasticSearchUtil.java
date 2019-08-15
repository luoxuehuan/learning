package com.hulb.java.utils;

import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.stats.IndicesStatsRequest;
import org.elasticsearch.action.admin.indices.stats.IndicesStatsResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.util.Set;

/**
 * @author hulb
 * @date 2018/11/28 下午9:36
 */
public class ElasticSearchUtil {

    /**
     * 创建es client 建立连接 查询index 列表
     */
    public static void listIndex() throws Exception{


        Settings esSettings = Settings.builder()
                //设置ES实例的名称
                .put("cluster.name", "elasticsearch")
                //自动嗅探整个集群的状态，把集群中其他ES节点的ip添加到本地的客户端列表中
                .put("client.transport.sniff", true)
                .build();

        TransportClient client = new PreBuiltTransportClient(esSettings);
        client.addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9200));

        client.admin().indices().prepareCreate("java_new_index").get();

        client.admin().indices().prepareCreate("").get();
    }

    public static Set getAllIndices() throws Exception {
        Settings esSettings = Settings.builder()
                .put("cluster.name", "elasticsearch")
                //设置ES实例的名称
                //自动嗅探整个集群的状态，把集群中其他ES节点的ip添加到本地的客户端列表中
                .put("client.transport.sniff", true)
                .build();

        TransportClient client = new PreBuiltTransportClient(esSettings);
        client.addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9200));

        ActionFuture<IndicesStatsResponse> isr = client.admin().indices().stats(new IndicesStatsRequest().all());
//        IndicesAdminClient indicesAdminClient = client.admin().indices();
//        Map<String, IndexStats> indexStatsMap = isr.actionGet().getIndices();
        Set<String> set = isr.actionGet().getIndices().keySet();
        return set;
    }

    public static void main(String[] args) throws Exception{
        ElasticSearchUtil.getAllIndices();
    }




}
