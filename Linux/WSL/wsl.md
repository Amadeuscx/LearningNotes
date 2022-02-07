# wsl

## 配置wsl2

### 1.启用wsl

用管理员打开powershell输入

```
dism.exe /online /enable-feature /featurename:Microsoft-Windows-Subsystem-Linux /all /norestart
```



### **2. 升级为WSL2的必要条件**

- 对于x64的系统要求win10版本为**1903** 或者更高
- win + R 输入 `winver`查看版本



### 3. 启用虚拟平台

用管理员打开powershell输入

```
dism.exe /online /enable-feature /featurename:VirtualMachinePlatform /all /norestart
```



### 4. 下载Linux内核升级包

下载地址：https://wslstorestorage.blob.core.windows.net/wslblob/wsl_update_x64.msi

下载完成后双击安装



### **5. 将WSL2设置为默认版本**

用管理员打开powershell输入

```
wsl --set-default-version 2
```

到这里WSL就安装好了，下面安装ArchLinux



## 安装ArchLinux

### 1. 安装LxRunOffline

下载地址：https://github.com/DDoSolitary/LxRunOffline/releases

选择最新版下载，解压后将LxRunOffline.exe所在的文件夹加入Path环境变量



### 2. **下载Archlinux**

下载地址： https://mirrors.tuna.tsinghua.edu.cn/archlinux/iso/latest/

找到 `archlinux-bootstrap-xxxx.xx.xx-x86_64.tar.gz`， 注意是 `tar.gz`文件



### **3. 安装archlinux到WSL**

命令1：

```
LxRunOffline i -n <自定义名称> -f <Arch镜像位置> -d <安装系统的位置> -r root.x86_64
```

比如：

```
LxRunOffline i -n ArchLinux -f C:\Users\kainhuck\Downloads\archlinux-bootstrap-2020.10.01-x86_64.tar.gz -d C:\Users\kainhuck\Linux -r root.x86_64
```

命令2：

```
wsl --set-version <名称> 2
```

比如：

```
wsl --set-version ArchLinux 2
```



在完成以上步骤后你应该可以

- 在windows文件资源管理器里找到Linux选项，打开其中的<名称>(如ArchLinux)文件夹即可进入Linux文件系统。
- 在windows终端中进入Linux子系统



## 系统配置

### 0. 进入系统

你可以通过windows终端进入Linux子系统

也可以使用命令在powershell中进入Linux子系统

```powershell
wsl -d <名字>
```

比如

```powershell
wsl -d ArchLinux
```



### 1. 切换国内镜像源

编辑 `/etc/pacman.d/mirrorlist`

里面有注释了的 `China` 的镜像，选择合适的的取消注释



### 2. 添加 ArchlinuxCN 源

编辑 `/etc/pacman.conf` ，在文件末尾添加以下内容

```
[archlinuxcn]
Server = https://mirrors.tuna.tsinghua.edu.cn/archlinuxcn/$arch
Server = https://mirrors.ustc.edu.cn/archlinuxcn/$arch
```



### 3. 开启 32 位支持库

编辑`/etc/pacman.conf`, 去掉[multilib]一节中两行的注释，来开启 32 位库支持



### 4. 更新并初始化

输入以下命令

```bash
pacman -Syyu
pacman-key --init
pacman-key --populate
pacman -S archlinuxcn-keyring
```

再次更新

```bash
pacman -Syyu
```



### 5. 安装一些基本的软件

```bash
pacman -S base base-devel wget git curl zsh yay neofetch
```



### 6. 设置 Locale 进行本地化

Locale 决定了地域、货币、时区日期的格式、字符排列方式和其他本地化标准。

编辑 `/etc/locale.gen`，去掉` en_US.UTF-8` 所在行以及 `zh_CN.UTF-8` 所在行的注释符号（#）。

然后使用如下命令生成 locale。

```bash
locale-gen
```

向 /etc/locale.conf 导入内容

```bash
echo 'LANG=en_US.UTF-8'  > /etc/locale.conf
```



### 7. 为root用户设置密码

```bash
passwd root
```



### 8. 添加非root用户

添加用户，比如新增加的用户叫 testuser

```bash
useradd -m -G wheel -s /bin/bash testuser  #wheel附加组可sudo，以root用户执行命令 -m同时创建用户家目录
```

设置新用户 testuser 的密码

```
passwd testuser
```

编辑 sudoers 配置文件

```
vim /etc/sudoers
```

找到下面这样的一行，把前面的注释符号 `#` 去掉，`:wq` 保存并退出即可。

```
#%wheel ALL=(ALL) ALL
```

查看当前用户id

```bash
id -u <用户名>
```



### 9. 设置使用普通用户登录Archlinux

紧接上一步，退出Arch

```bash
exit
```

在powershell中执行

```powershell
lxrunoffline su -n <你的arch名字> -v <账户id>
```



### 10. 终端中文显示

如果你正确执行了“6. 设置 Locale 进行本地化”操作，那么想要在ArchLinux终端显示中文你只需要：

在 `~/.bashrc`里单独设置中文 locale，即添加下面两行到文件的**最开头**

```
export LANG=zh_CN.UTF-8
export LANGUAGE=zh_CN:en_US
```

