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
package com.dianping.cat.message.spi;

public interface MessageQueue {

	/**
	 * 向队列尾部添加元素
	 *
	 * @param tree 元素
	 * @return true - 添加成功， false-添加失败，队列可能已满
	 */
	boolean offer(MessageTree tree);

	/**
	 * 检索但不删除此队列的头
	 *
	 * @return 返回队首元素
	 */
	MessageTree peek();

	/**
	 * 检索并移除此队列的头
	 *
	 * @return 返回队首元素
	 */
	MessageTree poll();

	// the current size of the queue
	int size();
}
