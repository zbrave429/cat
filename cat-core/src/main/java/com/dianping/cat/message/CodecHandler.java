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
package com.dianping.cat.message;

import com.dianping.cat.message.spi.codec.MessageCodecTypeEnum;
import io.netty.buffer.ByteBuf;

import com.dianping.cat.message.spi.MessageCodec;
import com.dianping.cat.message.spi.MessageTree;
import com.dianping.cat.message.spi.codec.NativeMessageCodec;
import com.dianping.cat.message.spi.codec.PlainTextMessageCodec;

public class CodecHandler {

    private static final MessageCodec m_plainTextCodec = new PlainTextMessageCodec();

    private static final MessageCodec m_nativeCodec = new NativeMessageCodec();

    public static MessageTree decode(ByteBuf buf) {
        MessageTree tree;
        byte hint = buf.getByte(4);

        buf.resetReaderIndex();

        if (MessageCodecTypeEnum.PT1.getValue() == hint) {
            tree = m_plainTextCodec.decode(buf);
        } else if (MessageCodecTypeEnum.NT1.getValue() == hint) {
            tree = m_nativeCodec.decode(buf);
        } else {
            throw new RuntimeException("Error message type : " + hint);
        }

        MessageTreeFormat.format(tree);
        return tree;
    }

    public static void reset() {
        m_plainTextCodec.reset();
        m_nativeCodec.reset();
    }

}
