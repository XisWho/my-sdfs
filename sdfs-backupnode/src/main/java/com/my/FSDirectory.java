package com.my;

import com.alibaba.fastjson.JSONObject;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FSDirectory {

    private INodeDirectory dirTree;

    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    /**
     * 当前文件目录树的更新到了哪个txid对应的editslog
     */
    private long maxTxid = 0;

    public FSDirectory() {
        dirTree = new INodeDirectory("/");
    }

    public void mkdir(long txid, String path) {
        // path = /usr/warehouse/hive
        // 你应该先判断一下，“/”根目录下有没有一个“usr”目录的存在
        // 如果说有的话，那么再判断一下，“/usr”目录下，有没有一个“/warehouse”目录的存在
        // 如果说没有，那么就得先创建一个“/warehosue”对应的目录，挂在“/usr”目录下
        // 接着再对“/hive”这个目录创建一个节点挂载上去
        //synchronized(dirTree) { // 内存数据结构，更新的时候必须得加锁的
        try {
            readWriteLock.writeLock().lock();

            maxTxid = txid;

            String[] pathes = path.split("/");
            INodeDirectory parent = dirTree;

            for (String splitedPath : pathes) { // ["","usr","warehosue","hive"]
                if (splitedPath.trim().equals("")) {
                    continue;
                }

                INodeDirectory dir = findDirectory(parent, splitedPath); // parent="/usr"

                if (dir != null) {
                    parent = dir;
                    continue;
                }

                INodeDirectory child = new INodeDirectory(splitedPath); // "/usr"
                parent.addChild(child);
                parent = child;
            }
        } finally {
            readWriteLock.writeLock().unlock();
        }
        //}

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

    public FSImage getFSImage() {
        FSImage fsimage = null;
        try {
            readWriteLock.readLock().lock();
            String fsimageJson = JSONObject.toJSONString(dirTree);
            // 在这个时刻，我们还需要知道，当前这份元数据，同步到的最大的txid是多少
            // 这样，我们才知道，这个fsimage是对应着txid等于多少的editslog，在这个txid之前的editslog都可以不需要了
            // 都可以删除了

            fsimage = new FSImage(maxTxid, fsimageJson);
        } finally {
            readWriteLock.readLock().unlock();
        }
        return fsimage;
    }

    interface INode {
    }

    class INodeDirectory implements INode {

        private String path;
        private List<INode> children;

        public INodeDirectory(String path) {
            this.path = path;
            this.children = new LinkedList<INode>();
        }

        public void addChild(INode inode) {
            this.children.add(inode);
        }
        public String getPath() {
            return path;
        }
        public void setPath(String path) {
            this.path = path;
        }
        public List<INode> getChildren() {
            return children;
        }
        public void setChildren(List<INode> children) {
            this.children = children;
        }

        @Override
        public String toString() {
            return "INodeDirectory [path=" + path + ", children=" + children + "]";
        }

    }

    class INodeFile implements INode {

        private String name;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "INodeFile [name=" + name + "]";
        }

    }

}
