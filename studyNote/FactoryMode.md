1.通常，在工厂模式中，我们会定义一个工厂生产的接口方法：
public interface IFruit
{
    void produce();
}
2.接着，定义具体的工厂生产线类
public class Apple implements IFuit
{
    @Override
    public void produce()
    {
        Log.e("","生产苹果");
    }
}
public class Pear implements IFuit
{
    @Override
    public void produce()
    {
        Log.e("","生产梨子");
    }
}
3.然后，定义生产工厂类
public class FruitFactory
{
    public static IFuit create(int id)
    {
        if(1==id)
        {
           return new Apple();
        }
        if(2==id)
        {
            return new Pear();
        }
        return null;
    }
}
4.最后使用工厂
public void produceFruit()
{
    FruitFactory.create(1).produce();
    FruitFactory.create(2).produce();
}
在以上例子中，每次新增生产线的时候，都需要定义一个生产线，，然后在FruitFactory的create方法中新增判断，
返回新的生产线类，并且每次添加的代码都是非常的相似重复的。
