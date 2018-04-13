package com.quick.ext.primefaces.base.entity;

import org.eclipse.persistence.config.DescriptorCustomizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.history.HistoryPolicy;

/**
 *@TODO auto create hist table if not exists
 * @author Jason Chen
 */
public class HistoryCustomizer implements DescriptorCustomizer {
 
    @Override
    public void customize(ClassDescriptor descriptor) {        
        HistoryPolicy policy = new HistoryPolicy();        
        policy.addHistoryTableName( descriptor.getTableName()+"_HIST");
        policy.addStartFieldName("START_DATE");
        policy.addEndFieldName("END_DATE");
        descriptor.setHistoryPolicy(policy);
    }
}