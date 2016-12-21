package rml.service;


import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/9/25.
 */
public interface BaseService<T extends Serializable> {
    void add(T t) throws Exception;

    /**
     * 集合查询
     *
     * @param pageNum  页码
     * @param pageSize 每页的查询数量
     * @return
     */
    List<T> findAll(int pageNum, int pageSize);
}
