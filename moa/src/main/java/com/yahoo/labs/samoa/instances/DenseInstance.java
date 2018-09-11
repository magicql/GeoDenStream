/*
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 	        http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the
 * License.  
 */
package com.yahoo.labs.samoa.instances;

import java.util.Date;

/**
 * The Class DenseInstance.
 */
public class DenseInstance extends InstanceImpl {
	protected int mId = -1;
	protected Date mTimeFlag;
	
    /**
     * Instantiates a new dense instance.
     *
     * @param weight the weight
     * @param res the res
     */
    public DenseInstance(double weight, double[] res) {
        super(weight, res);
    }

    /**
     * Instantiates a new dense instance.
     *
     * @param inst the inst
     */
    public DenseInstance(InstanceImpl inst) {
        super(inst);
    }

    /**
     * Instantiates a new dense instance.
     *
     * @param inst the inst
     */
    public DenseInstance(Instance inst) {
        super((InstanceImpl) inst);
    }

    /**
     * Instantiates a new dense instance.
     *
     * @param numberAttributes the number attributes
     */
    public DenseInstance(double numberAttributes) {
        super((int) numberAttributes);
    }
    
    public void setInstancId(int pId)
    {
    	mId = pId;
    }
    
    public int getInstanceId()
    {
    	return mId;
    }
    
    public void setTimeFlag(Date pTimeFlag)
    {
    	mTimeFlag = pTimeFlag;
    }
    
    public Date getTimeFlag()
    {
    	return mTimeFlag;
    }
}
