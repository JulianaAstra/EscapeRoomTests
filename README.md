## <a href="https://escape-room-neon.vercel.app/"><img alt="EscapeRoom" height="52" src="images/logo/logo.svg" width="134"/></a>
# Проект по автоматизации тестирования для сайта бронирования квестов [EscapeRoom](https://escape-room-neon.vercel.app/)

* Проект Escape Room — ученический проект по фронтенд-разработке от HTML-Академии.
* [Репозиторий с проектом](https://github.com/JulianaAstra/Escape_Room)

## **Содержание:**
____

* <a href="#tools">Технологии и инструменты</a>

* <a href="#cases">Примеры автоматизированных тест-кейсов</a>

* <a href="#jenkins">Сборка в Jenkins</a>

* <a href="#console">Запуск из терминала</a>

* <a href="#allure">Allure отчет</a>

* <a href="#telegram">Уведомление в Telegram при помощи бота</a>

* <a href="#video">Примеры видео выполнения тестов на Selenoid</a>
____
<a id="tools"></a>
## <a name="Технологии и инструменты">**Технологии и инструменты:**</a>

<p align="center">  
<a href="https://www.java.com/"><img src="images/logo/Java.svg" width="50" height="50"  alt="Java"/></a>  
<a href="https://github.com/"><img src="images/logo/Github.svg" width="50" height="50"  alt="Github"/></a>  
<a href="https://junit.org/junit5/"><img src="images/logo/JUnit5.svg" width="50" height="50"  alt="JUnit 5"/></a>  
<a href="https://gradle.org/"><img src="images/logo/Gradle.svg" width="50" height="50"  alt="Gradle"/></a>  
<a href="https://selenide.org/"><img src="images/logo/Selenide.svg" width="50" height="50"  alt="Selenide"/></a>  
<a href="https://aerokube.com/selenoid/"><img src="images/logo/Selenoid.svg" width="50" height="50"  alt="Selenoid"/></a>  
<a href="https://allurereport.org/"><img src="images/logo/Allure.svg" width="50" height="50"  alt="Allure"/></a>  
<a href="https://www.jenkins.io/"><img src="images/logo/Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a>
<a href="https://qameta.io/"><img src="images/logo/Testops.svg" width="50" height="50"  alt="AllureTestops"/></a>
</p>

____
<a id="cases"></a>
## <a name="Примеры автоматизированных тест-кейсов">**Примеры автоматизированных тест-кейсов:**</a>
____
### UI тесты
- ✓ *Регистрация с валидными данными*
- ✓ *Регистрация с невалидным email*
- ✓ *Регистрация с невалидным паролем*
- ✓ *Бронирование квеста без регистрации*
- ✓ *Фильтрация квестов на главной странице*
- ✓ *Отрытие страницы квеста с подробной информацией*

____
### API тесты
- ✓ *Регистрация с валидными данными*
- ✓ *Соответствие данных запросу в теле ответа*
- ✓ *Регистрация с невалидным email*
- ✓ *Бронирование квеста*
- ✓ *Бронирование квеста с несуществующим id квеста*

____
### UI тесты c применением API для подготовки тестовых данных
- ✓ *Бронирование квеста*
- ✓ *Фильтрация квестов на главной странице*
  
____
<a id="jenkins"></a>
## <img alt="Jenkins" height="25" src="images/logo/Jenkins.svg" width="25"/></a><a name="Сборка"></a>Сборка в [Jenkins](https://jenkins.autotests.cloud/job/037-sandraboticelli-escaperoom-12/)</a>
____
<p align="center">  
<a href="https://jenkins.autotests.cloud/job/037-sandraboticelli-ui_api-diploma/"><img src="images/screen/jenkins.png" alt="Jenkins" width="950"/></a>  
</p>


### **Параметры сборки в Jenkins:**

- *browser (браузер, по умолчанию chrome)*
- *version (версия браузера, по умолчанию 128)*
- *windowSize (размер окна браузера, по умолчанию 1920x1080)*
- *baseUrl (адрес тестируемого веб-сайта)*
- *remoteUrl (логин, пароль и адрес удаленного сервера Selenoid)*
- *baseUri (базовый url тестируемого API)*
- *basePath (эндпойнт тестируемого API)*

## **Запуск тестов**

<a id="console"></a>
### Команды для запуска из терминала
___
***Локальный запуск всех тестов:***
```bash  
./gradlew clean test
```

***Локальный запуск тестов API:***
```bash  
./gradlew clean api
```

***Локальный запуск тестов UI:***
```bash  
./gradlew clean ui

```
***Локальный запуск тестов UI + API:***
```bash  
./gradlew clean uiapi
```

***Удалённый запуск через Jenkins:***
```bash  
clean ${TASK}
-Dbrowser=${BROWSER}
-DwindowSize=${WINDOW_SIZE}
-Dversion=${BROWSER_VERSION}
-DbaseUrl=${BASE_URL}
-Dremote=https://${USER}:${PASSWORD}@${REMOTE}/wd/hub
-DbaseUri=${BASE_URI}
-DbasePath=${BASE_PATH}

```
### NB: также добавлены теги для запуска разных наборов тестов по функционалу: 
- *authorization_all*, *authorization_api*, *authorization_ui*
- *booking_all*, *booking_api*, *booking_ui*
- *filtration_all*, *filtration_uiapi*, *filtration_ui*
___
<a id="allure"></a>
## <img alt="Allure" height="25" src="images/logo/Allure.svg" width="25"/></a> <a name="Allure"></a>Allure [отчет](https://jenkins.autotests.cloud/job/037-sandraboticelli-escaperoom-12/allure/)</a>
___

### *Основная страница отчёта*

<p align="center">  
<img title="Allure Overview Dashboard" src="images/screen/allure_report_1.png" width="850">  
</p>  

### *Тест-кейсы*

<p align="center">  
<img title="Allure Tests" src="images/screen/allure_report_4.png" width="850">  
</p>

### *Графики*

  <p align="center">  
<img title="Allure Graphics" src="images/screen/allure_report_2.png" width="850">

<img title="Allure Graphics" src="images/screen/allure_report_3.png" width="850">  
</p>

____
<a id="telegram"></a>
## <img alt="Allure" height="25" src="images/logo/Telegram.svg" width="25"/></a> Уведомление в Telegram при помощи бота
____
<p align="center">  
<img title="Allure Overview Dashboard" src="images/screen/telegram_report.png" width="550">  
</p>

____
<a id="video"></a>
## <img alt="Selenoid" height="25" src="images/logo/Selenoid.svg" width="25"/></a> Пример видео выполнения тестов на Selenoid
____
<p align="center">
<img title="Selenoid Video" src="images/video/video_report.gif" width="550" height="350"  alt="video">   
</p>



