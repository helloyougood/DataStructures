
public class Sort{

	
/*		排序算法总结
		类别：
			//以下全为内部排序
			1.插入排序：直接插入排序、折半插入排序、希尔排序
			2.交换排序：冒泡排序、改进的冒泡排序、快速排序
			3.选择排序：简单选择排序、堆排序（树形选择排序法）
			4.归并排序
			5.基数排序

			//外部排序

		默认排序为：从小到大
*/

	

	// 直接插入排序
	public static void InsertSort(int [] array,int n){
		int i,j;
		for(i=2;i<=n;i++){//i=1或i=2都可以
			array[0]=array[i];//设置一个哨兵
			for(j=i-1;array[0]<array[j];j--)
				array[j+1]=array[j];
			array[j+1]=array[0];
		}

	}

	//折半插入排序
	public static void BinarySort(int []array,int n){
		int i,j;
		int low,high,mid;
		for(i=2;i<=n;i++){//i=2
			array[0]=array[i];//设置一个哨兵

			//折半插入到1~i-1的序列中，其中i>2
			low=1;high=i-1;
			while(low<high){//寻找插入位置
				mid=(low+high)/2;
				if(array[0]<array[mid])high=mid-1;
				else low=mid+1;
			}
			for(int k=i-1;k>low;k--){//应插入到low+1处
				array[k+1]=array[k];
			}
			array[low+1]=array[0];
		}		
	}

	//希尔排序
	public static void ShellSort(int []array,int n){
		int d,k; 
		int i,j;
		int tem;
		for(d=n/2;d>=1;d=d/2){//缩小步长
			for(k=1;k<=d;k++){//d个组
				//直接插入排序
				for(i=k+d;i<=n;i=i+d){
					array[0]=array[i];//设置哨兵
					for(j=i-d;array[0]<array[j];j=j-d){						
							array[j+d]=array[j];
					}
					/*这里是j+d,不是j；对应直接插入那里的j+1*/
					array[j+d]=array[0];
				}
			}

		}

	}



	//冒泡排序
	public static void BubbleSort(int []array,int n){
		int i,j;
		int tem;
		for(i=1;i<n-1;i++){
			for(j=1;j<=n-i;j++)
				if(array[j]>array[j+1]){
					tem=array[j];
					array[j]=array[j+1];
					array[j+1]=tem;
				}
		}
	}


	//改进的冒泡排序
	public static void BubbleProvedSort(int []array,int n){
		
	}

	//划分方法
	public static int Partion(int []array, int low, int high){
		int pivot=array[low];
		while(low<high){
			/*这里如果写成下面的形式会出错*/
			// while(array[high]>pivot)high--;array[low]=array[high];
			// while(array[low]<pivot)low++;array[high]=array[low];

			while(array[high]>pivot&&low<high)high--;array[low]=array[high];
			while(array[low]<pivot&&low<high)low++;array[high]=array[low];
		}
		/*如果忘记了写下一句会出错*/
		array[low]=pivot;

		return low;
	}

	//快速排序1次划分
	public static void QuickSort(int []array,int low, int high){
		/*经常容易写成下面这样，会出错*/
		// int pivotpos=Partion(array,low,high);
		// while(low<high){
		// 	QuickSort(array,low,pivotpos-1);
		// 	QuickSort(array,pivotpos+1,high);
		// }
		if(low<high){
			int pivotpos=Partion(array,low,high);
			QuickSort(array,low,pivotpos-1);
		 	QuickSort(array,pivotpos+1,high);
		}
	}

	// 简单选择排序
	public static void SelectSort(int []array,int n){
		int i,j;
		int tem;
		for(i=1;i<=n-1;i++){//最多需要n-1次选择即可完成n个数据排序
			array[0]=array[i];//设置一个哨兵
			for(j=i+1;j<=n;j++){
				if(array[0]>array[j]){
					tem=array[0];
					array[0]=array[j];
					array[j]=tem;
				}
			}
			array[i]=array[0];
		}
	}
	//合并有序序列low~mid和有序序列mid+1~high
	public static void Merge(int []array,int low,int mid, int high){
		int []tem=new int[high+1];
		int i,j;
		int k;
		for(k=low;k<=high;k++)
			tem[k]=array[k];
		k=low;i=low;j=mid+1;
		while(i<=mid&&j<=high){
			if(tem[i]<tem[j])
				array[k++]=tem[i++];
			else
				array[k++]=tem[j++];
		}
		if(i<=mid)//如果i指向的数据剩余，则插入到array后面
			while(i<=mid)array[k++]=tem[i++];
		else
			while(j<=mid)array[k++]=tem[j++];
	}

	// 2-路归并排序
	public static void MergeSort(int []array,int low,int high){
		if(low<high){
			int mid=(low+high)/2;
			MergeSort(array,low,mid);//左边采用归并排序后有序
			MergeSort(array,mid+1,high);//右边采用归并排序后有序
			Merge(array,low,mid,high);//将左右有序序列合并
		}
	}


	public static void main(String[] args){
		int [] array={0,2,5,6,3};//n=4
		// InsertSort(array, 4);
		// for(int i=1;i<=4;i++){
		// 	System.out.println(array[i]);
		// }
		

		// BinarySort(array, 4);
		// for(int i=1;i<=4;i++){
		// 	System.out.println(array[i]);
		// }

		// ShellSort(array, 4);
		// for(int i=1;i<=4;i++){
		// 	System.out.println(array[i]);
		// }

		// BubbleSort(array, 4);
		// for(int i=1;i<=4;i++){
		// 	System.out.println(array[i]);
		// }

		// BubbleProvedSort(array, 4);
		// for(int i=1;i<=4;i++){
		// 	System.out.println(array[i]);
		// }

		// QuickSort(array, 1,4);
		// for(int i=1;i<=4;i++){
		// 	System.out.println(array[i]);
		// }

		// SelectSort(array, 4);
		// for(int i=1;i<=4;i++){
		// 	System.out.println(array[i]);
		// }

		MergeSort(array, 1, 4);
		for(int i=1;i<=4;i++){
			System.out.println(array[i]);
		}


	}
}