package Heap;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Heap1<T extends Comparable<T>> {
    private ArrayList<T> list;
    public Heap1(){
        list =new ArrayList<>();
    }
    public void swap(int first,int second){
            T temp= list.get(first);
            list.set(first,list.get(second));
            list.set(second,temp);
    }
    private  int parent(int index){
        return (index-1)/2;

    }
    public int size(){
        return list.size();
    }
    private int left (int index){
        return (index*2)+1;
    }
    private int right(int index){
        return (index*2)+2;
    }
    public void insert(T value){
        list.add(value);
        upHeap( list.size()-1);
    }
    private void upHeap(int index){
            if(index==0){
                return ;
            }
            int p=parent(index);
            if(list.get(index).compareTo(list.get(p))<0){
                swap(index,p);
                upHeap(p);
            }
    }
    public T remove() throws Exception{
        if(list.isEmpty()){
            throw  new Exception("Removing element from empty list");
        }
        T temp=list.get(0);
        T last=list.remove(list.size()-1);
        if(!list.isEmpty()){
            list.set(0,last);
            downheap(0);
        }
        return temp;

    }
    private void downheap(int index){
        int min=index;
        int left=left(index);
        int right =right(index);
        if(left <list.size() && list.get(min).compareTo(list.get(left))>0){
            min=left;
        }
        if(right <list.size() && list.get(min).compareTo(list.get(right))>0){
            min=right;
        }
        if(min!=index){
            swap(min,index);
            downheap(min);
        }
    }
    public ArrayList<T> heapSort() throws Exception{
        ArrayList<T> ans =new ArrayList<>();
        while (!list.isEmpty()){
            ans.add(this.remove());
        }
        return ans;
    }
    public static void main(String[] args) throws Exception {
            Heap1<Integer> heap =new Heap1<>();
            heap.insert(34);
            heap.insert(43);
            heap.insert(11);
            heap.insert(55);
            heap.insert(89);
//        System.out.println(heap.remove());
//        System.out.println(heap.remove());
//        System.out.println(heap.remove());
        ArrayList<Integer> ans=heap.heapSort();
        System.out.println(ans);
    }
}
