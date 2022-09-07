package com.elisaverza;

import org.junit.Assert;
import org.junit.Test;

import junit.framework.TestCase;

import com.alibaba.fastjson.JSON;

public class JSONFieldTest extends TestCase {

	@Test
	public void test_jsonField() throws Exception {
		VO vo = new VO();
		
		vo.setId(123);
		vo.setName("xx");
		
		String text = JSON.toJSONString(vo);
		Assert.assertEquals("{\"id\":123}", text);
	}
}
