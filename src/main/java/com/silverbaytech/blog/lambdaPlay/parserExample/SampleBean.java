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

import java.math.BigDecimal;
import java.util.Properties;

public class SampleBean
{
	private String stringItem1;
	private String stringItem2;
	private Integer integerItem1;
	private Integer integerItem2;
	private BigDecimal amount;
	private String currency;
	
	public SampleBean()
	{
	}

	public String getStringItem1()
	{
		return stringItem1;
	}

	public void setStringItem1(String stringItem1)
	{
		this.stringItem1 = stringItem1;
	}

	public String getStringItem2()
	{
		return stringItem2;
	}

	public void setStringItem2(String stringItem2)
	{
		this.stringItem2 = stringItem2;
	}

	public Integer getIntegerItem1()
	{
		return integerItem1;
	}

	public void setIntegerItem1(Integer integerItem1)
	{
		this.integerItem1 = integerItem1;
	}

	public Integer getIntegerItem2()
	{
		return integerItem2;
	}

	public void setIntegerItem2(Integer integerItem2)
	{
		this.integerItem2 = integerItem2;
	}
	
	public void setPrice(BigDecimal amount, String currency)
	{
		this.amount = amount;
		this.currency = currency;
	}
	
	public BigDecimal getAmount()
	{
		return amount;
	}
	
	public String getCurrency()
	{
		return currency;
	}
	
	public void load(Properties properties)
	{
		LambdaParser.transferString(this, SampleBean::setStringItem1, properties, "s1");
		LambdaParser.transferString(this, SampleBean::setStringItem2, properties, "s2");
		LambdaParser.transferInteger(this, SampleBean::setIntegerItem1, properties, "i1");
		LambdaParser.transferInteger(this, SampleBean::setIntegerItem2, properties, "i2");
		LambdaParser.transferPrice(this, SampleBean::setPrice, properties, "price");
	}
}

