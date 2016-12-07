package utils;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bean on 2016/6/21.
 */
public class Utils {
    private static final MyLogger LOGGER = new MyLogger(Utils.class);
    public static final Charset utf8 = Charset.forName("utf-8");
    public static char[] getChars (byte[] bytes) {


	  int len = bytes.length;
	  char[] chars = new char[len];

	  for(int i=0;i<len;i++){
	  chars[i]= (char)(bytes[i] & 0xff);
	if(bytes[i]<0){
	  chars[i] = (char) (bytes[i]+256);
	  //chars[i]= (char)(bytes[i] & 0xff);
	}else{
	  chars[i] = (char)bytes[i];
	}
	  }
	  return chars;
    }

    /**
     * 向页面抛出IO流，打印数据
     *
     * @param o
     * @param response
     */
    public static void sos(String o, HttpServletResponse response) {
        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
            pw.print(o);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//Print HTML


    }

    /**
     * Base64 加密String...
     *
     * @param Params
     * @return
     */
    public static String Bill(List<String> Params) {

        ByteBuffer byteBuffer = ByteBuffer.allocate(20480);
        for (String Param : Params) {
            byteBuffer.put(Param.getBytes(utf8));
        }

        byteBuffer.flip();
        String Pa = "";


        int l = byteBuffer.limit();
        for (int i = 0; i < l; i++) {
            Pa += byteBuffer.get(i) + ";";
        }
        LOGGER.info("Pa:" + Pa);
        byte[] byten = new byte[l];

        byteBuffer.get(byten, 0, l);
        byten[0] = -122;
        String o ;
        o = new BASE64Encoder().encode(byten).replaceAll("\r\n", "");
        LOGGER.info("返回字符串：" + o + ";byteBuffer:"+Pa);
        return o;
        //return "hgICCwZBQkMwMDESMjAxNjA3MDExNzM4NDhBMDAxAkEyBEEwMDEDNTAwAzUwMAExAzUwMBIyMDE2MDcwMTE3Mzg0OEFCMDkBMQE1DjIwMTYwNzA4MTM1MTQ0";
    }

    /**
     * 随机生成n位随机验证码
     * 方法说明
     * @param n 验证码位数
     * @return String
     * @Discription:扩展说明
     * @Author: bean
     * @Date: 2015年4月17日 下午7:19:02
     * @ModifyUser：feizi
     * @ModifyDate: 2015年4月17日 下午7:19:02
     */
    public static String createRandomVcode(int n) {
        //验证码
        String vcode = "";
        for (int i = 0; i < n; i++) {
            vcode = vcode + (int) (Math.random() * 9);
        }
        return vcode;
    }

    public static Map<String, String> array2Map(String str) throws IOException {
        String retrunStr = "";
        String retrunDate = "";

//        Date date=new Date();
//        SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMddHHmmss");
//        String optTime= sdf.format(date);
//
//        String[] testStr={"134", "0x02", "0x01", "0x0a", optTime};
//
//        StringBuilder b=new StringBuilder();
//
//        for(String s:testStr) {
//            b.append(s);
//        }
//        byte[] a =b.toString().getBytes();
//
//        String a1=Base64.encodeBase64String(a);

        Map<String, String> map = new HashMap<String, String>();

        byte[] decodeStr = Base64.decodeBase64(str.getBytes());

        if ((decodeStr[1] == 2 && decodeStr[2] == 1 && decodeStr[3] == 2)
                || (decodeStr[1] == 2 && decodeStr[2] != 1)
//                || (decodeStr[1] == 2 && decodeStr[2] != 9)
                || decodeStr[1] != 2) {//所有有长度位的数据
            int bitNum = 0;
            int nextBitNum = 4;
            for (int i = 4; i < decodeStr.length; i++) {
                if (i == nextBitNum) {
                    bitNum = decodeStr[i] & 0xff;//长度位
                    nextBitNum = bitNum + i + 1;//下一个长度位
                    for (int j = i + 1; j <= i + bitNum; j++) {
                        retrunDate += (char) decodeStr[j];
                    }
                    retrunDate += ",";
                }
            }
            retrunDate = retrunDate.substring(0, retrunDate.length() - 1);
        } else {//无长度位数据
            for (int i = 4; i < decodeStr.length; i++) {
                retrunDate += (char) decodeStr[i];
            }
        }
        //标识字符
        for (int i = 0; i < 4; i++) {
            retrunStr += (decodeStr[i] & 0xff) + ",";
        }
        retrunStr = retrunStr.substring(0, retrunStr.length() - 1);
        map.put("type", retrunStr);
        map.put("data", retrunDate);
        return map;
    }
}
