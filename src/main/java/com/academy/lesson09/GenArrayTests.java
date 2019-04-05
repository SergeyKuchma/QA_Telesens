/* ДЗ 09
2) Создать тесты GenArrayTests, в которых:
	a) Протестировать работу методов
	b) Протестировать работу при некорректном индексе массива, перехватывая исключение
*/

package com.academy.lesson09;

public class GenArrayTests {
    private GenericArray<Integer> intArrayData; //это общая переменная, чтобы использовать ее во всех методах

    public static void main(String[] args) throws GenericArrayException {

        //GenArrayTests tests = new GenArrayTests();
        // tests.trySet();

        Integer[] intArr = {10, 20, 30, 100};//инициализируется исходный массив
        Integer[] currentArr = intArr.clone(); //текущий масиив для изменений
        Integer[] secondCurrentArr = {1, 2, 3, 4, 5, 6};//массив с другим количеством элементов для проверки
        int countOfTrueResults=0;// счетчик положительных тестов
        int countOfFalseResults=0;// счетчик отрицательных тестов
        int countOfTrueResultsTotal=0;// счетчик положительных тестов
        int countOfFalseResultsTotal=0;// счетчик отрицательных тестов


        GenericArray<Integer> genIntArray = new GenericArray<>(intArr);
        GenericArray<Integer> genIntArrayCurrent = new GenericArray<>(currentArr);
        GenericArray<Integer> genIntArrayCurrent2 = new GenericArray<>(secondCurrentArr);

        System.out.println("Исходный массив: " + genIntArray);

        System.out.println("\n****** Тестирование метода Get() **********");
        //проверяем все действующие индексы
        for (int i = 0; i < genIntArrayCurrent.lengthOfGenArr(); i++)
             if (tryToGet(i, genIntArrayCurrent)) countOfTrueResults++;
            else countOfFalseResults++;


        //тестируем несуществующие индексы
        if (tryToGet(999, genIntArrayCurrent)) countOfTrueResults++;
        else countOfFalseResults++;

        if (tryToGet(-999, genIntArrayCurrent)) countOfTrueResults++;
        else countOfFalseResults++;

        System.out.println(String.format("*** Количество проведенных тестов по методу Get() равно = %d ***",countOfTrueResults+countOfFalseResults));
        System.out.println("*** Количество Положительных результатов: "+countOfTrueResults);
        System.out.println("*** Количество Отрицательных результатов: "+countOfFalseResults);
        System.out.println("*** Тестирование метода Get() закончено *");
        countOfTrueResultsTotal+=countOfTrueResults;// общий счетчик положительных тестов
        countOfFalseResultsTotal+=countOfFalseResults;// общий счетчик отрицательных тестов

        countOfTrueResults=0;//обнуляем счетчики
        countOfFalseResults=0;

        System.out.println("\n****** Тестирование методов toString() и equals() после Get() **********");
        System.out.println("Метод toString() после Get(): " + genIntArrayCurrent.toString());
        System.out.println("Метод equals() после Get(): " + genIntArrayCurrent.equals(genIntArray));


        //тестирование длины массивов для сравнения
        if (genIntArrayCurrent.equalsLenght(genIntArray.lengthOfGenArr())) {
            System.out.println("Количество элементов массивов одинаково и равно = " + genIntArray.lengthOfGenArr());
            countOfTrueResults++;

            //тогда сравниваем поэлементно между массивами
            for (int i = 0; i < genIntArray.lengthOfGenArr(); i++)
                if (genIntArrayCurrent.equalsElement(i, genIntArray.get(i)))//передаем индекс и элемент исходного массива для сравнения
                {System.out.println("текущий элемент массива [" + i + "]=" + genIntArrayCurrent.get(i) + " соответствует исходному элементу =" + genIntArray.get(i) + " - все OK");
                    countOfTrueResults++;
                }
                else
                {System.out.println("текущий элемент массива [" + i + "]=" + genIntArrayCurrent.get(i) + " НЕ соответствует исходному элементу =" + genIntArray.get(i) + " - ошибка!");
                    countOfFalseResults++;}
        } else
            { System.out.println("Количество элементов массивов разное: у исходного массива = " + genIntArray.lengthOfGenArr() + ", а у текущего массива = " + genIntArrayCurrent.lengthOfGenArr() + " - ошибка!");
              countOfFalseResults++;}

        System.out.println(String.format("*** Количество проведенных тестов по методам toString() и equals() равно = %d ***",countOfTrueResults+countOfFalseResults));
        System.out.println("*** Количество Положительных результатов: "+countOfTrueResults);
        System.out.println("*** Количество Отрицательных результатов: "+countOfFalseResults);
        System.out.println("*** Тестирование методов toString() и equals() закончено ***");
        countOfTrueResultsTotal+=countOfTrueResults;// общий счетчик положительных тестов
        countOfFalseResultsTotal+=countOfFalseResults;// общий счетчик отрицательных тестов


        countOfTrueResults=0;//обнуляем счетчики
        countOfFalseResults=0;

        System.out.println("\n****** Тестирование метода Set() **********");
        //проверяем все действующие индексы
        for (int i = 0; i < genIntArrayCurrent.lengthOfGenArr(); i++)
            if (tryToSet(i, 111, genIntArrayCurrent)) countOfTrueResults++;
                else countOfFalseResults++;


        //тестируем несуществующие индексы
        if (tryToSet(999, 111, genIntArrayCurrent)) countOfTrueResults++;
            else countOfFalseResults++;

        if (tryToSet(-999, 111, genIntArrayCurrent)) countOfTrueResults++;
        else countOfFalseResults++;

      //  if (tryToSet(qqq, eee, test)) countOfTrueResults++;
      //  else countOfFalseResults++;

        System.out.println(String.format("*** Количество проведенных тестов по методу Set() равно = %d ***",countOfTrueResults+countOfFalseResults));
        System.out.println("*** Количество Положительных результатов: "+countOfTrueResults);
        System.out.println("*** Количество Отрицательных результатов: "+countOfFalseResults);
        System.out.println("*** Тестирование метода Set() закончено *");
        countOfTrueResultsTotal+=countOfTrueResults;// общий счетчик положительных тестов
        countOfFalseResultsTotal+=countOfFalseResults;// общий счетчик отрицательных тестов

        countOfTrueResults=0;//обнуляем счетчики
        countOfFalseResults=0;

        System.out.println("\n****** Тестирование методов toString() и equals() после Set() **********");
        System.out.println("Метод toString() для исходного массива после Set(): " + genIntArray.toString());
        System.out.println("Метод toString() для измененного массива после Set(): " + genIntArrayCurrent.toString());
        System.out.println("Метод equals() между исходным и измененным массивами: " + genIntArrayCurrent.equals(genIntArray));

        //тестирование длины массивов
        if (genIntArrayCurrent.equalsLenght(genIntArray.lengthOfGenArr())){
            System.out.println("Количество элементов массивов одинаково и равно = " + genIntArray.lengthOfGenArr());
            countOfTrueResults++;

            //тестирование поэлементно между массивами
        for (int i = 0; i < genIntArray.lengthOfGenArr(); i++)
            if (genIntArrayCurrent.equalsElement(i, genIntArray.get(i))) {
                System.out.println("текущий элемент массива [" + i + "]=" + genIntArrayCurrent.get(i) + " соответствует исходному элементу =" + genIntArray.get(i) + " - все OK");
                countOfTrueResults++;
            }
            else {
                System.out.println("текущий элемент массива [" + i + "]=" + genIntArrayCurrent.get(i) + " НЕ соответствует исходному элементу =" + genIntArray.get(i) + " - ошибка!");
                countOfFalseResults++;
                }
            }
        else {
            System.out.println("Количество элементов массивов разное: у исходного массива = " + genIntArray.lengthOfGenArr() + ", а у текущего массива = " + genIntArrayCurrent.lengthOfGenArr() + " - ошибка!");
            countOfFalseResults++;
        }

        //Сравнение массивов с разным количеством элементов
        System.out.println("\n****** Сравнение массивов с разным количеством элементов  **********");
        System.out.println("Исходный массив: " + genIntArray.toString());
        System.out.println("Текущий массив для сравнения: " + genIntArrayCurrent2.toString());

        //тестирование длины массивов
        if (genIntArrayCurrent2.equalsLenght(genIntArray.lengthOfGenArr())) {
            System.out.println("Количество элементов массивов одинаково и равно = " + genIntArray.lengthOfGenArr());
            countOfTrueResults++;

            //тестирование поэлементно между массивами
            for (int i = 0; i < genIntArray.lengthOfGenArr(); i++)
                if (genIntArrayCurrent2.equalsElement(i, genIntArray.get(i))){
                    System.out.println("текущий элемент массива [" + i + "]=" + genIntArrayCurrent2.get(i) + " соответствует исходному элементу =" + genIntArray.get(i) + " - все OK");
                    countOfTrueResults++;
                }
            else
                {  System.out.println("текущий элемент массива [" + i + "]=" + genIntArrayCurrent2.get(i) + " НЕ соответствует исходному элементу =" + genIntArray.get(i) + " - ошибка!");
                   countOfFalseResults++;
                }
    }
        else {
            System.out.println("Количество элементов массивов разное: у исходного массива = "+genIntArray.lengthOfGenArr()+", а у текущего массива = "+genIntArrayCurrent2.lengthOfGenArr()+" - ошибка!");
            countOfFalseResults++;
        }

        System.out.println(String.format("*** Количество проведенных тестов после метода Set() равно = %d ***",countOfTrueResults+countOfFalseResults));
        System.out.println("*** Количество Положительных результатов: "+countOfTrueResults);
        System.out.println("*** Количество Отрицательных результатов: "+countOfFalseResults);
        System.out.println("*** Тестирование методов после Set() закончено *");

        countOfTrueResultsTotal+=countOfTrueResults;// общий счетчик положительных тестов
        countOfFalseResultsTotal+=countOfFalseResults;// общий счетчик отрицательных тестов

        System.out.println("\n**********************************************************");
        System.out.println(String.format("\n*** Общее количество проведенных тестов равно = %d ***",countOfTrueResultsTotal+countOfFalseResultsTotal));
        System.out.println("*** Количество Положительных результатов: "+countOfTrueResultsTotal);
        System.out.println("*** Количество Отрицательных результатов: "+countOfFalseResultsTotal);
        System.out.println("\n*** Тестирование завершено ***");

    }

