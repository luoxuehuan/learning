package com.didichuxing.sparkjob.main

import com.didichuxing.sparkjob.model.{AnalyseColumnConfig, DataAnalyseCreateDTO, Task}

object MockData {

  def getTestDataAnalyse():DataAnalyseCreateDTO = {
    val dataAnalyseCreateDTO = new DataAnalyseCreateDTO
    dataAnalyseCreateDTO.setStorageName("data_search_test.test_table_0")
    dataAnalyseCreateDTO.setPartitionName("ds=20211101")
    val  columnConfig = new AnalyseColumnConfig
    columnConfig.setColumnName("age");
    columnConfig.setColumnType("bigint")
    columnConfig.setBaseStatistics(1)
    columnConfig.setAdvancedStatistics(1)
    columnConfig.setDataDistribution(1)
    dataAnalyseCreateDTO.getAnalyseColumnConfigList.add(columnConfig)

    val  columnConfig2 = new AnalyseColumnConfig
    columnConfig2.setColumnName("name");
    columnConfig2.setColumnType("string")
    columnConfig2.setBaseStatistics(1)
    columnConfig2.setAdvancedStatistics(1)
    columnConfig2.setDataDistribution(1)
    dataAnalyseCreateDTO.getAnalyseColumnConfigList.add(columnConfig2)

    val  columnConfig3 = new AnalyseColumnConfig
    columnConfig3.setColumnName("id");
    columnConfig3.setBaseStatistics(1)
    columnConfig3.setAdvancedStatistics(1)
    columnConfig3.setDataDistribution(1)
    dataAnalyseCreateDTO.getAnalyseColumnConfigList.add(columnConfig3)

    dataAnalyseCreateDTO
  }


  def getTaskConfig():Task = {
    val task = new Task()
    task.setAnalyseId(111L)
    task.setCallBackUri("http://localhost:8080/dataMap/api/admin/report_data_analyse_result")
    task.setDataAnalyseCreateDTO(getTestDataAnalyse())
    task
  }



}
