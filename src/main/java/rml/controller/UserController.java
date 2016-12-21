package rml.controller;

import rml.model.ResponseObj;
import rml.model.User;
import rml.service.UserService;
import rml.utils.GsonUtils;
import rml.utils.PublicUtil;
import rml.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 用户请求相关控制器
 * <br/>Created by acheng on 2016/9/26.
 */
@RestController
@RequestMapping("/userAction")
public class UserController {

    @Autowired
    private UserService userService;    //自动载入Service对象

    private ResponseObj responseObj;

    /**
     * 为什么返回值是一个ModelAndView，ModelAndView代表一个web页面<br/>
     * setViewName是设置一个jsp页面的名称
     *
     * @param response http响应
     * @param user     发起请求后，spring接收到请求，然后封装的bean数据
     * @return 返回一个web页面
     * @throws Exception
     */
    @RequestMapping(value = "/reg"
            , method = RequestMethod.POST
            , produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object reg(HttpServletRequest request, HttpServletResponse response, User user, HttpSession session) throws Exception {
        Object result;
        responseObj = new ResponseObj<User>();
        if (null == user) {
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("用户信息不能为空！");
            result = new GsonUtils().toJson(responseObj);
            return result;
        }
        if (StringUtils.isEmpty(user.getLoginId()) || StringUtils.isEmpty(user.getPwd())) {
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("用户名或密码不能为空！");
            result = new GsonUtils().toJson(responseObj);
            return result;
        }
        if (null != userService.findUser(user)) {
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("用户已经存在！");
            result = new GsonUtils().toJson(responseObj);
            return result;
        }
        try {
            userService.add(user);
        } catch (Exception e) {
            e.printStackTrace();
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("其他错误！");
            result = new GsonUtils().toJson(responseObj);
            return result;
        }
        userService.updateLoginSession(request.getSession().getId(), user.getLoginId());
        responseObj.setCode(ResponseObj.OK);
        responseObj.setMsg("注册成功");
        user.setPwd(session.getId());   //单独设置密码为sessionId 误导黑客，前端访问服务器的时候必须有这个信息才能操作
        user.setNextUrl(request.getContextPath() + "/mvc/home");    //单独控制地址
        responseObj.setData(user);
        session.setAttribute("userInfo", user);
        result = new GsonUtils().toJson(responseObj);
        return result;
    }

    /**
     * 登录接口
     *
     * @param request
     * @param user
     * @return
     */
    @RequestMapping(value = "/login"
            , method = RequestMethod.POST
            , produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object login(HttpServletRequest request, HttpServletResponse response, User user, HttpSession session) throws Exception {
        Object result;
        if (PublicUtil.isJsonRequest(request)) {    //确认是否json提交
            user = new GsonUtils().jsonRequest2Bean(request.getInputStream(), User.class);  //转换为指定类型的对象
            return user.toString();
        }
        if (null == user) {
            responseObj = new ResponseObj<User>();
            responseObj.setCode(ResponseObj.EMPUTY);
            responseObj.setMsg("登录信息不能为空");
            result = new GsonUtils().toJson(responseObj);
            return result; //返回json
        }
        if (StringUtils.isEmpty(user.getLoginId()) || StringUtils.isEmpty(user.getPwd())) {
            responseObj = new ResponseObj<User>();
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("用户名或密码不能为空");
            result = new GsonUtils().toJson(responseObj);
            return result;
        }
        //查找用户
        User user1 = userService.findUser(user);
        if (null == user1) {
            responseObj = new ResponseObj<User>();
            responseObj.setCode(ResponseObj.EMPUTY);
            responseObj.setMsg("未找到该用户");
            result = new GsonUtils().toJson(responseObj);
        } else {
            if (user.getPwd().equals(user1.getPwd())) {
                user1.setPwd(session.getId());
                user1.setNextUrl(request.getContextPath() + "/mvc/home");
                responseObj = new ResponseObj<User>();
                responseObj.setCode(ResponseObj.OK);
                responseObj.setMsg(ResponseObj.OK_STR);
                responseObj.setData(user1);
                userService.updateLoginSession(request.getSession().getId(), user.getLoginId());
                session.setAttribute("userInfo", user);
                result = new GsonUtils().toJson(responseObj);
            } else {
                responseObj = new ResponseObj<User>();
                responseObj.setCode(ResponseObj.FAILED);
                responseObj.setMsg("用户密码错误");
                result = new GsonUtils().toJson(responseObj);
            }
        }
        return result;
    }

    @RequestMapping(value = "/uploadHeadPic"
            , method = RequestMethod.POST
            , produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object uploadHeadPic(@RequestParam(required = false) MultipartFile file, HttpSession session) {

        if (null == file || file.isEmpty()) {
            responseObj = new ResponseObj();
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("文件不能为空");
            return new GsonUtils().toJson(responseObj);
        }
        responseObj = new ResponseObj();
        responseObj.setCode(ResponseObj.OK);
        responseObj.setMsg("文件长度为：" + file.getSize());
        return new GsonUtils().toJson(responseObj);
    }

}
