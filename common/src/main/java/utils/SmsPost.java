/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package utils;




import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Pattern;
/**
 * 短信验证码辅助类
 *
 * @author NINGPAI-zhangqiang
 * @since 2014年6月12日 下午3:44:51
 * @version 0.0.1
 */
public final class SmsPost {
    public static final String MSG_PASSWORD="恭喜您成功注册会员，您的初始密码是@1，为了确保账户安全，请及时更改密码。" +
            "详情请关注官方微信“菜道365”";
    public static final String MSG_SENDPACKAGE="您好，您的商品已送达小区云柜{0}柜，取货密码为{1}，您也可以直接刷会员卡取货，为保证商品新鲜，请在24小时内取货。";
    private SmsPost() {

    }

//    /**
//     * 短信发送
//     *
//     * @param utils.Sms
//     *            接口帮助类
//     * @return
//     * @throws IOException
//    public static boolean sendPost(utils.Sms utils.Sms) throws IOException {
//        // 调用zyer.cn的短信接口
//        if (utils.Sms.getHttpUrl().indexOf("zyer") != -1) {
//            return zyerSend(utils.Sms);
//        }
//        else if(utils.Sms.getHttpUrl().indexOf("ipyy")!=-1) //创世华信
//        {
//        	return huaxSend(utils.Sms);
//        }
//        else {
//            // 使用美圣
//            return meriSSend(utils.Sms);
//        }
//    }*/
//
//    /**
//     * zyer send
//     *
//     * @param utils.Sms
//     * @return
//     * @throws IOException
//     */
//    private static boolean meriSSend(utils.Sms utils.Sms) throws IOException {
//        /**
//         * 首先要和URL下的URLConnection对话。 URLConnection可以很容易的从URL得到。比如： // Using java.net.URL and //java.net.URLConnection http://sdk.zyer.cn/SmsService/SmsService.asmx/SendEx
//         */
//        URL url;
//        url = new URL(utils.Sms.getHttpUrl());
//        URLConnection connection = url.openConnection();
//        /**
//         * 然后把连接设为输出模式。URLConnection通常作为输入来使用，比如下载一个Web页。 通过把URLConnection设为输出，你可以把数据向你个Web页传送。下面是如何做：
//         */
//        connection.setDoOutput(true);
//        /**
//         * 最后，为了得到OutputStream，简单起见，把它约束在Writer并且放入POST信息中，例如： ...
//         */
//        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
//        String param = "";
//        param += "username=" + utils.Sms.getLoginName();
//        param += "&scode=" + utils.Sms.getPassword();
//        param += "&content=@1@=" + utils.Sms.getMsgContext();
//        param += "&tempid="+utils.Sms.getSmsTempid();//liuyou201506061705modify 短信模版id，需要根据配置进行修改，建议模版id可配置
//        param += "&mobile=" + utils.Sms.getSendSim();
//        // post的关键所在！
//        out.write(param);
//        // remember to clean up
//        out.flush();
//        out.close();
//        /**
//         * 这样就可以发送一个看起来象这样的POST： POST /jobsearch/jobsearch.cgi HTTP 1.0 ACCEPT: text/plain Content-type: application/x-www-form-urlencoded Content-length: 99 username=bob password=someword
//         */
//        // 一旦发送成功，用以下方法就可以得到服务器的回应：
//        String sCurrentLine;
//        StringBuilder sTotalString = new StringBuilder();
//        sCurrentLine = "";
//        InputStream l_urlStream;
//        l_urlStream = connection.getInputStream();
//        // 传说中的三层包装阿！
//        BufferedReader l_reader = new BufferedReader(new InputStreamReader(l_urlStream));
//        while ((sCurrentLine = l_reader.readLine()) != null) {
//            sTotalString.append(sCurrentLine);
//            // sTotalString.append("\r\n");
//        }
//
//        if (Pattern.compile("^ 0#[0-9]*#[0-9]*$").matcher(sTotalString.toString()).find()) {
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * meisheng send
//     *
//     * @param utils.Sms
//     * @return
//     * @throws IOException
//     */
//    public static boolean zyerSend(utils.Sms utils.Sms) throws IOException {
//        /**
//         * 首先要和URL下的URLConnection对话。 URLConnection可以很容易的从URL得到。比如： // Using java.net.URL and //java.net.URLConnection http://sdk.zyer.cn/SmsService/SmsService.asmx/SendEx
//         */
//        URL url = new URL(utils.Sms.getHttpUrl());
//        URLConnection connection = url.openConnection();
//        /**
//         * 然后把连接设为输出模式。URLConnection通常作为输入来使用，比如下载一个Web页。 通过把URLConnection设为输出，你可以把数据向你个Web页传送。下面是如何做：
//         */
//        connection.setDoOutput(true);
//        /**
//         * 最后，为了得到OutputStream，简单起见，把它约束在Writer并且放入POST信息中，例如： ...
//         */
//        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "GB2312");
//        String param = "";
//        param += "LoginName=" + utils.Sms.getLoginName();
//        param += "&Password=" + utils.Sms.getPassword();
//        param += "&SendSim=" + utils.Sms.getSendSim();
//        param += "&SmsKind=" + utils.Sms.getSmsKind();
//        param += "&ExpSmsId=";
//        param += "&MsgContext=" + "您好,商城" + "提醒您,您本次的验证码是" + utils.Sms.getMsgContext() + "。";
//        // post的关键所在！
//        out.write(param);
//        // remember to clean up
//        out.flush();
//        out.close();
//        /**
//         * 这样就可以发送一个看起来象这样的POST： POST /jobsearch/jobsearch.cgi HTTP 1.0 ACCEPT: text/plain Content-type: application/x-www-form-urlencoded Content-length: 99 username=bob password=someword
//         */
//        // 一旦发送成功，用以下方法就可以得到服务器的回应：
//        String sCurrentLine;
//        StringBuilder sTotalString = new StringBuilder();
//        sCurrentLine = "";
//        InputStream l_urlStream;
//        l_urlStream = connection.getInputStream();
//        // 传说中的三层包装阿！
//        BufferedReader l_reader = new BufferedReader(new InputStreamReader(l_urlStream));
//        while ((sCurrentLine = l_reader.readLine()) != null) {
//            sTotalString.append(sCurrentLine);
//            sTotalString.append("\r\n");
//        }
//        String[] s = sTotalString.toString().split("<Code>0</Code>");
//        if (s.length > 1) {
//            return true;
//        }
//        return false;
//    }

