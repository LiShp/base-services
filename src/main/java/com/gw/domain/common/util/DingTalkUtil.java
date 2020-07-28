package com.gw.domain.common.util;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.gw.cloud.common.base.util.DateUtil;
import com.gw.domain.common.constant.DingTaklRobot;
import com.gw.gwlog.GWMLogger;
import com.gw.gwlog.GWMLoggerFactory;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 自定义机器人webhook 工具类
 * 官方文档： https://ding-doc.dingtalk.com/doc#/serverapi2/qf2nxq
 * @author wjn
 */
@Component
public class DingTalkUtil {
    protected static GWMLogger logger = GWMLoggerFactory.getSimpleLogger(DingTalkUtil.class);
    private static String dingServerUrl ="https://oapi.dingtalk.com/robot/send?access_token=";

    @Value("${spring.profiles.active}")
    private String source;

    /**
     * 获取签名
     * @param timestamp 时间戳
     * @param secret    密钥
     * @return
     * @throws Exception
     */
    private static String getSign(Long timestamp , String secret) throws Exception {
        String stringToSign = timestamp + "\n" + secret;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
        String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)),"UTF-8");
        return sign;
    }

    /**
     *将手机号字符串转换成集合
     * @param mobiles 手机号字符串多个用英文逗号','分割
     * @return
     */
    private static List<String> getMobiles(String mobiles){
        List<String> mobileList = new ArrayList<>();
        if (mobiles!=null){
            String[] split = mobiles.split(",");
            mobileList = Arrays.asList(split);
        }

        return mobileList;
    }
    private static String getMobilesText(String mobiles){
        String mobileStr ="";
        if (mobiles!=null){
            String[] split = mobiles.split(",");
            List<String> mobileList = Arrays.asList(split);
            for (String mobile:mobileList){
                mobile = "@"+mobile;
                mobileStr =mobileStr+mobile;
            }

        }
        return mobileStr;
    }

    /**
     *发送text类型消息
     * @param token
     * @param content
     * @param mobiles
     * @param isAtAll
     * @throws Exception
     */
    public static void sendTextTalk(String token, String content, String mobiles,Boolean isAtAll,String secret) throws Exception {
        String serverUrl;
        Long timestamp = System.currentTimeMillis();
        if (!StringUtils.isEmpty(secret)){
            String sign = getSign(timestamp, secret);
            serverUrl = dingServerUrl + token + "&timestamp="+timestamp+"&sign="+sign;
        }else {
            serverUrl = dingServerUrl + token;
        }
        DingTalkClient client = new DefaultDingTalkClient(serverUrl);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("text");
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent(content);
        request.setText(text);
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setAtMobiles(DingTalkUtil.getMobiles(mobiles));
        at.setIsAtAll(isAtAll);
        request.setAt(at);
        OapiRobotSendResponse response = client.execute(request);
        logger.info(response.getBody());
    }

    /**
     * 发送link类型的消息
     * @param token     token
     * @param messageUrl 消息地址
     * @param imageUrl   图片地址
     * @param title      标题
     * @param text       文本
     * @param secret     加签
     * @throws Exception
     */
    public static void sendLinkTalk(String token, String messageUrl,String imageUrl,String title,String text,String secret) throws Exception {
        String serverUrl;
        Long timestamp = System.currentTimeMillis();
        if (!StringUtils.isEmpty(secret)){
            String sign = getSign(timestamp, secret);
            serverUrl = dingServerUrl + token + "&timestamp="+timestamp+"&sign="+sign;
        }else {
            serverUrl = dingServerUrl + token;
        }
        DingTalkClient client = new DefaultDingTalkClient(serverUrl);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("link");
        OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
        link.setMessageUrl(messageUrl);
        link.setPicUrl(imageUrl);
        link.setTitle(title);
        link.setText(text);
        request.setLink(link);
        OapiRobotSendResponse response = client.execute(request);
        logger.info(response.getBody());
    }

    /**
     * 发送markdown类型消息
     * @param token    token
     * @param title     标题
     * @param text      文本
     * @param mobiles   手机号字符串
     * @param isAtAll   是否@所有人
     * @param secret    加签密钥
     * @throws Exception
     */
    public static void sendMarkdownTalk(String token,String title,String text,String mobiles,Boolean isAtAll,String secret) throws Exception {
        String serverUrl;
        Long timestamp = System.currentTimeMillis();
        if (!StringUtils.isEmpty(secret)){
            String sign = getSign(timestamp, secret);
            serverUrl = dingServerUrl + token + "&timestamp="+timestamp+"&sign="+sign;
        }else {
            serverUrl = dingServerUrl + token;
        }
        DingTalkClient client = new DefaultDingTalkClient(serverUrl);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("markdown");
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle(title);
        markdown.setText(text+getMobilesText(mobiles));
        request.setMarkdown(markdown);
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setAtMobiles(getMobiles(mobiles));
        at.setIsAtAll(isAtAll);
        request.setAt(at);
        OapiRobotSendResponse response = client.execute(request);
        logger.info(response.getBody());
    }

    /**
     *
     * @param desc 信息详情
     */
    public void sendMarkdownTalk(String desc) throws Exception {
        String time = DateUtil.getFormatCurrentDate(DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME);
        DingTalkUtil.sendMarkdownTalk(DingTaklRobot.MES_IN_GROUP_TOKEN,
                                            DingTaklRobot.MES_IN_GROUP_METHOD_PLATFORM_NAME,
                                        DingTaklRobot.MES_IN_GROUP_METHOD_PLATFORM_NAME+"\n"+
                                            DingTaklRobot.MES_IN_GROUP_METHOD_SOURCE_FORMAT + source +"\n"+
                                            //DingTaklRobot.MES_IN_GROUP_METHOD_NAME_FORMAT+name+"\n"+
                                            DingTaklRobot.MES_IN_GROUP_METHOD_TIME_FORMAT+time+"\n"+
                                            DingTaklRobot.MES_IN_GROUP_METHOD_DESC_FORMAT+desc,
                                            null,false,null);
    }

}
