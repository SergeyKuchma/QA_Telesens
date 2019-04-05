/* ДЗ 09
1) Создать обобщенный класс GenArray, который:
	a) хранит элементы массива параметризированного типа.
		Размер массива постоянен во все время существования экземпляра класса и инициализируется во время создания
		(в конструктор передается конкретный массив: public GenArray(Integer[] array) {...}

	b)	Реализовать методы:
			T get(int index) - который вернет элемент массива
			void set(int index, T element), который установит элемент массива
			перегрузить методы toString() и equals();

	c)	Бросать исключения, если index выходит за пределы допустимого диапазона
        */
package com.academy.lesson09;
import java.util.Arrays;

public class GenericArray<T>{
    private T[] array;


    public GenericArray(T[] array) {
        this.array = array;
    }

    public T get(int index) throws GenericArrayException {
        if (index < 0 || index >= array.length)
            throw new GenericArrayException("для метода Get() - индекс должен быть в пределах от 0 до " + (array.length - 1));
        return array[index];// возвращает элемент массива
    }

    public void set(int index, T value) throws GenericArrayException {
        if (index < 0 || index >= array.length)
            throw new GenericArrayException("для метода Set() - индекс должен быть в пределах от 0 до " + (array.length - 1));
        array[index] = value;//устанавливает элемент массива
    }

    //Сравнивает элемент по индексу
    public boolean equalsElement(int index, T value) throws GenericArrayException {
        if (index < 0 || index >= array.length)
            throw new GenericArrayException("для метода equalsElement() - индекс должен быть в пределах от 0 до " + (array.length - 1));
        if (array[index] == value) return true;
            else return false;
    }

    //Сравнивает длину по максимальному индексу, который сюда передается
    public boolean equalsLenght(int index) {

        if (array.length == index) return true;
        else return false;
    }
    //Возвращает длину массива
    public int lengthOfGenArr() throws GenericArrayException {
        if (array == null )
            throw new GenericArrayException("для метода lengthOfGenArr() - массив не имеет значений" + array);
        return array.length ;// возвращает длину массива
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    } //перегружаем метод toString()


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenericArray<?> that = (GenericArray<?>) o;

        return Arrays.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }

}