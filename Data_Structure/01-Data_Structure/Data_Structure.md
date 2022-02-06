# Data_Structure

## 申明

- 本笔记是使用Typora编辑的MD笔记。为了保证你的阅读体验请务必打开MarkDown扩展语法，最好关闭拼写检查功能。

- 本笔记是记录自“腾讯课堂的小码哥-的恋上数据结构与算法(第一季)”这门课程 ，笔记内容也基本来自小码哥的课件。

## 0-前言

### 0.1 数据结构与算法是什么？

在计算机科学中，**数据结构（Data Structure）是计算机中存储、组织数据的方式**。为什么数据结构和算法经常放在一起讨论？算法用来设计一种使用计算机来解决问题的方法。设计高效的算法又是怎么来实现的？在我们学习了计算机编程后，也要学习数据结构与算法这些基础内容。

### 0.2 为什么要学习数据结构与算法？

1. 优化自身代码风格
2. 使我们写出性能更高的程序
3. 使我们能更加快速的理解学习新的技术

## 0.3 复杂度

> 复杂度是用于评价算法好坏的关键因素

### 0.3.1 如何评价一个算法的好坏？

如果单从执行效率上进行评估，可能会想到这么一种方案：比较不同算法对同一组输入的执行处理时间。这种方案也叫做：事后统计法。

但是上述方案有比较明显的缺点：

- 执行时间严重依赖硬件以及运行时各种不确定的环境因素
- 必须编写相应的测算代码测试
- 测试数据的选择比较难保证公正性

因此我们一般从以下维度来评估算法的优劣：

- 正确性、可读性、健壮性（对不合理输入的反应能力和处理能力）
- **时间复杂度**（time complexity）：估算程序指令的执行次数（执行时间）
- **空间复杂度**（space complexity）：估算所需占用的存储空间

### 0.3.2 大O表示法（Big O）

>  一般用大O表示法来描述复杂度，它表示的是数据规模 n 对应的复杂度

大O表示法特点：

-  忽略常数、系数、低阶
-  对数阶一般省略底数

举例来说：

![复杂度01](images\复杂度01.png)

注意：大O表示法仅仅**是一种粗略的分析模型**，是一种估算，能帮助我们短时间内了解一个算法的执行效率。

### 0.3.3 常见的复杂度

|           执行次数            | 复杂度(由小到大) | 非正式术语 |
| :---------------------------: | :--------------: | :--------: |
|           $$ 12 $$            |       O(1)       |   常数阶   |
|      $$ 4log_2 n + 5 $$       |     O(logn)      |   对数阶   |
|         $$ 2n + 3 $$          |       O(n)       |   线性阶   |
|   $$ 3n + 2nlog_3 n + 15 $$   |     O(nlogn)     |  nlogn阶   |
|      $$ 4n^2 + 2n + 6 $$      |   O($$ n^2 $$)   |   平方阶   |
| $$ 4n^3 + 3n^2 + 22n + 100 $$ |   O($$ n^3 $$)   |   立方阶   |
|           $$ 2^n $$           |   O($$ 2^n $$)   |   指数阶   |

#### 我们也可以借助函数生成工具对比复杂度的大小

**数据规模小时：**

![复杂度02](images\复杂度02.png)

**数据规模大时：**

![复杂度03](images\复杂度03.png)

结果来自：https://zh.numberempire.com/graphingcalculator.php





## 线性表

- **ArrayList**

- **LinkedList**

- **Stack**

- **Queue**

  

## 1-动态数组(ArrayList)

### 1.1特点介绍

动态数组是一种可以自动扩容的数组。

为此我们要维护以下几个接口：

```
int size();                      // 元素的数量
boolean isEmpty();               // 是否为空
boolean contains(E element);     // 是否包含某个元素
void add(E element);             // 添加元素到最后面
E get(int index);                // 返回index位置对应的元素
E set(int index, E element);     // 设置index位置的元素
void add(int index, E element);  // 往index位置添加元素
E remove(int index);             // 删除index位置对应的元素
int indexOf(E element);          // 查看元素的位置
void clear();                    // 清除所有元素
```

字段：

```
size : int
element : E[]
DEFAULT_CAPACITY : int = 10;
ELEMENT_NOT_FOUND : int = -1;
```

构造器：

```java
    public ArrayList(int capacity){
        capacity = (capacity > DEFAULT_CAPACITY) ? 
                    capacity : DEFAULT_CAPACITY;
        this.elements = (E[]) new Object[capacity];
    }

    public ArrayList(){
        this(DEFAULT_CAPACITY);
    }
```



### 1.2 内部数组

#### 1.2.1 内部数组的初始化

​	对于数组的初始化我们将给定一个固定的大小（*DEFAULT_CAPACITY*）。当用户传入的值大于DEFAULT_CAPACITY时则使用用户指定的大小。担当用户的值小于DEFAULT_CAPACITY时我们有两种选择：1.使用用户的值。2.继续使用DEFAULT_CAPACITY。这两种方案正常情况下没有优劣之分，如何选择是开发者的自由。这里我选择了第二种。

#### 1.2.2 内部数组的扩容

​	据统计扩容1.5倍是最好的选择：

```java
    private void ensureCapacity() {
        if (elements.length > size) return;
        int NewCapacity = elements.length + (elements.length >> 1);
        E[] NewElements = (E[]) new Object[NewCapacity];
        for (int i = 0; i < size; i++) {
            NewElements[i] = elements[i];
        }
        elements = NewElements;
    }
```



### 1.3 *null*处理

为我们要考虑是否允许用户储存*null*值。

若允许则要考录*null*值的处理，因为我们不能使用*null*来访问任何方法，这会使程序抛出异常。

