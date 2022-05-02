package elisaverza;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.Collection;

import com.alibaba.fastjson.JSON;

@RunWith(value=Parameterized.class)
public class JSONFieldTest extends TestCase {

    private String expected;
    private int id;
    private String name; 
    private String voStr;
    
    public VO configure(){
        VO vo = new VO();
        vo.setId(id);
        vo.setName(name);
        return vo;
    }

    @Parameters
    public static Collection<Object[]> getTextParameters(){
        return Arrays.asList(new Object[][]{
            {123, "xx", "{\"id\":123}"},
        });
    }

    public JSONFieldTest(int id, String name, String expected){
        this.expected = expected;
        this.id = id;
        this.name = name;
        this.voStr = JSON.toJSONString(configure());
    }

    @Test
    public void test_jsonField() throws Exception {
        Assert.assertEquals(expected, voStr);
    }
}
