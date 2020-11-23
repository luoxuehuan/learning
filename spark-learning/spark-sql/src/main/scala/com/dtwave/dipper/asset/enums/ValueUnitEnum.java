package com.dtwave.dipper.asset.enums;


/**
 * @author 棠汐 on 2019-06-13.
 */
public enum ValueUnitEnum {
    STRIP(5, "条"),

    KB(1, "KB"),

    MB(2, "MB"),
    GB(3, "GB"),
    TB(4, "TB");

    /**
     * 类型
     */
    private Integer type;

    /**
     * 类型名称
     */
    private String typeName;


    ValueUnitEnum(Integer type, String typeName){
        this.type = type;
        this.typeName = typeName;
    }

    public Integer getType(){
        return this.type;
    }

    public String getTypeName(){
        return this.typeName;
    }


    public static String getNameByType(Integer type) {
        for (ValueUnitEnum typeEnum : ValueUnitEnum.values()) {
            if (typeEnum.getType().equals(type)) {
                return typeEnum.getTypeName();
            }
        }
        return null;
    }

    public static ValueUnitEnum getEnum(Integer type) {
        for (ValueUnitEnum typeEnum : ValueUnitEnum.values()) {
            if (typeEnum.getType().equals(type)) {
                return typeEnum;
            }
        }
        return null;
    }
}
