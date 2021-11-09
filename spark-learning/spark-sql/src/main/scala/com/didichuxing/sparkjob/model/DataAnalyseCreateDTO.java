package com.didichuxing.sparkjob.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建探查
 * @author luoxuehuan
 */
@Data
public class DataAnalyseCreateDTO {

    public Long getAnalyseId() {
        return analyseId;
    }

    public void setAnalyseId(Long analyseId) {
        this.analyseId = analyseId;
    }

    public String getAnalyseName() {
        return analyseName;
    }

    public void setAnalyseName(String analyseName) {
        this.analyseName = analyseName;
    }

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public String getPartitionName() {
        return partitionName;
    }

    public void setPartitionName(String partitionName) {
        this.partitionName = partitionName;
    }

    public Integer getSamplePercent() {
        return samplePercent;
    }

    public void setSamplePercent(Integer samplePercent) {
        this.samplePercent = samplePercent;
    }

    public List<AnalyseColumnConfig> getAnalyseColumnConfigList() {
        return analyseColumnConfigList;
    }

    public void setAnalyseColumnConfigList(List<AnalyseColumnConfig> analyseColumnConfigList) {
        this.analyseColumnConfigList = analyseColumnConfigList;
    }

    private Long analyseId;
    private String analyseName;
    private String storageName;
    private String storageType;
    private String partitionName;
    private Integer samplePercent;

    private List<AnalyseColumnConfig>  analyseColumnConfigList = new ArrayList<>();

}
