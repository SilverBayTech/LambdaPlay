/*
 * Copyright (c) 2016 kevin
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.silverbaytech.blog.lambdaPlay.methodNames;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class MethodNameGeneric
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
		MethodNameGeneric object = new MethodNameGeneric();

		useConsumer((x) -> x.zeroInZeroOut(), object);
		useConsumer(MethodNameGeneric::zeroInZeroOut, object);

		useBiConsumer((x, y) -> x.oneInZeroOut(y), object, "input");
		useBiConsumer(MethodNameGeneric::oneInZeroOut, object, "input");

		useTriConsumer((x, y, z) -> x.twoInZeroOut(y, z), object, "input1", "input2");
		useTriConsumer(MethodNameGeneric::twoInZeroOut, object, "input1", "input2");

		useFunction((x) -> x.zeroInOneOut(), object);
		useFunction(MethodNameGeneric::zeroInOneOut, object);

		useBiFunction((x, y) -> x.oneInOneOut(y), object, "def");
		useBiFunction(MethodNameGeneric::oneInOneOut, object, "def");

		useTriFunction((x, y, z) -> x.twoInOneOut(y, z), object, "def", "ghi");
		useTriFunction(MethodNameGeneric::twoInOneOut, object, "def", "ghi");
	}

	public static void useConsumer(Consumer<MethodNameGeneric> consumer, MethodNameGeneric object)
	{
		consumer.accept(object);
	}

	public static void useBiConsumer(	BiConsumer<MethodNameGeneric, String> consumer,
										MethodNameGeneric object,
										String input2)
	{
		consumer.accept(object, input2);
	}

	public static void useTriConsumer(	TriConsumer<MethodNameGeneric, String, String> consumer,
										MethodNameGeneric object,
										String input1,
										String input2)
	{
		consumer.accept(object, input1, input2);
	}

	public static void useFunction(	Function<MethodNameGeneric, String> function,
									MethodNameGeneric object)
	{
		System.out.println("Calling function  returned '" + function.apply(object) + "'");
	}

	public static void useBiFunction(	BiFunction<MethodNameGeneric, String, String> function,
										MethodNameGeneric object,
										String input)
	{
		System.out.println("Calling function with '"
							+ input
							+ "' returned '"
							+ function.apply(object, input)
							+ "'");
	}

	public static void useTriFunction(	TriFunction<MethodNameGeneric, String, String, String> function,
										MethodNameGeneric object,
										String input1,
										String input2)
	{
		System.out
			.println("Calling function with '"
						+ input1
						+ "' and '"
						+ input2
						+ "' returned '"
						+ function.apply(object, input1, input2)
						+ "'");
	}

	@FunctionalInterface
	public static interface TriConsumer<T, R, S>
	{
		void accept(T t, R r, S s);
	}

	@FunctionalInterface
	public static interface TriFunction<T, R, S, U>
	{
		U apply(T t, R r, S s);
	}
}
