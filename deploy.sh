#!/bin/bash

# === Настройки ===
# === пароль ?~nBiGKm
REPO_URL="https://github.com/F1zon/Web-Developer-Dockuments.git"
BRANCH="PILOT"
APP_DIR="/opt/springboot"
FRONTEND_DIR="$APP_DIR/fornt/frontend"
FRONTEND_DEPLOY_DIR="/opt/deploy/react"
JAR_NAME="Web-Developer-Dockuments.jar"

# === Проверка зависимостей ===
echo "🔍 Проверяем наличие необходимых инструментов..."

command -v git >/dev/null 2>&1 || { echo "Git не установлен"; exit 1; }

if ! command -v java &> /dev/null
then
    echo "❌ Java не установлена. Установите Java 17+."
    exit 1
fi

if ! command -v gradle &> /dev/null
then
    echo "❌ Gradle не установлен. Установите Gradle."
    exit 1
fi

if ! command -v npm &> /dev/null
then
    echo "❌ NPM не установлен. Установите Node.js и npm."
    exit 1
fi

# === Очистка старой версии ===
if [ -d "$APP_DIR" ]; then
    echo "🗑 Удаление старого проекта..."
    rm -rf "$APP_DIR"
fi

mkdir -p "$APP_DIR"
cd "$APP_DIR" || { echo "❌ Не удалось перейти в $APP_DIR"; exit 1; }

# === Клонирование репозитория ===
echo "📥 Клонируем репозиторий: $REPO_URL (ветка: $BRANCH)"
git clone --branch "$BRANCH" "$REPO_URL" "$APP_DIR"
if [ $? -ne 0 ]; then
    echo "❌ Ошибка при клонировании репозитория."
    exit 1
fi

# === Сборка Spring Boot приложения ===
echo "🛠 Собираем Spring Boot приложение с помощью Gradle..."
./gradlew clean build
if [ $? -ne 0 ]; then
    echo "❌ Ошибка при сборке Spring Boot приложения."
    exit 1
fi

# === Поиск JAR файла ===
JAR_PATH=$(find ./build/libs -name "*.jar" | head -n 1)
if [ -z "$JAR_PATH" ]; then
    echo "❌ JAR файл не найден после сборки."
    exit 1
fi

# === Остановка текущего Spring Boot приложения (если запущено) ===
echo "🛑 Останавливаем Spring Boot, если запущен..."
PID=$(pgrep -f "$JAR_NAME" 2>/dev/null)
if [ ! -z "$PID" ]; then
    kill "$PID"
    sleep 3
fi

# === Копируем JAR и запускаем ===
cp "$JAR_PATH" "$APP_DIR/$JAR_NAME"

echo "🟢 Запускаем Spring Boot приложение..."
nohup java -jar "$JAR_NAME" > "$APP_DIR/app.log" 2>&1 &

# === Сборка фронтенда ===
if [ -d "$FRONTEND_DIR" ]; then
    echo "🛠 Собираем React-приложение..."
    cd "$FRONTEND_DIR" || { echo "❌ Не удалось перейти в директорию фронтенда"; exit 1; }

    # Установка зависимостей
    npm install

    # Сборка
    npm run build
    if [ $? -ne 0 ]; then
        echo "❌ Ошибка при сборке фронтенда"
        exit 1
    fi

    # === Размещение фронтенда по указанному пути ===
    echo "🚚 Размещаем фронтенд в $FRONTEND_DEPLOY_DIR"
    sudo rm -rf "$FRONTEND_DEPLOY_DIR"
    sudo mkdir -p "$FRONTEND_DEPLOY_DIR"
    sudo cp -r build/* "$FRONTEND_DEPLOY_DIR/"Это

    echo "✅ Фронтенд успешно развёрнут в $FRONTEND_DEPLOY_DIR"
else
    echo "⚠️ Директория фронтенда не найдена: $FRONTEND_DIR"
    exit 1
fi

echo "🎉 Full-stack деплой завершён!"
echo "📄 Логи Spring Boot доступны в $APP_DIR/app.log"
echo "🌐 Фронтенд доступен в $FRONTEND_DEPLOY_DIR"