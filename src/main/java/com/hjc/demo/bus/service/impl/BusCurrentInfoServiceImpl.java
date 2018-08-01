package com.hjc.demo.bus.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.hjc.demo.bus.entity.BusEntity;
import com.hjc.demo.bus.service.BusCurrentInfoService;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * @author : Administrator
 * @date : 2018/7/27 0027 17:03
 * @description : 公交实时查询service实现类
 */
@Service
public class BusCurrentInfoServiceImpl implements BusCurrentInfoService {

    private static final Logger logger = LoggerFactory.getLogger(BusCurrentInfoServiceImpl.class);

    private static final String URL = "http://m.basbus.cn/ssgj/m_search?";

    @Override
    public BusEntity getBusCurrentInfo(String busNo, String lineType) throws IOException {

        BusEntity resultBus = new BusEntity();

        StringBuilder url = new StringBuilder();
        StringBuilder resultMsg_ = new StringBuilder();
        StringBuilder resultMsg_temp = new StringBuilder();
        url.append(URL).append("id=").append(busNo).append("&linetype=").append(lineType);

        logger.info("url:" + url.toString());

        Document doc = getBusPageInof(url.toString());

        Element startStand = doc.selectFirst("div.left");
        if (startStand == null) {
            logger.info("查询不到该路线");
            return null;//"查询不到该路线";
        }
        //获取所有站点
        getAllBusStand(doc,resultBus);
        // 获取到达站点
        getCurrentBusStand(doc, resultBus);

        Element endStand = doc.selectFirst("div.right");

        logger.info(startStand.text() + "--->" + endStand.text());

        resultBus.setStartStand(startStand.text());
        resultBus.setEndStand(endStand.text());

        return resultBus;
    }

    /**
     * 发送请求到bus公交获取实时公交信息
     */
    private Document getBusPageInof(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String result = null;
        if (entity != null) {
            result = EntityUtils.toString(entity, "utf-8");
            EntityUtils.consume(entity);
            response.close();
        }
        return Jsoup.parse(result);
    }

    /**
     * 获取所有的该公交路线站点信息
     */
    private void getAllBusStand(Document doc, BusEntity resultBus) {
        Elements allLine = doc.select("div.buslineinfo");
        String allBusLine = allLine != null && allLine.size() > 0 ? allLine.get(0).text() : "";
        List<String> list = Lists.newArrayList(Splitter.on(" ").split(allBusLine));
        System.out.println("list:"+list);
        String resltMsg = "";
        List nodeList = new ArrayList();
        for (int i = 0; i < list.size() - 1;) {
            BusEntity.Node busEntityNode = resultBus.new Node();
            if (list.get(i).contains("辆")){
                i++;
                resltMsg = list.get(i);
                continue;
            }
            busEntityNode.setNodeName(list.get(i) + resltMsg);
            busEntityNode.setId(list.get(i+1));
            resltMsg = "";
            i+=2;
            nodeList.add(busEntityNode);
        }
        resultBus.setBusStands(nodeList);
    }

    /**
     * 获取该公交路线运行当前的到达站点信息
     */
    private void getCurrentBusStand(Document doc,BusEntity resultBus) {
        String resultMsg_temp;
        Elements elements = doc.select("img");
        for (Element el : elements) {
            Element parent = el.parent().parent();
            logger.info(parent.text());
            //到达该站点
            logger.info(el.attr("class"));
            if (!"inn".equals(el.attr("class"))){
                resultMsg_temp= "刚过该站点";
            }else{
                //过了该站点
                resultMsg_temp = "到达该站点";
            }
            for (BusEntity.Node nodes : resultBus.getBusStands()) {
                if (parent.text().contains(nodes.getNodeName())){
                    nodes.setMsg(resultMsg_temp);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
//        System.out.println(new BusCurrentInfoServiceImpl().getBusCurrentInfo("141","1"));
        String result = "红牌楼站, 1, 广福桥街口站,2, 高升桥东路站, 3, 肖家河站, 4, 高升桥东路北站, 5, 一环路南四段站, 6, 地铁衣冠庙站, 7, 九如村站, 8, 一环路南二段站, 9, 地铁磨子桥站, 10, 地铁新南门站, 11, 红星路四段站, 12, 纱帽街站, 13, 芷泉街站, 14, 2辆, 牛王庙路口站, 15, 水碾河站, 16, 水碾河路站, 17, 双桥子站, 18, 塔子山公园站, 19, 五桂桥公交站（下客）, 20";
        List<String> list = Lists.newArrayList(Splitter.on(",").split(result));
        Map<String, String> map = new LinkedHashMap<>();
        String resultMsg = "";
        for (int i = 0; i < list.size() -1;) {
            if (list.get(i).contains("辆")){
                resultMsg = list.get(i);
                i++;
                continue;
            }
            map.put(list.get(i+1),list.get(i)+resultMsg);
            resultMsg = "";
            i+=2;
        }
        System.out.println(map);
    }
}
