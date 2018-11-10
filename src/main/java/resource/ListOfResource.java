package resource;

import job.Process;

import java.util.ArrayList;
import java.util.List;

public class ListOfResource {
    private List<Resource> resourceList;

    public ListOfResource(List<Resource> resourceList) {
        this.resourceList = resourceList;
    }

    /**
     * 引数のプロセスが実行可能なリソースがあるかの判定
     * @param process
     * @return リソースがあればtrue，なければfalseを返す
     */
    public boolean isAbleResource(Process process){
        if (getAbleResource(process) == null){
            return false;
        }
        return true;
    }


    /**
     * 引数のプロセスが可能なリソースを返す
     * 一度Listにしているのは，複数存在してときへの対応を考えた為
     * @param process
     * @return
     */
    public Resource getAbleResource(Process process){
        List<Resource> foundResourceList = new ArrayList<>();
        for (Resource resource: this.resourceList){
            //可能なリソースが存在すれば追加する
            if (resource.isAble(process)){
                foundResourceList.add(resource);
            }
        }
        if (foundResourceList.isEmpty()){
            return null;
        }
        return foundResourceList.get(0);
    }

    /**
     * プロセスがセットされているリソースはプロセスの処理を行う
     * @param t
     */
    public void doProcess(int t){
        for (Resource resource: this.resourceList){
            if (resource.getProcess() != null && resource.getProcess().isIn())
            resource.doProcess(t);
        }
    }

    public List<Resource> getResourceList() {
        return resourceList;
    }


}
