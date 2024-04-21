package com.my;

public class FSDirectory {

    private INodeDirectory dirTree;

    public FSDirectory() {
        dirTree = new INodeDirectory("/");
    }

    public void mkdir(String path) {
        // path = /usr/warehouse/hive
        // 你应该先判断一下，“/”根目录下有没有一个“usr”目录的存在
        // 如果说有的话，那么再判断一下，“/usr”目录下，有没有一个“/warehouse”目录的存在
        // 如果说没有，那么就得先创建一个“/warehosue”对应的目录，挂在“/usr”目录下
        // 接着再对“/hive”这个目录创建一个节点挂载上去
        synchronized(dirTree) { // 内存数据结构，更新的时候必须得加锁的
            String[] pathes = path.split("/");
            INodeDirectory parent = dirTree;

            for(String splitedPath : pathes) { // ["","usr","warehosue","hive"]
                if(splitedPath.trim().equals("")) {
                    continue;
                }

                INodeDirectory dir = findDirectory(parent, splitedPath); // parent="/usr"

                if(dir != null) {
                    parent = dir;
                    continue;
                }

                INodeDirectory child = new INodeDirectory(splitedPath); // "/usr"
                parent.addChild(child);
                parent = child;
            }
        }

        // printDirTree(dirTree, "");
    }

    /**
     * 查找子目录
     * @param dir
     * @param path
     * @return
     */
    private INodeDirectory findDirectory(INodeDirectory dir, String path) {
        if(dir.getChildren().size() == 0) {
            return null;
        }

        for(INode child : dir.getChildren()) {
            if(child instanceof INodeDirectory) {
                INodeDirectory childDir = (INodeDirectory) child;
                if((childDir.getPath().equals(path))) {
                    return childDir;
                }
            }
        }

        return null;
    }

    private void printDirTree(INodeDirectory dirTree, String blank) {
        if(dirTree.getChildren().size() == 0) {
            return;
        }
        for(INode dir : dirTree.getChildren()) {
            System.out.println(blank + ((INodeDirectory) dir).getPath());
            printDirTree((INodeDirectory) dir, blank + " ");
        }
    }

}
