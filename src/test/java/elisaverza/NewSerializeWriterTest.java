package elisaverza;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.alibaba.fastjson.serializer.SerializeWriter;

@RunWith(value = JUnitParamsRunner.class)

public class NewSerializeWriterTest {
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
    @Parameters({
        "A, 156, 345, A156",
    })
    public void test_0(char c, int i, long l, String expected) throws Exception {
		sw.append(c);
		sw.writeInt(i);
		Assert.assertEquals(expected, sw.toString());
		//writer.writeLong(l);
		//Assert.assertEquals("A156345", writer.toString());

	}
	@Test
	@Parameters({
        "-1, -1",
    })
	public void test_1(int i, String expected) throws Exception {
		sw.writeInt(i);
		Assert.assertEquals(expected, sw.toString());
	}

	@Test
	@Parameters({
        "-1, ',', \"-1,\"",
    })
	public void test_4(int i, char c, String expected) throws Exception {
		sw.writeInt(-1);
		sw.write(',');
		Assert.assertEquals(expected, sw.toString());
	}

	@Test
	@Parameters({
        "-1L, -1",
    })
	public void test_5(long l, String expected) throws Exception {
		sw.writeLong(l);
		Assert.assertEquals(expected, sw.toString());
	}

	@Test
	@Parameters({
        "-1L, ',', \"-1,\"",
    })
	public void test_6(long l, char c, String expected) throws Exception {
		sw.writeLong(l);
		sw.write(c);
		Assert.assertEquals(expected, sw.toString());
	}
}
