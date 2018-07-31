package com.hjc.demo.bus;

import java.util.List;

/**
 * @author : Administrator
 * @date : 2018/7/31 0031 16:55
 * @description : 页面展示信息实体类
 */
public class entity {

    /**
     * 始发站
     */
    private String startStand;

    /**
     * 终点站
     */
    private String endStand;

    /**
     * 具体站点
     */
    private List<Node> busStands;

    public class Node {
        /**
         * 具体站点编号
         */
        private String id;
        /**
         * 站点名称
         */
        private String nodeName;
        /**
         * 信息描述，已过还是刚到
         */
        private String msg;
    }
}
