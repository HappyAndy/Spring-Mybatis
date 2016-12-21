package rml.service;


import rml.model.UserActionLog;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 程 on 2016/10/28.
 */
public interface ActionLogService extends BaseService<UserActionLog> {
    void add(HttpServletRequest request);

    List<UserActionLog> findAll(int pageNum, int pageSize);

    /**
     * 获取总条数
     * @return  获取总条数
     */
    int getAllCount();
}
