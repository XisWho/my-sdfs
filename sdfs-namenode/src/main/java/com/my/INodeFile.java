package com.my;

public class INodeFile implements INode {

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
