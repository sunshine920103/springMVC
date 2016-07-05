package cc.study.springmvc.controller;

import cc.study.springmvc.domain.User;
import cc.study.springmvc.service.UserService;
import cc.study.springmvc.util.Es_Client;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * TODO 控制层代码
 * @author
 * @date
 */
@Controller
public class HelloWorldController {


    @Autowired
    UserService userService;

    @Autowired
    Es_Client es_client;

    @RequestMapping(value="login",method = RequestMethod.POST) //用来处理前台的login请求
    /*@ResponseBody*/
    public  String hello(
            @RequestParam(value = "username", required = false)String username,
            String password,ModelMap modelMap)
    {
        boolean hasMatchUser=userService.hasMatchUser(username,password);
        User user=userService.findUserByUserName(username);
        if(hasMatchUser)
        {
            modelMap.put("user", user);
            SearchResponse response = es_client.getTransportClient().prepareSearch("razor_web_access")
                    .setTypes("access")
                    .addSort("date_time", SortOrder.DESC)
                    .addAggregation(
                            AggregationBuilders.terms("agg").field("user_id").size(1)
                    )
                    .setSize(20).setFrom(0)
                    .execute()
                    .actionGet();
            return response.toString();
        }
        return "0";

    }



}
