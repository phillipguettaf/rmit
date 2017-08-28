public class BstNode<T> {

	protected T item;
	protected BstNode<T> left;
	protected BstNode<T> right;
   protected int count;
   
   public BstNode()
	{
		this.item = null;
		this.left = null;
		this.right = null;
	}
	
	public BstNode(T item)
	{
		this.item = item;
      this.count = 1;
		left = null;
		right = null;
	}

   public int getCount()
	{
		return count;
	}
	
	public void updateCount(int newCount)
	{
		count =+ newCount;
	}
   
	public T get()
	{
		return item;
	}
}