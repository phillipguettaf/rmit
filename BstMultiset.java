import java.io.PrintStream;
import java.util.*;

@SuppressWarnings("unused")
public class BstMultiset<T> extends Multiset<T>
{
   public BstNode<T> root;
   protected int count;
   
   public BstMultiset() {
      
   } // end of BstMultiset()

   public void add(T item) {
      
      BstNode<T> newNode = new BstNode<T>(item);
      
      if(root == null)
      {
         root = newNode;
         return;
      }
      
      BstNode<T> current = root;
      BstNode<T> parent = null;
      
      while(true)
      {
         String itemString = (String)item;
         String currentString = (String)current.item;
         parent = current;
         
         if(itemString.compareTo(currentString) == 0)
         {
            current.updateCount(current.getCount() + 1);
            return;
         }
         
         if(itemString.compareTo(currentString) < 0)
         {
            current = current.left;
            
            if(itemString.compareTo(currentString) == 0)
            {
               current.updateCount(current.getCount() + 1);
               return;
            }
            
            if(current == null)
            {
               parent.left = newNode;
               return;
            }
         }
         
         else
         {
            current = current.right;
            //String currentString = (String)current.item;

            if(itemString.compareTo(currentString) == 0)
            {
               current.updateCount(current.getCount() + 1);
               return;
            }
            if(current == null)
            {
               parent.right = newNode;
               return;
            }
         }
      }
   }
   // end of add()


   public int search(T item) {
      
      BstNode current = root;
      String itemString = (String)item;
      String currentString = (String)current.item;
      
      while(current != null) {
         
         if(currentString.compareTo(itemString) == 0)
         {
            return current.getCount();
         }
         
         else if (currentString.compareTo(itemString) > 0)
         {
            current = current.left;
         }
         
         else
         {
            current = current.right;
         }
      }

      return 0;
   } // end of search()


   public void removeOne(T item) {
      
      BstNode current = root;
      
      if (search(item) == 0)
      {
         System.out.println("here"); // this is always called
         return;
      }
      
      //if only one exists, remove node, else decrement element count
      if (search(item) == 1)
      {
         removeAll(item);
      }
      else
      {
         while(true) {
            
            String itemString = (String)item;
            String currentString = (String)current.item;
            
            if(currentString.equals(itemString))
            {
               current.updateCount(current.getCount() - 1);
               return;
            }
            
            else if (currentString.compareTo(itemString) < 0)
            {
               current = current.left;
            }
            
            else
            {
               current = current.right;
            }
         }
      }
   } // end of removeOne()
   
   
   public void removeAll(T item) {
      // Implement me!
   } // end of removeAll()
   

   public void getPrint(BstNode root)
   {
      BstNode<T> printNode = root;
      
      if(root != null)
      {
         getPrint(root.left);
         System.out.println(printNode.get() + " | " + printNode.getCount());
         getPrint(root.right);
      }
   }
   
   public void print(PrintStream out) {
      
      getPrint(root);
   }
   
   
} // end of print()

// end of class BstMultiset
