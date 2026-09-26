// packages are two types built-in & User-defined package
// lang default package
// io
// Util
// Abstract window tool kit
package calculator;
public class Arithmatic {
        double d1, d2;
        public Arithmatic(double d1, double d2) {
            this.d1 = d1;
            this.d2 = d2;
        }
    public void add()
        {
            System.out.println("add of two num ="+(d1+d2));
        }
    public void sub()
        {
            System.out.println("sub of two num ="+(d1-d2));
        }
        public void mul()
        {
            System.out.println("mul of two num ="+(d1*d2));
        }
        public void div()
        {
            System.out.println("div of two num ="+(d1/d2));
        }
        public void per()
        {
            System.out.println("per of two num ="+(d1%d2));
        }
}