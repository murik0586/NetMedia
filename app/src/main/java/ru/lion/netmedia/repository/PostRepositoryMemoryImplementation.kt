package ru.lion.netmedia.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.lion.netmedia.dto.Post

//модел
class PostRepositoryMemoryImplementation : PostRepository {
    private var nextId: Long = 0
    private var posts: List<Post> = mutableListOf(
        Post(
            id = ++nextId,
            "Уроки разработки - менторство! Присоединяйтесь к нам, покорим Андроид!",
            "25 января в 21:46",
            "Привет, разработчики! 🌟 Android-разработка — это не просто код, это целый мир возможностей. Каждый день мы создаём приложения, которые упрощают жизнь миллионов пользователей по всему миру. Хотите стать частью этой динамичной сферы? Тогда пора изучить Jetpack Compose, разобраться в MVVM и освоить Kotlin! Начните сегодня — завтра ваши приложения могут изменить мир! https://github.com/netology-code/and2-homeworks/tree/master/03_constraint_layout",
            false,
            10,
            1100,
            10
        ),
        Post(
            ++nextId,
            "Как стать Android-разработчиком в 2024 году?",
            "10 февраля в 14:20",
            "Хотите войти в мир Android-разработки, но не знаете, с чего начать? 🚀 Освойте основы Kotlin, изучите Jetpack Compose и разберитесь с жизненным циклом Activity. Не забывайте про Clean Architecture! Начните свой путь сегодня! 💪",
            true,
            25,
            2000,
            18
        ),
        Post(
            ++nextId,
            "Лучшие библиотеки для Android-разработки",
            "8 февраля в 18:55",
            "Какие библиотеки реально упрощают жизнь Android-разработчику? Вот несколько must-have: Retrofit (сетевые запросы), Room (база данных), Glide (работа с изображениями) и Koin (DI). Пользуетесь ими? Или есть любимые альтернативы? 🤔",
            true,
            40,
            3200,
            29
        ),
        Post(
            ++nextId,
            "Как написать своё первое Android-приложение?",
            "5 февраля в 12:10",
            "Простой туториал: 1️⃣ Установите Android Studio, 2️⃣ Создайте новый проект, 3️⃣ Разметьте UI с Jetpack Compose, 4️⃣ Добавьте простую бизнес-логику, 5️⃣ Запустите на эмуляторе или устройстве. Всё! Готовы пробовать? 😎",
            false,
            15,
            800,
            7
        ),
        Post(
            ++nextId,
            "Почему стоит изучать Kotlin?",
            "3 февраля в 09:30",
            "Kotlin — это мощный язык, который делает код Android-приложений лаконичным, безопасным и удобным. Поддерживается Google, совместим с Java и активно развивается. Кто уже перешел на 100% Kotlin? 📢",
            true,
            30,
            2500,
            21
        ),
        Post(
            ++nextId,
            "Jetpack Compose: будущее UI-разработки",
            "2 февраля в 15:45",
            "Jetpack Compose — это декларативный UI-фреймворк от Google, который упрощает создание интерфейсов. Менее кода, больше гибкости! Кто уже использует? Делитесь опытом! 🎨",
            true,
            20,
            1400,
            12
        ),
        Post(
            ++nextId,
            "Зачем нужен MVVM?",
            "1 февраля в 11:15",
            "MVVM (Model-View-ViewModel) — это одна из самых популярных архитектур Android-разработки. Почему? Отделение логики от UI, упрощённое тестирование и поддержка LiveData/StateFlow. Кто использует MVVM в своих проектах? 🏗️",
            true,
            35,
            2700,
            19
        ),
        Post(
            ++nextId,
            "Разбираем Coroutines в Kotlin",
            "30 января в 17:50",
            "Асинхронность в Android — это важно. Coroutines позволяют писать асинхронный код просто и без callback-ада. Запускаем корутину в ViewModelScope и обновляем UI с Flow! 🔥",
            true,
            28,
            2200,
            16
        ),
        Post(
            ++nextId,
            "Как правильно работать с API?",
            "28 января в 13:10",
            "Для работы с API в Android часто используют Retrofit + Gson/Moshi. Настраиваем OkHttpClient, добавляем интерсепторы и обрабатываем ошибки. Какие best practices знаете вы? 🌍",
            false,
            18,
            1000,
            9
        ),
        Post(
            ++nextId,
            "Топ ошибок начинающих Android-разработчиков",
            "26 января в 20:30",
            "❌ Писать весь код в Activity, ❌ Не использовать архитектурные паттерны, ❌ Игнорировать обработку ошибок, ❌ Не следить за памятью. Узнали себя? Время исправлять! 🚀",
            false,
            50,
            4000,
            35
        )
    )

    private val data = MutableLiveData(posts)
    override fun getAll(): LiveData<List<Post>> = data

    override fun like(id: Long) {
        posts = posts.map {
            if (it.id == id) {
                it.copy(
                    likedByMe = !it.likedByMe,
                    likes = if (it.likedByMe) it.likes - 1 else it.likes + 1
                )
            } else it
        }
        data.value = posts
    }

    override fun share(id: Long) {
        posts = posts.map {
            if (it.id == id) it.copy(shared = it.shared + 1) else it
        }
        data.value = posts

    }

    override fun view(id: Long) {
        posts = posts.map {
            if (it.id == id) it.copy(views = it.views + 1) else it
        }
        data.value = posts
    }

    override fun remove(id: Long) {
        posts = posts.filter { it.id != id }
        Log.i("Delete","Удалили")
        data.value = posts
    }

    override fun save(post: Post) {
        posts = if (post.id == 0L) {
            Log.i("ID", "ID равен нулю")
            listOf(post.copy(id = nextId++)) + posts
        } else {
            posts.map {
                Log.i("ID","ID равен ${it.id}")
                if (it.id != post.id) it else it.copy(content = post.content)
            }
        }
        data.value = posts
    }

}