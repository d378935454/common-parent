package com.xx.server.service;

import com.bean.model.CanbinetManager;
import com.bean.model.PackageRecord;
import com.bean.service.CanbinetService;
import com.xx.server.message.Listen;
import com.xx.server.message.Packet;
import com.xx.server.server.SocketServer;
import com.xx.server.util.DatasUtil;
import com.xx.server.util.SpringContextUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.springframework.stereotype.Service;
import utils.MyLogger;

import java.nio.charset.Charset;

import static io.netty.buffer.Unpooled.wrappedBuffer;

/**
 * Created by gukt on 2016/7/30.
 *
 * @author gukt
 * @version 1.0
 */
@Service
public class CanbinetHandle {
    private MyLogger LOGGER = new MyLogger(CanbinetHandle.class);
    private CanbinetService canbinetService = null;
    private Charset utf8 = Charset.forName("utf-8");

    /**
     * 返回data的btye字节
     *
     * @param packet
     * @return
     */
    private ByteBuf dealDate(Channel channel, Packet packet) {
        LOGGER.info(packet.toString());
        canbinetService = SpringContextUtil.getBean("canbinetService");
        if (null == SocketServer.keyMap.get(packet.getBoxId())) {//第一次连接时，将boxid与channel的key关联 在数据库中
            SocketServer.keyMap.put(packet.getBoxId(), channel);
            try {
                canbinetService.updateVendorSocketKey(channel.toString(), packet.getBoxId());
            } catch (Exception e) {
//                e.printStackTrace();
                LOGGER.error("将boxid:{0}与channel的key:{1}关联", packet.getBoxId(), channel.toString());
            }
        }
        byte[] bytes = packet.getData().getBytes(Charset.forName("utf-8"));
        return wrappedBuffer(bytes);
    }

    /**
     * 出來rfid10转成16进制
     * 列 0001849801 1C39C9 C9391C    C9391C00
     */
    public String Rfid10to16(String Rfid){
        Rfid=Integer.toHexString(Integer.valueOf(Rfid));
        String rs=Rfid.substring(4,6)+Rfid.substring(2,4)+Rfid.substring(0,2);
        while (true){
            if(rs.length()<8){
                rs=rs+"0";
            }else {
                break;
            }

        }
        return rs.toUpperCase();
    }
    /**
     * 本地未匹配到用户会员卡号。则认为员工刷卡，直接发此命令到服务器 data： 员工卡id（string）
     *
     * @param channel
     * @param packet
     * @return 服务器应答员工id刷卡 data： 员工id（string）＋ 此卡是否有效（int）＋此卡的权限掩码（string）
     */
    @Listen("01")
    public Object handler01(Channel channel, Packet packet) {
        ByteBuf byteBuf = dealDate(channel, packet);
        int len = byteBuf.readInt();
        String Rfid = byteBuf.readBytes(len).toString(utf8);
        CanbinetManager canbinetManager = null;
        try {
          String  rfid=canbinetService.Rfid16to10(Rfid);
            canbinetManager = canbinetService.getCabinetManagerByRfidAndCanbinetId(rfid, packet.getBoxId());
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("fail sqle 得到云柜管理员 rfidCode:" + Rfid + ", boxId;" + packet.getBoxId());
        }
        int isabled = 0;
        String role = "4";
        if (null != canbinetManager) {
            isabled = 1;
            role = canbinetManager.getRole().toString();
        }
        packet.setCmd("02");
        String data = DatasUtil.stringTo(Rfid) + DatasUtil.intTo(isabled) + DatasUtil.stringTo(role);
        packet.setData(data);
        // 最简单的方法是通过return返回响应
        return packet;
        // 可以通过channel.writeAndFlush方法直接将响应返回给客户端
        // channel.writeAndFlush(new Packet(packet.getBoxId(), "03", "这是在02之前推送的消息"));
    }


