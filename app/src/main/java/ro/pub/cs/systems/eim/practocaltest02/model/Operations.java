package ro.pub.cs.systems.eim.practocaltest02.model;

public class Operations {
    private int operator1 = 0;
    private int operator2 = 0;

    public Operations(int operator1, int operator2) {
        this.operator1 = operator1;
        this.operator2 = operator2;
    }

    public int add(){
        return operator1 + operator2;
    }

    public int mul() {
        return operator1 * operator2;
    }

    public int getOperator1() {
        return operator1;
    }

    public int getOperator2() {
        return operator2;
    }

    public void setOperator1(int operator1) {
        this.operator1 = operator1;
    }

    public void setOperator2(int operator2) {
        this.operator2 = operator2;
    }
}
