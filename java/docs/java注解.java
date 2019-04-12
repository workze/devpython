//定义注解
//---GetType.java
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GetType {
	int value() default 3;
}
//---MM.java
public class MM {
	//使用注解
	@GetType(value=999)
	int a=10;
}

//获取注解
//--- mian.java
Field field = MM.class.getDeclaredField("a");    //先获取字段或函数
GetType anno = field.getAnnotations(GetType.class );    //再获取注解对象
System.out.println( anno.value() );              //调用注解的方法

参考 http://blog.csdn.net/zknxx/article/details/51511447 

