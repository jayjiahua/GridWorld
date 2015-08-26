MazeBug
作者：吴嘉华  学号：13331271
源文件有两个类，除了在分岔路选择方向的方式不同之外，其余均相同
由于两个类的文件十分相似，用sonar检测会有大量的重复代码，故直接让MazeBug2继承MazeBug，然后重写chooseDirection方法

chooseDirection 为新增方法，用于选择合适的方向

MazeBug  选择行走方向使用java的随机数类Random
MazeBug2 按照方向的偏向性选择方向

编译 & 运行
    ant

注意：由于没有将自己写的类打包，故载入地图之后，要先把原先地图上的MazeBug删除，然后再用鼠标点击起点。选择添加MazeBug或MazeBug2。


