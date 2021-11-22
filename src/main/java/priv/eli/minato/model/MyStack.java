package priv.eli.minato.model;

import java.util.Arrays;

/**
 * @author Eli
 * @date 2020/3/25
 * 自己实现一个栈，要求栈实现 push(), pop(), peek(), isEmpty(), size()等这些基本方法。
 */
public class MyStack {

    // 栈内信息数组
    private int[] table;

    // 栈内数据总量
    private int count;

    // 栈的容量
    private int capacity;

    // 每次扩容都得
    private static final int GROW_FACTOR = 2;

    // 默认初始化大小
    private static final int DEFAILT_INITALCAPACITY = 8;


    public MyStack() {
        this.table = new int[DEFAILT_INITALCAPACITY];
        this.capacity = DEFAILT_INITALCAPACITY;
    }

    public MyStack(int initalCapacity) {
        if (initalCapacity < DEFAILT_INITALCAPACITY) {
            initalCapacity = DEFAILT_INITALCAPACITY;
        }
        this.table = new int[initalCapacity];
        this.capacity = initalCapacity;
    }


    public void reSize() {
        int newInitalCapacity = this.capacity * GROW_FACTOR;
        this.table = Arrays.copyOf(table, newInitalCapacity);
        this.capacity = newInitalCapacity;
    }


    public void push(int element) {
        if (count >= capacity) {
            reSize();
        }
        table[count++] = element;
    }


    public int pop() {
        if (this.count == 0) {
            throw new IllegalArgumentException("Stack is empty");
        }
        return table[--count];
    }


    public int peek() {
        if (this.count == 0) {
            throw new IllegalArgumentException("Stack is empty");
        }
        return table[count - 1];
    }


    public boolean isEmpty() {
        return this.count == 0;
    }

    public int size() {
        return this.count;
    }

    @Override
    public String toString() {

        StringBuffer sb = new StringBuffer("[");
        for (int i : table) {
            sb.append(i).append(", ");
        }
        sb.substring(0, sb.length() - 2);

        sb.append("]");
        return sb.toString();

    }


    public static void main(String[] args) {
        MyStack myStack = new MyStack();

        for (int i = 1; i < 15; i++) {
            myStack.push(i);
        }
        System.out.println(myStack.toString());

        System.out.println(myStack.size());


    }


}
