/*
 * Copyright (c) 2011-2018, Meituan Dianping. All Rights Reserved.
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dianping.cat.analysis;

/**
 *
 * 周期开始结束时间点
 *        start_time         end_time
 *            |                 |
 * -----------|-------...-------|----------
 * 		 ^                 ^     ^-------remove
 * 	   create     next period create
 * (提前3分钟创建下个周期)     (延迟3分钟结束上一个周期)
 *
 */
public class PeriodStrategy {
	/**
	 * 周期持续时间（1小时）
	 */
	private long m_duration;

	/**
	 * 周期结束延迟时间
	 */
	private long m_extraTime;

	/**
	 * 周期提前开始时间
	 */
	private long m_aheadTime;

	/**
	 * 周期开始时间（小时）
	 */
	private long m_lastStartTime;

	private long m_lastEndTime;

	public PeriodStrategy(long duration, long extraTime, long aheadTime) {
		m_duration = duration;
		m_extraTime = extraTime;
		m_aheadTime = aheadTime;
		m_lastStartTime = -1;
		m_lastEndTime = 0;
	}

	public long getDuration() {
		return m_duration;
	}

	public long next(long now) {
		long startTime = now - now % m_duration;

		// for current period
		if (startTime > m_lastStartTime) {
			m_lastStartTime = startTime;
			return startTime;
		}

		// prepare next period ahead，57min
		if (now - m_lastStartTime >= m_duration - m_aheadTime) {
			m_lastStartTime = startTime + m_duration;
			return startTime + m_duration;
		}

		// last period is over   63
		if (now - m_lastEndTime >= m_duration + m_extraTime) {
			long lastEndTime = m_lastEndTime;
			m_lastEndTime = startTime;
			return -lastEndTime;
		}

		return 0;
	}
}