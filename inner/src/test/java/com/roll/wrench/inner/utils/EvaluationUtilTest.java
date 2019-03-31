package com.roll.wrench.inner.utils;


import com.roll.wrench.inner.common.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zongqiang.hao
 * created on 2019-03-26 20:24.
 */
public class EvaluationUtilTest {

    @Test
    public void setUserTest() {
        User user = new User();
        Map<String, Object> params = new HashMap<>();
        params.put("name", "nam1");
        params.put("age", 23);
        EvaluationUtil.evaluationByName(user, params);
        System.out.println(user.toString());
        Assert.assertEquals("nam1", user.getName());
    }
}
