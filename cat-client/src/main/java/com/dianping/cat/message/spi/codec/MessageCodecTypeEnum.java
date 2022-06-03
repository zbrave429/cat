package com.dianping.cat.message.spi.codec;

/**
 * codec type
 *
 * @author <a href='1286998496@qq.com'>zhangyong</a>
 * @date 2022-06-03 11:24
 */
public enum MessageCodecTypeEnum {

    PT1((byte) 1, "PT1"),
    NT1((byte) 2, "NT1"),
    ;

    MessageCodecTypeEnum(byte value, String code) {
        this.value = value;
        this.code = code;
    }

    private final byte value;

    private final String code;

    public byte getValue() {
        return value;
    }

    public String getCode() {
        return code;
    }
}
