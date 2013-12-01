/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doubleconnectedlist;
import java.io.*;
import java.util.*;
import java.lang.*;
//import java.lang.reflect.;
/**
 *
 * @author Daniel
 */
public class DoubleConnectedList {

       
    private class Node{
      Object element;
      Node next;
      Node prev;
      
      Node(Object element){
          this.element = element;
          next = null;
          prev = null;
      }
      Node(Object element, Node prev){
          this.element = element;
          prev.next = this;
      }
    }
    private Node head;
    private Node tail;
    private int count;
    
    public DoubleConnectedList(){
        this.head = null;
        this.tail = null;
        this.count = 0;
    }
    public void add(Object item){
        if (head == null){
            //we have empty list
            head = new Node(item);
            tail = head;
        } else {
            //we have non-empty list
            Node newNode = new Node(item, tail);
            tail = newNode;
        }
        count++;
    }
    public Object remove(int index){
        if (index>=count || index<0 ){
           throw new IndexOutOfBoundsException("Invalid index:"+index); 
        }
        //find the element at the specified index
        int currentIndex = 0;
        Node currentNode = head;
        currentNode.prev = null;
        while(currentIndex < index){
            currentNode.prev = currentNode;
            currentNode = currentNode.next;
            currentIndex++;            
        }
        //remove the element
        count--;
        if (count == 0){
            head = null;
            tail = null;            
        } else if( currentNode.prev == null){
            head = currentNode.next;            
        } else {
            currentNode.prev.next = currentNode.next;
        }
        return currentNode.element;
    }
    public int remove(Object item){
        int currentIndex = 0;
        Node currentNode = head;
        currentNode.prev = null; 
        while(currentNode != null){
            if ((currentNode.element != null && currentNode.element.equals(item)) ||
                    (currentNode.element == null) && (item == null)){
                break;
            }
            currentNode.prev = currentNode;
            currentNode = currentNode.next;
            currentIndex++;
        }
        if (currentNode != null){
            count--;
            if (count == 0){
                head = null;
                tail = null;                
            } else if(currentNode.prev == null){
                head = currentNode.next;                
            } else {
                currentNode.prev.next = currentNode.next;
            }
            return currentIndex;
        } else {
            return -1;
        }
    }
      /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DoubleConnectedList listOfBeer = new DoubleConnectedList();
        listOfBeer.add("Kamenitza");
        listOfBeer.add("Astika");
        listOfBeer.add("Tuborg");
        listOfBeer.add("Pirinsko");  
        listOfBeer.remove("Tuborg");
        System.out.println("We have these beers:");
        for (int i=0; i < listOfBeer.getLength(); i++){
            System.out.println(listOfBeer.elementAt(i));
        }
        
       
    }
}
