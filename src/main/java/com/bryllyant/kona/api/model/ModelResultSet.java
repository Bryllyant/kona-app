package com.bryllyant.kona.api.model;

import com.bryllyant.kona.data.model.KJsonModel;
import com.bryllyant.kona.data.model.KModel;
import com.bryllyant.kona.util.KResultList;
import org.bouncycastle.math.raw.Mod;

import java.util.List;

public class ModelResultSet<T extends KModel> extends KJsonModel {
    private Long totalSize;
    private Integer startIndex;
    private Integer endIndex;
    private Integer pageSize;
    private Integer currentPage;
    private Integer totalPages;
    private List<T> data;

    public static <T extends KModel> ModelResultSet from(KResultList items, List<T> data) {
        ModelResultSet<T>  result = new ModelResultSet<>();

        result.setTotalSize(items.getTotalSize());
        result.setStartIndex(items.getStartIndex());
        result.setEndIndex(items.getEndIndex());
        result.setPageSize(items.getPageSize());
        result.setCurrentPage(items.getCurrentPage());
        result.setTotalPages(items.getTotalPages());
        result.setData(data);

        return result;
    }

    public Long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Long totalSize) {
        this.set("totalSize", totalSize);
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.set("startIndex", startIndex);
    }

    public Integer getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(Integer endIndex) {
        this.set("endIndex", endIndex);
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.set("pageSize", pageSize);
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.set("currentPage", currentPage);
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.set("totalPages", totalPages);
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.set("data", data);
    }
}
