package com.hjc.demo.bus.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
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


    @Override
    public String getBusCurrentInfo(String busNo, String lineType) throws IOException {
        StringBuilder url = new StringBuilder();
        StringBuilder resultMsg_ = new StringBuilder();
        StringBuilder resultMsg_temp = new StringBuilder();
        url.append("http://m.basbus.cn/ssgj/m_search?").append("id=").append(busNo).append("&linetype=").append(lineType);
        logger.info("url:"+url.toString());
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url.toString());
        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String result = null;
        if (entity != null) {
            result = EntityUtils.toString(entity, "utf-8");
            EntityUtils.consume(entity);
            response.close();
        }
        Document doc = Jsoup.parse(result);
        Elements allLine = doc.select("div.buslineinfo");
        String allBusLine = allLine != null && allLine.size() > 0 ? allLine.get(0).text() : "";
        List<String> list = Lists.newArrayList(Splitter.on(" ").split(allBusLine));
        System.out.println("list:"+list);
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
            return "查询不到该路线";
        }
        Element endStand = doc.selectFirst("div.right");
        logger.info(startStand.text() + "--->" + endStand.text());
        resultMsg_.append(startStand.text()).append("---->").append(endStand.text()).append("\r\n").append(resultMsg_temp).append("=======").append(allBusLine);
        return resultMsg_.toString();
    }

    public static void main(String[] args) throws IOException {
        System.out.println(new BusCurrentInfoServiceImpl().getBusCurrentInfo("141","1"));
    }
}
