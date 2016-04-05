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

package com.silverbaytech.blog.lambdaPlay.methodNames;

import java.util.function.Function;
import java.util.function.Supplier;

public class MethodNameConstructor
{
	private long createdAt;
	
	public MethodNameConstructor()
	{
		createdAt = System.currentTimeMillis();
	}
	
	public MethodNameConstructor(long createdAt)
	{
		this.createdAt = createdAt;
	}
	
	public long getCreatedAt()
	{
		return createdAt;
	}
	
	public static void main(String[] args)
	{
		useSupplier(() -> new MethodNameConstructor());
		useSupplier(MethodNameConstructor::new);
		
		useFunction((x) -> new MethodNameConstructor(x), 123L);
		useFunction(MethodNameConstructor::new, 123L);
		
		useSupplier(() -> new MethodNameConstructor(123L));
	}
	
	public static void useSupplier(Supplier<MethodNameConstructor> supplier)
	{
		MethodNameConstructor object = supplier.get();
		System.out.println("Got a MethodNameConstructor with createdAt " + object.getCreatedAt());
	}
	
	public static void useFunction(Function<Long, MethodNameConstructor> function, long argument)
	{
		MethodNameConstructor object = function.apply(argument);
		System.out.println("Built a MethodNameConstructor with createdAt " + object.getCreatedAt());
	}
}

