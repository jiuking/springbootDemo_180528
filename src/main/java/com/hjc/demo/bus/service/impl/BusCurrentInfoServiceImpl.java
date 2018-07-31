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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    public List<BusEntity> getBusCurrentInfo(String busNo, String lineType) throws IOException {

        List<BusEntity> resultBus = new ArrayList<>();

        StringBuilder url = new StringBuilder();
        StringBuilder resultMsg_ = new StringBuilder();
        StringBuilder resultMsg_temp = new StringBuilder();
        url.append("http://m.basbus.cn/ssgj/m_search?").append("id=").append(busNo).append("&linetype=").append(lineType);

        logger.info("url:" + url.toString());

        Document doc = getBusPageInof(url.toString());

        getAllBusStand(doc,resultBus);

        Elements elements = doc.select("img");
        for (Element el : elements) {
            Element parent = el.parent().parent();
            logger.info(parent.text());
            //到达该站点
            logger.info(el.attr("class"));
            if (!"inn".equals(el.attr("class"))){
                resultMsg_temp.append("刚过该站点---");
            }else{
                //过了该站点
                resultMsg_temp.append("到达该站点---");
            }
            resultMsg_temp.append(parent.text()).append(" ");
        }
        System.out.println(resultMsg_temp.toString());
        Element startStand = doc.selectFirst("div.left");
        if (startStand == null) {
            logger.info("查询不到该路线");
            return null;//"查询不到该路线";
        }
        Element endStand = doc.selectFirst("div.right");
        logger.info(startStand.text() + "--->" + endStand.text());
        resultMsg_.append(startStand.text()).append("---->").append(endStand.text()).append("\r\n").append(resultMsg_temp).append("=======").append("");
        return resultBus;//resultMsg_.toString();
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
    private void getAllBusStand(Document doc, List<BusEntity> resultBus) {
        Elements allLine = doc.select("div.buslineinfo");
        String allBusLine = allLine != null && allLine.size() > 0 ? allLine.get(0).text() : "";
        List<String> list = Lists.newArrayList(Splitter.on(" ").split(allBusLine));
        System.out.println("list:"+list);
        for (int i = 0; i < allBusLine.length() - 2; i++) {
            BusEntity.Node busEntityNode = new BusEntity().new Node();
            busEntityNode.setNodeName(list.get(i));
            busEntityNode.setId(list.get(i+1));
        }
    }

    /**
     * 获取该公交路线运行当前的到达站点信息
     */
    private void getCurrentBusStand() {

    }

    public static void main(String[] args) throws IOException {
        System.out.println(new BusCurrentInfoServiceImpl().getBusCurrentInfo("141","1"));
    }
}
