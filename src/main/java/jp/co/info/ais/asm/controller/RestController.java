package jp.co.info.ais.asm.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.info.ais.asm.common.Page;
import jp.co.info.ais.asm.domain.SampleUser;


@Controller
public class RestController {

	private static final Logger logger = LogManager.getLogger(RestController.class);

    @RequestMapping("/getuserlist")
    @ResponseBody
    public Page<SampleUser> getuserlist(@RequestBody Page<SampleUser> page) {
    	logger.debug(page.getColumns().get(0).getData());
        //Fetch the page number from client
    	int pageNumber = (page.getStart()/10)+1;

        List<SampleUser> list = new ArrayList<SampleUser>();
        for (int i=0; i<10; i++) {
            SampleUser user= new SampleUser(
                    String.valueOf(i+1),
                    String.format("u%04d@sample.com",i+1));
            list.add(user);
        }
        page.setData(list);

        page.setRecordsTotal(100);

        return page;
    }
}