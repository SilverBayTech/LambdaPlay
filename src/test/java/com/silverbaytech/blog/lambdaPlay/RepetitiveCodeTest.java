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

import org.junit.Test;

public class RepetitiveCodeTest
{
	public static class AttributeCopier
	{
		public void copy(Map<String, Optional<String>> source, SampleBean dest)
		{
			Optional<String> alpha = source.get("alpha");
			if (alpha != null)
			{
				if (alpha.isPresent())
				{
					dest.setAlpha(alpha.get());
				}
				else
				{
					dest.setAlpha(null);
				}
			}

			Optional<String> bravo = source.get("bravo");
			if (bravo != null)
			{
				if (bravo.isPresent())
				{
					dest.setBravo(bravo.get());
				}
				else
				{
					dest.setBravo(null);
				}
			}

			Optional<String> charlie = source.get("charlie");
			if (charlie != null)
			{
				if (charlie.isPresent())
				{
					dest.setCharlie(charlie.get());
				}
				else
				{
					dest.setCharlie(null);
				}
			}
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
