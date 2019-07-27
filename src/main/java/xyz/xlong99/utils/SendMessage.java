package xyz.xlong99.utils;



import com.alibaba.fastjson.JSONObject;

import java.awt.geom.RectangularShape;
import java.util.HashMap;
import java.util.Map;

/**
 * 发送验证码
 * @author xlong
 * @date 2019-06-01 15:55
 */
public class SendMessage {
    public static String url ="http://pay-api.xinjigame.com/api/phoneValidateCode.jhtml";
    public static String sendMessage(String phoneNum)  {
        Map<String, String> messageParam = new HashMap<>(1);
        messageParam.put("phoneNum",phoneNum);
        String res = null;
        try {
            res = HTTPUtils.get(url, messageParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = JSONObject.parseObject(res);
        return jsonObject.getString("validateCode");
    }

}
