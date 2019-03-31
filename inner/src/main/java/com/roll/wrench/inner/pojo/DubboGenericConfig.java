package com.roll.wrench.inner.pojo;

import java.util.Arrays;

/**
 * @author zongqiang.hao
 * created on 2019-03-29 09:11.
 */
public class DubboGenericConfig {
    /**
     * zookeeper地址
     */
    private String address = "192.168.6.55:2181,192.168.6.56:2181,192.168.6.57:2181";

    /**
     * 协议名称
     */
    private String protocol = "zookeeper";

    /**
     * 应用名称
     */
    private String name = "wrench-dubbo-generic";

    /**
     * 注册的服务
     */
    private String group = "dubbo";

    /**
     * 接口名
     */
    private String serviceName;

    /**
     * 版本号
     */
    private String version;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 输入参数类型
     */
    private String[] inputParamsArray;

    /**
     * 输入参数对象
     */
    private Object[] inputObjectArray;

    public DubboGenericConfig() {
    }

    public DubboGenericConfig(String serviceName, String version, String methodName, String[] inputParamsArray, Object[] inputObjectArray) {
        this.serviceName = serviceName;
        this.version = version;
        this.methodName = methodName;
        this.inputParamsArray = inputParamsArray;
        this.inputObjectArray = inputObjectArray;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String[] getInputParamsArray() {
        return inputParamsArray;
    }

    public void setInputParamsArray(String[] inputParamsArray) {
        this.inputParamsArray = inputParamsArray;
    }

    public Object[] getInputObjectArray() {
        return inputObjectArray;
    }

    public void setInputObjectArray(Object[] inputObjectArray) {
        this.inputObjectArray = inputObjectArray;
    }

    @Override
    public String toString() {
        return "DubboGenericConfig{" +
                "address='" + address + '\'' +
                ", protocol='" + protocol + '\'' +
                ", name='" + name + '\'' +
                ", group='" + group + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", version='" + version + '\'' +
                ", methodName='" + methodName + '\'' +
                ", inputParamsArray=" + Arrays.toString(inputParamsArray) +
                ", inputObjectArray=" + Arrays.toString(inputObjectArray) +
                '}';
    }
}
