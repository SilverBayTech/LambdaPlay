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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;

import org.junit.Test;

public class MethodNameTest
{
	public static class AttributeCopier
	{
		private void setAttribute(	SampleBean dest,
									Map<String, Optional<String>> source,
									String attribute,
									BiConsumer<SampleBean, String> lambda)
		{
			Optional<String> value = source.get(attribute);

			if (value != null)
			{
				if (value.isPresent())
				{
					lambda.accept(dest, value.get());
				}
				else
				{
					lambda.accept(dest, null);
				}
			}
		}

		public void copy(Map<String, Optional<String>> source, SampleBean dest)
		{
			setAttribute(dest, source, "alpha", SampleBean::setAlpha);
			setAttribute(dest, source, "bravo", SampleBean::setBravo);
			setAttribute(dest, source, "charlie", SampleBean::setCharlie);
		}
	}

	@Test
	public void copyWorksCorrectly()
	{
		AttributeCopier copier = new AttributeCopier();

		HashMap<String, Optional<String>> source = new HashMap<>();
		source.put("alpha", Optional.of("newAlpha"));
		source.put("bravo", Optional.empty());

		SampleBean dest = new SampleBean();

		copier.copy(source, dest);

		assertThat(dest.getAlpha(), equalTo("newAlpha"));
		assertNull(dest.getBravo());
		assertThat(dest.getCharlie(), equalTo("defaultCharlie"));
	}
}
