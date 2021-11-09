package com.didichuxing.sparkjob.model;

import lombok.Data;

/**
 * @author didi
 */
@Data
public class AnalyseColumnConfig {

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public Integer getBaseStatistics() {
        return baseStatistics;
    }

    public void setBaseStatistics(Integer baseStatistics) {
        this.baseStatistics = baseStatistics;
    }

    public Integer getAdvancedStatistics() {
        return advancedStatistics;
    }

    public void setAdvancedStatistics(Integer advancedStatistics) {
        this.advancedStatistics = advancedStatistics;
    }

    public Integer getDataDistribution() {
        return dataDistribution;
    }

    public void setDataDistribution(Integer dataDistribution) {
        this.dataDistribution = dataDistribution;
    }

    private String columnName;
    private String columnType;
    private Integer baseStatistics;
    private Integer advancedStatistics;
    private Integer dataDistribution;
}
