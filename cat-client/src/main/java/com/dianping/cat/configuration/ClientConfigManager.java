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
package com.dianping.cat.configuration;

import com.dianping.cat.configuration.client.entity.Domain;
import com.dianping.cat.configuration.client.entity.Server;
import com.dianping.cat.message.spi.MessageTree;

import java.io.File;
import java.util.List;

public interface ClientConfigManager {

    public Domain getDomain();

    public int getMaxMessageLength();

    public String getRouters();

    public double getSampleRatio();

    /**
     * 获取服务端配置
     * @return
     */
    List<Server> getServers();

    public int getTaggedTransactionCacheSize();

    public void initialize(File configFile) throws Exception;

    /**
     * 判断是否原子消息，如果是原子消息则放入原子消息队列，合并后发布，否则直接放到发送队列
     *
     * @param tree 消息
     * @return bool
     */
    boolean isAtomicMessage(MessageTree tree);

    /**
     * 检查是否拦截发送
     * 可以通过服务端routerConfig配置
     *
     * @return bool
     */
    boolean isBlock();

    public boolean isCatEnabled();

    public boolean isDumpLocked();

    public void refreshConfig();

    public int getLongThresholdByDuration(String key, int duration);

}