package elisaverza;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.Collection;

import com.alibaba.fastjson.serializer.SerializeWriter;

@RunWith(value=Parameterized.class)
public class SerializeWriterTest extends TestCase {
    private char c;
    private Integer num;
    private Long lNum;
    private String expected;
	private boolean w;
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
    public static Collection<Object[]> getTextParameters(){
        return Arrays.asList(new Object[][]{
            //Il campo booleano serve per sapere se fare append o write del char
            {'A', 156, null, "A156", false}, //First param set test_0()
            {'A', 156, 345L, "A156345", false}, //Second param set test_0()
            {'\0', -1, null, "-1", false}, //Param set test_1()
            {',', -1, null, ",-1", true}, //Param set test_4()
            {'\0', null, -1L, "-1", false}, //Param set test_5()
            {',', null, -1L, ",-1", true}, //Param set test_6()
        });
    }

    public SerializeWriterTest(char c, Integer num, Long lNum, String expected, boolean w){
        this.c = c;
        this.num = num;
        this.lNum = lNum;
        this.expected = expected;
        this.w = w;
    }
    
    @Test
    public void swTest(){
        if(c!='\0'&& !w){sw.append(c);}
        else if(c!='\0' && w){sw.write(c);}
        if(num != null){ sw.writeInt(num);}
        if(lNum != null){sw.writeLong(lNum);}
        
        Assert.assertEquals(expected, sw.toString());
    }
    
    /*public void test_0() throws Exception {
		SerializeWriter writer = new SerializeWriter();
		writer.append('A');
		writer.writeInt(156);
		Assert.assertEquals("A156", writer.toString());
		writer.writeLong(345);
		Assert.assertEquals("A156345", writer.toString());

	}

	public void test_1() throws Exception {
		SerializeWriter writer = new SerializeWriter();
		writer.writeInt(-1);
		Assert.assertEquals("-1", writer.toString());
	}

	public void test_4() throws Exception {
		SerializeWriter writer = new SerializeWriter();
		writer.writeInt(-1);
		writer.write(',');
		Assert.assertEquals("-1,", writer.toString());
	}

	public void test_5() throws Exception {
		SerializeWriter writer = new SerializeWriter();
		writer.writeLong(-1L);
		Assert.assertEquals("-1", writer.toString());
	}

	public void test_6() throws Exception {
		SerializeWriter writer = new SerializeWriter();
		writer.writeLong(-1L);
		writer.write(',');
		Assert.assertEquals("-1,", writer.toString());
	}*/
}