    public static boolean huaxSend(String mobile, String conText) throws IOException {
        Sms sms=new Sms();
        sms.setHttpUrl("http://sh2.ipyy.com/sms.aspx");
        sms.setLoginName("jksc343");
        sms.setPassword("Shcdxxkjyxgs1234");
        sms.setSmsKind("1");

        sms.setSendSim(mobile);
        sms.setMsgContext(conText);
        URL url;
        url = new URL(sms.getHttpUrl());
        URLConnection connection = url.openConnection();

        connection.setDoOutput(true);

        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
        String param = "";
        param += "account=" + sms.getLoginName();
        param += "&password=" + sms.getPassword();
        param += "&userid=&action=send";
        param += "&content=" + sms.getMsgContext() +"【上海菜道】".toString();
        param += "&sendTime=&extno=";
        param += "&mobile=" + sms.getSendSim();
        out.write(param);
        out.flush();
        out.close();

        String sCurrentLine;
        StringBuilder sTotalString = new StringBuilder();
        sCurrentLine = "";
        InputStream l_urlStream;
        l_urlStream = connection.getInputStream();
        BufferedReader l_reader = new BufferedReader(new InputStreamReader(l_urlStream,"UTF-8"));
        while ((sCurrentLine = l_reader.readLine()) != null) {
            sTotalString.append(sCurrentLine);
        }
        System.out.println(sTotalString.toString());
        if (Pattern.compile("<returnstatus>Success</returnstatus>").matcher(sTotalString.toString()).find()) {
            return true;
        }
        return false;
    }
}
