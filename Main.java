
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a={99,22,88,66,44,33,77,11,55};
		//bubbleSort(a);
		//selectionSort(a);
		//insertionSort(a);
		//mergeSort(a);
		//shellSort(a);
		//quickSort(a);
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+" ");
		}

	}
//Bubble Sort 
	//O(n^2)
	public static void bubbleSort(int[] a){
		int n=a.length;
		for(int i=0;i<n-1;i++){//focus on how many rounds//(n-1) times of comparison round
			for(int j=0;j<n-1-i;j++){
				if(a[j]>a[j+1]){
					swap(a,j,j+1);
				}
			}
			
		}
	}
	
	public static void swap(int[] a, int i,int j){
		int temp=a[i];
		a[i]=a[j];
		a[j]=temp;
		
	}
	
//Selection Sort
	//find the max, swap to the highest index
	public static void selectionSort(int[] a){
		if(a==null || a.length<2)return;
		int n=a.length;
		
		for(int i=n-1;i>0;i--){//find the max, swap to i
			int k=i;//k refer to the index of max value
			for(int j=0;j<i;j++){
				if(a[j]>a[k]){
					k=j;
				}
			}
			swap(a,k,i);
		}
	}
	
//Insertion Sort
	//starting from i=1, move each bigger value one step forward,
	public static void insertionSort(int[] a){
		if(a==null || a.length<2)return;
		int n=a.length;
		
		for(int i=1;i<n;i++){//i point to the value we would like to insert
			int target=a[i];
			int j=i-1;
			while(j>=0 && a[j]>target){// two conditions
				a[j+1]=a[j];
				j--;
			}
			a[j+1]=target;
		}
	}

//Shell Sort
	//Similar with insertion sort, instead of moving one step forward, move j step forward
	//first round, move 6 step forward
	//second round, move 3 steps forward
	//last round, move 1 step forward
	//O is around O(n^1.3)
	public static void shellSort(int[] a){
		if(a==null || a.length<2)return;
		int n=a.length;
		
		int d=n/2;
		while(d>=1){//it has to be 1 finally
			shellSort(a,d);
			d /= 2;
		}
	}
	public static void shellSort(int[] a, int d){
		if(a==null || a.length<2)return;
		int n=a.length;
		
		for(int i=d;i<a.length;i++){//move from last index
			int target=a[i];
			int j=i-d;
			while(j>=0&&target<a[j]){
				a[j+d]=a[j];
				j=j-d;//index move d steps, not one step
			}
			if(j != i-d){//there is smaller value 
				a[j+d]=target;//every jump's difference is d
			}
			
		}
	}

//Merge Sort
	//time O(nlgn); space O(n)
	public static void mergeSort(int[] a){
		mergeSort(a,0,a.length-1);
	}
	public static void mergeSort(int[] a, int low, int high){
		if(low>=high){
			return;
		}
		
		int mid=(low+high)/2;
		
		mergeSort(a,low,mid);
		mergeSort(a,mid+1,high);
		merge(a,low,mid,high);
		
	}
	
	public static void merge(int[] a,int low, int mid,int high){
		int[] b=new int[high-low+1];
		int i=low,j=mid+1,k=0;
		
		while(i<=mid && j<=high){
			if(a[i]<a[j]){
				b[k++]=a[i++];
			}else{//a[j]>=a[i]
				b[k++]=a[j++];
			}
		}
		while(i<=mid){
			b[k++]=a[i++];
		}
		while(j<=high){
			b[k++]=a[j++];
		}
		for(int p=0;p<b.length;p++){
			a[low+p]=b[p];
		}

	}

//Quick Sort
	//o(nlgn), pivot, partition
	public static void quickSort(int[] a){
		if(a==null || a.length<2){
			return;
		}
		quickSort(a,0,a.length-1);
	}

	public static void quickSort(int[] a,int i,int j){
		if(i>=j){
			return;
		}
		int pivotPos=partition(a,i,j);//i refer to left pointer, j refer to right pointer
		quickSort(a,i,pivotPos-1);//pivotPos is at right position
		quickSort(a,pivotPos+1,j);
	}
	
/*	public static int partition(int[] a, int i,int j){
		int pivot=a[i];//choose the left index's value as pivot
		int pointer=i;
		while(i<j){
			while(i<j && a[j]>=pivot){
				j--;
			}
			while(i<j && a[i]<=pivot){
				i++;
			}
			swap(a,i,j);
		}
		swap(a,pointer,i);
		return i;
	}*/
	//Optimized partition method, 
	//move j value to left every time found it is smaller than pivot
	//move i value to right every time found it is bigger than pivot
	public static int partition(int[] a, int i,int j){
		int pivot=a[i];
		while(i<j){
			while(i<j && a[j]>=pivot){
				j--;
			}
			a[i]=a[j];// put small value to left
			while(i<j && a[i]<=pivot){
				i++;
			}
			a[j]=a[i];//put big value to right
		}
		a[i]=pivot;
		return i;
	}
	
	
//Heap Sort
	//bigger value at right, 
	public static void heapAdjust(int[] a, int start, int end){//adjust a[start] to a[end] become a heap
		int temp=a[start];
		for(int i=2*start+1;i<=end;i*=2){//2i+1, 2i+2
			if(i<end && a[i]<a[i+1]){
				i++;
			}
			if(temp>=a[i]){
				break;//it is already a heap
			}
			a[start]=a[i]; //put leaf up
			start=i;//start a new round
		}
		a[start] =temp;//put it to right position
	}
	
/*	public static void heapSort(int[] a){
		if(a==null || a.length<2)return;
		int n=a.length;
		for(int i=n/2-1;i>=0;i--){//build the heap
			heapAdjust(a,i,n-1);
		}
		for(int i=n-1;i>0;i--){
			swap(a,0,i);//put biggest value to the last index
			heapAdjust(a,0,i-1);//?i-1
		}
	}*/

//Counting Sort (Tally Sort)
	//O(n)

//Radix Sort

//Bucket Sort
	
}
