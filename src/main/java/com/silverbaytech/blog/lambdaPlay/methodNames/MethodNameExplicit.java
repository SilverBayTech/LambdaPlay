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

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodNameExplicit
{
	public void zeroInZeroOut()
	{
		System.out.println("Called zeroInZeroOut");
	}
	
	public void oneInZeroOut(String a)
	{
		System.out.println("Called oneInZeroOut with " + a);
	}

	public void twoInZeroOut(String a, String b)
	{
		System.out.println("Called twoInZeroOut with " + a + " and " + b);
	}

	public String zeroInOneOut()
	{
		return "zeroInOneOut";
	}

	public String oneInOneOut(String input)
	{
		return input.toUpperCase();
	}

	public String twoInOneOut(String input1, String input2)
	{
		return input1 + input2;
	}

	public static void main(String[] args)
	{
		MethodNameExplicit object = new MethodNameExplicit();
		
		useConsumer((x)->object.oneInZeroOut(x), "input");
		useConsumer(object::oneInZeroOut, "input");
		
		useBiConsumer((x,y)->object.twoInZeroOut(x,y), "input1", "input2");
		useBiConsumer(object::twoInZeroOut, "input1", "input2");
		
		useSupplier(() -> object.zeroInOneOut());
		useSupplier(object::zeroInOneOut);
		
		useFunction((x) -> object.oneInOneOut(x), "abc");
		useFunction(object::oneInOneOut, "abc");
		
		useBiFunction((x,y)->object.twoInOneOut(x,y), "abc", "def");
		useBiFunction(object::twoInOneOut, "abc", "def");
		
		useRunnable(() -> object.zeroInZeroOut());
		useRunnable(object::zeroInZeroOut);
	}

	public static void useConsumer(Consumer<String> consumer, String input)
	{
		consumer.accept(input);
	}

	public static void useBiConsumer(BiConsumer<String, String> consumer, String input1, String input2)
	{
		consumer.accept(input1, input2);
	}

	public static void useSupplier(Supplier<String> supplier)
	{
		System.out.println("Supplier returned " + supplier.get());
	}

	public static void useFunction(Function<String, String> function, String input)
	{
		System.out.println("Calling function with '"
							+ input
							+ "' returned '"
							+ function.apply(input)
							+ "'");
	}
	public static void useBiFunction(BiFunction<String, String, String> function, String input1, String input2)
	{
		System.out.println("Calling function with '"
							+ input1
							+ "' and '"
							+ input2
							+ "' returned '"
							+ function.apply(input1, input2)
							+ "'");
	}
	
	public static void useRunnable(Runnable x)
	{
		x.run();
	}
}