    /**
     * 配货员刷（输入）配货条码后发送此命令到服务器 03
     * data：员工类型（int： 超级管理员 0； 一般管理员1； 配货员2（刷员工卡进入菜单的操作员））
     * 员工id（string，员工类型为2有此字段）
     * 配货条码（string）
     * 拟开门的门号（string，FF表示为爆柜存货）
     *
     * @param channel
     * @param packet
     * @return 配货员配货条码应答 04
     * data：配货条码单号（string）
     * 拟开门的门号（string，FF表示为爆柜存货）
     * 此条码是否有效（int）条码有效有后续字段）
     * 用户密码（string）
     * 用户会员id（string）
     * 是否需要支付密码（int）
     * 会员的支付密码（string）
     */
    @Listen("03")
    public Object handler03(Channel channel, Packet packet)  {
        ByteBuf byteBuf = dealDate(channel, packet);
        int len = 0;
        int role = byteBuf.readInt();
        String managerId = null;
        if (role == 2) {
            len = byteBuf.readInt();
            managerId = byteBuf.readBytes(len).toString(utf8);
        }
        len = byteBuf.readInt();
        String packageCode = byteBuf.readBytes(len).toString(utf8);
        len = byteBuf.readInt();
        String cellCode = byteBuf.readBytes(len).toString(utf8);
        PackageRecord packageRecord = null;
        try {
            packageRecord = canbinetService.insertPackageIntoCabinet(packet.getBoxId(), packageCode, cellCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("配货员刷（输入）配货条码03:员工类型({0}),员工id({1}),拟开门的门号({2}),配货条码({3})", role, managerId, cellCode, packageCode);
        String data = DatasUtil.stringTo(packageCode)
                + DatasUtil.stringTo(cellCode)
                + DatasUtil.intTo(packageRecord == null ? 0 : 1)//判断是否已送
                + (packageRecord == null ?"": DatasUtil.stringTo(packageRecord.getPassword())
                    + DatasUtil.stringTo(Rfid10to16(packageRecord.getRfid()))
                    + DatasUtil.intTo(0)
                    + DatasUtil.stringTo(""));
        packet.setCmd("04");
        packet.setData(data);
        // 最简单的方法是通过return返回响应
        return packet;
    }

    /**
     * 条码开门失败命令 05
     * data：员工类型（int： 超级管理员 0； 一般管理员1； 配货员2（刷员工卡进入菜单的操作员））
     * 员工id（string，员工类型为2有此字段）
     * 拟开门的门号（string）
     * 配货条码（string）
     * 失败原因（string）
     *
     * @param channel
     * @param packet
     * @return 柜门开门失败命令(command05)的应答 20
     * data: 拟开门的门号（string）
     * 配货条码（string）
     */
    @Listen("05")
    public Object handler05(Channel channel, Packet packet) {
        ByteBuf byteBuf = dealDate(channel, packet);
        int len = 0;
        int type = byteBuf.readInt();
        len = byteBuf.readInt();
        String managerId = byteBuf.readBytes(len).toString(utf8);
        len = byteBuf.readInt();
        String cellCode = byteBuf.readBytes(len).toString(utf8);
        len = byteBuf.readInt();
        String packageNo = byteBuf.readBytes(len).toString(utf8);
        len = byteBuf.readInt();
        String result = byteBuf.readBytes(len).toString(utf8);
        packet.setCmd("05");
        LOGGER.info("开门失败:员工类型({0}),员工id({1}),拟开门的门号({2}),配货条码({3}),失败原因({4})", type, managerId, cellCode, packageNo, result);
        String data = DatasUtil.stringTo(cellCode) + DatasUtil.stringTo(packageNo);
        packet.setData(data);
        return packet;

    }

    /**
     * 配货员（管理员）直接开门命令 06
     * data：员工类型（int： 超级管理员 0； 一般管理员1；配货员2（刷员工卡进入菜单的操作员））
     * 员工id（string，员工类型为2有此字段）
     * 柜门号（string）
     * 开门操作（int： 存货：0； 取货：1； 开柜：2）（开门操作为存货/取货都有此字段）
     * 配货单号（string）（取货时的单号为此门之前绑定的单号，如果此门未绑定单号，则传空字符串，即长度为0）
     *
     * @param channel
     * @param packet
     * @return 配货员（管理员）直接开门命令应答 07
     * data：柜门号（string）
     * 开门操作（int： 存货：0； 取货：1； 开柜：2）（开门操作为存货有后续字段）
     * 此条码是否有效（int）
     * 用户密码（string）
     * 用户会员id（string）
     * 是否需要支付密码（int）
     * 会员的支付密码（string）
     */
    @Listen("06")
    public Object handler06(Channel channel, Packet packet) {
//        ByteBuf byteBuf = dealDate(channel, packet);
//        int len = 0;
//        int type = byteBuf.readInt();//员工类型
//        Long managerId = 0L;
//        if (type == 2) {
//            len = byteBuf.readInt();
//            managerId = Long.valueOf(byteBuf.readBytes(len).toString(utf8));
//        }
//        len = byteBuf.readInt();
//        String cellCode = byteBuf.readBytes(len).toString(utf8);
//        int openType = byteBuf.readInt();//（int： 存货：0； 取货：1； 开柜：2）（开门操作为存货/取货都有此字段）
//        len = byteBuf.readInt();
//        String packageNo = byteBuf.readBytes(len).toString(utf8);
//        cellCode=String.valueOf(Integer.valueOf(cellCode));
//        PackageRecord packageRecord = null;
//        try {
//            packageRecord = canbinetService.insertPackageIntoCabinet(packet.getBoxId(), packageNo, cellCode);
//        } catch (Exception e) {
//            e.printStackTrace();
//            LOGGER.error("送货 e packageNo :" + packageNo + "send fail");
//        }
//        String data = DatasUtil.stringTo(cellCode)
//                + DatasUtil.intTo(openType)
//                + (openType != 0 ? "" : (DatasUtil.intTo(packageRecord == null ? 0 : 1)
//                + DatasUtil.stringTo(packageRecord == null ? "" : packageRecord.getPassword())
//                + DatasUtil.stringTo(packageRecord == null ? "" : Rfid10to16(packageRecord.getRfid()))
//                + DatasUtil.intTo(1) + DatasUtil.stringTo(packageRecord == null ? "" : packageRecord.getPassword())));
        packet.setCmd("07");
//        LOGGER.info("直接开门命令06:员工类型({0}),员工id({1}),柜门号({2}),开门操作({3})", type, managerId, cellCode, openType);
////        String data = DatasUtil.stringTo(cellCode)
////                + DatasUtil.intTo(openType)
////                + (openType != 0 ? "" : (DatasUtil.intTo(1)
////                + DatasUtil.stringTo("123")
////                + DatasUtil.stringTo("123")
////                + DatasUtil.intTo(1) + DatasUtil.stringTo("123")));
//        packet.setData(data);
        return packet;

    }

    /**
     * 用户开门取货后命令（一个用户开多个门，可逐条发送） 08
     * data：柜门号（string，FF表示为爆柜存货记录取货）
     * 配货单条码（string）
     * 用户取货方式（int：密码：0；刷卡：1）
     *
     * @param channel
     * @param packet
     * @return 用户开门取货命令应答（多个门逐条应答） 09
     * data：柜门号（string，FF表示为爆柜存货记录取货）
     * 配货单号（string）
     */
    @Listen("08")
    public Object handler08(Channel channel, Packet packet) {
        ByteBuf byteBuf = dealDate(channel, packet);
        int len = 0;
        len = byteBuf.readInt();
        String cellCode = byteBuf.readBytes(len).toString(utf8);
        len = byteBuf.readInt();
        String packageNo = byteBuf.readBytes(len).toString(utf8);
        int type = byteBuf.readInt();

        try {
            canbinetService.updatePackage(packet.getBoxId(), packageNo,type);
            LOGGER.info("用户开门取货后命令08:柜门号({0}),配货单条码({1}),用户取货方式({2})", cellCode, packageNo, type);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String data = DatasUtil.stringTo(cellCode)
                + DatasUtil.stringTo(packageNo);
        packet.setCmd("09");
        packet.setData(data);
        return packet;

    }

    /**
     * @param channel
     * @param packet
     * @return
     */
    @Listen("10")
    public Object handler10(Channel channel, Packet packet) {
        //todo
        ByteBuf byteBuf = dealDate(channel, packet);
        int len = 0;
        String data = "";
        packet.setCmd("10");
        packet.setData(data);
        return packet;
    }

    /**
     * 远程开门应答 11
     * data：柜门号（string）
     * 执行结果（int： 失败：0； 成功：1）
     *
     * @param channel
     * @param packet
     * @return
     */
    @Listen("11")
    public void handler11(Channel channel, Packet packet) {
        ByteBuf byteBuf = dealDate(channel, packet);
        int len = 0;
        len = byteBuf.readInt();
        String cellCode = byteBuf.readBytes(len).toString(utf8);
        int res = byteBuf.readInt();
        LOGGER.info("远程开门应答:柜门号({0}),执行结果({1})", res == 0 ? "失败" : "成功");
    }


    /**
     * 柜子在线心跳 18
     * data：整柜温控模块状态（int，无：0；有：1）
     * 当前副柜温度（int，单位为摄氏度，一共包含最多4个副柜的温度信息，每个副柜占用1个字节，为带符号的数值）
     * 当前整柜供电状态（int，断电：0；通电：1）
     *
     * @param channel
     * @param packet
     * @return 柜子在线心跳的应答 19
     * data：秒为单位的心跳包发送间隔（int）（此字段不可为空，为0表示无需设置心跳间隔；如果此字段值大于0，则重新设置心跳的发送间隔）
     * 自1970-1-1 00:00:00到目前的秒数（int）（此字段为0表示无需设置）
     */
    @Listen("18")
    public Object handler18(Channel channel, Packet packet) {
        ByteBuf byteBuf = dealDate(channel, packet);
        int len = 0;
        int state = byteBuf.readInt();
        byte temperature1 = byteBuf.readByte();
        byte temperature2 = byteBuf.readByte();
        byte temperature3 = byteBuf.readByte();
        byte temperature4 = byteBuf.readByte();
        int elestate = byteBuf.readInt();
        try {
            canbinetService.insertTemperature(packet.getBoxId(),temperature4);
            LOGGER.info("柜子在线心跳18:整柜温控模块状态({0}),当前副柜1温度({1})副柜2温度({2})副柜3温度({3})副柜4温度({4}),当前整柜供电状态({5})", state == 0 ? "无" : "有", temperature1, temperature2, temperature3, temperature4, elestate == 0 ? "断电" : "通电");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("云柜:({0})上传温度 {1}", packet.getBoxId(),String.valueOf(temperature4));
        }
        String data = DatasUtil.intTo(0) + DatasUtil.intTo(0);//(int)System.currentTimeMillis() / 1000
        packet.setCmd("19");
        packet.setData(data);

        return packet;
    }

    /**
     * 设置副柜温控阀值的应答 30
     * data：设置结果（int  0：失败；1：成功）
     *
     * @param channel
     * @param packet
     * @return
     */
    @Listen("30")
    public void handler30(Channel channel, Packet packet) {
        ByteBuf byteBuf = dealDate(channel, packet);
        int res = byteBuf.readInt();
        LOGGER.info("设置副柜温控阀值:{0}", res == 0 ? "失败" : "成功");
    }

    /**
     * 报警信息 41
     * data： 异常长时间开启的柜门号（string）
     *
     * @param channel
     * @param packet
     * @return 报警信息的应答 42
     * data： 无
     */
    @Listen("41")
    public Object handler41(Channel channel, Packet packet) {
        ByteBuf byteBuf = dealDate(channel, packet);
        int len = 0;
        len = byteBuf.readInt();
        String cellCode = byteBuf.readBytes(len).toString(utf8);
        String data = "";
        packet.setCmd("42");
        packet.setData(data);
        LOGGER.info("异常长时间开启的柜门号:柜门号({0})", cellCode);
        return packet;
    }

    /**
     * 配货员刷（输入）快递单号后发送此命令到服务器 51
     * data：员工类型（int： 超级管理员 0； 一般管理员1； 配货员2（刷员工卡进入菜单的操作员））
     * 员工id（string，员工类型为2有此字段）
     * 快递单号（string）
     * 手机号（string）
     * 拟开门的门号（string）
     *
     * @param channel
     * @param packet
     * @return 配货员送快递包裹条码应答 52
     * data： 配货条码单号（string）
     * 拟开门的门号（string）
     * 此条码是否有效（int）
     * （条码有效有后续字段）用户密码（string）
     * 用户会员id（string）
     * 是否需要支付密码（int）
     * 会员的支付密码（string）
     */
    @Listen("51")
    public Object handler51(Channel channel, Packet packet) {
        ByteBuf byteBuf = dealDate(channel, packet);
        int len = 0;
        int type = byteBuf.readInt();
        Long managerId = 0L;
        if (type == 2) {
            len = byteBuf.readInt();
            managerId = Long.valueOf(byteBuf.readBytes(len).toString(utf8));
        }
        len = byteBuf.readInt();
        String packageNo = byteBuf.readBytes(len).toString(utf8);
        len = byteBuf.readInt();
        String mobile = byteBuf.readBytes(len).toString(utf8);
        len = byteBuf.readInt();
        String cellCode = byteBuf.readBytes(len).toString(utf8);

        PackageRecord packageRecord = null;
        try {
            packageRecord = canbinetService.insertPackageIntoCabinet(packet.getBoxId(), packageNo, cellCode);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("送货 e mobile :" + mobile + "send fail");
        }


        String data = DatasUtil.stringTo(packageRecord.getOrderCode())
                + DatasUtil.stringTo(cellCode)
                + DatasUtil.intTo(packageRecord.getPackageStatus().longValue() != 1 ? 0 : 1)//判断是否已送
                + DatasUtil.stringTo(packageRecord.getPassword())
                + DatasUtil.stringTo(Rfid10to16(packageRecord.getRfid()))
                + DatasUtil.intTo(0)
                + DatasUtil.stringTo("");

        packet.setCmd("52");
        packet.setData(data);
        LOGGER.info("快递单号后发送此命令到服务器:员工类型（{0}）员工id（{1}）快递单号（{2]）手机号（{3}）拟开门的门号（{4}）", type, managerId, packageNo, mobile, cellCode);
        return packet;
    }

    /**
     * 配货员（管理员）直接存快递开门命令 53
     * data：员工类型（int： 超级管理员 0； 一般管理员1；配货员2（刷员工卡进入菜单的操作员））
     * 员工id（string，员工类型为2有此字段）
     * 柜门号（string）
     * 配货单号（string）
     * 手机号（string）
     *
     * @param channel
     * @param packet
     * @return 配货员（管理员）直接存快递开门应答 54
     * data：柜门号（string）
     * 此条码是否有效（int）
     * 用户密码（string）
     * 用户会员id（string）
     * 是否需要支付密码（int）
     * 会员的支付密码（string）
     */
    @Listen("53")
    public Object handler53(Channel channel, Packet packet) {
        ByteBuf byteBuf = dealDate(channel, packet);
        int len = 0;
        int type = byteBuf.readInt();
        Long managerId = 0L;
        if (type == 2) {
            len = byteBuf.readInt();
            managerId = Long.valueOf(byteBuf.readBytes(len).toString(utf8));
        }
        len = byteBuf.readInt();
        String cellCode = byteBuf.readBytes(len).toString(utf8);
        len = byteBuf.readInt();
        String packageNo = byteBuf.readBytes(len).toString(utf8);
        len = byteBuf.readInt();
        String mobile = byteBuf.readBytes(len).toString(utf8);


        PackageRecord packageRecord = null;
        try {
            packageRecord = canbinetService.insertPackageIntoCabinet(packet.getBoxId(), packageNo, cellCode);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("送货 e mobile :" + mobile + "send fail");
        }

        String data = DatasUtil.stringTo(packageRecord.getOrderCode())
                + DatasUtil.stringTo(cellCode)
                + DatasUtil.intTo(packageRecord.getPackageStatus().longValue() != 1 ? 0 : 1)//判断是否已送
                + DatasUtil.stringTo(packageRecord.getPassword())
                + DatasUtil.stringTo(Rfid10to16(packageRecord.getRfid()))
                + DatasUtil.intTo(0)
                + DatasUtil.stringTo("");

        packet.setCmd("54");
        packet.setData(data);
        LOGGER.info("异常长时间开启的柜门号:柜门号({0})", cellCode);
        return packet;
    }

    /**
     * 配货员（管理员）回收快递包裹开门命令 55
     * data：员工类型（int： 超级管理员 0； 一般管理员1；配货员2（刷员工卡进入菜单的操作员））
     * 员工id（string，员工类型为2有此字段）
     * 柜门号（string）
     * 配货单号（string）
     * 手机号（string）
     *
     * @param channel
     * @param packet
     * @return 配货员（管理员）回收快递包裹开门应答 56
     * data：配货单号（string）
     * 柜门号（string）
     */
    @Listen("55")
    public Object handler55(Channel channel, Packet packet) {
        ByteBuf byteBuf = dealDate(channel, packet);
        int len = 0;
        int type = byteBuf.readInt();
        Long managerId = 0L;
        if (type == 2) {
            len = byteBuf.readInt();
            managerId = Long.valueOf(byteBuf.readBytes(len).toString(utf8));
        }
        len = byteBuf.readInt();
        String cellCode = byteBuf.readBytes(len).toString(utf8);
        len = byteBuf.readInt();
        String packageNo = byteBuf.readBytes(len).toString(utf8);
        len = byteBuf.readInt();
        String mobile = byteBuf.readBytes(len).toString(utf8);


        try {
            canbinetService.updatePackage(packet.getBoxId(), packageNo, type);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String data = DatasUtil.stringTo(packageNo)
                + DatasUtil.stringTo(cellCode);

        packet.setCmd("56");
        packet.setData(data);
        LOGGER.info("配货员（管理员）回收快递包裹开门命令:员工类型（{0}）员工id（{1}）柜门号（{2]）配货单号（{3}）手机号（{4}）", type, managerId, cellCode, packageNo, mobile);
        return packet;
    }

    /**
     * 配货员刷（输入）配货条码后开门成功命令 57
     * data：员工类型（int： 超级管理员 0； 一般管理员1； 配货员2（刷员工卡进入菜单的操作员））
     * 员工id（string，员工类型为2有此字段）
     * 配货条码（string）
     * 开门的门号（string，FF表示为爆柜存货）
     * 用户密码（string）
     *
     * @param channel
     * @param packet
     * @return 配货员刷（输入）配货条码后开门成功应答 58
     * data： 配货条码单号（string）
     * 开门的门号（string，FF表示为爆柜存货）
     */
    @Listen("57")
    public Object handler57(Channel channel, Packet packet) {
        ByteBuf byteBuf = dealDate(channel, packet);
        int len = 0;
        int type = byteBuf.readInt();
        Long managerId = 0L;
        if (type == 2) {
            len = byteBuf.readInt();
            managerId = Long.valueOf(byteBuf.readBytes(len).toString(utf8));
        }

        len = byteBuf.readInt();
        String packageNo = byteBuf.readBytes(len).toString(utf8);
        len = byteBuf.readInt();
        String cellCode = byteBuf.readBytes(len).toString(utf8);
        len = byteBuf.readInt();
        String password = byteBuf.readBytes(len).toString(utf8);

        try {
            canbinetService.updatePackage(packet.getBoxId(), packageNo, type);
            LOGGER.info("用户开门取货后命令08:柜门号({0}),配货单条码({1}),用户取货方式({2})", cellCode, packageNo, type);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String data = DatasUtil.stringTo(packageNo)
                + DatasUtil.stringTo(cellCode);

        packet.setCmd("58");
        packet.setData(data);
        LOGGER.info("配货员（管理员）回收快递包裹开门命令:员工类型（{0}）员工id（{1}）柜门号（{2}）配货单号（{3}）用户密码（{4}）", type, managerId, cellCode, packageNo, password);
        return packet;
    }
}
