package com.wayakeji.common.core.exception;

import lombok.NoArgsConstructor;

/**
 * 计划策略异常
 * 
 * @NoArgsConstructor：生成无参的构造方法。
 * @AllArgsConstructor：生成该类下全部属性的构造方法。
 * @RequiredArgsConstructor：生成该类下被final修饰或者non-null字段生成一个构造方法。
 */
@NoArgsConstructor
public class TaskException extends Exception
{
    private static final long serialVersionUID = 1L;

    private Code code;

    public TaskException(String msg, Code code)
    {
        this(msg, code, null);
    }

    public TaskException(String msg, Code code, Exception nestedEx)
    {
        super(msg, nestedEx);
        this.code = code;
    }

    public Code getCode()
    {
        return code;
    }

    public enum Code
    {
        TASK_EXISTS, NO_TASK_EXISTS, TASK_ALREADY_STARTED, UNKNOWN, CONFIG_ERROR, TASK_NODE_NOT_AVAILABLE
    }
}