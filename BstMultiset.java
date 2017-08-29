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

      
      while(current != null) {
         
         String itemString = (String)item;
         String currentString = (String)current.item;
         
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
      
      if(root == null)
      {
         return;
      }
      
      
      BstNode<T> current = root;
      
      while(true)
      {
         String itemString = (String)item;
         String currentString = (String)current.item;
         
         if(itemString.compareTo(currentString) == 0)
         {
            current.updateCount(current.getCount() - 1);
            return;
         }
         
         if(itemString.compareTo(currentString) < 0)
         {
            current = current.left;
            
            if(itemString.compareTo(currentString) == 0)
            {
               current.updateCount(current.getCount() -1);
               return;
            }
         }
         
         else
         {
            current = current.right;

            if(itemString.compareTo(currentString) == 0)
            {
               current.updateCount(current.getCount() - 1);
               return;
            }
         }
      }
   } // end of removeOne()
   
   
   public void removeAll(T item) {
      
    
   
      //find node to be deleted
      
      BstNode<T> parent = root;
      BstNode<T> current = root;
      
      String itemString = (String)item;
      String currentString = (String)current.item;
      
      boolean leftChild = false;
      
      while(currentString.compareTo(itemString) != 0)
      {
         parent = current;
         
         if(currentString.compareTo(itemString) < 0)
         {
            leftChild = true;
            current = current.left;
         }
         
         else
         {
            leftChild = false;
            current = current.right;
         }
      }
      //node to be removed has no children
      
      if(current.left == null && current.right == null)
      {
         if(current == root)
         {
            root = null;
         }
         
         if(leftChild)
         {
            parent.left = null;
         }
         
         else
         {
            parent.right = null;
         }
      }
      
      
      //node to be removed has one child
      
      if(current.right == null)
      {
         if(current == root)
         {
            root = current.left;
         }
         
         else if(leftChild)
         {
            parent.left = current.left;
         }
         
         else
         {
            parent.right = current.right;
         }
      }
      
      else if(current.left == null)
      {
         if(current == root)
         {
            root = current.right;
         }
         
         else if(leftChild)
         {
            parent.left = current.right;
         }
         
         else
         {
            parent.right = current.right;
         }
      }
      
      //node to be removed has two children
      
      else if(current.left != null && current.right != null)
      {
         BstNode<T> replacement = getReplacement(current);
         
         if(current == root)
         {
            root = replacement;
         }
         
         else if(leftChild)
         {
            parent.left = replacement;
         }
         
         else
         {
            parent.right = replacement;
         }
         
         replacement.left = current.left;
      }
      
      
   }
   // end of removeAll()
   
   
   public BstNode getReplacement(BstNode remove)
   {
      BstNode<T> replacement = null;
      BstNode<T> parent = null;
      BstNode<T> current = remove.right;
      
      while(current != null)
      {
         parent = replacement;
         replacement = current;
         current = current.left;
      }
      
      if(replacement != remove.right)
      {
         parent.left = replacement.right;
         replacement.right = remove.right;
      }
      
      return replacement;
         
   }

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
