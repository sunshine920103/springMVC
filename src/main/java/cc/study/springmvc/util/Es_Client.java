package cc.study.springmvc.util;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 * Created by cc on 2016/4/4.
 */

@Component
public class Es_Client  {

    @Value("${es.cluster_name}")
    private String CLUSTER_NAME; //实例名称

    @Value("${es.ip}")
    private String IP;

    @Value("${es.port}")
    private int PORT;  //端口

    //1.设置集群名称：默认是elasticsearch，并设置client.transport.sniff为true，使客户端嗅探整个集群状态，把集群中的其他机器IP加入到客户端�?
    //对ES2.0有效
    private static Settings settings;

    //创建私有对象
    private  TransportClient client;

    public Es_Client() {
    }

    public Es_Client(String cluster_name,String ip,int port)
    {
        this.CLUSTER_NAME=cluster_name;
        this.IP=ip;
        this.PORT=port;
    }

    public TransportClient getTransportClient(){
        try {
            if(client==null) {

                settings= Settings
                        .settingsBuilder()
                        .put("cluster.name", CLUSTER_NAME)
                        .put("client.transport.sniff", true)
                        .build();

                client = TransportClient.builder().settings(settings).build()
                        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(IP), PORT));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return client;
    }



    public static void main(String[] args)  throws Exception{

        SearchResponse response = new Es_Client("es.cobub.com","razor_es",9300).getTransportClient().prepareSearch("razor_web_access")
                .setTypes("access")

                .addSort("date_time", SortOrder.DESC)
                .addAggregation(
                        AggregationBuilders.terms("agg").field("user_id").size(100)
                )

                .setSize(20).setFrom(0)
                .execute()
                .actionGet();


        System.out.println(response.toString());

    }

}
