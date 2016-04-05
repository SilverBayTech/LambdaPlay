/*
 *  Copyright (c) 2016 kevin
 *  
 *  Licensed under the Apache License, Version 2.0 (the "License"); 
 *  you may not use this file except in compliance with the License. 
 *  You may obtain a copy of the License at 
 *  
 *  http://www.apache.org/licenses/LICENSE-2.0 
 *  
 *  Unless required by applicable law or agreed to in writing, software 
 *  distributed under the License is distributed on an "AS IS" BASIS, 
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 *  See the License for the specific language governing permissions and 
 *  limitations under the License. 
 */

package com.silverbaytech.blog.lambdaPlay.parserExample;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.math.BigDecimal;
import java.util.Properties;

import org.junit.Test;

public class SampleBeanTest
{
	public SampleBeanTest()
	{
	}
	
	@Test
	public void testLoad()
	{
		Properties properties = new Properties();
		properties.put("s1", "string1");
		properties.put("s2", "string2");
		properties.put("i1", "99");
		properties.put("i2", "-99");
		properties.put("price.amount", "10.00");
		properties.put("price.currency", "USD");
		
		SampleBean objUnderTest = new SampleBean();
		
		objUnderTest.load(properties);
		
		assertThat(objUnderTest.getStringItem1(), equalTo("string1"));
		assertThat(objUnderTest.getStringItem2(), equalTo("string2"));
		assertThat(objUnderTest.getIntegerItem1(), equalTo(Integer.valueOf(99)));
		assertThat(objUnderTest.getIntegerItem2(), equalTo(Integer.valueOf(-99)));
		assertThat(objUnderTest.getAmount(), equalTo(new BigDecimal("10.00")));
		assertThat(objUnderTest.getCurrency(), equalTo("USD"));
	}
}

