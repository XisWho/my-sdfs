package com.my;

import java.util.LinkedList;
import java.util.List;

public class INodeDirectory implements INode {

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
