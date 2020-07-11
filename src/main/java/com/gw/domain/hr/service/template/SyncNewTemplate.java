package com.gw.domain.hr.service.template;

import com.gw.cloud.common.base.mapper.BaseMapper;
import com.gw.cloud.common.base.util.DozerUtil;
import com.gw.domain.hr.common.mapper.DomainBaseMapper;
import com.gw.gwlog.GWMLogger;
import com.gw.gwlog.GWMLoggerFactory;
import org.apache.ibatis.session.RowBounds;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.text.MessageFormat;
import java.util.List;

/**
 * @author gwx
 * @description 同步全量数据模板
 * @date 2020-07-11
 */

public abstract class SyncNewTemplate<T1, T2> {

    private GWMLogger logger = GWMLoggerFactory.getSimpleLogger(this.getClass());

    private Class<T1> clazzF = (Class<T1>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    private Class<T2> clazzTo = (Class<T2>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];

    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public int syncNew(String fieldName) {
        this.logger.info(MessageFormat.format("人力资源向主数据增量同步数据:{0}到{1}", clazzF, clazzTo));
        int numCreate = 0;
        int numUpdate = 0;

        Example createExample = new Example(clazzF);
        createExample.orderBy("createTime");
        createExample.createCriteria().andGreaterThan("createTime", getDomainMapper().selectMaxCreateTime());
        int createCount = getHrMapper().selectCountByExample(createExample);

        Example updateExample = new Example(clazzF);
        updateExample.orderBy("updateTime");
        updateExample.createCriteria().andGreaterThan("updateTime", getDomainMapper().selectMaxUpdateTime());
        int updateCount = getHrMapper().selectCountByExample(updateExample);

        int pageSize = 1000;
        int careteLoop = (createCount%pageSize==0?createCount/pageSize:createCount/pageSize+1);
        for(int i=0; i<careteLoop; i++){
            RowBounds rowBounds = new RowBounds(i*pageSize, pageSize);
            List<T1> fileInfoList = getHrMapper().selectByExampleAndRowBounds(createExample, rowBounds);
            List<T2> domainEmployeeInfoList = DozerUtil.convert(fileInfoList, clazzTo);
            numCreate += getDomainMapper().insertList(domainEmployeeInfoList);
            this.logger.info(MessageFormat.format("循环插入主数据，数据类型:{0}，数量:{1}", clazzTo, numCreate));
        }

        int updateLoop = (updateCount%pageSize==0?updateCount/pageSize:updateCount/pageSize+1);
        for(int i=0; i<updateLoop; i++){
            RowBounds rowBounds = new RowBounds(i*pageSize, pageSize);
            List<T1> fileInfoList = getHrMapper().selectByExampleAndRowBounds(updateExample, rowBounds);
            List<T2> domainFileInfoList = DozerUtil.convert(fileInfoList, clazzTo);
            for(T2 targetObject : domainFileInfoList) {
                Example example = new Example(clazzTo);
                Field field = ReflectionUtils.findField(clazzTo, fieldName);
                ReflectionUtils.makeAccessible(field);
                Object object = ReflectionUtils.getField(field, targetObject);
                example.createCriteria().andEqualTo(fieldName, object);
                numUpdate += getDomainMapper().updateByExampleSelective(targetObject, example);
            }
            this.logger.info(MessageFormat.format("循环更新主数据，数据类型:{0}，数量:{1}", clazzTo, numCreate));
        }
        this.logger.info(MessageFormat.format("人力资源向主数据全量同步数据结束:{0}到{1}。共同步新增数量:{2}，同步更新数量:{3}", clazzF, clazzTo, numCreate , numUpdate));
        return numCreate + numUpdate;
    }

    public abstract BaseMapper getHrMapper();

    public abstract DomainBaseMapper getDomainMapper();


}
