package com.kotabek.to;

import com.kotabek.utils.StringUtils;

/**
 * Created by kotabek on 6/1/17.
 */
public class AttachmentFilterTO {
    private String name;
    private Long minSize;
    private Long maxSize;
    private Long afterTime;
    private Long beforeTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMinSize() {
        return minSize;
    }

    public void setMinSize(Long minSize) {
        this.minSize = minSize;
    }

    public Long getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Long maxSize) {
        this.maxSize = maxSize;
    }

    public void setAfterTime(Long afterTime) {
        this.afterTime = afterTime;
    }

    public Long getAfterTime() {
        return afterTime;
    }

    public void setBeforeTime(Long beforeTime) {
        this.beforeTime = beforeTime;
    }

    public Long getBeforeTime() {
        return beforeTime;
    }

    public boolean isEmpty() {
        return StringUtils.isEmpty(name)
               && minSize == null
               && maxSize == null
               && afterTime == null
               && beforeTime == null;
    }
}