    //Метод tryToSet - попытка положить элемент по индексу
    public static boolean tryToSet (int currentIndex, int currentValue, GenericArray<Integer> intArr){
      //  GenericArray<Integer> genIntArray = new GenericArray<>(intArr);
        //проверка на несуществующий индекс для SET
        try {
            intArr.set(currentIndex, currentValue);// положить по индексу
            System.out.println("Метод Set("+currentIndex+", " +currentValue+"): измененный массив: "+intArr+" - все OK");
        } catch (GenericArrayException e) {
            System.out.println("Метод Set("+currentIndex+", " +currentValue+") - Exception - " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Common error для индекса " + e);
            System.exit(1);
        }
        return true;
    }

    //Метод tryToGet - попытка получить элемент по индексу
    public static boolean tryToGet (int currentIndex, GenericArray<Integer> intArr){
//        GenericArray<Integer> genIntArray = new GenericArray<>(intArr);
        Integer currentValue = null;
        //проверка на несуществующий индекс для GET
        try {
            currentValue = intArr.get(currentIndex);//получаем значение по индексу
            System.out.println("Метод Get("+currentIndex+") выдает значение "+currentValue+" из массива: "+intArr+" - все OK");
       } catch (GenericArrayException e) {
            System.out.println("Метод Get("+currentIndex+") - Exception - " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Common error для индекса " + e);
            System.exit(1);
        }
        return true;
    }
}
