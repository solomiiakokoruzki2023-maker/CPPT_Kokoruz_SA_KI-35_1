import sys

# Функція для коректного зчитування цілого числа
def get_int_input(prompt):
    while True:
        try:
            value = input(prompt)
            return int(value)
        except ValueError:
            print("Помилка: Введіть коректне ціле число.")

# 1. Введення розміру матриці (N) та перевірка, що N > 0
try:
    N = get_int_input("Введіть розмір квадратної матриці (N > 0): ")
    if N <= 0:
        print("Помилка: Розмір матриці має бути більшим за 0.")
        sys.exit(1)
except Exception:
    sys.exit(1)

# 2. Введення символу-заповнювача та перевірка кількості символів
filler_symbol = input("Введіть символ-заповнювач (один символ): ")

if len(filler_symbol) == 0:
    print("Не введено символ-заповнювач.")
    sys.exit(1)
elif len(filler_symbol) > 1:
    print("Забагато символів-заповнювачів (потрібен лише один).")
    sys.exit(1)

empty_symbol = '-'

N_half = N // 2 

# 3. Генерація матриці та зубчатого списку
matrix = []       
ragged_list = []  


for i in range(N):
    matrix.append([])      
    ragged_list.append([]) 

    
    for j in range(N):
        
               
        is_shaded = (i < N_half and j >= N_half) or \
                    (i >= N_half and j < N_half)
        
        if is_shaded:
            element = filler_symbol
            # Додаємо елемент до зубчатого списку
            ragged_list[i].append(element)
        else:
            element = empty_symbol
        
        # Заповнюємо матрицю
        matrix[i].append(element)

# 4. Вивід сформованого масиву
print("\n--- Сформована матриця та виділені елементи ---")
for row in matrix:
    print(" ".join(row))

# 5. Вивід результуючого зубчатого списку
print("\n--- Результуючий зубчатий список (лише заштриховані області) ---")
for row in ragged_list:
    print(row)

# завершення програми
sys.exit(0)