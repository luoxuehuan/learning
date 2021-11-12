package com.didichuxing.sparkjob.model;

import lombok.Data;

@Data
public class Task {
    public Long getAnalyseId() {
        return analyseId;
    }

    public void setAnalyseId(Long analyseId) {
        this.analyseId = analyseId;
    }

    public DataAnalyseCreateDTO getDataAnalyseCreateDTO() {
        return dataAnalyseCreateDTO;
    }

    public void setDataAnalyseCreateDTO(DataAnalyseCreateDTO dataAnalyseCreateDTO) {
        this.dataAnalyseCreateDTO = dataAnalyseCreateDTO;
    }

    public String getCallBackUri() {
        return callBackUri;
    }

    public void setCallBackUri(String callBackUri) {
        this.callBackUri = callBackUri;
    }

    private Long analyseId;
    private DataAnalyseCreateDTO dataAnalyseCreateDTO;
    private String callBackUri;
}
