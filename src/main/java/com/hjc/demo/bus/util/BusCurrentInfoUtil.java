package com.hjc.demo.bus.util;

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

import java.io.IOException;

/**
 * @author : Administrator
 * @date : 2018/7/27 0027 15:45
 * @description : 公交车实时信息工具类
 */
public class BusCurrentInfoUtil {

    public static String getBusCurrentInfo(String busNo,String lineType) throws IOException {
        StringBuilder url = new StringBuilder();
        StringBuilder resultMsg_ = new StringBuilder();
        StringBuilder resultMsg_temp = new StringBuilder();
        url.append("http://m.basbus.cn/ssgj/m_search?").append("id=").append(busNo).append("&linetype=").append(lineType);
        System.out.println("url:"+url.toString());
        CloseableHttpClient httpCilent = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url.toString());
        CloseableHttpResponse response = httpCilent.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String result = null;
        if (entity != null) {
            result = EntityUtils.toString(entity, "utf-8");
            EntityUtils.consume(entity);
            response.close();
        }
        Document doc = Jsoup.parse(result);
        Elements elements = doc.select("img");
        for (Element el : elements) {
            Element parent = el.parent().parent();
            System.out.println(parent.text());
            resultMsg_temp.append("\r\n").append(parent.text());
        }
        Element startStand = doc.selectFirst("div.left");
        if (startStand == null) {
            System.out.println("查询不到该路线");
            return "查询不到该路线";
        }
        Element endStand = doc.selectFirst("div.right");
        System.out.println(startStand.text() + "--->" + endStand.text());
        resultMsg_.append(startStand.text()).append("---->").append(endStand.text()).append(resultMsg_temp);
        return resultMsg_.toString();
    }

    public static void main(String[] args) throws IOException {
        System.out.println(getBusCurrentInfo("710","2"));
    }
}
