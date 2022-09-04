package com.elisaverza;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.alibaba.fastjson.serializer.SerializeWriter;

@RunWith(value=Parameterized.class)
public class NewSerializeWriterTest {
    private String charValue;
    private Integer intValue;
    private Long longValue;
    private Boolean intFirst;
    private String expected;

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
            {charParams.get(0)+intParams.get(0).toString(), charParams.get(0), intParams.get(0), null, false},
            {charParams.get(0)+intParams.get(0)+longParams.get(0).toString(), charParams.get(0), intParams.get(0), longParams.get(0), false},
            {intParams.get(1).toString(), null, intParams.get(1), null, false},
            {intParams.get(1)+charParams.get(1), charParams.get(1), intParams.get(1), null, true},
            {longParams.get(1).toString(), null, null, longParams.get(1), false},
            {longParams.get(1)+charParams.get(1).toString(), charParams.get(1), null, longParams.get(1), false},
        });
    }


    public NewSerializeWriterTest(String expected, String charValue, Integer intValue, Long longValue, Boolean intFirst){
        this.charValue = charValue;
        this.intValue = intValue;
        this.longValue = longValue;
        this.intFirst = intFirst;
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

    @Test
    public void test1(){

        if(intValue!=null && !intFirst){
            if(charValue!=null){
                sw.append(charValue);
            }
            sw.writeInt(intValue);
            
            if(longValue!=null){
                sw.writeLong(longValue);
            }
        }
        else if(longValue!=null){
            sw.writeLong(longValue);
            if(charValue!=null){
                sw.write(charValue);
            }
        }
        else if(intFirst){
            sw.writeInt(intValue);
            sw.write(charValue);
        }
        assertEquals(expected, sw.toString());

    }
    
    
}
