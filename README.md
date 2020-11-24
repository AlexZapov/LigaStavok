# LigaStavok
Флоу автотеста:
1. Зайти на market.yandex.ru
2. Выбрать раздел Электроника - > Телевизоры
3. Задать параметр поиска:
  по цене: от 20000 рублей
  по производителю: Samsung и LG
4. Открыть первый найденный телевизор из списка. Проверить что производитель и цена соответствуют выбранным параметрам поиска

# Особенности автотеста
Автотест реализован с помощью DataProvider для более детального поиска информации. Запускается 3 раза с разными параметрами. 
Первый автотест будет искать телевизоры марки LG, второй - Samsung, третий - и LG, и Samsung.

# Необходимые настройки
Перед запуском автотестов необходимо в файле conf.properties (путь /src/test/resources) указать корректный путь к ChromeDriver в переменной chromedriver.
Если есть желание, то в этом же файле можно изменить цену.

# Запуск автотестов
1. В командной строке перейти в директорию проекта ранее скаченного проекта
___cd %folderName%___

2. Запустить автотесты командой
___mvn clean test -Dtestng.dtd.http=true___

# Генерация отчетов Allure
Для генерации отчетов, после успешного прогона автотестов необходимо запустить команду
___mvn allure:serve___
