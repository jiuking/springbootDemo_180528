package com.hjc.demo.bus.service;

import java.io.IOException;

/**
 * @author : Hjc
 * @date : 2018/7/27 0027 17:00
 * @description : 公交实时查询service
 */
public interface BusCurrentInfoService {
    /**
     * 获取公交实时信息
     * @param busNo
     * @param lineType
     * @return
     */
    String getBusCurrentInfo(String busNo, String lineType) throws IOException;
}
