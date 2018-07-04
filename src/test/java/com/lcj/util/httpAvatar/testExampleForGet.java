package com.lcj.util.httpAvatar;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

/**
 *  参考：https://github.com/OnTheWay111/Avatar.git
 样例：调用get请求
 *
 */
public class testExampleForGet {
    private Map<String,String> headerMap = new HashMap<String,String>(); //Map<String,String> headerMap,为header列表
    private String url = ""; //String url为post请求的url
    private List<String> responseDataList = new LinkedList<>(); //断言list，元素为String类型
    private String httpStatus = "";
    private String hostIP = "";
     

    @Test
    public void DoGetTest() throws Exception {
        headerMap.put("User-Agent", "smatisance");
        headerMap.put("Content-type", "application/x-www-form-urlencoded;charset:UTF-8");
        headerMap.put("cookie", "ssouid=2126494707; sso_tk=415dfhjkb5678vhj5jhgasyucibniagcnaohbahdhskhasc");


        url = "https://ascdsca-go.asd.com/api/query/viewCart.jsonp?asdasd=10002&cityId=10048&arrivalId=10501&toPay=0&deviceid=&version=4.0&rs=1100&_=1503543771058&callback=Zepto1503543771015";

        responseDataList.add("Hellosabiasnas");
        responseDataList.add("lizhen");
        responseDataList.add("bvghkl");
        httpStatus = "200";

        HttpGetTest httpGetTest = new HttpGetTest(headerMap, url, responseDataList, httpStatus, hostIP);

    }
}
