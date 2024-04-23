package com.my;

import com.alibaba.fastjson.JSONObject;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FSDirectory {

    private INode dirTree;

    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    /**
     * 当前文件目录树的更新到了哪个txid对应的editslog
     */
    private long maxTxid = 0;

    public FSDirectory() {
        dirTree = new INode("/");
    }

    public INode getDirTree() {
        return dirTree;
    }

    public void setDirTree(INode dirTree) {
        this.dirTree = dirTree;
    }

    public long getMaxTxid() {
        return maxTxid;
    }

    public void setMaxTxid(long maxTxid) {
        this.maxTxid = maxTxid;
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
            INode parent = dirTree;

            for (String splitedPath : pathes) { // ["","usr","warehosue","hive"]
                if (splitedPath.trim().equals("")) {
                    continue;
                }

                INode dir = findDirectory(parent, splitedPath); // parent="/usr"

                if (dir != null) {
                    parent = dir;
                    continue;
                }

                INode child = new INode(splitedPath); // "/usr"
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
     * 创建文件
     * @param filename 文件名
     * @return
     */
    public Boolean createFile(long txid, String filename) {
        // /image/product/img001.jpg
        // 其实完全可以把前面的路径部分截取出来，去找对应的目录
        try {
            readWriteLock.writeLock().lock();

            maxTxid = txid;

            String[] splitedFilename = filename.split("/");
            String realFilename = splitedFilename[splitedFilename.length - 1];

            INode parent = dirTree;

            for(int i = 0; i < splitedFilename.length - 1; i++) {
                if(i == 0) {
                    continue;
                }

                INode dir = findDirectory(parent, splitedFilename[i]);

                if(dir != null) {
                    parent = dir;
                    continue;
                }

                INode child = new INode(splitedFilename[i]);
                parent.addChild(child);
                parent = child;
            }

            // 此时就已经获取到了文件的上一级目录
            // 可以查找一下当前这个目录下面是否有对应的文件了
            if(existFile(parent, realFilename)) {
                return false;
            }

            // 真正的在目录里创建一个文件出来
            INode file = new INode(realFilename);
            parent.addChild(file);
            return true;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    private Boolean existFile(INode dir, String filename) {
        if(dir.getChildren() != null && dir.getChildren().size() > 0) {
            for(INode child : dir.getChildren()) {
                if(child.getPath().equals(filename)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 查找子目录
     * @param dir
     * @param path
     * @return
     */
    private INode findDirectory(INode dir, String path) {
        if(dir.getChildren().size() == 0) {
            return null;
        }

        for(INode child : dir.getChildren()) {
            if(child instanceof INode) {
                INode childDir = (INode) child;
                if((childDir.getPath().equals(path))) {
                    return childDir;
                }
            }
        }

        return null;
    }

    private void printDirTree(INode dirTree, String blank) {
        if(dirTree.getChildren().size() == 0) {
            return;
        }
        for(INode dir : dirTree.getChildren()) {
            System.out.println(blank + ((INode) dir).getPath());
            printDirTree((INode) dir, blank + " ");
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

    public static class INode {

        private String path;
        private List<INode> children;

        public INode() {}

        public INode(String path) {
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

}
