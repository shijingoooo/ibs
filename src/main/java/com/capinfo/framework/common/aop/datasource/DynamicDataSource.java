package com.capinfo.framework.common.aop.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.capinfo.framework.common.aop.datasource.DataSourceSwitcher;

public class DynamicDataSource extends AbstractRoutingDataSource {  
  
    @Override  
    protected Object determineCurrentLookupKey() {  
        return DataSourceSwitcher.getDataSource();  
    }  
  
}