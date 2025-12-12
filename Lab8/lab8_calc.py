import math
import struct
import json

# Константа для визначення кількості значень для обчислення
NUM_POINTS = 10

def calculate_y(x):
    """
    Обчислює значення функції y = ctg(x) / tg(x).
    
    Аргументи:
        x (float): Значення аргументу.
        
    Повертає:
        float: Значення функції y.
        
    Викликає:
        ValueError: Якщо тангенс або котангенс x не визначений або 
                    якщо тангенс x близький до нуля (ділення на нуль).
    """
    try:
        # tg(x) = math.tan(x)
        # ctg(x) = 1 / math.tan(x)
        
        tan_x = math.tan(x)
        
        # Перевірка на ділення на нуль (коли tg(x) = 0)
        if abs(tan_x) < 1e-9:
            raise ValueError(f"Тангенс x = {x} близький до нуля. Ділення на нуль.")
            
        cot_x = 1.0 / tan_x
        
        # Перевірка, чи не визначений ctg(x) / tg(x), тобто коли tg(x) -> нескінченність
        # Це відбувається, коли x = pi/2 + n*pi. У цьому випадку ctg(x) -> 0.
        # Хоча math.tan(pi/2) дасть велике число, теоретично тут може бути проблема.
        # Але оскільки ми ділимо ctg(x) на tg(x), це еквівалентно ctg(x)^2.
        
        # Обчислення ctg(x) / tg(x)
        y = cot_x / tan_x
        
        return y
        
    except ValueError as e:
        # Перевикидаємо помилку для кращої обробки в основній програмі
        raise ValueError(f"Помилка обчислення для x={x}: {e}")
        
    except Exception as e:
        raise Exception(f"Непередбачена помилка обчислення для x={x}: {e}")


def generate_data(start_x, step_x):
    """
    Генерує список пар (x, y) для заданого діапазону.
    
    Аргументи:
        start_x (float): Початкове значення x.
        step_x (float): Крок зміни x.
        
    Повертає:
        list: Список кортежів, де кожен кортеж - (x, y).
    """
    results = []
    x = start_x
    for i in range(NUM_POINTS):
        try:
            y = calculate_y(x)
            results.append((x, y))
        except ValueError as e:
            # Записуємо помилку замість значення, щоб вказати на проблему
            results.append((x, str(e)))
        
        x += step_x
        
    return results


def write_to_text_file(filename, data):
    """
    Записує дані у текстовий файл у форматі: x, y.
    
    Аргументи:
        filename (str): Ім'я файлу для запису.
        data (list): Список кортежів (x, y).
    """
    print(f"\n---> Запис у текстовий файл: {filename}")
    with open(filename, 'w') as f:
        f.write("# x\ty\n")  # Заголовок
        for x, y in data:
            f.write(f"{x}\t{y}\n")
    print("Запис завершено.")


def read_from_text_file(filename):
    """
    Читає дані з текстового файлу.
    
    Аргументи:
        filename (str): Ім'я файлу для читання.
        
    Повертає:
        list: Список рядків, що містять дані.
    """
    print(f"\n---> Читання з текстового файлу: {filename}")
    data = []
    try:
        with open(filename, 'r') as f:
            lines = f.readlines()
            # Пропускаємо заголовок
            for line in lines[1:]:
                # Розділяємо рядок на x та y (або повідомлення про помилку)
                parts = line.strip().split('\t')
                if len(parts) == 2:
                    data.append(parts)
    except FileNotFoundError:
        print(f"Помилка: Файл {filename} не знайдено.")
        return []
        
    print("Читання завершено.")
    return data


def write_to_binary_file(filename, data):
    """
    Записує числові дані у двійковий файл.
    Використовує формат 'dd' (два double-precision floats) для (x, y).
    Не записує рядки з помилками, щоб підтримувати двійковий числовий формат.
    
    Аргументи:
        filename (str): Ім'я файлу для запису.
        data (list): Список кортежів (x, y) або (x, 'Помилка').
    """
    print(f"\n---> Запис у двійковий файл: {filename}")
    # Формат 'dd' - два 8-байтові числа з плаваючою точкою (double)
    format_string = 'dd' 
    
    with open(filename, 'wb') as f:
        for x, y in data:
            # Записуємо тільки числові значення, ігноруючи помилки
            if isinstance(y, (int, float)):
                # struct.pack() перетворює Python-значення у послідовність байтів
                packed_data = struct.pack(format_string, x, y)
                f.write(packed_data)
            else:
                 print(f"Попередження: Пропущено запис (x={x}, y='{y}') у двійковий файл.")
                 
    print("Запис завершено.")


def read_from_binary_file(filename):
    """
    Читає дані з двійкового файлу.
    
    Аргументи:
        filename (str): Ім'я файлу для читання.
        
    Повертає:
        list: Список кортежів (x, y) зчитаних значень.
    """
    print(f"\n---> Читання з двійкового файлу: {filename}")
    data = []
    format_string = 'dd'
    # struct.calcsize() повертає кількість байтів, яку займає формат
    record_size = struct.calcsize(format_string) 
    
    try:
        with open(filename, 'rb') as f:
            while True:
                # Зчитуємо один "запис" (8 байтів для x + 8 байтів для y)
                binary_record = f.read(record_size)
                
                if not binary_record:
                    break # Досягнуто кінця файлу
                    
                # struct.unpack() перетворює послідовність байтів у Python-значення
                x, y = struct.unpack(format_string, binary_record)
                data.append((x, y))
                
    except FileNotFoundError:
        print(f"Помилка: Файл {filename} не знайдено.")
        return []
    except struct.error:
        print("Помилка: Некоректний формат даних у двійковому файлі.")
        return []
        
    print("Читання завершено.")
    return data


if __name__ == '__main__':
    """
    Приклад використання функцій модуля.
    """
    print("===== Запуск модуля Lab8 для демонстрації =====")
    
    # --- 1. Обчислення даних ---
    start_x_value = 0.1
    step_x_value = math.pi / 8
    
    # Генеруємо дані. Зверніть увагу: близько x = pi/2, тангенс і котангенс 
    # будуть мати особливості, що продемонструє обробка помилок.
    print(f"Генерація {NUM_POINTS} точок, починаючи з x={start_x_value}, крок={step_x_value}")
    data_points = generate_data(start_x_value, step_x_value)
    
    print("\nЗгенеровані дані (x, y):")
    for x, y in data_points:
        print(f"x={x:.4f}, y={y}")
        
    # --- 2. Робота з текстовим файлом ---
    text_file = "results_text.txt"
    write_to_text_file(text_file, data_points)
    
    # Читаємо і перевіряємо
    read_text_data = read_from_text_file(text_file)
    print("\nЗчитані дані з текстового файлу:")
    for row in read_text_data:
        print(f"x={row[0]}, y={row[1]}")
        
    # --- 3. Робота з двійковим файлом ---
    binary_file = "results_binary.bin"
    write_to_binary_file(binary_file, data_points)
    
    # Читаємо і перевіряємо
    read_binary_data = read_from_binary_file(binary_file)
    print("\nЗчитані дані з двійкового файлу:")
    for x, y in read_binary_data:
        print(f"x={x:.4f}, y={y:.4f}")

    print("\n===== Демонстрація завершена. =====")

# Кінець файлу lab8_calc.py