package moa.clusterers.denstream;

import com.yahoo.labs.samoa.instances.DenseInstance;
import com.yahoo.labs.samoa.instances.Instance;

public class DenPoint extends DenseInstance {
	
	private static final long serialVersionUID = 1L;
	
	protected boolean covered;
	
	public DenPoint(Instance nextInstance, Long timestamp) {
		super(nextInstance);
		mId = ((DenseInstance)nextInstance).getInstanceId();
		//this.setDataset(nextInstance.dataset());
	}
}