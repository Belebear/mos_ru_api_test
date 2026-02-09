
# Автоматизированные тесты API для сервиса [Mos.ru](https://www.mos.ru)

## <a name="Технологии и инструменты">**Технологии и инструменты:**</a>

<p align="center">  
<a href="https://www.jetbrains.com/idea/"><img src="files/icons/intellij.svg" width="40" height="40"  alt="IDEA"/></a>  
<a href="https://www.java.com/"><img src="files/icons/java.svg" width="40" height="40"  alt="Java"/></a>
<a href="https://rest-assured.io/"><img src="files/icons/Rest-Assured.svg" width="40" height="40"  alt="Rest Assured"/></a>
<a href="https://github.com/"><img src="files/icons/github.svg" width="40" height="40"  alt="Github"/></a>  
<a href="https://junit.org/junit5/"><img src="files/icons/junit.svg" width="40" height="40"  alt="JUnit 5"/></a>  
<a href="https://gradle.org/"><img src="files/icons/gradle.svg" width="40" height="40"  alt="Gradle"/></a>  
<a href="ht[images](images)tps://github.com/allure-framework/allure2"><img src="files/icons/Allure.svg" width="40" height="40"  alt="Allure"/></a> 
<a href="https://qameta.io/"><img src="files/icons/Allure_TestOps.svg" width="40" height="40"  alt="Allure TestOps"/></a>   
<a href="https://www.jenkins.io/"><img src="files/icons/jenkins.svg" width="40" height="40"  alt="Jenkins"/></a>  
</p>

## Примеры автоматизированных тест-кейсов:
- Тестирование Api готовности карты москвича
- Тестирование Api статуса карты москвича
- Тестирование Api обратной связи

---

## Запуск тестов через терминал:

#### Для локального запуска
- Запуск API тестов на проверку готовности карты москвича
``` bash 
gradle clean ready_test
```
- Запуск API тестов на проверку статуса карты москвича
``` bash 
gradle clean status_test
```
- Запуск API тестов на проверку обратной связи
``` bash 
gradle clean feedback_test
```
- Запуск всех API тестов
``` bash 
gradle clean all_api_test // Запуск всех API тестов
```

#### Для запуска из Jenkins
``` bash 
clean ${TESTS}
```

## Сборка в [Jenkins](https://jenkins.autotests.cloud/job/38-belebear-rigla_project_lesson12/) <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/jenkins/jenkins-original.svg" widht="40" height="40" />

### Возможные параметры сборки в Jenkins:
<p align="center">  
<img src="files/screen/jenkins1.png"/>
</p>

- TESTS - параметр отвечающий за запуск по тестовой модели
- COMMENT - параметр, отвечающий за сообщение, которое будет отправлено в телеграм с отчетом

## Отчетность в [Allure](https://jenkins.autotests.cloud/job/38-belebear-rigla_project_lesson12/allure/) <img src="https://github.com/RomaQA/RomaQA/blob/main/media/icons/allure-Report-logo.svg" widht="40" height="40" />

### Главная страница Allure Reports

<p align="center">  
<img src="files/screen/allure1.png"/>
</p>

### Страница со всеми тест-кейсами и шагами

<p align="center">  
<img src="files/screen/allure2.png"/>
</p>

### По итогу прохождения тестов формируются:
- Лог запроса
- Лог ответа или ошибки
## Отчетность в Telegram <img src="files/icons/TG.svg" widht="40" height="40" />

### Краткий отчет после прохождения тестов от чат-бота

<p align="center">  
<img src="files/screen/tg1.png"/>
</p>
