# Компиляция и запуск UIAutomator теста

1. Для генерации файлов конфигурации сборки теста необходимо выполнить следующую команду в терминале:

 ```
$ <android-sdk>/tools/android create uitest-project -n <name> -t  <target-id> -p <path>
```

 Где `<name>` — имя проекта, который создавался для теста **UIAutomator** (в нашем случае: SendMessage), `<target-id>` — выбор устройства и Android API Level (можно получить список установленных устройств командой: <android-sdk>/tools/android list targets) и `<path>` — путь к директории с проектом.

2. Необходимо экспортировать переменную окружения ANDROID_HOME:

 * Windows:
```
set ANDROID_HOME=<path_to_your_sdk>
```

 * Unix:
```
export ANDROID_HOME=<path_to_your_sdk>
```

3. Заходим в директорию с проектом, в которой лежит сгенерированный на шаге 1 файл **build.xml** и выполняем команду:

 ```
$ ant build
```

4. Копируем собранный JAR файл на устройство с помощью команды `adb push`:

 ```
$ adb push <path_to_output_jar> /data/local/tmp/
```

 Для нашего случая:

 ```
$ adb push <project_dir>/bin/SendMessage.jar /data/local/tmp/
```

5. Запускаем скрипт:

 ```
$ adb shell UIAutomator runtest /data/local/tmp/SendMessage.jar –c blog.send.message.SendMessage
```
