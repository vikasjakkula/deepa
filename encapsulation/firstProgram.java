// class Student {
//     String name;
//     int age;

//     void showDetails() {
//         System.out.println("Name: " + name);
//         System.out.println("age: " + age);
//     }
// }

// public class firstProgram {
//     public static void main(String[] args) {
//         Student s1 = new Student();

//         s1.name = "vikas";
//         s1.age = 18;

//         Student s2 = new Student();
//         s2.name = "varun";
//         s2.age = 21;
//         s1.showDetails();
//         s2.showDetails();
//     }
// }


// class Animal {
//     void sound() {
//         System.out.println("Animal makes a sound");
//     }
// }

// class Dog extends Animal {
//     @Override                    // optional tag, but good practice
//     void sound() {
//         System.out.println("Dog barks");
//     }
// }

// class Cat extends Animal {
//     @Override
//     void sound() {
//         System.out.println("Cat meows");
//     }
// }

// public class Main {
//     public static void main(String[] args) {
//         // Parent reference holding child ob
//         Animal a1 = new Dog();
//         Animal a2 = new Cat();

//         a1.sound();     // Java picks Dog's
//         a2.sound();     // Java picks Cat's version at runtime

//         // Same code works for any animal
//         Animal[] animals = { new Dog(), new
//         for (Animal a : animals) {
//             a.sound();
//         }
//     }
// }
// }

// import java.util.*;

// public class firstProgram {
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         int a = sc.nextInt();
//         int b = sc.nextInt();

//         System.out.println(a + b);
//     }
// }

// import java.util.*;
// class main
// {
//     public static void main(String[] args)
//     {
//         int n;
//         Scanner sc = new Scanner(System.in);
//         System.out.println("Enter a number: ");
//         n = sc.nextInt();
//         int result = fact(n);
//         System.out.println("Factorial of "+ n + ": "+ result);
//         sc.close();
//     }
//     static int fact(int n)
//     {
//         int result = 1;
//         for(int i=1;i<=n;i++){
//             result=result*i;
//         }
//         return result;
//     }
// }