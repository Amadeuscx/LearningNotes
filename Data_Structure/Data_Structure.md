# Data_Structure

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

  

## 1-ArrayList(动态数组)

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

若允许则要考录*null*值的处理，因为我们不能使用null来访问任何方法，这会使程序抛出异常。

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

