package com.util;

import net.sf.json.JSONObject;

/**
 * 确定要返回去的数据
 */
public class CommonUtil {
    public JSONObject constructResponse(int code, String msg, Object data){
        JSONObject jsonObject = new JSONObject(  );
        jsonObject.put( "code" ,code);
        jsonObject.put( "msg",msg );
        jsonObject.put( "data",data );
        return  jsonObject;
    }
}
