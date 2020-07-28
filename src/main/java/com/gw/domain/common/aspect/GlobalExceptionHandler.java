package com.gw.domain.common.aspect;

import com.gw.cloud.common.core.base.result.JsonResult;
import com.gw.cloud.common.core.util.JsonResultUtil;
import com.gw.domain.common.exception.SyncException;
import com.gw.domain.common.util.DingTalkUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

	private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@Autowired
	DingTalkUtil dingTalkUtil;

	@ExceptionHandler(Exception.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public JsonResult<Object> handleAllException(Exception ex) {
		logger.error("request exception", ex);
		String msg = ex.getMessage();
		if (StringUtils.isBlank(msg)) {
			msg = "后台服务异常";
		}
		return JsonResultUtil.createFailureJsonResult(String.format("异常信息：%s", msg));
	}

	@ExceptionHandler(SyncException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public JsonResult<Object> handleSyncException(SyncException ex) throws Exception {
		logger.error("request exception", ex);
		String msg = ex.getMessage();
		if (StringUtils.isBlank(msg)) {
			msg = "后台服务异常";
		}
		dingTalkUtil.sendMarkdownTalk(msg);
		return JsonResultUtil.createFailureJsonResult(msg);
	}


}