```java
    public int indexOf(E element) {
        if (element == null) { //null值处理
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {return i;}
            }
        }else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) {return i;}
            }
        }
        return ELEMENT_NOT_FOUND;
    }
```

### 1.4 进阶：环形数组





## 5-二叉树(BinaryTree)

### 5.0.1 树（Tree）的基本概念

- 层数（level）：根节点在第 1 层，根节点的子节点在第 2 层，以此类推（有些教程也从第 0 层开始计算）

- 节点的深度（depth）：从根节点到当前节点的唯一路径上的节点总数 

- 节点的高度（height）：从当前节点到最远叶子节点的路径上的节点总数

- 树的深度：所有节点深度中的最大值

- 树的高度：所有节点高度中的最大值

- 树的深度 等于 树的高度



### 5.0.2 有序树、无序树、森林

有序树：树中任意节点的子节点之间有顺序关系

无序树：树中任意节点的子节点之间没有顺序关系 也称为“自由树” 

森林：由 m（m ≥ 0）棵互不相交的树组成的集合



### 5.0.3 二叉树的特点

- 每个节点的度最大为 2（最多拥有 2 棵子树）

- 左子树和右子树是有顺序的

- 即使某节点只有一棵子树，也要区分左右子树



### 5.0.4 二叉树的性质

- 非空二叉树的第 i 层，最多有$$2^{i - 1}$$个节点（ i ≥ 1 )

- 在高度为 h 的二叉树上最多有$$2^h - 1$$个结点（ h ≥ 1 ）

- 对于任何一棵非空二叉树，如果叶子节点个数为 n0，度为 2 的节点个数为 n2，则有: n0 = n2 + 1

​		证明：假设度为 1 的节点个数为 n1，那么二叉树的节点总数 n = n0 + n1 + n2 

​					二叉树的边数 T = n1 + 2 * n2 = n – 1 = n0 + n1 + n2 – 1

​					因此：n0 = n2 + 1



### 5.0.5 真二叉树（Proper Binary Tree）

- ==真二叉树==：所有节点的度都要么为 0，要么为 2 



### 5.0.6 满二叉树（Full Binary Tree）

- ==满二叉树==：最后一层节点的度都为 0，其他节点的度都为 2
- 在同样高度的二叉树中，满二叉树的叶子节点数量最多、总节点数量最多
- 满二叉树一定是真二叉树，真二叉树不一定是满二叉树



### 5.0.7 完全二叉树（Complete Binary Tree）

- ==完全二叉树==：对节点从上至下、左至右开始编号，其所有编号都能与相同高度的满二叉树中的编号对应
- ==ps.对于完全二叉树，对节点从上至下、左至右数。当出现第一个*null*节点时，剩余的节点均应为*null*节点==

![](images\5.0.7-1.png)

- 叶子节点只会出现最后 2 层，最后 1 层的叶子结点都靠左对齐

- 完全二叉树从根结点至倒数第 2 层是一棵满二叉树

- 满二叉树一定是完全二叉树，完全二叉树不一定是满二叉树



#### 5.0.7.1 完全二叉树的性质

**一棵有 n 个节点的完全二叉树（n > 0），从上到下、从左到右对节点从 0 开始进行编号，对任意第 i 个节点**

- 如果 i = 0 ，它是根节点

- 如果 i > 0 ，它的父节点编号为 floor( (i – 1) / 2 )

- 如果 2i + 1 ≤ n – 1 ，它的左子节点编号为 2i + 1

- 如果 2i + 1 > n – 1 ，它无左子节点 如果 2i + 2 ≤ n – 1 ，它的右子节点编号为 2i + 2

- 如果 2i + 2 > n – 1 ，它无右子节点



### 5.0.8 国外教材的说法

- ==Full Binary Tree==：完满二叉树

​		所有非叶子节点的度都为 2

​		就的国内说的“真二叉树” 

- ==Perfect Binary Tree==：完美二叉树

​		所有非叶子节点的度都为 2，且所有的叶子节点都在最后一层

​		就是国内说的“满二叉树” 

- ==Complete Binary Tree==：完全二叉树

​		和国内的定义一样



### 5.0.9 二叉树的遍历

遍历是数据结构中的常见操作，目的是把所有元素都访问一遍

线性数据结构的遍历比较简单，分为正序遍历与与逆序遍历。

==根据节点访问顺序的不同，二叉树的常见遍历方式有4种==

- 前序遍历（Preorder Traversal）

- 中序遍历（Inorder Traversal）

- 后序遍历（Postorder Traversal）

- 层序遍历（Level Order Traversal）

**下面我们由易至难完成二叉树的遍历**



#### 5.0.9.1 层序遍历（levelOrder）

访问顺序：从上到下、从左到右依次访问每一个节点

7、4、9、2、5、8、11、1、3、10、12

![](images\5.0.9.1-1.png)

==实现思路：使用队列==

1. 将根节点入队 

2.  循环执行以下操作，直到队列为空

   - 将队头节点 A 出队，进行访问

   - 将 A 的左子节点入队 将 A 的右子节点入队



```java
    /**
     * 层序遍历
     * @param root 开始节点
     * @param visitor 访问器（拿到节点后干什么）
     */
    protected void levelOrder(Node<E> root, Visitor visitor) {
        if (root == null || visitor == null) return;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> cur = queue.poll();
            if (visitor.visit(cur)) return;

            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
    }
```



#### 5.0.9.2 中序遍历





## 5.1-AVL树

AVL树是最早发明的自平衡二叉搜索树之一

AVL 取名于两位发明者的名字

G. M. Adelson-Velsky 和 E. M. Landis（来自苏联的科学家）

