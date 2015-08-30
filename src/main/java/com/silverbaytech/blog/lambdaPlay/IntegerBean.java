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

public class IntegerBean
{
	private Integer golf = Integer.valueOf(-999);
	private Integer hotel = Integer.valueOf(-999);
	private Integer india = Integer.valueOf(-999);

	public IntegerBean()
	{
	}

	public Integer getGolf()
	{
		return golf;
	}

	public void setGolf(Integer Golf)
	{
		this.golf = Golf;
	}

	public Integer getHotel()
	{
		return hotel;
	}

	public void setHotel(Integer Hotel)
	{
		this.hotel = Hotel;
	}

	public Integer getIndia()
	{
		return india;
	}

	public void setIndia(Integer India)
	{
		this.india = India;
	}
}
