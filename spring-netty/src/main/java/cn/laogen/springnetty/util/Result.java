package cn.laogen.springnetty.util;

/**
 * @Description:
 * @author: zhonglianxi
 * @date: 2020-02-21
 */
public class Result<T> {

    /**
     * 泛型类的普通方法只能接受定义泛型类的类型
     * 泛型方法可以接受任何类型的参数类型，泛型方法表示此泛型仅仅在此方法用
     */

    private T data;


    public static <T> Result<T> success(T data){
        return new Result<T>(data);
    }

    private Result(T data){
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
