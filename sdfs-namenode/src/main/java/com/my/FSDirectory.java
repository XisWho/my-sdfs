package com.my;

import java.util.LinkedList;
import java.util.List;

public class FSDirectory {

    private INode dirTree;

    public FSDirectory() {
        dirTree = new INode("/");
    }

    public INode getDirTree() {
        return dirTree;
    }
    public void setDirTree(INode dirTree) {
        this.dirTree = dirTree;
    }

    public void mkdir(String path) {
        // path = /usr/warehouse/hive
        // 你应该先判断一下，“/”根目录下有没有一个“usr”目录的存在
        // 如果说有的话，那么再判断一下，“/usr”目录下，有没有一个“/warehouse”目录的存在
        // 如果说没有，那么就得先创建一个“/warehosue”对应的目录，挂在“/usr”目录下
        // 接着再对“/hive”这个目录创建一个节点挂载上去
        synchronized(dirTree) { // 内存数据结构，更新的时候必须得加锁的
            String[] pathes = path.split("/");
            INode parent = dirTree;

            for(String splitedPath : pathes) { // ["","usr","warehosue","hive"]
                if(splitedPath.trim().equals("")) {
                    continue;
                }

                INode dir = findDirectory(parent, splitedPath); // parent="/usr"

                if(dir != null) {
                    parent = dir;
                    continue;
                }

                INode child = new INode(splitedPath); // "/usr"
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
    private INode findDirectory(INode dir, String path) {
        if(dir.getChildren().size() == 0) {
            return null;
        }

        for(INode child : dir.getChildren()) {
            if(child instanceof INode) {
                INode childDir = child;
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
