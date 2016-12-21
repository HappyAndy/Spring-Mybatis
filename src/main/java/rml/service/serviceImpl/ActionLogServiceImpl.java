package rml.service.serviceImpl;

import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rml.dao.ActionLogDao;
import rml.model.UserActionLog;
import rml.service.ActionLogService;
import rml.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by 程 on 2016/10/28.
 */
@Service("actionLogService")
public class ActionLogServiceImpl implements ActionLogService {

    @Autowired
    private ActionLogDao actionLogDao;

    private UserActionLog userActionLog;

    public void add(HttpServletRequest request) {
        //获取请求参数集合
        Map<String, String[]> params = request.getParameterMap();
        String queryString = "";
        for (String key : params.keySet()) {
            String[] values = params.get(key);
            for (int i = 0; i < values.length; i++) {
                String value = values[i];
                queryString += key + "=" + value + "&";
            }
        }


        userActionLog = new UserActionLog();
        userActionLog.setMethod(request.getMethod());   //获取请求方式
        if (request.getHeader("x-forwarded-for") == null) { //获取请求IP
            userActionLog.setIpAddrV4(request.getRemoteAddr());
        } else {
            userActionLog.setIpAddrV4(request.getHeader("x-forwarded-for"));
        }
        userActionLog.setOther(request.getHeader("User-Agent"));    //获取user-agent
        userActionLog.setSessionId(request.getSession().getId());   //获取用户操作的sessionID，必须
        userActionLog.setDescription(request.getRequestURI());  //获取访问的地址
        if (!StringUtils.isEmpty(queryString)) userActionLog.setRequestBody(queryString);   //参数集合内容不为空存入数据库

        try {
            UserAgent agent = new UserAgent(request.getHeader("User-Agent"));   //载入user-agent
            userActionLog.setOsName(agent.getOperatingSystem().getName());  //设定os名称
            userActionLog.setBroName(StringUtils.isEmpty(agent.getBrowser().getName()) ? "" : agent.getBrowser().getName()); //设定浏览器名称
            userActionLog.setBroVersion(StringUtils.isEmpty(agent.getBrowserVersion().getVersion()) ? "" : agent.getBrowserVersion().getVersion());    //设定浏览器版本
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            actionLogDao.add(userActionLog);
        }

    }

    @Deprecated
    public void add(UserActionLog userActionLog) throws Exception {

    }

    public List<UserActionLog> findAll(int pageNum, int pageSize) {
        //因为数据库内容是从第一条出的数据，所以我们查询的 起始位置 = 页码 * 条数 + 1；
        pageNum -= 1;
        return actionLogDao.findAll(pageNum * pageSize + 1, pageSize);
    }

    public int getAllCount() {
        return actionLogDao.getAllCount();
    }
}
