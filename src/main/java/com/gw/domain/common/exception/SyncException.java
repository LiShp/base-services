package com.gw.domain.common.exception;

/**
 * @author gwx
 * @description 数据同步异常处理类
 * @date 2020-07-28
 */

public class SyncException extends RuntimeException {
    public SyncException(String message) {
        super(message);
    }

    public SyncException(String message, Throwable e) {
        super(message, e);
    }
}
