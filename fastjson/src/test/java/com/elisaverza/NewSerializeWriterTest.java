package com.elisaverza;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import junitparams.Parameters;

import com.alibaba.fastjson.serializer.SerializeWriter;

@RunWith(value=Parameterized.class)
public class NewSerializeWriterTest {
    private String charValue;
    private Integer intValue;
    private Long longValue;
    private String expected;

    public NewSerializeWriterTest(String valueOne, String valueTwo, String expected){
        this.charValue = charValue;
        this.intValue = intValue;
        this.longValue = longValue;
        this.expected = expected;
    }

    private SerializeWriter sw;
	@Before
    public void configure(){
        sw = new SerializeWriter();
    }

    @After
    public void closure(){
        sw.flush();
        sw.close();
    }


    @Parameters
    public static Collection<Object[]> getTestParameter(){
        Map<Integer, String> charParams = Stream.of(new Object[][] { 
            { 0, "A"}, 
            { 1, ","}, 
        }).collect(Collectors.toMap(data -> (Integer) data[0], data -> (String) data[1]));

        Map<Integer, Integer> intParams = Stream.of(new Integer[][] { 
            { 0, 156}, 
            { 1, -1}, 
        }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

        Map<Integer, Long> longParams = Stream.of(new Object[][] { 
            { 0, 345L}, 
            { 1, -1L}, 
        }).collect(Collectors.toMap(data -> (Integer) data[0], data -> (Long) data[1]));

        return Arrays.asList(new Object[][]{
            {charParams.get(0)+intParams.get(0), charParams.get(0), intParams.get(0), null},
            {charParams.get(0)+intParams.get(0)+longParams.get(0), charParams.get(0), intParams.get(0), longParams.get(0)},
            {intParams.get(1), null, intParams.get(1), null},
            {intParams.get(1)+charParams.get(1), charParams.get(1), intParams.get(1), null},
            {longParams.get(1), null, null, longParams.get(1)},
            {longParams.get(1)+charParams.get(1), charParams.get(1), null, longParams.get(1)},
        });
    }

    @Test
    public void test1(){
        if(charValue!=null && intValue!=null){
            sw.append(charValue);
            sw.writeInt(intValue);
            assertEquals(expected, sw.toString());
            if(longValue!=null){
                sw.writeLong(longValue);
                assertEquals(expected, sw.toString());
            }
        }
    }

    
}
