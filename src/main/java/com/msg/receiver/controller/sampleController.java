package com.msg.receiver.controller;

import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import com.msg.gw.config.AppConfig;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
public class sampleController {

	private static final String logLevel = "DEBUG";

	@ResponseBody
	@RequestMapping(value="/get.do" , method={RequestMethod.GET ,RequestMethod.POST} , produces="application/json;charset=UTF-8")
	public String getProp(HttpServletRequest req) {
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


    @PostMapping(value="/put.do", produces="application/json;charset=UTF-8")
    public String putProp(@RequestBody Map<String, Object> payload) {
        Map prop = AppConfig.getConfig();
        String name = (String)payload.get("name");
        String value = (String)payload.get("value");
        try {
            prop.put(name , value );
            System.out.println(prop.toString());
        }catch (Exception e){
        }
        return payload.toString();
    }
}
