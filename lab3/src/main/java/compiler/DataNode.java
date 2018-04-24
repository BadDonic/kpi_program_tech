package compiler;

public class DataNode extends Node {
    private Object data;

    public DataNode(Object value) {
        data = value;
    }

    public Object value() {
        return data;
    }
}
