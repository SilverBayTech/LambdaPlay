/*
 * Copyright (c) 2015 Kevin Hunter
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

package com.silverbaytech.blog.lambdaPlay;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;

import org.junit.Test;

public class DoubleGenericTest
{
	public static class AttributeDefinition<T, S>
	{
		private final String attribute;
		private final BiConsumer<T, S> setter;

		public AttributeDefinition(String attribute, BiConsumer<T, S> setter)
		{
			this.attribute = attribute;
			this.setter = setter;
		}

		public void apply(Map<String, Optional<S>> source, T dest)
		{
			Optional<S> value = source.get(attribute);

			if (value != null)
			{
				if (value.isPresent())
				{
					setter.accept(dest, value.get());
				}
				else
				{
					setter.accept(dest, null);
				}
			}
		}
	}

	public static class AttributeCopier<T, S>
	{
		private final List<AttributeDefinition<T, S>> definitions = new ArrayList<>();

		public AttributeCopier()
		{
		}

		public void addDefinition(String attribute, BiConsumer<T, S> setter)
		{
			definitions.add(new AttributeDefinition<T, S>(attribute, setter));
		}

		public void copy(Map<String, Optional<S>> source, T dest)
		{
			for (AttributeDefinition<T, S> definition : definitions)
			{
				definition.apply(source, dest);
			}
		}
	}

	@Test
	public void sampleBean_copyWorksCorrectly()
	{
		AttributeCopier<SampleBean, String> copier = new AttributeCopier<SampleBean, String>();
		copier.addDefinition("alpha", SampleBean::setAlpha);
		copier.addDefinition("bravo", SampleBean::setBravo);
		copier.addDefinition("charlie", SampleBean::setCharlie);

		HashMap<String, Optional<String>> source = new HashMap<>();
		source.put("alpha", Optional.of("newAlpha"));
		source.put("bravo", Optional.empty());

		SampleBean dest = new SampleBean();

		copier.copy(source, dest);

		assertThat(dest.getAlpha(), equalTo("newAlpha"));
		assertNull(dest.getBravo());
		assertThat(dest.getCharlie(), equalTo("defaultCharlie"));
	}

	@Test
	public void otherBean_copyWorksCorrectly()
	{
		AttributeCopier<OtherBean, String> copier = new AttributeCopier<OtherBean, String>();
		copier.addDefinition("delta", OtherBean::setDelta);
		copier.addDefinition("echo", OtherBean::setEcho);
		copier.addDefinition("foxtrot", OtherBean::setFoxtrot);

		HashMap<String, Optional<String>> source = new HashMap<>();
		source.put("delta", Optional.of("newDelta"));
		source.put("echo", Optional.empty());

		OtherBean dest = new OtherBean();

		copier.copy(source, dest);

		assertThat(dest.getDelta(), equalTo("newDelta"));
		assertNull(dest.getEcho());
		assertThat(dest.getFoxtrot(), equalTo("defaultFoxtrot"));
	}

	@Test
	public void integerBean_copyWorksCorrectly()
	{
		AttributeCopier<IntegerBean, Integer> copier = new AttributeCopier<IntegerBean, Integer>();
		copier.addDefinition("golf", IntegerBean::setGolf);
		copier.addDefinition("hotel", IntegerBean::setHotel);
		copier.addDefinition("india", IntegerBean::setIndia);

		HashMap<String, Optional<Integer>> source = new HashMap<>();
		source.put("golf", Optional.of(1));
		source.put("hotel", Optional.empty());

		IntegerBean dest = new IntegerBean();

		copier.copy(source, dest);

		assertThat(dest.getGolf(), equalTo(Integer.valueOf(1)));
		assertNull(dest.getHotel());
		assertThat(dest.getIndia(), equalTo(Integer.valueOf(-999)));
	}
}
