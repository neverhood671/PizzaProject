function createRequest()
{
    var request = false;

    if (window.XMLHttpRequest)
    {
        //Gecko-совместимые браузеры, Safari, Konqueror
        request = new XMLHttpRequest();
    }
    else if (window.ActiveXObject)
    {
        //Internet explorer
        try
        {
            request = new ActiveXObject("Microsoft.XMLHTTP");
        }
        catch (CatchException)
        {
            request = new ActiveXObject("Msxml2.XMLHTTP");
        }
    }

    if (!request)
    {
        alert("Невозможно создать XMLHttpRequest");
    }

    return request;
}

/*
 Функция посылки запроса к файлу на сервере
 r_method  - тип запроса: GET или POST
 r_path    - путь к файлу
 r_args    - аргументы вида a=1&b=2&c=3...
 r_handler - функция-обработчик ответа от сервера
 */
function sendRequest(r_method, r_path, r_args, r_handler)
{
    //Создаём запрос
    var request = createRequest();

    //Проверяем существование запроса еще раз
    if (!request) {
        return;
    }

    //Назначаем пользовательский обработчик
    request.onreadystatechange = function() {
        //Если обмен данными завершен
        if (request.readyState == 4) {
            //Передаем управление обработчику пользователя
            r_handler(request);
        }
    }

    //Проверяем, если требуется сделать GET-запрос
    if (r_method.toLowerCase() == "get" && r_args.length > 0)
        r_path += "?" + r_args;

    //Инициализируем соединение
    request.open(r_method, r_path, true);

    if (r_method.toLowerCase() == "post") {
        //Если это POST-запрос

        //Устанавливаем заголовок
        request.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=utf-8");
        //Посылаем запрос
        request.send(r_args);
    } else {
        //Если это GET-запрос

        //Посылаем нуль-запрос
        request.send();
    }
}
