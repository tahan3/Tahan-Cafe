# Software Design Specification

### Содержание
1. [Введение](#1)<br>
1.1 [Цель](#1.1)<br>
1.2 [Объем](#1.2)<br>
1.3 [Определения, акронимы и сокращения](#1.3)<br>
2. [Системный обзор](#2)<br>
3. [Соображение по дизайну](#3)<br>
3.1 [Предположения и зависимости](#3.1)<br>
3.2 [Общие ограничения](#3.2)<br>
3.3 [Цели и рекомендации](#3.3)<br>
3.4 [Методология разработки](#3.4)<br>
4. [Архитектурная стратегия](#4)<br>
5. [Описание базы данных](#5)<br>
5.1 [Описание базы данных](#5.1)<br>
5.2 [Таблицы](#5.2)<br>
6. [Дизайн высокого уровня](#6)<br>
6.1 [Пользователь](#6.1)<br>
6.2 [Администратор](#6.2)<br>
7. [Дизайн низкого уровня](#7)<br>

## 1. Введение <a name="1"></a>

### 1.1 Цель <a name="1.1"></a>
Целю данного документа является представить подробное описание сайта интернет-кафе. Здесь будут описаны программные средства и решения для реализации проекта.

### 1.2 Объем <a name="1.2"></a>
Документ по дизайну программного обеспечения продемонстрирует, как дизайн будет выполнять функциональные и нефункциональные требования, зафиксированные в спецификации требований к программному обеспечению (SRS). Документ предоставит платформу программистам посредством описания компонентов высокого уровня и архитектуры, подсистемы, интерфейса, дизайн базы данных и дизайн алгоритма. Это достигается за счет использования архитектурных паттернов, паттернов проектирования, диаграмм базы данных, диаграмм классов, реляционных модели и пользовательские интерфейсы.

### 1.3 Определения <a name="1.3"></a>
* Пользователь - человек, использующий систему
* База данных - коллекция хранимых связанных данных
* Архитектурный дизайн - установление общей структуры программного комплекса

## 2. Системный обзор <a name="2"></a>
Данный сайт представляет собой онлайн кафе с понятным интерфейсом. Данный сайт разрабатывается для экономии времени у людей на поход в кафе, ресторан. Данная система позволяет выбрать один или несколько заказов, указать адрес доставки и совершить заказ. Кроме того, система позволяет выбирать категории товаров, что упростит поиск нужного продукта для потребителя.

## 3. Соображения по дизайну <a name="3"></a>
Для работы сайта у пользователя должен быть в наличии один из видов устройств: мобильный телефон, планшет, компьютер и доступ к интернету, а также установленный браузер последней версии.

### 3.1 Предположения и зависимости <a name="3.1"></a>
* Сайт может поддерживать от 1-го до N-пользователей
* При тестировании системы будут использоваться собственные значения базы данных товаров 

### 3.2 Общие ограничения <a name="3.2"></a>
* Серверная часть будет реализована на Tomcat 9.0.0 M9
* 
* Для хранения данных будет использоваться реляционная база данных MySQL
* 

### 3.3 Цели и рекомендации <a name="3.3"></a>
* При тестировании системы будут использоваться собственные значения базы данных товаров
* Система должна быть полностью функциональна в запланированные сроки
* Интерфейс должен был интуитивно понятным для базового пользователя

### 3.4 Методология разработки <a name="3.4"></a>

## 4. Архитектурная стратегия <a name="4"></a>
На архитектуру и дизайн повлияли следующие дизайнерские решения и стратегии:
* В дизайне используются объектно-ориентированные принципы (ООП). Компромисс увеличения накладных расходов на код и объектная передача сообщений считается оправданной за счет модульности функциональности, данных инкапсуляция, связь через интерфейсы и повторное использование через полиморфизм
* Вся система использует клиент-серверную архитектуру
* Для пользователя интерфейс будет представлен в качестве HTML страницы
* Данные будут храниться в базе данных.
* Паттерны
* Взаимодействие клиента и сервера будут производиться с помощью Https протокола

## 5. Структура базы данных <a name="5"></a>

### 5.1 Описание базы данных <a name="5.1"></a>
В качестве базы данных выбрана MySQL.

### 5.2 Таблицы <a name="5.2"></a>
* client - таблица, содержащая в себе информацию о клиентах.
* client_comment - таблица, содержащая информацию о отзывах пользователей.
* hall - таблица, содержащая информацию о залах.
* meal - таблица, содержащая информацию о блюдах.
* meal_category - таблица, содержашая информацию о категориях блюд.
* meal_composition - таблица, содержащая информацию о составе блюда.
* meal_ingredient - таблица, содержашая информацию о ингридиентах.
* order - таблица, содержащая информацию о заказах.
* order_composition - таблица, содержащая информацию о блюдах включенных в заказы.
* reservation - таблица, содержащая информацию о бронировании залов.
* user - таблица, содержащая информацию о всех зарегистрированных пользователях.

## 6 Дизайн высокого уровня <a name="6"></a>
  
### 6.1 Пользователь <a name="6.1"></a>
* [Домой](https://github.com/tahan3/Tahan-Cafe/blob/main/Mockups/user/Main%20menu.PNG)
* [Авторизация](https://github.com/tahan3/Tahan-Cafe/blob/main/Mockups/user/%D0%B0%D0%B2%D1%82%D0%BE%D1%80%D0%B8%D0%B7%D0%B0%D1%86%D0%B8%D1%8F.PNG)
* [Бронирование зала](https://github.com/tahan3/Tahan-Cafe/blob/main/Mockups/user/%D0%B1%D1%80%D0%BE%D0%BD%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5%20%D0%B7%D0%B0%D0%BB%D0%B0.PNG)
* [Меню бронирования](https://github.com/tahan3/Tahan-Cafe/blob/main/Mockups/user/%D0%BC%D0%B5%D0%BD%D1%8E%20%D0%B1%D1%80%D0%BE%D0%BD%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D1%8F.png)
* [Меню с блюдами](https://github.com/tahan3/Tahan-Cafe/blob/main/Mockups/user/%D0%BC%D0%B5%D0%BD%D1%8E.PNG)
* [Как сделать заказ](https://github.com/tahan3/Tahan-Cafe/blob/main/Mockups/user/%D1%81%D0%B4%D0%B5%D0%BB%D0%B0%D1%82%D1%8C%20%D0%B7%D0%B0%D0%BA%D0%B0%D0%B7.PNG)
  
### 6.2 Администратор <a name="6.2"></a>
* [Категории](https://github.com/tahan3/Tahan-Cafe/blob/main/Mockups/admin/%D0%9A%D0%B0%D1%82%D0%B5%D0%B3%D0%BE%D1%80%D0%B8%D0%B8.PNG)
* [Заказы](https://github.com/tahan3/Tahan-Cafe/blob/main/Mockups/admin/%D0%B7%D0%B0%D0%BA%D0%B0%D0%B7%D1%8B.PNG)
* [Залы](https://github.com/tahan3/Tahan-Cafe/blob/main/Mockups/admin/%D0%B7%D0%B0%D0%BB%D1%8B.PNG)
* [Ингредиенты](https://github.com/tahan3/Tahan-Cafe/blob/main/Mockups/admin/%D0%B8%D0%BD%D0%B3%D1%80%D0%B8%D0%B4%D0%B8%D0%B5%D0%BD%D1%82%D1%8B.PNG)
* [Информация о заказе](https://github.com/tahan3/Tahan-Cafe/blob/main/Mockups/admin/%D0%B8%D0%BD%D1%84%D0%BE%D1%80%D0%BC%D0%B0%D1%86%D0%B8%D1%8F%20%D0%BE%20%D0%B7%D0%B0%D0%BA%D0%B0%D0%B7%D0%B5.PNG)
* [Информация о пользователе](https://github.com/tahan3/Tahan-Cafe/blob/main/Mockups/admin/%D0%B8%D0%BD%D1%84%D0%BE%D1%80%D0%BC%D0%B0%D1%86%D0%B8%D1%8F%20%D0%BE%20%D0%BF%D0%BE%D0%BB%D1%8C%D0%B7%D0%BE%D0%B2%D0%B0%D1%82%D0%B5%D0%BB%D0%B5.PNG)
* [Личный кабинет](https://github.com/tahan3/Tahan-Cafe/blob/main/Mockups/admin/%D0%BB%D0%B8%D1%87%D0%BD%D1%8B%D0%B9%20%D0%BA%D0%B0%D0%B1%D0%B8%D0%BD%D0%B5%D1%82.PNG)
* [Пользователи](https://github.com/tahan3/Tahan-Cafe/blob/main/Mockups/admin/%D0%BF%D0%BE%D0%BB%D1%8C%D0%B7%D0%BE%D0%B2%D0%B0%D1%82%D0%B5%D0%BB%D0%B8.PNG)

## 7 Дизайн низкого уровня <a name="7"></a>