package com.msg.receiver.controller;

import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import com.msg.gw.config.AppConfig;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class sampleController {

	private static final String logLevel = "DEBUG";

	@ResponseBody
	@RequestMapping(value="/putHello.do" , method={RequestMethod.GET ,RequestMethod.POST} , produces= {"application/json"})
	public String HelloWolrd(HttpServletRequest req) {
        Map prop = AppConfig.getConfig();
	    try {
            System.out.println(prop.toString());
        }catch (Exception e){
        }

        JSONObject json = new JSONObject();
	    json.putAll(prop);

	    System.out.println("CHECK : " + json.toJSONString());

	    return json.toJSONString();
    }
}
