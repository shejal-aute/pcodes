package DAA;

public class DAA1Fibonacci {
    public static void main(String[] args) {
        int n=10;
        for (int i = 0; i < n; i++) {
            System.out.print(fib(i)+" ");
        }
        System.out.println();
        fibIter(10);
    }
    public static int fib(int n){
        if(n<=1){
            return n;
        }
        return fib(n-1)+fib(n-2);
    }
    public static void fibIter(int n){
        int n1=0;
        int n2=1;
        int cnt=0;
        while (cnt<n){
            System.out.print(n1+" ");
            int n3=n1+n2;
            n1=n2;
            n2=n3;
            cnt++;
        }
    }
}
