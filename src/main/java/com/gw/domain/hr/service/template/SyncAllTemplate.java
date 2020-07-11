package com.gw.domain.hr.service.template;

import com.gw.cloud.common.base.mapper.BaseMapper;
import com.gw.cloud.common.base.util.DozerUtil;
import com.gw.gwlog.GWMLogger;
import com.gw.gwlog.GWMLoggerFactory;
import org.apache.ibatis.session.RowBounds;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.text.MessageFormat;
import java.util.List;

/**
 * @author gwx
 * @description 同步全量数据模板
 * @date 2020-07-11
 */

public abstract class SyncAllTemplate<T1, T2> {

    private GWMLogger logger = GWMLoggerFactory.getSimpleLogger(this.getClass());

    private Class<T1> clazzF = (Class<T1>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    private Class<T2> clazzTo = (Class<T2>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];

    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public int syncAll() {
        this.logger.info(MessageFormat.format("人力资源向主数据全量同步数据:{0}到{1}", clazzF, clazzTo));
        int numCreate = 0;
        int deleteCount = getDomainMapper().deleteByExample(new Example(clazzTo));
        this.logger.info(MessageFormat.format("删除全量数据，数据类型:{0}，删除数量:{1}" ,clazzTo, deleteCount));

        Example countExample = new Example(clazzF);
        countExample.orderBy("createTime");
        int count = getHrMapper().selectCountByExample(countExample);
        int pageSize = 1000;
        int loop = (count%pageSize==0?count/pageSize:count/pageSize+1);
        for(int i=0; i<loop; i++){
            RowBounds rowBounds = new RowBounds(i*pageSize, pageSize);
            List<T1> groupList = getHrMapper().selectByExampleAndRowBounds(countExample, rowBounds);
            List<T2> domainOrgStructureList = DozerUtil.convert(groupList, clazzTo);
            numCreate += getDomainMapper().insertList(domainOrgStructureList);
            this.logger.info(MessageFormat.format("循环插入主数据，数据类型:{0}，数量:{1}", clazzTo, numCreate));
        }

        this.logger.info(MessageFormat.format("人力资源向主数据全量同步数据结束:{0}到{1}。共同步数量:{2}", clazzF, clazzTo, numCreate));
        return numCreate;
    }

    public abstract BaseMapper getHrMapper();

    public abstract BaseMapper getDomainMapper();


}
