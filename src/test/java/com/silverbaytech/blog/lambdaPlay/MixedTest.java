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

public class MixedTest
{
	public static class AttributeDefinition<T, S>
	{
		private final String attribute;
		private final BiConsumer<T, S> setter;

		public AttributeDefinition(Class<S> clazz, String attribute, BiConsumer<T, S> setter)
		{
			this.attribute = attribute;
			this.setter = setter;
		}

		public void apply(Map<String, Optional<?>> source, T dest)
		{
			Optional<?> value = source.get(attribute);

			if (value != null)
			{
				if (value.isPresent())
				{
					@SuppressWarnings("unchecked")
					S nonNullValue = (S)value.get();
					setter.accept(dest, nonNullValue);
				}
				else
				{
					setter.accept(dest, null);
				}
			}
		}
	}

	public static class AttributeCopier<T>
	{
		private final List<AttributeDefinition<T, ?>> definitions = new ArrayList<>();

		public AttributeCopier()
		{
		}

		public <S> void addDefinition(Class<S> clazz, String attribute, BiConsumer<T, S> setter)
		{
			definitions.add(new AttributeDefinition<T, S>(clazz, attribute, setter));
		}

		public void copy(Map<String, Optional<?>> source, T dest)
		{
			for (AttributeDefinition<T, ?> definition : definitions)
			{
				definition.apply(source, dest);
			}
		}
	}

	@Test
	public void mixedBean_copyWorksCorrectly()
	{
		AttributeCopier<MixedBean> copier = new AttributeCopier<MixedBean>();
		copier.addDefinition(String.class, "alpha", MixedBean::setAlpha);
		copier.addDefinition(String.class, "bravo", MixedBean::setBravo);
		copier.addDefinition(String.class, "charlie", MixedBean::setCharlie);
		copier.addDefinition(Integer.class, "golf", MixedBean::setGolf);
		copier.addDefinition(Integer.class, "hotel", MixedBean::setHotel);
		copier.addDefinition(Integer.class, "india", MixedBean::setIndia);

		HashMap<String, Optional<?>> source = new HashMap<>();
		source.put("alpha", Optional.of("newAlpha"));
		source.put("bravo", Optional.empty());
		source.put("golf", Optional.of(1));
		source.put("hotel", Optional.empty());

		MixedBean dest = new MixedBean();

		copier.copy(source, dest);

		assertThat(dest.getAlpha(), equalTo("newAlpha"));
		assertNull(dest.getBravo());
		assertThat(dest.getCharlie(), equalTo("defaultCharlie"));
		assertThat(dest.getGolf(), equalTo(Integer.valueOf(1)));
		assertNull(dest.getHotel());
		assertThat(dest.getIndia(), equalTo(Integer.valueOf(-999)));
	}

	@Test(expected = ClassCastException.class)
	public void wrongValueType_copyWorksCorrectly()
	{
		AttributeCopier<MixedBean> copier = new AttributeCopier<MixedBean>();
		copier.addDefinition(String.class, "alpha", MixedBean::setAlpha);

		HashMap<String, Optional<?>> source = new HashMap<>();
		source.put("alpha", Optional.of(1));

		MixedBean dest = new MixedBean();

		copier.copy(source, dest);
	}
}
