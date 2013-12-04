package com.lynx.info.controller;

import com.lynx.core.BasicController;
import com.lynx.core.Result;
import com.lynx.core.util.EncryptUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with IntelliJ IDEA.
 * User: chris.liu
 * Date: 13-12-2
 * Time: 上午11:50
 */
@Controller
@RequestMapping(value = "/news", method = RequestMethod.POST,
        produces = "text/plain;charset=UTF-8")
public class InfoController extends BasicController {

    @RequestMapping(value = "/")
    public ResponseEntity<String> location(@RequestParam(value = "type", required = false) int type,
                                           @RequestParam(value = "page", required = false) int page) {
        Result result = null;
        try {
            result = new Result(Result.RS_OK, null);
        } catch (Exception e) {
            log.info(e.getStackTrace());
            result = new Result(Result.RS_ERROR, "server inner error");
        }
        return new ResponseEntity<String>(EncryptUtil.format(result), HttpStatus.OK);
    }
}
