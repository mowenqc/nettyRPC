package com.mowen.common.message;

import java.io.Serializable;
import java.util.Arrays;

/***
 * desc  : com.mowen.common
 * author: mowen
 * create_time: 2019/6/11 8:16
 * project_name : nettyRPC_parent
 */
public class ResMessage implements Serializable {
    //发出特殊的命令
    /**
     * 请求的命令
     */
    private String comand;

    /**
     * 非rpc调用请求的内容
     */
    private byte[] content;

    /**
     * 请求的类名或者接口名
     */
    private Class<?> clazz;

    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 参数类型
     */
    private Class<?> [] parameterType;

    private Object[]  parameter;

    private Object result;

    private String error;


    public String getComand() {
        return comand;
    }

    public void setComand(String comand) {
        this.comand = comand;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterType() {
        return parameterType;
    }

    public void setParameterType(Class<?>[] parameterType) {
        this.parameterType = parameterType;
    }

    public Object[] getParameter() {
        return parameter;
    }

    public void setParameter(Object[] parameter) {
        this.parameter = parameter;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public static Builder builder(){
        return new Builder();
    }

    @Override
    public String toString() {
        return "ResMessage{" +
            "comand='" + comand + '\'' +
            ", content=" + Arrays.toString(content) +
            ", clazz=" + clazz +
            ", methodName='" + methodName + '\'' +
            ", parameterType=" + Arrays.toString(parameterType) +
            ", parameter=" + Arrays.toString(parameter) +
            ", result=" + result +
            ", error='" + error + '\'' +
            '}';
    }

    public static class Builder{
        /**
         * 请求的命令
         */
        private String comand;

        /**
         * 非rpc调用请求的内容
         */
        private byte[] content;

        /**
         * 请求的类名或者接口名
         */
        private Class<?> clazz;

        /**
         * 方法名称
         */
        private String methodName;
        /**
         * 参数类型
         */
        private Class<?> [] parameterType;

        private Object[]  parameter;

        private Object result;

        private String error;

        public Builder command(String comand){
            this.comand = comand;
            return this;
        }

        public Builder content(byte[] bytes){
            this.content = bytes;
            return this;
        }

        public Builder clazz(Class<?> clazz){
            this.clazz = clazz;
            return this;
        }
        public Builder methodName(String methodName){
            this.methodName = methodName;
            return this;
        }
        public Builder parameterType(Class<?> [] parameterType){
            this.parameterType = parameterType;
            return this;
        }
        public Builder parameter(Object[]  parameter){
            this.parameter = parameter;
            return this;
        }
        public Builder result(Object result){
            this.result = result;
            return this;
        }
        public Builder error(String error){
            this.error = error;
            return this;
        }

        public ResMessage build(){
            ResMessage resMessage = new ResMessage();

            resMessage.setClazz(clazz);
            resMessage.setComand(comand);
            resMessage.setContent(content);
            resMessage.setError(error);
            resMessage.setMethodName(methodName);
            resMessage.setParameter(parameter);
            resMessage.setParameterType(parameterType);
            resMessage.setResult(result);
            return resMessage;
        }
    }
}
