package example;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemManager;
import example.MockupStockDB;
import java.util.Map;
import java.util.HashMap;
/**
 * This class was automatically generated by the data modeler tool.
 */

/**
 *  In real projects, WorkItemHandler implementation is packaged separately (in a simple jar)
 *  You first need to upload it into Authoring -> Artifact Repository
 *  Once done you need to add it on the classpath of your KJAR (project in business-central)
 *  by navigating to Authoring->Project Authoring-> <your project-> Open Project Editor->Project Settings->Dependencies
 *  Make me proud, and in the final projects, do it like that!
 */
 
public class StockDBHandler implements java.io.Serializable, WorkItemHandler {

    static final long serialVersionUID = 1L;
    
    public void	abortWorkItem(WorkItem workItem, WorkItemManager manager) {
        
    }

public void	executeWorkItem(WorkItem workItem, WorkItemManager manager) {
    
    //this is how you can access input parameters
    String key = (String)workItem.getParameter("ProductId");
    Integer requestedAmount = (Integer) workItem.getParameter("amount");
    
    //decide whether requested product is on stock or not
    Boolean inStock = MockupStockDB.getStockStatus(key,requestedAmount);
    
    
    //this is how you can construct workitem output
    Map<String,Object> results = new HashMap<String,Object>();
    results.put("inStock",inStock);
    
    if (inStock) {
        System.out.println("Product:"+key+" is in stock and we will decrease the stock amount");
        MockupStockDB.decreaseStockStatus(key,requestedAmount);
    }
    else
        System.out.println("Product:"+key+" is not in stock");

//you always need to call complete method, otherwise your process will not proceed!
    manager.completeWorkItem(workItem.getId(), results);

}


    public StockDBHandler() {
    }

}