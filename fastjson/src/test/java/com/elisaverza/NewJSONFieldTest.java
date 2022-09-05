package com.elisaverza;

import java.util.Arrays;
import java.util.Collection;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.alibaba.fastjson.JSON;

@RunWith(value=Parameterized.class)
public class NewJSONFieldTest {
    private String name;
    private Integer id;
    private String expected;

    public NewJSONFieldTest(String expected, String name, Integer id){
        this.expected = expected;
        this.name = name;
        this.id = id;
    }

    private VO myVo;

    @Before
    public void configure(){
        myVo = new VO();
    }

    @Parameters
    public static Collection<Object[]> getTestParameter(){
        JSONObject obj = new JSONObject();

        obj.put("id", 123);
        //obj.put("name", "xx");

        return Arrays.asList(new Object[][]{
            {obj.toString(), "xx", 123}
        });
    }


    @Test
    public void idTest(){
        myVo.setId(id);
        myVo.setName(name);
        Assert.assertEquals(expected, JSON.toJSONString(myVo));
    }
}
