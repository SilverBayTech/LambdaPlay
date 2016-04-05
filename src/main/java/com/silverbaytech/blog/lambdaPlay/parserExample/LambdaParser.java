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
import java.util.function.BiConsumer;

public class LambdaParser
{
	public static <T> void transferString(T object, BiConsumer<T,String> setter, Properties source, String name)
	{
		setter.accept(object, source.getProperty(name));
	}
	
	public static <T> void transferInteger(T object, BiConsumer<T,Integer> setter, Properties source, String name)
	{
		setter.accept(object, Integer.parseInt(source.getProperty(name)));
	}
	
	public static <T> void transferPrice(T object, TriConsumer<T,BigDecimal,String> setter, Properties source, String name)
	{
		BigDecimal amount = new BigDecimal(source.getProperty(name + ".amount"));
		String currency = source.getProperty(name + ".currency");
		setter.accept(object, amount, currency);
	}
}

