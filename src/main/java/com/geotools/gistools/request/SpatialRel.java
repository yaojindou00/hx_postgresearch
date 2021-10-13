package com.geotools.gistools.request;

import java.io.Serializable;

/**
 * 功能描述：
 *
 * @Author: ddw
 * @Date: 2021/5/12 10:13
 */
public enum SpatialRel {
    /**
     * 脱节(Disjoint)：几何形状没有共有的点。
     */
    DISJOINT(1, "Disjoint", "脱节"),

    /**
     * 相交(Intersects)：几何形状至少有一个共有点（区别于脱节）
     */
    INTERSECTS(2, "Intersects", "相交"),

    /**
     * 接触(Touches)：几何形状有至少一个公共的边界点，但是没有内部点。
     */
    TOUCHES(3, "Touches", "接触"),

    /**
     * 交叉(Crosses)：几何形状共享一些但不是所有的内部点。
     */
    CROSSES(4, "Crosses", "交叉"),

    /**
     * 内含(Within)：几何形状A的线都在几何形状B内部。
     */
    WITHIN(5, "Within", "内含"),

    /**
     * 包含(Contains)：几何形状B的线都在几何形状A内部（区别于内含）
     */
    CONTAINS(6, "Contains", "包含"),

    /**
     * 重叠(Overlaps)：几何形状共享一部分但不是所有的公共点，而且相交处有他们自己相同的区域。
     */
    OVERLAPS(7, "Overlaps", "重叠");

    private int type = 2;

    private String name = null;

    private String label = null;

    SpatialRel(int type, String name, String label) {
        this.type = type;
        this.name = name;
        this.label = label;
    }

    public static SpatialRel fromName(String name) {
        if (name == null) {
            return null;
        }
        SpatialRel[] geometryRelations = SpatialRel.values();
        for (SpatialRel rel : geometryRelations) {
            if (rel.getName().equalsIgnoreCase(name)) {
                return rel;
            }
        }
        return null;
    }

    public static SpatialRel fromType(int type) {
        SpatialRel[] geometryRelations = SpatialRel.values();
        for (SpatialRel rel : geometryRelations) {
            if (rel.getType() == type) {
                return rel;
            }
        }
        return null;
    }

    public int getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public String getLabel() {
        return this.label;
    }

}
